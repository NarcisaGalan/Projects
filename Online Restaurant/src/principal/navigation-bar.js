import React, { Component } from 'react'

import './nav.css';
import { Link, withRouter } from 'react-router-dom';
import { getTotalFromCart } from '../componentCart/repository'



const textStyle = {
    color: 'white',
    textDecoration: 'none',
    background: 'rgb(68,59,224)',
    background: 'linear-gradient(90deg, rgba(68,59,224,1) 0%, rgba(32,3,71,0.9559174011401436) 31%, rgba(28,98,112,1) 100%)'
};


class NavigationBarRegLink extends Component {

    constructor(props) {
        super(props);
        this.state = {
            numberNotification: 0,
            total: 0,
        }
    }


    componentDidMount() {/*GET ALL NOTIFICATION UNREAD */
        const apiUrl = 'http://localhost:52004/api/Notifications/unread';

        fetch(apiUrl)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        numberNotification: result.length,
                    })
                },
                (error) => {
                    this.setState({ error });
                }
            )

        getTotalFromCart(localStorage.getItem('userStorageId')).then((total) => {

            this.setState({ total });
        });
    }

    logOut(e) {
        e.preventDefault()
        localStorage.removeItem('userOccupationId');
        this.props.history.push(`/`)
    }

    render() {

        const { total } = this.state;

        const loginRegLink = (

            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                    <ul class="navbar-nav mr-auto">

                        <li class="nav-item">
                            <a class="nav-link" href="/">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/news">News</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/productsUserNotLogged">Products</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/menuUser">Menus</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/contact">Contact us!</a>
                        </li>
                    </ul>

                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/login">Log In</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/register">Register</a>
                        </li>
                    </ul>

                </div>
            </div>

        )


        const userLink = (

            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                    <ul class="navbar-nav mr-auto">

                        <li class="nav-item">
                            <a class="nav-link" href="/">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/news">News</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/productsUser">Products</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/menuUser">Menu</a>
                        </li>


                        <li class="nav-item">
                            <a class="nav-link" href="/reservationUser">Reservation</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/contact">Contact us!</a>
                        </li>
                    </ul>

                    <ul class="navbar-nav ml-auto">

                        <li class="nav-item">
                            <a class="nav-link" href="/ordersUser">Orders</a>

                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/cart">
                                {/* <span className="label label-info">(${total})</span> My Cart */}
                                <i class="fas fa-shopping-cart"> {this.state.total} lei</i>
                            </a>
                        </li>


                        <li class="nav-item">
                            <a class="nav-link" href="" onClick={this.logOut.bind(this)}>LogOut</a>
                        </li>
                    </ul>

                </div>
            </div >

        )

        const adminLink = (

            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/">Home</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/productsAdmin">Products</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/menuAdmin">Menus</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/reservationAdmin">Reservations</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/cartAdmin">Carts</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/chart">Chart</a>
                        </li>



                    </ul>

                    <ul class="navbar-nav ml-auto">

                        <li class="nav-item">
                            <a class="nav-link" href="/staffAdmin">Staff</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link">v3.1</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="" onClick={this.logOut.bind(this)}>LogOut</a>
                        </li>

                    </ul>

                </div>

            </div>



        )

        const cookLink = (

            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">

                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/">Home</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/productsCook">Products</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/menuCook">Menus</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/reservationCook">Reservations</a>
                        </li>
                    </ul>

                    <ul class="navbar-nav ml-auto">

                        <li class="nav-item">
                            <a class="nav-link">v3.1</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/notification">
                                <i class="fas fa-bell"> {this.state.numberNotification}</i>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="" onClick={this.logOut.bind(this)}>LogOut</a>
                        </li>

                    </ul>

                </div >

            </div >
        )


        const project = () => {
            switch (localStorage.getItem('userOccupationId')) {

                case "null": return userLink;
                case "1": return adminLink;
                case "3": return cookLink;

                default: return loginRegLink;
            }
        }

        return (

            <nav class="navbar navbar-expand-md navbar-dark bg-dark" id="color-nav">
                {project()}
            </nav>


        )
    }


}
export default withRouter(NavigationBarRegLink) 
