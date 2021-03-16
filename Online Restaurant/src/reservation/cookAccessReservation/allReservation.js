import React from 'react';

import '../styles/styleTable.css';
import '../../principal/nav.css'

class AllReservation extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            reservations: [],
            response: {},
            alert_message: ''
        }
    }



    componentDidMount() {/*GET ALL RESERVATION */
        const apiUrl = 'http://localhost:52004/api/reservations';

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

    render() {
        
        const { error, reservations } = this.state;

        if (error) {
            return (
                <div>Error: {error.message}</div>
            )
        } else {
            
            return (
                <div>
                    <h1 id='title'>Reservations List</h1>
                    <table id='students'>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Comments</th>
                                <th>From</th>
                                <th>To</th>
                                <th>customer_id</th>
                                <th>diningtable_id</th>
                            </tr>
                        </thead>
                        <tbody>
                            {reservations.map(reservation => (
                                <tr key={reservation.id}>
                                    <td>{reservation.id}</td>
                                    <td>{reservation.comments}</td>
                                    <td>{reservation.from}</td>
                                    <td>{reservation.to}</td>
                                    <td>{reservation.customerId}</td>
                                    <td>{reservation.diningtableId}</td>
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