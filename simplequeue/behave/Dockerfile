FROM python:3.9.7-alpine3.14

WORKDIR /workspace

COPY requirements.txt ./

RUN pip install -r requirements.txt

COPY features ./
