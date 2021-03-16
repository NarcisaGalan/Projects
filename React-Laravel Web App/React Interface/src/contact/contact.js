import React, { useState } from 'react';
import { Container, Row, Col, Jumbotron, Form, Label, Input, FormGroup, Button } from 'reactstrap';
import './contact-form.css';
import { withNamespaces } from 'react-i18next';
import {  withRouter } from 'react-router-dom';

const backgroundStyle = {
  backgroundPosition: 'center',
  backgroundSize: 'cover',
  backgroundRepeat: 'no-repeat',
  width: "100%",
  height: "650px",
  margin: 0,
};

var emailField = '';


class Contact extends React.Component {

  constructor(props) {
    super(props);
    this.state = { emailField: '' };
  }

  handleChange = event => {
    this.setState({ emailField: event.target.value });
    console.log(event.target.value);
  };

  handleClick = event => {
    //console.log(this.state.emailField);
    const url = "http://127.0.0.1:5000/contact/";
    const response = url + this.state.emailField;
    window.open(response, "Email sent")
  };

  render() {
    const {t} = this.props;
    return (
      
      <div class="wrapper">

        <div class="contact-form">

        </div>


        <Container fluid>
          <Row xs="2">
            <Col>
              <h1 id="titleC">Contact</h1>
              <Jumbotron fluid style={backgroundStyle} id="backC">
                <Container fluid >
                  <h2 id="address">{t("contact.address")}</h2>
                  <h3 id="paragraphC">Facultatea de Automatica si Calculatoare, Cluj-Napoca, Cluj</h3>
                  <h4 id="address">{t("contact.number")}</h4>
                  <h5 id="paragraphC">+0040703031510</h5>
                  <h4 id="address">{t("contact.email")}</h4>
                  <h5 id="paragraphC">g.narcisamaria@gmail.com</h5>

                </Container>
              </Jumbotron>
            </Col>
            <Col>
              <h1 id="h1">{t("contact.send")}</h1>
              <Form>
                <FormGroup row>
                  <Label for="email" sm={2}>Email</Label>
                  <Col sm={10}>
                    <Input type="email" name="email" id="email" placeholder="example@123.com" value={this.state.emailField}
                      onChange={this.handleChange} />
                  </Col>
                </FormGroup>

                <FormGroup row>
                  <Label for="sendText" sm={2}>Message</Label>
                  <Col sm={10}>
                    <Input type="textarea" name="text" id="sendText" placeholder={t("contact.message")} />
                  </Col>
                </FormGroup>

                <FormGroup check row>
                  <Col sm={{ size: 10, offset: 2 }}>
                    <Button value={this.state.emailField}
                      onClick={this.handleClick}>{t("contact.button1")} </Button>
                  </Col>
                </FormGroup>
              </Form>

            </Col>

          </Row>
        </Container>
      </div>
    )
  };
}

export default withRouter(withNamespaces()(Contact));