import React from 'react';
import { withRouter } from 'react-router-dom';
import { Button, Label, Row, Col, Container, Table, Modal, ModalHeader, ModalBody } from 'reactstrap';
import '../commons/styles/common-style.css'
import { AvForm, AvField } from 'availity-reactstrap-validation';
import swal from 'sweetalert2';

import axios from 'axios';


class Profil extends React.Component {

    constructor() {
        super()
        this.state = {
            isActive: false,
            parolaNoua: '',
            parolaVerfica: '',
            errors: {}

        }

        this.onChange = this.onChange.bind(this)

        this.handleValidSubmit = this.handleValidSubmit.bind(this)
        this.handleInvalidSubmit = this.handleInvalidSubmit.bind(this);
    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value })
    }
    toggleModal = () => {
        this.setState({
            isActive: !this.state.isActive
        })

    }

    openModal = () => {
        this.toggleModal();
    }

    handleValidSubmit() {
        if (this.state.parolaNoua === this.state.parolaVerfica) {

            axios.put(`http://localhost:8443/user/changePassword/${localStorage.getItem("userID")}`, this.state.parolaNoua, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem("token")}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.state.parolaNoua),

            })
                .then(res => {
                    console.log(res)
                    swal.fire("Succes", "Parola a fost schimbata", "success");
                    
                })
                .catch(err => {
                    this.setState({ errorMessage: err.response.data });
                    swal.fire("Error", err.response.data.message, "error");
                });


        } else {
            swal.fire("Eroare", "Cele doua parole nu coincid, introduceti din nou", "error")

        }


    }

    handleInvalidSubmit(event, errors, values) {

        swal.fire("Eroare", "Formularul a fost completat gresit!Verificati tot inca o data", "error")
        this.setState({ error: true });
        return;

    }

    render() {

        return (
            <div>

                <Row  style={{marginBottom:"30px"}}>
                    <Col sm={{ size: 'auto', offset: 1 }}>
                        <Container fluid style={{ marginLeft: '40px', marginTop: '20px' }} className="block-example border border-danger" >
                            <Label id="subtitleCommon" >Profil Utilizator:</Label>

                            <Label id="subtitleCommon" style={{ color: '#bd1515', marginLeft: '10px' }}> {localStorage.getItem("numeAngajat")}</Label>
                            <Table responsive size="sm" borderless>
                                <thead>
                                    <tr><th>Informatii de contact:</th></tr>
                                </thead>
                                <tbody>
                                    <tr><td style={{ color: '#bd1515' }}>{localStorage.getItem("userEmail")}</td></tr>
                                    <tr><td><Label id="labelStyle">Informatii generale:</Label></td></tr>
                                    <tr><td style={{ color: '#bd1515' }}>Nume: {localStorage.getItem("numeAngajat")}</td></tr>
                                    <tr><td style={{ color: '#bd1515' }}>Facultate: {localStorage.getItem("facultate")}</td></tr>
                                    <tr><td style={{ color: '#bd1515' }}>Departament: {localStorage.getItem("departament")}</td></tr>
                                    <tr><td><Label id="labelStyle">Permisiuni:</Label></td></tr>
                                    <tr><td style={{ color: '#bd1515' }}>Rol: {localStorage.getItem("userRole")}</td></tr>
                                </tbody>
                            </Table>

                        </Container>
                    </Col>
                    <Col sm={{ size: 'auto', offset: 1 }}>
                        <Container fluid style={{ marginTop: '60px' }} >
                            <Row><Label id="labelStyle" style={{ marginTop: '10px' }}>Actiuni:</Label></Row>
                            <Row><Button onClick={this.openModal} outline color="danger" style={{ marginTop: '5px' }}> Schimba Parola</Button></Row>
                        </Container>
                    </Col>


                    <Modal isOpen={this.state.isActive} toggle={this.toggleModal} >
                        <ModalHeader toggle={this.toggleModal}>
                            Schimbare Parola
                       </ModalHeader>
                        <ModalBody>
                            <h2 id="help-message">Parola trebuie să conțină cel puțin un numar, o literă mare și una mică, un caracter special și să aibă cel puțin 8 caractere *</h2>

                            <AvForm onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>
                                <AvField name="parolaNoua" label="Parola Noua *" type="password" onChange={this.onChange} validate={{
                                    required: { value: true, errorMessage: 'Introduceti parola' },
                                    pattern: {
                                        value: '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})'
                                        , errorMessage: 'Parola trebuie sa contina cel putin un numar, o litera mare si una mica, un caracter special si sa aiba cel putin 8 caractere '
                                    },


                                }}
                                />
                                <AvField name="parolaVerfica" label="Reintroduceti parola **" type="password" onChange={this.onChange}
                                    validate={{
                                        required: { value: true, errorMessage: 'Introduceti parola' },
                                        pattern: {
                                            value: '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})'
                                            , errorMessage: 'Parola trebuie sa contina cel putin un numar, o litera mare si una mica, un caracter special si sa aiba cel putin 8 caractere '
                                        },


                                    }} />
                                <Button outline color="danger">Salveaza</Button>
                            </AvForm>

                        </ModalBody>
                    </Modal>
                </Row>

            </div>


        );
    }
}
export default withRouter(Profil);