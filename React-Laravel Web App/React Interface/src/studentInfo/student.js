import React from 'react';
import {Container, Jumbotron} from 'reactstrap';
import img from '../commons/images/student1.jpg';
import './student.css';
import { withNamespaces } from 'react-i18next';

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "60%",
    height: "95%",
    margin: 0,
};


class Student extends React.Component {


    render() {
        const {t}=this.props;
        return (

            <div class="wrapper">
              
                <div class="form">
                    <h1 id= "titleC">Galan Maria </h1>
                    <Jumbotron fluid style={backgroundStyle} id= "backC">
                         <Container fluid >
                            
                             <h2 id="address">{t("student.technologies")}</h2>
                             <h3 id="paragraphC">{t("student.artificial")} </h3>
                             <h4 id="paragraphC">Blockchain </h4>
                             <h4 id="paragraphC">IoT </h4>
                
                    
                         </Container>
                    </Jumbotron>
                </div>
                <img class="right-img" src={img}/>
                
            </div>
        )
    };
}

export default withNamespaces()(Student)