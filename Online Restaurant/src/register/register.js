import React, { Component } from 'react'
import { register } from './repoRegister'
import { withRouter } from 'react-router-dom';

class Register extends Component {
    constructor(props) {
        super(props)
        this.state = {
            username: '',
            password: '',
            firstname: '',
            lastname: '',
            email: '',
            phone: '',
            address: '',
            open: false
        }

        this.onChange = this.onChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
    }

    
    onChange (e) {
        this.setState({ [e.target.name]: e.target.value })
    }
    onSubmit (e) {
        e.preventDefault()

        const newUser = {
            username: this.state.username,
            password: this.state.password,
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            email: this.state.email,
            phone: this.state.phone,
            address: this.state.address
        }

        register(newUser).then(res => {
            console.log('User successfully added')
        })

        this.props.history.push('/login')
    }

    render () {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-6 mt-5 mx-auto">
                        <form noValidate onSubmit={this.onSubmit}>
                            <h1 className="h3 mb-3 font-weight-normal">
                                Register
                            </h1>
                            
                            <div className="form-group">
                                <label htmlFor="username">Username</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    name="username"
                                    placeholder="Enter your user name"
                                    value={this.state.username}
                                    onChange={this.onChange}
                                />
                            </div>
                           

                           
                            <div className="form-group">
                                <label htmlFor="password">Password</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    name="password"
                                    placeholder="Password"
                                    value={this.state.password}
                                    onChange={this.onChange}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="firstname">First name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    name="firstname"
                                    placeholder="Enter your first name"
                                    value={this.state.firstname}
                                    onChange={this.onChange}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="lastname">Last name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    name="lastname"
                                    placeholder="Enter your lastname name"
                                    value={this.state.lastname}
                                    onChange={this.onChange}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="email">Email address</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    name="email"
                                    placeholder="Enter email"
                                    value={this.state.email}
                                    onChange={this.onChange}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="address">Address</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    name="address"
                                    placeholder="Enter your address"
                                    value={this.state.address}
                                    onChange={this.onChange}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="phone">Phone</label>
                                <input
                                    type="phone"
                                    className="form-control"
                                    name="phone"
                                    placeholder="Enter your phone"
                                    value={this.state.phone}
                                    onChange={this.onChange}
                                />
                            </div>
                            <button
                                type="submit"
                                className="btn btn-lg btn-primary btn-block"
                            >
                                Register!
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

export default withRouter(Register)
