from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities

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


def build_remote_driver():
    # Disable CORSS
    options = webdriver.ChromeOptions()
    options.add_argument("test-type")
    options.add_argument("--disable-web-security")
    options.add_argument("--allow-running-insecure-content")

    return webdriver.Remote("http://chrome:4444/wd/hub",
                            DesiredCapabilities.CHROME,
                            options=options)


def test():
    driver = build_remote_driver()
    page = MainPageObject(driver,"http://frontend:4200")

    # driver = webdriver.Chrome("/opt/chromedriver")
    # page = MainPageObject(driver,"http://localhost:4200")

    try:
        page.go()

        initial_todo_count = len(page.get_todo_list())

        page.type_todo("test-todo-item")
        page.click_add()

        # Verify there's one more element
        assert len(page.get_todo_list()) == initial_todo_count + 1 
        # Verify there's one todo with the content given in input
        found = [item for item in page.get_todo_list() if "test-todo-item" in item.text]
        assert len(found) == 1
        print("PASSED")
    except e:
        print("FAILED")
    finally:
        driver.close()


if __name__ == "__main__":
    test()
