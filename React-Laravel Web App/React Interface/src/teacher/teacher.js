import React from 'react';
import {Button, Container, Jumbotron} from 'reactstrap';
import img from '../commons/images/teacher.jpg';
import './teacher.css';
import { withNamespaces } from 'react-i18next';
import {  withRouter } from 'react-router-dom';

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "80%",
    height: "95%",
    margin: 0,
};


class Teacher extends React.Component {

    
    render() {
        const { t } = this.props;
        return (

            <div class="wrapper">
              
                <div class="form">
                    <h1 id= "titleA">DR. IONUT ANGHEL </h1>
                    <Jumbotron fluid style={backgroundStyle} id= "backA">
                         <Container fluid >
                            
                             <h2 id="titleT">{t("teacher.1")}</h2>
                             <h3 id="titleT">{t("teacher.2")} </h3>
                             <h4 id="titleT">{t("teacher.3")}</h4>
                             <h4 id="titleT">{t("teacher.4")}</h4>
                         </Container>
                    </Jumbotron>
                </div>
                <img class="right-img" src={img}/>
                <p className="lead">
                            <Button color="primary" id = "fixedbutton" onClick={() => window.open('http://users.utcluj.ro/~ianghel/index.html')}>
                            {t("teacher.button1")}</Button>
                </p>
                
            </div>
        )
    };
}

export default withRouter(withNamespaces()(Teacher))