import React from 'react';
import { withRouter } from 'react-router-dom';
import { Button, Label, Table, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import './functii.css'
import { AvForm, AvField } from 'availity-reactstrap-validation';
import { getStatFunctiiByContract } from './apiFunctii'
import { getContracteByUser } from '../commons/api/api'
import axios from 'axios'
import swal from 'sweetalert2';

import Container from 'reactstrap/lib/Container';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import { search } from "./searchRequests";



class StatFunctii extends React.Component {
    constructor() {
        super();
        this.state = {
            error: null,

            numeAngajatNou: '',
            idAngajat:null,
            codDepartament: localStorage.getItem('codDepartament'),
            functieAngajatNou: '',
            dataInceput: new Date(),
            dataSfarsit: new Date(),

            angajati: [],
            nume: '',
            functia: '',
            numarContract: '',


            contracte: [],
            contractValid: false,
            beneficiar: '',
          

            isActive: false,
            idContract: null,
            alert_message: '',
            loading: false,
            value:'',
            angajatiExistenti:[],
        }
        this.onChange = this.onChange.bind(this);
        this.getStatFunctiiByContract = this.getStatFunctiiByContract.bind(this);
        this.handleSearchAngajat=this.handleSearchAngajat.bind(this);


    }

    componentDidMount() {/* toate contractele by user */

        getContracteByUser(localStorage.getItem("userID")).then((contracte) => {
            this.setState({ contracte });
            this.setState({ contractValid: true });
        });
       
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

    handleSearchAngajat(angajat){
        if(angajat){
            this.setState({
                idAngajat: angajat.idAngajat,
                numeAngajatNou: angajat.nume,
                codDepartament: angajat.departament.codDepartament
    
            })
        }else{
            this.setState({
                idAngajat: '',
                numeAngajatNou: '',
                codDepartament: ''
    
            })
        }
       
    }
    search = async val => {
        this.setState({ loading: true });
        let idDirector =localStorage.getItem("userID");
        const results = await search(
          `http://localhost:8443/angajat/searchAngajati/byContracteDirector/${idDirector}/${val}`
        );
       console.log(results);
       if (typeof results !== 'undefined'){
        this.setState({ angajatiExistenti: results, loading: false });
       }
    
      };
    
    onChangeHandler = async e => {
        console.log(e.target.value);
        this.search(e.target.value);
        this.setState({ value: e.target.value });
        
      };

    addAngajat = (e) => {

        e.preventDefault();

        const angajatNou = {
            idAngajat: this.state.idAngajat,
            nume:this.state.numeAngajatNou,
            codDepartament: this.state.codDepartament,
            functie: this.state.functieAngajatNou,
            dataInceput: this.state.dataInceput,
            dataSfarsit: this.state.dataSfarsit,
            contractTertiId: this.state.idContract
        }

        const url = 'http://localhost:8443/angajat/addAngajatAndStatFunctii';

        axios.post(url, angajatNou, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem("token")}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(angajatNou),

        })
            .then(res => {
                console.log(res)
                swal.fire("Succes", "Angajatul a fost adaugat", "success");
                setTimeout(() => {   window.location.reload(false);}, 2000);
            })
            .catch(err => {
                this.setState({ errorMessage: err.response.data });
                swal.fire("Error", err.response.data, "error");
            });



        
    }

    getStatFunctiiByContract(idContract) {

        if (this.state.contractValid) {

            getStatFunctiiByContract(idContract)
            .then((data) => {

                this.setState({ angajati: data.angajati,
                                beneficiar: data.beneficiar });

            });
            
        } else {
            console.log("contractValid in get:  ", this.state.contractValid);
            setTimeout(this.getStatFunctiiByContract, 1000);
        }
    }



    render() {


        return (
            <div style={{ marginBottom:"60px"}}>

                <Container fluid>
                    <AvForm style={{ marginLeft: 30 }} onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>

                        <Label id="top" for="numarContract">Număr Contract: *</Label>

                        <AvField type="select" name="numarContract" id="numarContract"
                            required
                            grid={{ xs: 2 }}

                            onChange={e => (
                                this.setState({
                                    idContract: e.target.value,
                                    contractValid: true

                                }),
                                this.getStatFunctiiByContract(e.target.value)

                            )
                            }>
                            <option> </option>
                            {this.state.contracte.map((contract) => (

                                <option
                                    key={contract.idContractTerti}
                                    value={contract.idContractTerti}
                                >
                                    {contract.numar}
                                </option>

                            ))}
                        </AvField>

                    </AvForm>
                    <h1 id='pageTitle'>Stat de Funcții Listă</h1>
                    <h2 id="subtitleCommon">Beneficiar: {this.state.beneficiar}</h2>
                    <Table striped bordered id="angajati">
                        <thead>
                            <tr>
                                <th>Nume</th>
                                <th>Funcția</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.angajati.map(angajat => (
                                <tr key={angajat.nume}>
                                    <td>{angajat.nume}</td>
                                    <td>{angajat.functie}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Container>

                <Button onClick={this.openModal} style={{ marginLeft: '200px', backgroundColor: '#a83236' }} className="btn btn-primary" id="fixedbutton" > Adaugă Angajat</Button>
                <Modal isOpen={this.state.isActive} toggle={this.toggleModal} >
                    <ModalHeader toggle={this.toggleModal}>Adaugă angajat</ModalHeader>
                    <ModalBody>
                    <div className="form-group">
                                    <Autocomplete
                                        options={this.state.angajatiExistenti}
                                        getOptionLabel={(option) => option.nume}
                                        style={{ width: 300, height: 40, marginTop: 15 }}

                                        renderInput={params => (
                                            <TextField {...params} label="Caută angajat după nume" variant="outlined" fullWidth onChange={(event, val) => this.onChangeHandler(event)} />
                                        )}
                                        onChange={(event, val) => { this.handleSearchAngajat(val) }}

                                    />
                                    
                                    <div className="form-group" style={{marginTop:'40px'}}>
                                        <label htmlFor="numeAngajatNou">Nume Angajat *</label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            name="numeAngajatNou"
                                            placeholder="Nume"
                                            value={this.state.numeAngajatNou}
                                            onChange={this.onChange}
                                        />
                                    </div>
                                    <div className="form-group" >
                                        <label htmlFor="codDepartament">Cod Departament *</label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            name="codDepartament"
                                            placeholder="cod departament"
                                            value={this.state.codDepartament}
                                            onChange={this.onChange}
                                        />

                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="functieAngajatNou">Funcție Angajat *</label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            name="functieAngajatNou"
                                            placeholder="functie angajat"
                                            value={this.state.functieAngajatNou}
                                            onChange={this.onChange}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="dataInceput">Dată început *</label>
                                        <input
                                            type="date"
                                            className="form-control"
                                            name="dataInceput"
                                            placeholder="Enter dataInceput"
                                            value={this.state.dataInceput}
                                            onChange={this.onChange}

                                        />
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="dataSfarsit">Dată sfârșit *</label>
                                        <input
                                            type="date"
                                            className="form-control"
                                            name="dataSfarsit"
                                            placeholder="Enter dataSfarsit"
                                            value={this.state.dataSfarsit}
                                            onChange={this.onChange}

                                        />
                                    </div>
                              </div>
                        </ModalBody>
                    <ModalFooter>
                        <Button color="primary" style={{ backgroundColor: '#a83236' }}  onClick={this.addAngajat}>Salvează</Button>{' '}
                        <Button color="secondary" style={{ backgroundColor: '#a83236' }}  onClick={this.toggleModal}>Închide</Button>
                    </ModalFooter>
                </Modal>
               
               
            </div>
        )
    }
}

export default withRouter(StatFunctii);