import React from 'react';
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button } from 'reactstrap';
import Modal from 'react-modal';
import Modal2 from 'react-modal';
import axios from 'axios'

/*Style*/
import './MenuStyle.css';

import '../../principal/nav.css'
import '../../commons/styles/buttons.css';
import '../../reservation/styles/styleModal.css'
import '../../reservation/styles/styleSelect.css';

import imgMenu from '../../menu/menu.jpg';

class MenuCartItem extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      menuId: 0,
      //campuri pentru menu
      price: 0.0,
      productId: 0,
      details: '',

      // For Modal
      isActive: false,
      isActive2: false,

      //campuri pentru meniu cu produs
      idMeniuNew: 0,
      name: "",
      description: "",
      price: 0.0,
      ingredients: "",
      weight: 0.0,
      kcal: 0,
      imageurl: "",
      menus: [],
      teams: [],

      productsFromMenuID: [],

      products: '',
      menuIdRemember: 0
    }
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.onSubmitAddProduct = this.onSubmitAddProduct.bind(this);
  }

  componentWillMount() {
    Modal.setAppElement('body');
  }

  componentWillMount() {
    Modal2.setAppElement('body');
  }

  toggleModal = () => {
    this.setState({
      isActive: !this.state.isActive
    })
  }

  toggleModal2 = () => {
    this.setState({
      isActive2: !this.state.isActive2
    })
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value })
  }

  onSubmit(e) {/*Asta ii ok */
    e.preventDefault()

    const newMenu = {
      id: parseInt(this.state.menuId),
      details: this.state.details,
      price: parseFloat(this.state.price),
      productId: parseInt(this.state.productId),
    }

    console.log('Menu ID ' + this.state.menuId);
    console.log(' Detalii ' + this.state.details);
    console.log('Price ' + this.state.price);
    console.log('Product ID ' + this.state.productId);

    console.log('meniu = ' + newMenu.price)


    const options = {
      method: 'put',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newMenu)
    }

    fetch('http://localhost:52004/api/Menus/' + this.state.menuId, options)
      .then(res => {
        console.log(res)
      })

    console.log('http://localhost:52004/api/Menus/' + this.state.menuId)


    //this.toggleModal();
    window.location.reload(false); //asta imi da refres dar nu se mai vede record-ul
  }

  componentDidMount() {/*Fetch care da toate produsele din lista la create */
    fetch(
      'http://localhost:52004/api/products'
    )
      .then(response => {
        return response.json();
      })
      .then(data => {
        let teamsFromApi = data.map(team => {
          return { value: team.id, display: team.name };
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

  editMenu = () => {
    let id = this.props.menu.menuID;//imi scoate id-ul meniului

    //http://localhost:52004/api/Menus/getproducts/2 --scoate produsele dintr-un meniu

    const apiUrl = 'http://localhost:52004/api/Menus/getproducts/' + id;

    fetch(apiUrl)
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            productsFromMenuID: result
          });
        },
        (error) => {
          this.setState({ error });
        }
      )

    //console.log(this.state.productsFromMenuID)
    this.toggleModal();
    
  }

  deleteProductsFromMenu = (idProduct) => {
    let idMenu = this.props.menu.menuID;
    //http://localhost:52004/api/Menus/2/1---sterge produsul
    console.log('Meniul ' + idMenu);
    console.log('Produsul ' + idProduct);

    const options = {
      method: 'delete',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      }
    }

    fetch('http://localhost:52004/api/Menus/' + idMenu + '/' + idProduct, options)
      .then(res => {
        this.setState({
          alert_message: "Product has been deleted"
        })
      })

    window.location.reload(false);
  }

  addProductInMenuExistent = () => {
    //http://localhost:52004/api/Menus -- baga in meniu un produs
    let idMenu = this.props.menu.menuID;//id-ul meniului

    this.setState({
      menuIdRemember: idMenu
    })

    console.log('remember Id Menu = ' + this.state.menuIdRemember)

    this.toggleModal2();
    console.log(idMenu)
  }

  onSubmitAddProduct(e) {
    e.preventDefault()
    //http://localhost:52004/api/Menus -- post meniu cu produs
    let idMenu = this.props.menu.menuID;

    const newMenuWithAddProduct = {
      id: parseInt(idMenu),
      details: this.state.details,
      price: this.state.price,
      productId: parseInt(this.state.productId)
    }

    console.log('produs Id' + this.state.productId)

    const options = {
      method: 'post',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newMenuWithAddProduct)

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

    console.log('ID MENIU' + idMenu);
    window.location.reload(false);
  }

  renderTableData() {
    return (
      <table id='students'>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Option</th>
          </tr>
        </thead>
        <tbody>
          {this.state.productsFromMenuID.map(product => (
            <tr key={product.id}>
              <td>{product.id}</td>
              <td>{product.name}</td>
              <td>{product.description}</td>
              <td>{product.price}</td>
              <td>
                <Button variant="danger" onClick={() => this.deleteProductsFromMenu(product.id)}>Delete Product</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    )
  }

  render() {
    const { menu, productsFromMenuID } = this.props;

    return (

      <Card id="card">
        <CardImg id="img" top width="100%" src={imgMenu} alt="Card image cap" />
        <CardBody>
          <CardTitle id="titleProduct">{menu.detail}  {menu.price} RON</CardTitle>
          <CardText id="titleProduct">{menu.products}</CardText>

          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          <Button onClick={this.editMenu} id="btnEdit">Edit Menu</Button>
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          <Button onClick={this.addProductInMenuExistent} id="btn">Add product</Button>
          <Modal
            className="color-modal"
            size="xl"
            aria-labelledby="example-modal-sizes-title-sm" isOpen={this.state.isActive} onRequestClose={this.toggleModal}>
            <button type="submit" onClick={this.toggleModal} className="btn btn-secondary">Close</button>

            <div className="container">
              <div className="row">
                <div className="col-md-6 mt-5 mx-auto">
                  <h1 className="h3 mb-3 front-weight-normal">
                    Edit Menu
                  </h1>

                  {this.renderTableData()}
                </div>
              </div>
            </div>

          </Modal>

          <Modal2
            className="color-modal"
            size="xl"
            aria-labelledby="example-modal-sizes-title-sm" isOpen={this.state.isActive2} onRequestClose={this.toggleModal2}>
            <button type="submit" onClick={this.toggleModal2} className="btn btn-secondary">Close</button>


            <div className="container">
              <div className="row">
                <div className="col-md-6 mt-5 mx-auto">
                  <form noValidate onSubmit={this.onSubmitAddProduct}>
                    <h1 className="h3 mb-3 front-weight-normal">
                      Add product in Menu
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

                    <div class="box select">
                      <select
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

                    <button type="submit" className="btn btn-secondary">Add</button>
                  </form>
                </div>
              </div>
            </div>

          </Modal2>


        </CardBody>
      </Card>




    )
  }
}
export default MenuCartItem
