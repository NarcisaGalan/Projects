import React from 'react';
import { getCustomersById, getTotalFromCart } from './repository';
import { Link , withRouter } from 'react-router-dom';
import { Button } from 'reactstrap';
import axios from 'axios'
import SuccessAlert from '../reservation/ModalForRequest/SuccessAlert'



class Checkout extends React.Component {
	constructor(props) {
		super(props)
		this.state = {
			id: '',
			username: '',
			password: '',
			firstname: '',
			lastname: '',
			email: '',
			phone: '',
			address: '',
			add: '',
			ph: '',
			total: '',
			price: '',
			shoppingcartId: '',
			details: "order placed",
			alert_message: '',
		}
		this.handleChangePh = this.handleChangePh.bind(this);
		this.handleChangeAdd = this.handleChangeAdd.bind(this);
		this.handleSubmitAddress = this.handleSubmitAddress.bind(this);
		this.handleSubmitPhone = this.handleSubmitPhone.bind(this);
	}

	componentDidMount() {
		
		getCustomersById(localStorage.getItem('userStorageId')).then(customer => {
			
			this.setState({ 
				id: customer.id,
				username: customer.username,
				password: customer.password,
				firstname: customer.firstname,
				lastname: customer.lastname,
				email: customer.email,
				phone: customer.phone,
				address: customer.address });
		});
		
    }

	handleChangeAdd = (e) => {
 
            this.setState({
				add: e.target.value,
				
            });
		}
	handleChangePh = (e) => {
 
            this.setState({
				
				ph: e.target.value,
            });
        }
	

	componentWillMount(){
		getTotalFromCart(localStorage.getItem('userStorageId')).then((total) => {

			this.setState({ total });
		  });
	}

	handleSubmitAddress = (e) => {
        e.preventDefault();
 
       const newCustomer = {
		   id: localStorage.getItem("userStorageId"),
		   username: this.state.username,
			password: this.state.password,
			firstname: this.state.firstname,
			lastname: this.state.lastname,
			email: this.state.email,
			phone: this.state.phone,
			address: this.state.add
			

	   }
	  
	   console.log(this.state.id )
	   console.log(this.state.username )
	   console.log(this.state.firstname )
	   console.log(this.state.email )

	   axios.put(`http://localhost:52004/api/customers/`+ localStorage.getItem("userStorageId"), newCustomer)
	   .then((res) => {
		   console.log('Customer successfully updated')
	   }).catch((error) => {
		   console.log(error)
		});
		window.location.reload(false)
	}


	handleSubmitPhone = (e) => {
        e.preventDefault();
 
       const newCustomer = {
		   id: localStorage.getItem("userStorageId"),
		   username: this.state.username,
			password: this.state.password,
			firstname: this.state.firstname,
			lastname: this.state.lastname,
			email: this.state.email,
			phone: this.state.ph,
			address: this.state.address
			

	   }
	  
	   console.log(this.state.id )
	   console.log(this.state.username )
	   console.log(this.state.firstname )
	   console.log(this.state.email )

	   axios.put(`http://localhost:52004/api/customers/`+ localStorage.getItem("userStorageId"), newCustomer)
	   .then((res) => {
		   console.log('Customer successfully updated')
	   }).catch((error) => {
		   console.log(error)
		});
		window.location.reload(false)
	}

	addToOrder = () => {
		const newOrder = {
			price: this.state.total,
			details: this.state.details,
			shoppingcartId: localStorage.getItem("shoppingStorageId")
		}

		axios.post(`http://localhost:52004/api/orders`, newOrder)
		.then(res => this.setState({
			alert_message: "success"
		}));

		//fara metoda API din backend delete by shoppping cart id, ca e doar unu la un user logat
		axios.delete(`http://localhost:52004/api/shoppingcarts/`+ localStorage.getItem("shoppingStorageId"))

		console.log(localStorage.getItem("shoppingStorageId"));

		
		//this.props.history.push('/products')
	}

	render() {

		const { total} = this.state;
		
		return (

			<div id="backProducts">
				 <hr />
                    {this.state.alert_message == "success" ? <SuccessAlert /> : null}
			
			<div className=" container">
				<h3 className="card-title">Place order</h3>

				<hr />
				<div><h4><small>Total Amount:</small><span className="float-right">{total} Lei</span></h4><hr /></div>

				<hr />
				<div><h4><small>Delivery Address </small></h4><hr /></div>

				<table className="table col-md-4 mx-auto">

					<tbody>
						<tr>
							<td>Name</td>
							<td>{this.state.firstname} {this.state.lastname}</td>
							
						</tr>

						<tr>
							<td>Address</td>
							<td>
							<form onSubmit={this.handleSubmitAddress}>
								<div className="col-xs-4">
                         		 	<input type="text" className="form-control input-sm" placeholder={this.state.address} onChange={this.handleChangeAdd} value={this.state.add}/>
                      			</div>
								  <td> <Button type="submit" className="btn btn-info">Update</Button></td>
							</form>
							</td>

						
						</tr>
						<tr>
							<td>Phone</td>
							<td>
							<form onSubmit={this.handleSubmitPhone}>
								<div className="col-xs-4">
                         		 	<input type="text" className="form-control input-sm" placeholder={this.state.phone} onChange={this.handleChangePh} value={this.state.ph}/>
                      			</div>
								  <td> <Button type="submit" className="btn btn-info">Update</Button></td>
							</form>
							</td>
						</tr>
						
					</tbody>
				</table>
				<button className="btn btn-success float-right" onClick={this.addToOrder}>Submit</button>
				<Link to="/cart"><button className="btn btn-danger float-right" style={{ marginRight: "10px" }}>Cancel</button></Link>

				<div><h4><small>Payment Details</small></h4></div>

				<div>
					<input type="radio" /> Cash
				</div>

				<div>
					<input type="radio" /> Credit Card
                </div>

			</div>
			</div>
		);
	}
}
export default  withRouter(Checkout)