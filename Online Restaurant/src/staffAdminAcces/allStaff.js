import React from 'react';
import { Button } from 'react-bootstrap';
import SuccessAlert from '../reservation/ModalForRequest/SuccessAlert';
import ErrorAlert from '../reservation/ModalForRequest/ErrorAlert';
import Modal from 'react-modal';

import axios from 'axios';

import '../reservation/styles/styleTable.css';
import '../principal/nav.css'





class AllStaff extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            staffs: [],
            id: '',
            username: '',
            password: '',
            firstname: '',
            lastname: '',
            email: '',
            phone: '',
            address: '',
            salary: '',
            staffoccupationId: '',
            response: {},
            alert_message: ''
        }
        this.onChange = this.onChange.bind(this);
        this.addStaff = this.addStaff.bind(this);
    }



    componentDidMount() {/*GET ALL staff */
        const apiUrl = 'http://localhost:52004/api/staffs';

        fetch(apiUrl)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        staffs: result
                    });
                },
                (error) => {
                    this.setState({ error });
                }
            )

        Modal.setAppElement('body');

    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value })
    }

    toggleModal = () => {
        this.setState({
            isActive: !this.state.isActive
        })
    }


    openModal = () => {

        const newStaff = {
            id: this.state.id,
            username: this.state.username,
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            email: this.state.email,
            phone: this.state.phone,
            address: this.state.address,
            salary: this.state.salary,
            staffoccupationId: this.state.staffoccupationId
        }

        this.toggleModal();

    }


    addStaff = (e) => {

        e.preventDefault();

        const newStaff = {

            username: this.state.username,
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            email: this.state.email,
            phone: this.state.phone,
            address: this.state.address,
            salary: parseInt(this.state.salary),
            staffoccupationId: parseInt(this.state.staffoccupationId),
        }


        const url = 'http://localhost:52004/api/staffs';

        axios.post(url, newStaff, {
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newStaff),

        })
            .then(res => {
                console.log(res)
            });



        this.setState({
            alert_message: "success"
        });

        window.location.reload(false);
    }


    deleteStaff(id) {/*DELETE staff WITH ID*/
        const { staffs } = this.state;

        const apiUrl = 'http://localhost:52004/api/staffs/'.concat(id);
        const formData = new FormData();
        formData.append('id', id);

        const options = {
            method: 'DELETE',
            body: formData
        }

        fetch(apiUrl, options)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        response: result,
                        staffs: staffs.filter(staff => staff.id !== id)
                    });
                },
            )
            .then(res => {
                this.setState({
                    alert_message: "success"
                });
            })
            .catch(error => {
                this.setState({
                    alert_message: "error"
                });
            })
        window.location.reload(false);
    }

   

    render() {
        
        const project = (staffoccupationId) => {
            switch (staffoccupationId) {
    
                case 2: return "Chelner";
                case 1: return "Admin";
                case 3: return "Bucatar";
    
                default: return "Tehnic";
            }
        }

        const { error, staffs } = this.state;

        if (error) {
            return (
                <div>Error: {error.message}</div>
            )
        } else {

            return (
                <div>
                    <hr />
                    {this.state.alert_message == "success" ? <SuccessAlert /> : null}
                    {this.state.alert_message == "error" ? <ErrorAlert /> : null}
                    <h1 id='title'>Staffs List</h1>
                    {/* {this.state.response.message && <Alert variant="info">{this.state.response.message}</Alert>} */}
                    <table id='students'>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Username</th>
                                <th>FirstName</th>
                                <th>LastName</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th>Salary</th>
                                <th>Role</th>
                                <th>Option</th>
                            </tr>
                        </thead>
                        <tbody>
                            {staffs.map(staff => (
                                <tr key={staff.id}>
                                    <td>{staff.id}</td>
                                    <td>{staff.username}</td>
                                    <td>{staff.firstname}</td>
                                    <td>{staff.lastname}</td>
                                    <td>{staff.email}</td>
                                    <td>{staff.phone}</td>
                                    <td>{staff.address}</td>
                                    <td>{staff.salary}</td>
                                    <td>{project(staff.staffoccupationId)}</td>
                                    <td>
                                        <Button variant="danger" onClick={() => { this.deleteStaff(staff.id) }}>Delete</Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    <Button onClick={this.openModal} className="btn btn-primary" id="fixedbutton" > Add Staff</Button>
                    <Modal

                        size="xl"
                        aria-labelledby="example-modal-sizes-title-sm" isOpen={this.state.isActive} onRequestClose={this.toggleModal}>
                        <button type="submit" onClick={this.toggleModal} className="btn btn-secondary">Close</button>
                        <button type="submit" onClick={this.addStaff} className="btn btn-secondary">Save</button>

                        <div className="container">
                            <div className="row">
                                <div className="col-md-6 mt-5 mx-auto">

                                    <form noValidate onSubmit={this.addStaff}>
                                        <h1 className="h3 mb-3 front-weight-normal">Add person</h1>

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
                                                placeholder="Enter your last name"
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
                                                placeholder="Enter address"
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
                                                placeholder="Enter phone"
                                                value={this.state.phone}
                                                onChange={this.onChange}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="salary">Salary</label>
                                            <input type="text"
                                                className="form-control"
                                                name="salary"
                                                placeholder="Enter salary"
                                                value={this.state.salary}
                                                onChange={this.onChange} />
                                        </div>


                                        <div className="form-group">
                                            <label htmlFor="occupation">Occupation</label>
                                            <input type="text"
                                                className="form-control"
                                                name="staffoccupationId"
                                                placeholder="1-admin, 2-chelner, 3-bucatar"
                                                value={this.state.staffoccupationId}
                                                onChange={this.onChange}
                                            />
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>

                    </Modal>
                </div>
            )
        }
    }
}

export default AllStaff;