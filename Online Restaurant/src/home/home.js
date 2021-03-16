import React from 'react';
import './home.css'
import img from '../commons/images/img.jpg';

import { Container, Jumbotron } from 'reactstrap';

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "370px",
    margin: 0,
    backgroundImage: `url(${img})`
};
const backgroundStyle1 = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "250px",
    
};


function Home() {

    return (

        <div>

            <Jumbotron fluid style={backgroundStyle}  >
                <Container fluid >
                    <h1 id="titleHome">FoodYum </h1>

                </Container>
            </Jumbotron>
            
            <Container fluid  id="backHome"  style={backgroundStyle1}>
                    <h1 id="subtitle" >A UNIQUE TASTE EXPERIENCE: OUR FOOD WILL FILL YOUR EYES AND DELIGHT YOUR PALATE</h1>
                    <h2 id="paragraph">Here at FoodYum, it is all in the detail. Our consciously sourced produce is carefully combined to create beautiful dishes with unique flavours. Organic wines have been hand-picked to complement our menus, giving our customers the best experience with no compromise on quality. We work with local friends who provide us with our organic coffee, cold pressed juices and vegan treats. </h2>
            </Container>
           
            

        </div>

    )

}

export default Home
