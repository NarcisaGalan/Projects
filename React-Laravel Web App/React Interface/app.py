from flask import Flask
from flask_mail import Mail, Message

app = Flask(__name__)
mail= Mail(app)

app.config['MAIL_SERVER']='smtp.gmail.com'
app.config['MAIL_PORT'] = 465
app.config['MAIL_USERNAME'] = 'narcisaDAW@gmail.com'
app.config['MAIL_PASSWORD'] = 'Daw11111!'
app.config['MAIL_USE_TLS'] = False
app.config['MAIL_USE_SSL'] = True
mail = Mail(app)

@app.route("/contact/<email>")
def api_sendMail(email):
   msg = Message('Maria Galan', sender = 'narcisaDAW@gmail.com', recipients = [email])
   msg.body = "Hi there!Your email was sent! "
   mail.send(msg)
   return "Sent to " + email

if __name__ == '__main__':
   app.run(debug = True)