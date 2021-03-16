import React from "react";
import {MDBContainer } from "mdbreact";


class Footer extends React.Component{
    render(){
        return(
            
        <div className="footer-copyright text-center py-3">
            <MDBContainer fluid>
                &copy; {new Date().getFullYear()} Copyright: <a> Food Yum </a>
            </MDBContainer>
        </div>
        )
    };
}
export default Footer