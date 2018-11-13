FROM python

RUN mkdir -p /code 

COPY requirements.txt /code/requirements.txt
WORKDIR /code

RUN pip install -r requirements.txt

COPY . /code
