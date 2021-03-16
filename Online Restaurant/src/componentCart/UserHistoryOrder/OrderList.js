import React from 'react';
import {  Button } from 'react-bootstrap';
import SuccessAlert from '../../reservation/ModalForRequest/SuccessAlert';
import ErrorAlert from '../../reservation/ModalForRequest/ErrorAlert';

import '../../reservation/styles/styleTable.css'
import '../../principal/nav.css'
import '../../products/productItem.css'





class OrderList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            orders: [],
            response: {},
            alert_message: ''
        }
    }



    componentDidMount() {/*GET ALL orders by id shopping carts */
        const apiUrl = 'http://localhost:52004/api/orders/getOrdersByshoppingcart/'.concat(localStorage.getItem("shoppingStorageId"));

        fetch(apiUrl)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        orders: result
                    });
                },
                (error) => {
                    this.setState({ error });
                }
            )
            
    }
    

    render() {
        
        const { error, orders } = this.state;

        if (error) {
            return (
                <div>Error: {error.message}</div>
            )
        } else {
            
            return (
                <div id='backProducts'>
                    <hr/>
                    {this.state.alert_message=="success"?<SuccessAlert/>:null}
                    {this.state.alert_message=="error"?<ErrorAlert/>:null}
                    <h1 id='title'>Your Orders</h1>
                    {/* {this.state.response.message && <Alert variant="info">{this.state.response.message}</Alert>} */}
                    <table id='students'>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Details</th>
                                <th>Price</th>
                             
                               
                            </tr>
                        </thead>
                        <tbody>
                            {orders.map(order => (
                                <tr key={order.id}>
                                    <td>{order.id}</td>
                                    <td>{order.details}</td>
                                    <td>{order.price}</td>
                                    
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )
        }
    }
}

export default OrderList;