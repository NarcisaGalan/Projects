import React, { Component } from 'react';
import img from '../../commons/images/restaurantPlan.gif';
import Modal from 'react-modal';
import AllReservation from './allReservation';
import { withRouter } from 'react-router-dom'
import { Button } from 'react-bootstrap'
import DateTimePicker from 'react-datetime-picker';
import SuccessAlert from '../ModalForRequest/SuccessAlert';
import ErrorAlert from '../ModalForRequest/ErrorAlert';

import '../../principal/nav.css'
import '../../commons/styles/buttons.css';
import '../styles/styleModal.css'
import '../styles/styleSelect.css';




class Reservation extends Component {

    constructor(props) {
        super(props)

        this.state = {
            isActive: false,
            teams: [],
            diningtableId: 0,
            validationError: "",
            from: new Date(),
            to: new Date(),
            comments: '',
            alert_message: ''
        }

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentDidMount() {
        fetch(
            'http://localhost:52004/api/diningtables/getavailabletables'
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
                                "Select an available table"
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

    onChangeTimeFROM = from => this.setState({ from })
    onChangeTimeTO = to => this.setState({ to })

    onSubmit(e) {
        e.preventDefault()

        const newReservation = {
            from: this.state.from,
            to: this.state.to,
            comments: this.state.comments,
            diningtableId: parseInt(this.state.diningtableId)
        }

        console.log(this.state.diningtableId);
        console.log(this.state.from);
        console.log(this.state.to);
        console.log(newReservation);

        const options = {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newReservation)

        }
        fetch('http://localhost:52004/api/reservations', options)
        .then(res=> {
            this.setState({
                alert_message: "success"
            });
        })
        .catch(error=>{
            this.setState({
                alert_message: "error"
            });
        })

        
        //this.toggleModal();
        window.location.reload(false); //asta imi da refres dar nu se mai vede record-ul
    }

    render() {
        return (
            <div>
                <hr />
                    {this.state.alert_message == "success" ? <SuccessAlert /> : null}
                    {this.state.alert_message == "error" ? <ErrorAlert /> : null}
            <section>
                <img src={img} />
                <Button class="btn btn-secondary"
                    type="button"
                    buttonSize="btn--primary--solid"
                    buttonSize="btn--large"
                    onClick={this.toggleModal}
                >Create a reservation</Button>
                
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
                                        Reservation
                                    </h1>

                                    <div>
                                        {/* <label htmlFor="from">FROM</label> */}
                                        <DateTimePicker
                                            className="form-control"
                                            name="from"
                                            onChange={this.onChangeTimeFROM}
                                            value={this.state.from}
                                        />
                                    </div>


                                    <div>
                                        <label htmlFor="to">TO</label>
                                        <DateTimePicker
                                            className="form-control"
                                            name="to"
                                            onChange={this.onChangeTimeTO}
                                            value={this.state.to}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="comments">comments</label>
                                        <input type="text"
                                            className="form-control"
                                            name="comments"
                                            placeholder="Enter comments"
                                            value={this.state.comments}
                                            onChange={this.onChange} />
                                    </div>

                                    <div class="box select">
                                        <select
                                            value={this.state.diningtableId}
                                            onChange={e =>
                                                this.setState({
                                                    diningtableId: e.target.value,
                                                    validationError:
                                                        e.target.value === ""
                                                            ? "You must select a Table"
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
                <AllReservation />
            </section>
            </div>
        );
    }
}

export default withRouter(Reservation);