import React, { Component } from 'react'

import axios from 'axios';
import {  withRouter } from 'react-router-dom';

class Register extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: '',
            password: '',
            email: '',
            c_password:'',
            photourl: '',
            preferences: '',
            open: false,
            alert_message: ''
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
            name: this.state.name,
            email: this.state.email,
            password: this.state.password,
            c_password:this.state.c_password ,
            photourl: this.state.photourl,
            preferences: this.state.preferences
        }

        const url = 'http://localhost:8000/api/register/';

		axios.post(url, newUser, {
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(newUser),

		})
			.then(res => {
				console.log(res)
			});

		

		this.setState({
			alert_message: "success"
		});
	
        
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
                                <label htmlFor="name">Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    name="name"
                                    placeholder="Enter your name"
                                    value={this.state.name}
                                    onChange={this.onChange}
                                />
                            </div>
                           
                            <div className="form-group">
                                <label htmlFor="email">Email </label>
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
                                <label htmlFor="c_password">Password</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    name="c_password"
                                    placeholder="Enter the password again"
                                    value={this.state.c_password}
                                    onChange={this.onChange}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="photourl">Photo URL</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    name="photourl"
                                    placeholder="PhotoURL"
                                    value={this.state.photourl}
                                    onChange={this.onChange}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="preferences">Field of interest</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    name="preferences"
                                    placeholder="Fiels of interest"
                                    value={this.state.preferences}
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
