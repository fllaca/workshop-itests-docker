from flask import Flask, jsonify, request
from flaskext.mysql import MySQL
import os, json
import sys
from logging.config import dictConfig

dictConfig({
    'version': 1,
    'formatters': {'default': {
        'format': '[%(asctime)s] %(levelname)s in %(module)s: %(message)s',
    }},
    'handlers': {'wsgi': {
        'class': 'logging.StreamHandler',
        'stream': 'ext://sys.stdout',
        'formatter': 'default'
    }},
    'root': {
        'level': 'INFO',
        'handlers': ['wsgi']
    }
})

app = Flask(__name__)
mysql = MySQL()

# MySQL configurations
app.config['MYSQL_DATABASE_USER'] = os.getenv("MYSQL_DATABASE_USER")
app.config['MYSQL_DATABASE_PASSWORD'] = os.getenv("MYSQL_DATABASE_PASSWORD")
app.config['MYSQL_DATABASE_DB'] = os.getenv("MYSQL_DATABASE_DB")
app.config['MYSQL_DATABASE_HOST'] = os.getenv("MYSQL_DATABASE_HOST")

mysql.init_app(app)

@app.route('/todos')
def list_todos():
    cur = mysql.connect().cursor()
    cur.execute("select * from {}.todos".format(app.config["MYSQL_DATABASE_DB"]))
    r = [dict((cur.description[i][0], value)
              for i, value in enumerate(row)) for row in cur.fetchall()]
    return jsonify(r)

@app.route('/todos',methods=['POST'])
def create_todo():
    data = request.data
    dataDict = json.loads(data)
    conn = mysql.connect()
    cur = conn.cursor()
    cur.execute("insert into {}.todos (content) values (\"{}\")".format(app.config["MYSQL_DATABASE_DB"], dataDict["content"]))
    conn.commit()
    r = [dict((cur.description[i][0], value)
              for i, value in enumerate(row)) for row in cur.fetchall()]
    return jsonify(r)

@app.route('/todos/<id>',methods=['DELETE'])
def delete_todo(id):
    conn = mysql.connect()
    cur = conn.cursor()
    cur.execute("delete from {}.todos where id={}".format(app.config["MYSQL_DATABASE_DB"], id))
    # TODO: purposely introduced bug:
    # conn.commit()
    r = [dict((cur.description[i][0], value)
              for i, value in enumerate(row)) for row in cur.fetchall()]
    return jsonify(r)

if __name__ == '__main__':
    app.run(host='0.0.0.0')
