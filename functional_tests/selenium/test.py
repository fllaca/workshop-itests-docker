from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities

from assertpy import assert_that

class MainPageObject(object):
    def __init__(self, driver, url):
        self._driver = driver
        self._url = url

    def go(self):
        self._driver.get(self._url)

    def type_todo(self, content):
        elem = self._driver.find_element_by_name("todo")
        elem.clear()
        elem.send_keys(content)

    def click_add(self):
        elem = self._driver.find_element_by_id("add-todo-button")
        elem.click()

    def get_todo_list(self):
        return self._driver.find_elements_by_css_selector("li")

    def get_item_by_text(self, text):
        return [item for item in self.get_todo_list() if item.find_elements_by_css_selector("span")[0].text.strip() == text]

    def delete_item_by_text(self, text):
        found = self.get_item_by_text(text)
        if found:
            found[0].find_elements_by_css_selector("i")[0].click()


def build_remote_driver():
    # Disable CORSS
    options = webdriver.ChromeOptions()
    options.add_argument("test-type")
    options.add_argument("--disable-web-security")
    options.add_argument("--allow-running-insecure-content")

    return webdriver.Remote("http://chrome:4444/wd/hub",
                            DesiredCapabilities.CHROME,
                            options=options)


def test_add():
    driver = build_remote_driver()
    page = MainPageObject(driver,"http://reverse-proxy")

    try:
        page.go()

        initial_todo_count = len(page.get_todo_list())

        page.type_todo("test-todo-item")
        page.click_add()

        # Verify there's one more element
        assert_that(len(page.get_todo_list())).is_equal_to(initial_todo_count + 1)
        # Verify there's one todo with the content given in input
        found = page.get_item_by_text("test-todo-item")
        assert_that(len(found)).is_equal_to(1)
    finally:
        driver.close()

def test_remove():
    driver = build_remote_driver()
    page = MainPageObject(driver,"http://reverse-proxy")

    try:
        page.go()

        initial_todo_count = len(page.get_todo_list())

        page.type_todo("test-todo-item-2")
        page.click_add()

        # Verify there's one more element
        assert_that(len(page.get_todo_list())).is_equal_to(initial_todo_count + 1)

        # Verify there's one todo with the content given in input
        found = page.get_item_by_text("test-todo-item-2")
        assert_that(len(found)).is_equal_to(1)

        # Remove an item by test
        page.delete_item_by_text("test-todo-item-2")
        driver.implicitly_wait(2) # seconds, giving some time to the backend to work

        # Verify the size of the todo list is the original after remove
        assert_that(len(page.get_todo_list())).is_equal_to(initial_todo_count)

    finally:
        driver.close()

