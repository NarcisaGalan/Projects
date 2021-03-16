import React from 'react';
import { withRouter } from 'react-router-dom';
import { Button, Label , Col ,Row,Modal, ModalHeader, ModalBody, ModalFooter,Input} from 'reactstrap';
import './angajati.css'
import { AvForm, AvField } from 'availity-reactstrap-validation';
import {getStatFunctiiByContract} from './apiAngajati'
import {getContracteByUser, getDepartamente} from '../commons/api/api'
import axios from 'axios'
import swal from 'sweetalert2';
import Container from 'reactstrap/lib/Container';
import EditFunctie from './editFunctie'

class RaportAngajati extends React.Component {
    constructor() {
        super();
        this.state = {
            error: null,

            numeAngajatNou: '',
            codDepartament: localStorage.getItem('codDepartament'),
            functieAngajatNou: '',
            dataInceput: new Date(),
            dataSfarsit: new Date(),

            angajati:[],
            nume: '',
            functia: '',
            numarContract: '',
           

            contracte: [],
            contractValid: false,
            beneficiar:{denumire: ''},
            departamente: [],
            incetareActivitate:'',
            checked:false,
            angajatiVechi:[],

            isActive: false,
            idContract: null,
            alert_message: ''
        }
        this.onChange = this.onChange.bind(this);
        this.getStatFunctiiByContract = this.getStatFunctiiByContract.bind(this);
        this.dateIncomplete=this.dateIncomplete.bind(this);
        this.seteazaIncetareActivitate=this.seteazaIncetareActivitate.bind(this);
        

    }

    componentDidMount() {/* toate contractele by user */

        getContracteByUser(localStorage.getItem("userID")).then((contracte) => {
            this.setState({ contracte });
            this.setState({ contractValid: true });
        });
        getDepartamente(localStorage.getItem('idFacultate')).then((departamente)=>{
            this.setState({departamente});
        })
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

    addAngajat = (e) => {

        e.preventDefault();

        const angajatNou = {
            nume: this.state.numeAngajatNou,
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
            })
            .catch(err => {
                this.setState({ errorMessage: err.response.data });
                swal.fire("Error", err.response.data, "error");
            });
            setTimeout(() => {   window.location.reload(false);}, 2000);
    }

    dateIncomplete = (e) =>{
       let angajati= this.state.angajati;
        this.setState({
            checked: e.currentTarget.checked,
            angajatiVechi: angajati
          })
          if(e.currentTarget.checked === true){
            let today = new Date().toISOString().slice(0, 10);
            if(this.state.angajati.length > 0){
                const filtredAngajati = this.state.angajati.filter(angajat => (String(angajat.incetareActivitate) === 'null') || 
                (
                    ( ( String(angajat.dataSfarsit) < String(today) )&& ( String(angajat.incetareActivitate) === 'false' ) )
                 || ( (String(angajat.dataSfarsit) > String(today)) && ( String(angajat.incetareActivitate) === 'true' ) )
                 
                ) );
                this.setState({angajati:filtredAngajati});
            }else{
                swal.fire("Error","Nu exista angajati de filtrat!Selectati un contract mai intai", "error");
            }
          }else{
            this.setState({angajati:this.state.angajatiVechi});
          }
       
    }

    seteazaIncetareActivitate(){
        //parcurgem array angajati si setam incetare activitate 
        var idStatFunctiiList =[];
        this.state.angajati.forEach(angajat => {
            angajat.incetareActivitate = this.state.incetareActivitate;
            idStatFunctiiList.push(angajat.idFunctie);
        });
      
       //apoi apelam api de editare in db this.state.incetareActivitate
      
       axios.put(`http://localhost:8443/statFunctii/update/incetareActivitate/${this.state.incetareActivitate}`,idStatFunctiiList, {
           headers: {
               'Authorization': `Bearer ${localStorage.getItem("token")}`,
               'Content-Type': 'application/json'
           },
           body: JSON.stringify(idStatFunctiiList),

       })
           .then(res => {
               console.log(res)
               swal.fire("Succes", "Incetarea activitatii a fost modificata pentru toti angajatii", "success");
           })
           .catch(err => {
               this.setState({ errorMessage: err.response.data });
               swal.fire("Error", err.response.data, "error");
           });
           setTimeout(() => {   window.location.reload(false);}, 2000);
    }

    getStatFunctiiByContract(idContract) {

        if (this.state.contractValid) {

            getStatFunctiiByContract(idContract).then((data) => {

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
            <div id="pageBackground">

               <Container style={{marginBottom:"40px"}} fluid>
               <AvForm style={{marginLeft:30}} onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>
                    <Label id="top" for="numarContract">Numar Contract: *</Label>

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


                    <h1 id='pageTitle'>Raport Angajați</h1>
                    <h2 id="subtitleCommon">Beneficiar: {this.state.beneficiar.denumire}</h2>
                    <Row>
                        <Col>
                            <Button onClick={this.openModal} style={{ marginLeft: '200px', backgroundColor: '#790202' }} className="btn btn-primary" id="fixedbutton" >Editare Incetare Activitate</Button>
                        </Col>
                        <Col>
                        <Label check style={{ marginLeft: '200px'}}>
                                <Input type="checkbox" checked={this.state.checked}
                                onChange={this.dateIncomplete} />{' '}
                              Filtrare Date Incomplete
                             </Label>
                           </Col>
                    </Row>
               <table id='angajati'>
                    <thead>
                        <tr>
                            <th>Nume</th>
                            <th>Funcția</th>
                            <th>Data început</th>
                            <th>Data sfârșit</th>
                            <th>Încetare activitate</th>
                            <th>Operatii</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.angajati.map(angajat => (
                            <tr key={angajat.idAngajat}>
                                <td>{angajat.nume}</td>
                                <td>{angajat.functie}</td>
                                <td>{angajat.dataInceput}</td>
                                <td>{angajat.dataSfarsit}</td>
                                <td>{String(angajat.incetareActivitate)}</td>
                                <td><EditFunctie data={angajat}/></td>
                            </tr>
                        ))}
                    </tbody>
                </table>

               </Container>

                <Modal size='l' isOpen={this.state.isActive} onRequestClose={this.toggleModal}>
                    <ModalHeader toggle={this.toggle}>Editare încetare activiate pentru toti angajaii</ModalHeader>
                    <ModalBody>
                        <AvForm>
                        <AvField type="select" name="incetareActivitate" id="incetareActivitate" required label="Incetare Activitate"
                                    onChange={this.onChange}>
                                    <option> </option>
                                    <option>true </option>
                                    <option>false </option>
                                </AvField>
                        </AvForm>

                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary"  onClick={this.toggleModal} style={{backgroundColor:'#a83236'}} >Inchide</Button>
                        <Button color="primary"  onClick={this.seteazaIncetareActivitate} style={{backgroundColor:'#a83236'}} >Editare</Button>

                    </ModalFooter>
                </Modal>

            </div>
        )
    }
}

export default withRouter(RaportAngajati);