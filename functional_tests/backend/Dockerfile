FROM python:3.6-alpine

ENV APP_FOLDER /usr/app/todos

RUN apk add --no-cache build-base python3-dev libffi-dev openssl openssl-dev

RUN mkdir -p ${APP_FOLDER}
WORKDIR ${APP_FOLDER}

# Install dependencies
COPY requirements.txt ${APP_FOLDER}/requirements.txt
RUN pip install -r requirements.txt

# Add code
COPY . ${APP_FOLDER}

EXPOSE 5000
CMD python ${APP_FOLDER}/app.py