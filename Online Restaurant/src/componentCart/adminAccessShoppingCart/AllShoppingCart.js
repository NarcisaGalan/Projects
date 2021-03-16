import React from 'react';
import {  Button } from 'react-bootstrap';
import SuccessAlert from '../../reservation/ModalForRequest/SuccessAlert';
import ErrorAlert from '../../reservation/ModalForRequest/ErrorAlert';

import '../../reservation/styles/styleTable.css'
import '../../principal/nav.css'
import '../../products/productItem.css'



class AllShoppingCart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            shoppingcarts: [],
            response: {},
            alert_message: ''
        }
    }



    componentDidMount() {/*GET ALL Shopping carts */
        const apiUrl = 'http://localhost:52004/api/shoppingcarts';

        fetch(apiUrl)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        shoppingcarts: result
                    });
                },
                (error) => {
                    this.setState({ error });
                }
            )
            
    }
    

    render() {
        
        const { error, shoppingcarts } = this.state;

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
                    <h1 id='title'>Shopping Carts List</h1>
                    {/* {this.state.response.message && <Alert variant="info">{this.state.response.message}</Alert>} */}
                    <table id='students'>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Details</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Product</th>
                                <th>Client</th>
                             
                               
                            </tr>
                        </thead>
                        <tbody>
                            {shoppingcarts.map(shoppingcart => (
                                <tr key={shoppingcart.id}>
                                    <td>{shoppingcart.id}</td>
                                    <td>{shoppingcart.details}</td>
                                    <td>{shoppingcart.price}</td>
                                    <td>{shoppingcart.quantity}</td>
                                    <td>{shoppingcart.productId}</td>
                                    <td>{shoppingcart.customerId}</td>
                                    
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )
        }
    }
}

export default AllShoppingCart;