import React, { Component } from 'react';
import Modal from 'react-modal';
import { withRouter } from 'react-router-dom'
import { Button } from 'react-bootstrap'
import AllMenus from './allMenus';
import SuccessAlert from '../reservation/ModalForRequest/SuccessAlert';
import ErrorAlert from '../reservation/ModalForRequest/ErrorAlert';

import './MenuStyle.css'
import '../principal/nav.css';
import '../commons/styles/buttons.css';
import '../reservation/styles/styleModal.css'
import '../reservation/styles/styleSelect.css';

class Menu extends Component {

    constructor(props) {
        super(props)

        this.state = {
            idMenu: 0,
            details: '',
            price: 0.0,
            productId: 0,

            teams: [],
            isActive: false,
            validationError: "",
            alert_message: ''
        }

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentDidMount() {
        fetch(
            'http://localhost:52004/api/products'
        )
            .then(response => {
                return response.json();
            })
            .then(data => {
                let teamsFromApi = data.map(team => {
                    return { value: team.id, display: team.codename };
                });
                this.setState({
                    teams: [
                        {
                            value: "",
                            display:
                                "Select a product"
                        }
                    ].concat(teamsFromApi)
                });
            })
            .catch(error => {
                console.log(error);
            });
    }

    componentWillMount() {
        Modal.setAppElement('body');
    }

    toggleModal = () => {
        this.setState({
            isActive: !this.state.isActive
        })
    }


    onChange(e) {
        this.setState({ [e.target.name]: e.target.value })
    }

    onSubmit(e) {
        e.preventDefault()

        const newMenu = {
            details: this.state.details,
            price: parseFloat(this.state.price),
            productId: parseInt(this.state.productId)
        }

        console.log(this.state.details);
        console.log(this.state.price);
        console.log(this.state.productId);
        console.log(newMenu);

        const options = {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newMenu)

        }
        fetch('http://localhost:52004/api/Menus', options)
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


        this.toggleModal();
        window.location.reload(false); //asta imi da refres dar nu se mai vede record-ul
    }

    render() {
        return (
            <div id="backProducts">
                <hr />
                    {this.state.alert_message == "success" ? <SuccessAlert /> : null}
                    {this.state.alert_message == "error" ? <ErrorAlert /> : null}
                <section>
                    <Button class="btn btn-secondary"
                        type="button"
                        buttonSize="btn--primary--solid"
                        buttonSize="btn--large"
                        onClick={this.toggleModal}
                    >Create Menu</Button>

                    <Modal
                        className="color-modal"
                        size="xl"
                        aria-labelledby="example-modal-sizes-title-sm" isOpen={this.state.isActive} onRequestClose={this.toggleModal}>
                        <button type="submit" onClick={this.toggleModal} className="btn btn-secondary">Close</button>


                        <div className="container">
                            <div className="row">
                                <div className="col-md-6 mt-5 mx-auto">
                                    <form noValidate onSubmit={this.onSubmit}>
                                        <h1 className="h3 mb-3 front-weight-normal">
                                            New Menu
                                    </h1>

                                        <div className="form-group">
                                            <label htmlFor="details">Details</label>
                                            <input type="text"
                                                className="form-control"
                                                name="details"
                                                placeholder="Enter details"
                                                value={this.state.details}
                                                onChange={this.onChange} />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="details">Price</label>
                                            <input type="text"
                                                className="form-control"
                                                name="price"
                                                placeholder="Enter price"
                                                value={this.state.price}
                                                onChange={this.onChange} />
                                        </div>

                                        <div>
                                            <select className="box select"
                                                value={this.state.productId}
                                                onChange={e =>
                                                    this.setState({
                                                        productId: e.target.value,
                                                        validationError:
                                                            e.target.value === ""
                                                                ? "You must select a Product"
                                                                : ""
                                                    })
                                                }
                                            >
                                                {this.state.teams.map(team => (
                                                    <option
                                                        key={team.value}
                                                        value={team.value}
                                                    >
                                                        {team.display}
                                                    </option>
                                                ))}
                                            </select>
                                            <div
                                                style={{
                                                    color: "red",
                                                    marginTop: "5px"
                                                }}
                                            >
                                                {this.state.validationError}
                                            </div>
                                        </div>

                                        <button type="submit" className="btn btn-secondary">Save</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </Modal>

                    <AllMenus/>

                </section>
            </div>
        );
    }
}

export default withRouter(Menu);