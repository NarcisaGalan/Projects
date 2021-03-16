import React from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import NavigationBarRegLink from './navigation-bar'
import Home from '../home/home';
import News from '../news/news';
import Products from '../products/ProductsUserLogged/products';
import Contact from '../contact/contact';
import LogIn from '../login/login';
import Register from '../register/register';
import Reservation from '../reservation/reservation';
import Cart from '../componentCart/Cart';
import Checkout from '../componentCart/Checkout';
import Menu from '../menu/menu'

import Notification from '../notification/notification'

/*Reservation Access for Admin and Cook */
import ReservationCook from '../reservation/cookAccessReservation/reservation';
import ReservationAdmin from '../reservation/adminAccessReservation/reservation';
import ReservationUser from '../reservation/userAccessReservation/reservation';
/************************************** */

/*Menu Access for Admin, Cook, Users */
import MenuCook from '../menu/cookAccessMenu/menu';
import MenuAdmin from '../menu/adminAccessMenu/menu';
import MenuUser from '../menu/userAccessMenu/menu';
/*********************************** */

/************Shopping Cart Access for Admin */
import ShoppingCartAdmin from '../componentCart/adminAccessShoppingCart/AllShoppingCart'


/*********Products  */
import ProductsUserNotLoggin from '../products/ClientNotLogged/ProductList'
import ProductsAdmin from '../products/ProductsAdmin/ProductList'
import ProductsCook from '../products/ProductsAdmin/ProductList'

/*********Charts Admin  */

import OrderList from '../componentCart/UserHistoryOrder/OrderList'
import Footer from '../footer/footer';
import Charts from '../charts/charts.js'
import AllStaff from '../staffAdminAcces/allStaff';



import ErrorPage from '../commons/errorhandling/error-page';
import styles from '../commons/styles/project-style.css';



function Principal() {

    return (
        <div>
            <Router>
                <div>
                    <NavigationBarRegLink />
                    <Switch>

                        <Route
                            exact
                            path='/'
                            render={() => <Home />}
                        />

                        <Route
                            exact
                            path='/news'
                            render={() => <News />}
                        />

                        <Route
                            exact
                            path='/productsUser'
                            render={() => <Products />}
                        />

                        <Route
                            exact
                            path='/contact'
                            render={() => <Contact />}
                        />
                        <Route
                            exact
                            path='/reservation'
                            render={() => <Reservation />}
                        />
                        <Route
                            exact
                            path='/menu'
                            render={() => <Menu />}
                        />

                        <Route
                            exact
                            path='/login'
                            render={() => <LogIn />}
                        />

                        <Route
                            exact
                            path='/register'
                            render={() => <Register />}
                        />
                        <Route
                            exact
                            path='/cart' component={Cart}
                        />

                        <Route
                            exact
                            path='/checkout' component={Checkout}
                        />

                        {/* /*ReservationAccess */}
                        <Route
                            exact
                            path='/reservationAdmin'
                            render={() => <ReservationAdmin />}
                        />

                        <Route
                            exact
                            path='/reservationCook'
                            render={() => <ReservationCook />}
                        />
                        <Route
                            exact
                            path='/reservationUser'
                            render={() => <ReservationUser />}
                        />
                        {/* FINISH RESERVATION ACCESS */}

                        {/* MenuAccess */}
                        <Route
                            exact
                            path='/menuAdmin'
                            render={() => <MenuAdmin />}
                        />

                        <Route
                            exact
                            path='/menuCook'
                            render={() => <MenuCook />}
                        />

                        <Route
                            exact
                            path='/menuUser'
                            render={() => <MenuUser />}
                        />
                        {/* FINISH MENU ACCESS */}

                        {/* Shopping cart Access Admin */}

                        <Route
                            exact
                            path='/cartAdmin' component={ShoppingCartAdmin}
                        />
                        {/* FINISH CART ACCESS */}

                        {/* Products Access User Logg on */}
                        <Route
                            exact
                            path='/productsUserNotLogged' component={ProductsUserNotLoggin}
                        />
                         <Route
                            exact
                            path='/productsAdmin' component={ProductsAdmin}
                        />
                         <Route
                            exact
                            path='/productsCook' component={ProductsCook}
                        />

                        <Route
                            exact
                            path='/ordersUser' component={OrderList}
                        />

                        <Route
                            exact
                            path='/staffAdmin' component={AllStaff}
                        />

                        {/* Notification */}
                        <Route
                            exact
                            path='/notification'
                            render={() => <Notification />}
                        />

                        <Route
                            exact
                            path='/chart' component={Charts}
                        />


                        {/*Error*/}
                        <Route
                            exact
                            path='/error'
                            render={() => <ErrorPage />}
                        />

                        <Route render={() => <ErrorPage />} />
                    </Switch>
                </div>
            </Router>
            <Footer/>
        </div>
    )

}

export default Principal
