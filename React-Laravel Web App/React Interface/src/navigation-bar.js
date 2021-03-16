import React from 'react'
import logo from './commons/images/icon.png';
import {  withRouter } from 'react-router-dom';

import {
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand,
    NavLink,
    UncontrolledDropdown
} from 'reactstrap';

const textStyle = {
    color: 'white',
    textDecoration: 'none'
};

class NavigationBar extends React.Component{


    logOut(e) {
        e.preventDefault()
        localStorage.removeItem('userStatus');
        this.props.history.push(`/`)
    }

    render(){


        const unlogged = (
            <div>
            <Navbar color="dark" light expand="md">
                <NavbarBrand href="/">
                    <img src={logo} width={"60"}
                         height={"50"} />
                </NavbarBrand>
                <Nav className="mr-auto" navbar>
    
                    <UncontrolledDropdown nav inNavbar>
                        <DropdownToggle style={textStyle} nav caret>
                           Menu
                        </DropdownToggle>
                        <DropdownMenu right >
                            <DropdownItem>
                                <NavLink href="/news">News</NavLink>
                            </DropdownItem>
    
                            <DropdownItem>
                                <NavLink href="/about">About</NavLink>
                            </DropdownItem>
    
                            <DropdownItem>
                                <NavLink href="/student">Student Profile  </NavLink>
                            </DropdownItem>
    
                            <DropdownItem>
                                <NavLink href="/teacher">Guiding Teacher </NavLink>
                            </DropdownItem>
    
                            <DropdownItem>
                                <NavLink href="/contact">Contact </NavLink>
                            </DropdownItem>
                           
                        </DropdownMenu>
                    </UncontrolledDropdown>
                    <NavLink href= "/login" style={textStyle}>Login </NavLink>
                    <NavLink href= "/register" style={textStyle}>Register </NavLink>
    
                </Nav>
            </Navbar>
        </div>
        )

        const logged = (
            <div>
            <Navbar color="dark" light expand="md">
                <NavbarBrand href="/">
                    <img src={logo} width={"60"}
                         height={"50"} />
                </NavbarBrand>
                <Nav className="mr-auto" navbar>
    
                    <UncontrolledDropdown nav inNavbar>
                        <DropdownToggle style={textStyle} nav caret>
                           Menu
                        </DropdownToggle>
                        <DropdownMenu right >
                            <DropdownItem>
                                <NavLink href="/news">News</NavLink>
                            </DropdownItem>
    
                            <DropdownItem>
                                <NavLink href="/comments">About</NavLink>
                            </DropdownItem>
    
                            <DropdownItem>
                                <NavLink href="/studentEdit">My Student Profile </NavLink>
                            </DropdownItem>
    
                            <DropdownItem>
                                <NavLink href="/teacher">Guiding Teacher </NavLink>
                            </DropdownItem>
    
                            <DropdownItem>
                                <NavLink href="/contact">Contact </NavLink>
                            </DropdownItem>

                            <DropdownItem>
                                <NavLink href="/statistics">Charts </NavLink>
                            </DropdownItem>

                            <DropdownItem>
                                <NavLink href="/map">Map </NavLink>
                            </DropdownItem>
                           
                        </DropdownMenu>
                    </UncontrolledDropdown>
                    <NavLink href= "" style={textStyle}  onClick={this.logOut.bind(this)}>Logout </NavLink>
                </Nav>
            </Navbar>
        </div>

        )

        const navigation = () => {
            switch (localStorage.getItem('userStatus')) {

                case "null": return unlogged;
                case "1": return logged;
               
                default: return unlogged;
            }
        }


    return(
       <div>
        {navigation()}   
      </div>
    )
    }
}

export default withRouter(NavigationBar)
