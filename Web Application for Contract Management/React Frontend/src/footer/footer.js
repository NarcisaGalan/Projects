import React from "react";
import {MDBContainer } from "mdbreact";
import img from "../commons/images/logo_footer.png"


class Footer extends React.Component{
    render(){
        return(
            
        <div className="footer-copyright text-center py-3">
            <MDBContainer fluid>
                <img alt="Sigla UTCN" src={img} width={150}/>
            </MDBContainer>
        </div>
        )
    };
}
export default Footer