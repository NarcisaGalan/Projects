import React, { Component } from 'react'
import { Container, Row, Col } from 'reactstrap';
import './profil.css'

import axios from 'axios';
import {  withRouter } from 'react-router-dom';

class Profil extends Component {
    constructor() {
        super()
     
        this.state = {
            name: '',
            password: '*****',
            email: '',
            photourl: '',
            preferences: '',
            alert_message: '',

            
        }

        this.onChange = this.onChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
    }

    componentWillMount() {

        
        var userID = parseInt(localStorage.getItem("id"));
        console.log(userID);
        var url = `http://localhost:8000/api/users/${userID}`;

        fetch(url)
        .then(res => res.json())
        .then(
            (result) => {
                this.setState({
                    name: result.data.name,
                    email: result.data.email,
                    photourl: result.data.photourl,
                    preferences: result.data.preferences
                });
                console.log(result)
            },
            (error) => {
                this.setState({ error });
            }
        ) 

        
        
    }

    onChange (e) {
        this.setState({ [e.target.name]: e.target.value })
    }

    onSubmit (e) {
        e.preventDefault()

        const updateUser = {
            name: this.state.name,
            email: this.state.email,
            password: this.state.password,
            photourl: this.state.photourl,
            preferences: this.state.preferences
        }
        var userID = parseInt(localStorage.getItem("id"));
        var url = `http://localhost:8000/api/users/${userID}`;

		axios.put(url, updateUser, {
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(updateUser),

		})
			.then(res => {
				console.log(res)
			});

		

        console.log("NEW PASS: "+ this.state.password);
		this.setState({
			alert_message: "success"
		});
	
        
    }

    render () {
        return (

            <Row>
            <Col xs="6">
            <div className="container">
                <div className="row">
                    <div className="col-md-6 mt-5 mx-auto">
                        <form noValidate onSubmit={this.onSubmit}>
                            <h1 className="h3 mb-3 font-weight-normal">
                                My Profile
                            </h1>
                            
                            <div className="form-group">
                                <label htmlFor="name">Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    name="name"
                                    placeholder= {this.state.name}
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
                                    value={this.state.password}
                                    onChange={this.onChange}
                                />
                            </div>


                            <div className="form-group">
                                <label htmlFor="photourl">Photo URL</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    name="photourl"
                                    
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
                                    
                                    value={this.state.preferences}
                                    onChange={this.onChange}
                                />
                            </div>


                            <button
                                type="submit"
                                className="btn btn-lg btn-primary btn-block"
                            >
                                Update
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            
            </Col>
            <Col sm = "4">
            <img id="img"
                src={this.state.photourl}
                alt="new"
            />
            
            </Col>
          </Row>
           
        )
    }
}

export default withRouter(Profil)
