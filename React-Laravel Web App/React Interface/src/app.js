import React, { useEffect } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import NavigationBar from './navigation-bar';
import Home from './home/home';
import Persons from './person-data/person/persons';
import News from './news/news';
import About from './about/about';
import Contact from './contact/contact';
import Student from './studentInfo/student';
import Teacher from './teacher/teacher';
import Login from './login/login';
import Register from './register/register';
import Info from './about/info';
import Profil from './student/profil'
import Statistics from './charts/chartComments'
import GMap from './map/MapRender'

import { withNamespaces } from 'react-i18next';
import { Button, ButtonGroup, Container } from 'reactstrap';


import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';
import Cookies from 'universal-cookie';
import i18n from './i18n';

let enums = require('./commons/constants/enums');

function App({ t }) {

    const cookies = new Cookies();

    useEffect(() => {
        i18n.changeLanguage(cookies.get('lang'));
    }, []);

    const changeLanguage = (lng) => {
        i18n.changeLanguage(lng);
        cookies.set('lang', lng, { path: '/' });
    }

    return (
        <div className={styles.back}>

            <Router>
                <div>
                    <Container fluid id="backTranslate">
                    <ButtonGroup >
                        <Button active onClick={() => changeLanguage('en')}>US</Button>
                        <Button active onClick={() => changeLanguage('ro')}>RO</Button>
                    </ButtonGroup>
                    </Container>
                    <NavigationBar />
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
                            path='/about'
                            render={() => <About />}
                        />

                        <Route
                            exact
                            path='/student'
                            render={() => <Student />}
                        />

                        <Route
                            exact
                            path='/teacher'
                            render={() => <Teacher />}
                        />
                        <Route
                            exact
                            path='/contact'
                            render={() => <Contact />}
                        />
                         <Route
                            exact
                            path='/login'
                            render={() => <Login />}
                        />
                        <Route
                            exact
                            path='/register'
                            render={() => <Register />}
                        />

                        <Route
                            exact
                            path='/comments'
                            render={() => <Info />}
                        />

                        <Route
                            exact
                            path='/studentEdit'
                            render={() => <Profil />}
                        />

                        <Route
                            exact
                            path='/statistics'
                            render={() => <Statistics />}
                        />
                        <Route
                            exact
                            path='/map'
                            render={() => <GMap />}
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
        </div>
    )
};


export default withNamespaces()(App)
