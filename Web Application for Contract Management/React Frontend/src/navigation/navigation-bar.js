import React, { Component } from 'react'
import './nav.css';
import { withRouter } from 'react-router-dom';
import {
  Collapse,
  Navbar,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavbarToggler
} from 'reactstrap';
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import Icon from '../commons/images/logout.png'




class NavigationBarRegLink extends Component {

  constructor(props) {
    super(props);
    this.state = {
      isOpen: false,
      anchorEl: null
    }
    this.toggle = this.toggle.bind(this);
    this.handleClose = this.handleClose.bind(this);
    this.handleClick = this.handleClick.bind(this);

  }

  handleClose() {
    this.setState({ anchorEl: null })

  }

  handleClick(event) {
    this.setState({ anchorEl: event.currentTarget })
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    })
  }
  logOut(e) {
    e.preventDefault()
    localStorage.clear();
    this.props.history.push(`/`)
  }

  render() {

    const ButtonDeconectare = ({ align }) => (
      <button className="my-button" onClick={this.logOut.bind(this)}>

        <img className={align} src={Icon} alt="LogOut" />
            Deconectare
      </button>
    )

    const loginRegLink = (
      <Collapse isOpen={this.state.isOpen} navbar>
        <Nav className="mr-auto" navbar>
          <NavItem >
            <NavLink href="/" style={{ backgroundColor: '#b00000', color: '#ffffff' }}>Acasă</NavLink>
          </NavItem>
        </Nav>
        <Button href="/login" style={{ backgroundColor: '#b00000', color: '#ffffff' }}>
          Conectare
      </Button>
      </Collapse>
    )

    const adminLink = (
      <Collapse isOpen={this.state.isOpen} navbar>
        <Nav className="mr-auto" navbar>
          <NavItem >
            <NavLink href="/" >Acasă</NavLink>
          </NavItem>
          <NavItem >
            <NavLink href="/raportAdmin">Raport Lunar</NavLink>
          </NavItem>
          <NavItem >
            <NavLink href="/utilizator">Utilizatori</NavLink>
          </NavItem>
        </Nav>
        <Button aria-controls="simple-menu" aria-haspopup="true" onClick={this.handleClick} style={{ backgroundColor: '#b00000', color: '#ffffff' }}>
          {localStorage.getItem("numeMembru")}
        </Button>
        <Menu
          id="simple-menu"
          anchorEl={this.state.anchorEl}
          keepMounted
          open={Boolean(this.state.anchorEl)}
          onClose={this.handleClose}
        >
          <MenuItem onClick={this.handleClose} ><Button href="/profilAdmin" style={{ backgroundColor: '#b00000', color: '#ffffff' }} >Profil</Button></MenuItem>
          <MenuItem onClick={this.handleClose}><ButtonDeconectare align="left" /></MenuItem>
        </Menu>

      </Collapse>

    )
    const directorLink = (
      <Collapse isOpen={this.state.isOpen} navbar>
        <Nav className="mr-auto" navbar>
          <NavItem >
            <NavLink href="/" >Acasă</NavLink>
          </NavItem>
          <NavItem >
            <NavLink href="/actAditional">ActAdițional</NavLink>
          </NavItem>
          <NavItem >
            <NavLink href="/contractTerti">Contract</NavLink>
          </NavItem>
          <NavItem >
            <NavLink href="/cheltuiala">Cheltuială</NavLink>
          </NavItem>
          <NavItem >
            <NavLink href="/imprumut">Împrumut</NavLink>
          </NavItem>
          <NavItem >
            <NavLink href="/incasare">Încasare</NavLink>
          </NavItem>
          <NavItem >
            <NavLink href="/statFunctii">Funcții</NavLink>
          </NavItem>
          <UncontrolledDropdown nav inNavbar  >
            <DropdownToggle nav caret >
              Rapoarte
                  </DropdownToggle>
            <DropdownMenu right >
              <DropdownItem style={{ color: '#b00000' }} href="/raportContract" >
                Raport Contract
                    </DropdownItem>
              <DropdownItem style={{ color: '#b00000' }} href="/buget">
                Raport Buget
                    </DropdownItem>
              <DropdownItem style={{ color: '#b00000' }} href="/angajati" >
                Raport Angajati
                    </DropdownItem>
            </DropdownMenu>
          </UncontrolledDropdown>
        </Nav>
        <Button aria-controls="simple-menu" aria-haspopup="true" onClick={this.handleClick} style={{ backgroundColor: '#b00000', color: '#ffffff' }}>
          {localStorage.getItem("numeAngajat")}
        </Button>
        <Menu
          id="simple-menu"
          anchorEl={this.state.anchorEl}
          keepMounted
          open={Boolean(this.state.anchorEl)}
          onClose={this.handleClose}
        >
          <MenuItem onClick={this.handleClose} ><Button href="/profil" style={{ backgroundColor: '#b00000', color: '#ffffff' }} >Profil</Button></MenuItem>
          <MenuItem onClick={this.handleClose}><ButtonDeconectare align="left" /></MenuItem>
        </Menu>

      </Collapse>
    )


    const project = () => {
      switch (localStorage.getItem('userRole')) {

        case "admin": return adminLink;
        case "director": return directorLink;

        default: return loginRegLink;
      }
    }

    return (
      <div>
        <Navbar style={{ backgroundColor: '#b00000', fontSize: 18 }} dark expand="md" >
          <NavbarToggler onClick={this.toggle} />
          {project()}
        </Navbar>

      </div>

    )
  }


}
export default withRouter(NavigationBarRegLink) 
