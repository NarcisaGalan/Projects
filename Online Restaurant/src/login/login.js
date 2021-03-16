import React from 'react';
import { login } from './repoLogin';
import { withRouter } from 'react-router-dom';

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

  onSubmit(e) {
    e.preventDefault()

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
      <div className="container">
        <hr />
        <div className="col-sm-8 col-sm-offset-2">
          <div className="panel panel-primary">
            <div className="panel-heading">
              <h3>Log in </h3>
            </div>
            <div className="panel-body">
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <label>Email:</label>
                  <input type="text" className="form-control" name="email" value={this.state.email}
                    onChange={this.onChange} />
                </div>
                <div className="form-group">
                  <label>Password:</label>
                  <input type="password" className="form-control" name="password" value={this.state.password} onChange={this.onChange} />
                </div>
                <button type="submit" className="btn-primary btn-default">Submit</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default withRouter(Login)