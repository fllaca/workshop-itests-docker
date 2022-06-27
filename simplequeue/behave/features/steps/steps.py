from behave import given
from behave import when
from behave import then

import requests
import json
import os

BASE_URL=os.getenv('BASE_URL', "http://localhost:8080")

@given(u'a topic with name "{topicName}" is created')
def step_impl(context, topicName):
    response = requests.post(url=BASE_URL + "/topics", json={
        "name": topicName
    })

@when(u'topics are listed')
def step_impl(context):
    response = requests.get(url=BASE_URL + "/topics")
    context.topics = response.json()

@then(u'a topic with name "{topicName}" exists')
def step_impl(context, topicName):
    assert(len([topic for topic in context.topics if topic["name"] == topicName ]) > 0)

