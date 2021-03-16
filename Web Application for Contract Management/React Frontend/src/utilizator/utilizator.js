import React from 'react';
import { getAllDepartamente } from '../commons/api/api'
import { withRouter } from 'react-router-dom';
import { Button, Label, Row, Col, Container } from 'reactstrap';
import '../commons/styles/common-style.css'
import { AvForm, AvField } from 'availity-reactstrap-validation';
import swal from 'sweetalert2';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';

import axios from 'axios';



class Utilizator extends React.Component {

    constructor() {
        super()
        this.state = {

            userEmail: '',
            userRole: '',
            userName: '',
            userFunctie: '',

            membru: '',
            functieMembru: '',
            userDepartament: '',

            departamente: [],
            adminValid: true,
            directorValid:true,
            errors: {}
        }

        this.onChange = this.onChange.bind(this)
        this.handleValidUser = this.handleValidUser.bind(this)
        this.handleInvalidSubmit = this.handleInvalidSubmit.bind(this);
        this.randomString = this.randomString.bind(this);
        this.handleValidMembru = this.handleValidMembru.bind(this);
        this.checkRoleValid = this.checkRoleValid.bind(this);
    }

    componentDidMount() {/* toate contractele by user */

        getAllDepartamente().then((departamente) => {
            this.setState({ departamente });
        })
    }

    onChange(e) {

        this.setState({ [e.target.name]: e.target.value })
        console.log(e.target.name,e.target.value)

    }

    randomString() {
        var parola = '';
        var caractereRandom = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*+=-~/.,<>?';
        for (var i = 0; i < 10; i++) {
            parola += caractereRandom.charAt(Math.floor(Math.random() * caractereRandom.length));
        }
        return parola;
    }


    handleValidUser() {
        let parola = this.randomString();

        const userNou = {
            email: this.state.userEmail,
            parola: parola,
            nume: this.state.userName,
            role: this.state.userRole,
            functie: this.state.userFunctie,
            numeDepartament: this.state.userDepartament

        }
        const url = 'http://localhost:8443/user/add';
        axios.post(url, userNou, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(userNou),
        })
            .then(res => {
                console.log(res)
                swal.fire("Succes", "Utilizatorul a fost adăugat. Acesta va primi un email cu credențiale temporare.", "success");
            })
            .catch(err => {

                swal.fire("Error", err.response.data, "error");
            });


          
    }

    handleValidMembru() {

        const membruNou = {
            nume: this.state.membru,
            functie: this.state.functieMembru
        }
        const url = 'http://localhost:8443/admin/addMembru';
        axios.post(url, membruNou, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(membruNou),
        })
            .then(res => {
                console.log(res)
                swal.fire("Succes", "Membrul a fost adăugat cu succes! Vă mulțumim!", "success");
            })
            .catch(err => {

                swal.fire("Error", err.response.data, "error");
            });


        // window.location.reload(false);
    }

    handleInvalidSubmit(event, errors, values) {
        swal.fire("Error", "Formularul a fost completat gresit!Verificati tot inca o data", "error");
        this.setState({ error: true });
        return;

    }

    handleDepartament(val){
        this.setState({userDepartament: val.nume})
        console.log(val.nume)
    }

    checkRoleValid(value) {
        if (value === 'admin') {
            this.setState({ adminValid: false,
                             directorValid:true })
        } else {
            this.setState({ adminValid: true,
                            directorValid:false })
        }
    }



    render() {

        return (


            <div id="pageBackground" >
                <h1 id="pageTitle">Utilizatori</h1>

                <Container fluid >

                    <Row style={{ marginLeft: '60px' }}>

                        <Col xs="6">

                            <AvForm style={{ width:"70%" }} onValidSubmit={this.handleValidUser} onInvalidSubmit={this.handleInvalidSubmit}>

                                <Label id="top">Adaugare utilizator</Label>

                                <AvField type="text" name="userEmail" id="userEmail" label="Email" onChange={this.onChange} placeholder="ex: name@email.com" required />

                                <AvField type="text" name="userName" id="userName" label="Nume" onChange={this.onChange} required />

                                <AvField grid={{ xs: 4 }}  type="select" name="userRole" label="Rol" id="userRole" required
                                    onChange={e => (
                                        this.setState({
                                            userRole: e.target.value,


                                        }),
                                        this.checkRoleValid(e.target.value)

                                    )
                                    } >
                                    <option> </option>
                                    <option>admin</option>
                                    <option>director</option>
                                </AvField>

                                <h2 id="help-message">*Doar daca utilizatorul are rol de admin se va alege si functia acestuia!</h2>
                                <AvField grid={{ sm: 4 }}  type="select" name="userFunctie" label="Functie" id="userFunctie" onChange={this.onChange} disabled={this.state.adminValid}>
                                    <option> </option>
                                    <option>Director DMCDI</option>
                                    <option>Staff DMCDI</option>
                                </AvField>

                                <h2 id="help-message">*Doar daca utilizatorul are rol de director se va alege si departamentul acestuia!</h2>
                                <Autocomplete
                                    id="userDepartament"
                                    options={this.state.departamente}
                                    getOptionLabel={(option) => option.nume}
                                    style={{ width: 300 }}
                                    renderInput={(params) => <TextField {...params} label="Departament" variant="outlined" />}
                                    onChange={(event, val)  => {this.handleDepartament(val)}}
                                    disabled={this.state.directorValid}

                                />

                                <Button outline color="primary" type="submit" style={{ marginTop: '20px' }} id="button-space" > Salvare</Button>

                            </AvForm>

                        </Col>
                        <Col xs="6" >
                            <AvForm  style={{ width:"70%",marginLeft:'130px' }} onValidSubmit={this.handleValidMembru} onInvalidSubmit={this.handleInvalidSubmit}>

                                <Label id="top">Adaugare Membrii</Label>

                                <AvField type="text" name="membru" id="membru" label="Decoratii si Nume Membru" onChange={this.onChange} required />

                                <AvField  grid={{ sm: 4 }}  type="select" name="functieMembru" label="Functie" id="functieMembru" onChange={this.onChange} required>
                                    <option> </option>
                                    <option>Rector</option>
                                    <option>Director Economic</option>
                                    <option>Birou Juridic</option>
                                </AvField>

                                <Button outline color="primary" type="submit" id="button-space" > Salvare</Button>

                            </AvForm>

                        </Col>

                    </Row>

                </Container>
            </div>


        );
    }
}
export default withRouter(Utilizator);