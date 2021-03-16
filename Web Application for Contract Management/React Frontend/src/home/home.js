import React from 'react';
import './home.css'
import img from '../commons/images/home6.png';
import  {Jumbotron} from 'reactstrap';

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "400px",
    margin: 0,
    backgroundImage: `url(${img})`
};

function Home() {

    return (

        <div>
            <Jumbotron fluid style={backgroundStyle}  >
                    <h1 id="subtitle" >Managementul Contractelor este acum mult mai simplu</h1>
                    <h2 id="paragraph">Toate contractele sunt păstrate într-un singur loc, fiind accesibile oricând și de oriunde</h2>
                    
            </Jumbotron>
        </div>

    )

}

export default Home
