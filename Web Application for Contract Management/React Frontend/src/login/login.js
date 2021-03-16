import React from 'react';
import { login } from './repoLogin';
import { withRouter } from 'react-router-dom';
import { Button, Row, Col, Container } from 'reactstrap';
import './login.css';
import { AvForm, AvField } from 'availity-reactstrap-validation';


class Login extends React.Component {

  constructor() {
    super()
    this.state = {
      email: '',
      password: '',
      errors: {}
    }
    this.onChange = this.onChange.bind(this)
    this.onSubmit = this.onSubmit.bind(this)
  }


  onChange(e) {
    this.setState({ [e.target.name]: e.target.value })
  }
  onSubmit(e){
    e.stopPropagation();
    e.preventDefault();
    const user = {
      email: this.state.email,
      password: this.state.password
    }

    login(user).then(res => {

      this.props.history.push('/')
    })

  }

  render() {
    return (

      <Container fluid id="backgroundLogin" style={{marginBottom:'100px'}}>

        <Row>
          <Col id="login" sm="12" md={{ size: 4, offset: 4 }}>
            <AvForm onSubmit={this.onSubmit}>

              <AvField name="email" label="E-mail" placeholder="exemplu@utcn.com" type="email" onChange={this.onChange} required />

              <AvField type="password" name="password" id="examplePassword" label="Parola" placeholder="introduceti parola" value={this.state.password}
                onChange={this.onChange} />

              <Button outline color="primary" type="submit" >Conectare</Button>

            </AvForm>
          </Col>

        </Row>

      </Container>

    );
  }
}
export default withRouter(Login);