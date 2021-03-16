import React from 'react';
import { Button } from 'react-bootstrap';
// import SuccessAlert from './ModalForRequest/SuccessAlert';
// import ErrorAlert from './ModalForRequest/ErrorAlert';

import '../reservation/styles/styleTable.css';
import '../principal/nav.css'





class Notification extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            notifications: [],
            response: {},
            alert_message: '',
            id: 0,
            name: '',
            details: '',
            createdAt: '',
            status: 0,
            orderId: 0
        }
    }


    componentDidMount() {/*GET ALL NOTIFICATION UNREAD */
        const apiUrl = 'http://localhost:52004/api/Notifications/unread';

        fetch(apiUrl)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        notifications: result
                    });
                },
                (error) => {
                    this.setState({ error });
                }
            )

    }
   


    markAsRead(idNotification) {/*Read notification WITH ID*/

        // api/Notifications/5
        fetch('http://localhost:52004/api/Notifications/' + idNotification)
            .then(res => res.json())
            .then(
                (data) => {
                    this.setState({
                        id: parseInt(data.id),
                        name: data.name,
                        details: data.details,
                        status: data.status,
                        orderId: parseInt(data.orderId),
                        createdAt: data.createdAt,
                    });
                    console.log('notification=' + data.name)
                },
                (error) => {
                    this.setState({ error });
                }
            )


        const newNotification = {
            id: parseInt(this.state.id),
            name: this.state.name,
            details: this.state.details,
            status: 1,
            createdAt: this.state.createdAt,
            orderId: parseInt(this.state.orderId),
        }

        const apiUrl = 'http://localhost:52004/api/notifications/' + idNotification;

        const options = {
            method: 'PUT', // Method itself
            headers: {
                'Content-type': 'application/json; charset=UTF-8' // Indicates the content 
            },
            body: JSON.stringify(newNotification)
        }
        fetch(apiUrl, options)

        console.log(newNotification)
    }

    
    render() {

        const { error, notifications } = this.state;

        if (error) {
            return (
                <div>Error: {error.message}</div>
            )
        } else {

            return (
                <div>
                    {/* <hr/>
                    {this.state.alert_message=="success"?<SuccessAlert/>:null}
                    {this.state.alert_message=="error"?<ErrorAlert/>:null} */}
                    <h1 id='title'>Notifications List</h1>
                    {/* {this.state.response.message && <Alert variant="info">{this.state.response.message}</Alert>} */}
                    <table id='students'>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Details</th>
                                <th>Created at</th>
                                <th>Order</th>
                                <th>Option</th>
                            </tr>
                        </thead>
                        <tbody>
                            {notifications.map(notification => (
                                <tr key={notification.id}>
                                    <td>{notification.id}</td>
                                    <td>{notification.name}</td>
                                    <td>{notification.details}</td>
                                    <td>{notification.createdAt}</td>
                                    <td>{notification.orderId}</td>
                                    <td>
                                        <Button variant="danger" onClick={() => { this.markAsRead(notification.id) }}>Mark as read</Button>
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

export default Notification;