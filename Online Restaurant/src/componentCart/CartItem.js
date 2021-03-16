import React from 'react';
import { getShoppingCartByCustomerId , getProductsByCustomerId , getProducts, getQuantity} from './repository'
import { withRouter } from 'react-router-dom';
import axios from 'axios'
import  '../products/productItem.css';

class CartItem extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			id: 0,
    		quantity: 0,
    		productId: 0,
			customerId: 0,
			details: 'no details',
			status: 'in cart',
            products: [],
			qty: '',
			quantityDB: '',
			btnVisible: false,
			shoppingcart: [],

		}
		
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
    }

	handleChange = (e) => {
 
		if(e.target.value <= 0) {
            alert("Quantity must be greater than 0");
 
            return;
		}
        if(this.state.qty != e.target.value) {
            this.setState({
                qty: e.target.value,
                btnVisible: true
            });
        }
	}
	

	componentDidMount() {
		getShoppingCartByCustomerId(localStorage.getItem("userStorageId")).then(res =>
			{
				this.setState({ id: res.id,
								quantity: res.quantity,
								details: res.details,
								status: res.status,
								productId: res.productId,
								customerId: res.customerId});
				
			})
		
		getQuantity(localStorage.getItem("userStorageId"), this.props.product.id).then(data=>
			this.setState({quantityDB: data}))
	}

	
    handleSubmit = (e) => {
        e.preventDefault();
 
       const newShoppingCart = {
		   id: this.state.id,
		   details: this.state.details,
		   status: this.state.status,
		   quantity: this.state.qty,
		   productId: this.props.product.id,
		   customerId: localStorage.getItem("userStorageId"), //de aici poate fi problem.

	   }
	  
	  

	   axios.put(`http://localhost:52004/api/shoppingcarts/`+ localStorage.getItem("userStorageId"), newShoppingCart)
	   .then((res) => {
		   console.log('Cart successfully updated')
	   }).catch((error) => {
		   console.log(error)
	   })
        this.setState({
            btnVisible: false
		});
		window.location.reload(false)
	}
	
	deleteProductFromCart = () => {
		let id = this.props.product.id;
	
		const apiUrl = 'http://localhost:52004/api/shoppingcarts/' + localStorage.getItem("userStorageId") + '/' + id;
	
		const options = {
		  method: 'delete',
		  headers: {
			'Accept': 'application/json, text/plain, /',
			'Content-Type': 'application/json'
		  },
	
		}
		fetch(apiUrl, options)
		window.location.reload(false)
	
	  }


	render(){
		const { product } = this.props;

		return (
			
		    <div className="card" style={{ marginBottom: "10px"}}>
			  <div className="card-body">
			    <h4 className="card-title">{product.name}</h4>
			    <h5 className="card-text text-danger"><small>Price: </small>{product.price} Lei</h5>
			    <span className="card-text text-success"><small>Description: </small>{product.description}</span>

				<form onSubmit={this.handleSubmit}>

                      <div className="col-xs-4">
                          <input type="number" className="form-control input-sm" placeholder={this.state.quantityDB} onChange={this.handleChange} value={this.state.qty}/>
                      </div>
 
                      {
                          this.state.btnVisible?(
                              <div className="col-xs-2">
                                  <button type="submit" className="btn btn-info">Update</button>
                              </div>
                          ) : null
                      }
 
                     
                  </form>
			    
			    <button  className="btn btn-sm btn-warning float-right" onClick={this.deleteProductFromCart}>
                    
                    Remove from cart</button>

			  </div>
			</div>
		)
	}
}
export default withRouter(CartItem);
