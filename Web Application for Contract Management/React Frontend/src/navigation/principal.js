import React from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import NavigationBarRegLink from './navigation-bar'
import Home from '../home/home';
import Login from '../login/login'
import CerereImprumut from '../cerere-imprumut/cerereImprumut'
import ContractTerti from '../contract-terti/contract'
import StatFunctii from '../stat-functii/stat_functii'
import ActAditional from '../act-aditional/aditional'
import Cheltuiala from '../cheltuiala/cheltuiala'
import Incasare from '../incasare/incasare'
import RaportContract from '../raport-contract/raportContract'
import RaportBuget from '../raport-buget/buget'
import RaportAngajati from '../raport-angajati/raportAngajati'
import Profil from '../profil-user/profil'
import Utilizator from '../utilizator/utilizator'
import RaportAdmin from '../raport-admin/raportAdmin'
import ProfilAdmin from '../profil-user/profil-admin'
import Footer from '../footer/footer';
import ErrorPage from '../commons/errorhandling/error-page';





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
                            render={() => <Home/>}
                        />

                        <Route
                            exact
                            path='/login'
                            render={() => <Login/>}
                        />
                        
                        <Route
                            exact
                            path='/contractTerti'
                            render={() => <ContractTerti/>}
                        />
                         <Route
                            exact
                            path='/actAditional'
                            render={() => <ActAditional/>}
                        />
                         <Route
                            exact
                            path='/statFunctii'
                            render={() => <StatFunctii/>}
                        />
                         
                        <Route
                            exact
                            path='/imprumut'
                            render={() => <CerereImprumut/>}
                        />

                        <Route
                            exact
                            path='/cheltuiala'
                            render={() => <Cheltuiala/>}
                        />
                        <Route
                            exact
                            path='/incasare'
                            render={() => <Incasare/>}
                        />
                         <Route
                            exact
                            path='/buget'
                            render={() => <RaportBuget/>}
                        />
                        <Route
                            exact
                            path='/angajati'
                            render={() => <RaportAngajati/>}
                        />
                        <Route
                            exact
                            path='/raportContract'
                            render={() => <RaportContract/>}
                        />
                        <Route
                            exact
                            path='/profil'
                            render={() => <Profil/>}
                        />
                        <Route
                            exact
                            path='/utilizator'
                            render={() => <Utilizator/>}
                        />
                         <Route
                            exact
                            path='/raportAdmin'
                            render={() => <RaportAdmin/>}
                        />
                         <Route
                            exact
                            path='/profilAdmin'
                            render={() => <ProfilAdmin/>}
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
