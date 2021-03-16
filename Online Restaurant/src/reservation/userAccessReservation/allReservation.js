import React from 'react';
import {  Button } from 'react-bootstrap';
import SuccessAlert from '../ModalForRequest/SuccessAlert';
import ErrorAlert from '../ModalForRequest/ErrorAlert';

import '../styles/styleTable.css';
import '../../principal/nav.css'





class AllReservation extends React.Component {
    constructor(props) {
        super(props);
        let idCustomer=JSON.parse(parseInt(localStorage.getItem('userStorageId')));
        this.state = {
            customer_id: idCustomer,
            error: null,
            reservations: [],
            response: {},
            alert_message: ''
        }
    }



    componentDidMount() {/*GET RESERVATION CUSTOMER*/
        const apiUrl = 'http://localhost:52004/api/reservations/reservationbycustomer/' + this.state.customer_id;

        fetch(apiUrl)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        reservations: result
                    });
                },
                (error) => {
                    this.setState({ error });
                }
            )
            
    }
    

    deleteProduct(id) {/*DELETE RESERVATION CUSTOMER WITH ID*/
        const { reservations } = this.state;

        const apiUrl = 'http://localhost:52004/api/reservations/'.concat(id);
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
                        reservations: reservations.filter(reservation => reservation.id !== id)
                    });
                },
            )
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
            window.location.reload(false);
            this.setState({
                alert_message: "success"
            });
    }


    render() {
        
        const { error, reservations } = this.state;

        if (error) {
            return (
                <div>Error: {error.message}</div>
            )
        } else {
            
            return (
                <div id="backProducts">
                    <hr/>
                    {this.state.alert_message=="success"?<SuccessAlert/>:null}
                    {this.state.alert_message=="error"?<ErrorAlert/>:null}
                    <h1 id='title'>Your Reservations</h1>
                    <table id='students'>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Comments</th>
                                <th>From</th>
                                <th>To</th>
                                <th>customer_id</th>
                                <th>diningtable_id</th>
                                <th>Option</th>
                            </tr>
                        </thead>
                        <tbody>
                            {reservations.map(reservation => (
                                <tr key={reservation.id}>
                                    <td>{reservation.id}</td>
                                    <td>{reservation.comments}</td>
                                    <td>{reservation.from}</td>
                                    <td>{reservation.to}</td>
                                    <td>{this.state.customer_id}</td>
                                    <td>{reservation.diningtableId}</td>
                                    <td>
                                    <Button variant="danger" onClick={() => {this.deleteProduct(reservation.id) }}>Delete</Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )
        }
    }
}

export default AllReservation;