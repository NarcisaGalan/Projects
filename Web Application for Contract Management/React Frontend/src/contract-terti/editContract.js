import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Label } from 'reactstrap';
import { AvForm, AvField } from 'availity-reactstrap-validation';
import axios from 'axios';
import Swal from 'sweetalert2';


class EditContract extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modal: false,
           
            director: localStorage.getItem('numeAngajat'),
            partener: this.props.data.coordonatorPartener,
            titlu: this.props.data.titluProiect,
            moneda: this.props.data.moneda,
            valTotala: this.props.data.valoare,
            valTVA: this.props.data.tva,
            tip: this.props.data.tip,
            numarContract: this.props.data.numar,
            dataContract: this.props.data.data,
            dataIncheiere: this.props.data.dataIncheierii,
            dataSfarsit: this.props.data.dataSfarsit,
            numarPagini: this.props.data.nrPagini,
            numarDeExemplare: this.props.data.nrExemplare,
            exemplareBeneficiar: this.props.data.nrExemplareBeneficiar,
            
          
        }
        this.toggle = this.toggle.bind(this);
        this.onChange = this.onChange.bind(this);

    }
 

    onChange(e) {

        this.setState({ [e.target.name]: e.target.value })
        console.log(e.target.name,e.target.value)
       
    }

    toggle = () => {
        this.setState({ modal: !this.state.modal })
    }

    handleValidSubmit = () =>  {

        const contract={
            idContractTerti:this.props.data.idContractTerti,
            cifCuiBeneficiar:localStorage.getItem("cifCUIBeneficiar"),
            idDirector: localStorage.getItem("idAngajat"),
            partener: this.state.partener,
            titlu: this.state.titlu,
            moneda: this.state.moneda,
            valTotala: this.state.valTotala,
            valTVA: this.state.valTVA,
            dataIncheiere: this.state.dataIncheiere,
            tip: this.state.tip,
            numarContract: this.state.numarContract,
            dataContract: this.state.dataContract,
            dataInceput: this.state.dataContract,
            dataSfarsit: this.state.dataIncheiere,
            numarPagini: this.state.numarPagini,
            numarDeExemplare: this.state.numarDeExemplare,
            exemplareBeneficiar: this.state.exemplareBeneficiar,
           
          }
        const url = 'http://localhost:8443/contractTerti/update';
        axios.put(url, contract, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(contract),
        })
            .then(res => {
                console.log(res);
                Swal.fire("Succes", "Contractul a fost modificat!", "success");
                
            })
            .catch(err => {
              
                Swal.fire("Eroare", "Ceva nu a mers bine", "error")
                
            })



        // window.location.reload(false);
    }

    handleInvalidSubmit(event, errors, values) {
        Swal.fire("Eroare", "Formularul a fost completat gresit!Verificati tot inca o data", "error")
      
    
        return;

    }


    render() {
        return (
            <div>
                <Button color="danger" outline onClick={this.toggle}>Modifica</Button>
                <Modal size='l' isOpen={this.state.modal} toggle={this.toggle} className="Modal">
                    <ModalHeader toggle={this.toggle}>Modificare Act Aditional</ModalHeader>
                    <ModalBody>
                        <AvForm onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>
                        <Label id="top">Director Proiect</Label>
                                <AvField type="text" name="director" id="director" value={localStorage.getItem('numeAngajat')} required grid={{ xs: 6 }}  disabled />
                                <Label for="partener">Coordonator/Partener</Label>
                                <AvField type="text" name="partener" id="partener"value={this.state.partener} placeholder="Coordonator/Partener" onChange={this.onChange} grid={{ xs: 6 }}  required />

                                <Label id="labelStyle">Obiectul Contractului</Label>
                                <AvField type="text" name="titlu" id="titlu" placeholder="Titlu Proiect" value={this.state.titlu} onChange={this.onChange} required />
                                <Label id="labelStyle">Valoarea Contractului</Label>
                                <br />
                                <Label for="moneda">Moneda</Label>
                                <AvField type="select" value={this.state.moneda} name="moneda"  id="moneda" onChange={this.onChange} grid={{ xs: 3 }}  required>
                                    <option> </option>
                                    <option>RON</option>
                                    <option>EUR</option>
                                    <option>USD</option>
                                    <option>GBP</option>
                                    <option>CHF</option>
                                </AvField>
                                <AvField type="text" name="valTotala" id="valTotala" value={this.state.valTotala} grid={{ xs: 4 }}  placeholder="Valoarea totală" onChange={this.onChange} required />

                                <AvField type="text" name="valTVA" id="valTVA" value={this.state.valTVA}grid={{ xs: 4 }}  placeholder="Valoare TVA" onChange={this.onChange} required />

                                <Label id="labelStyle">Durata Contractului</Label>

                                <AvField
                                    type="date"
                                    name="dataIncheiere"
                                    id="dataIncheiere"
                                    value={this.state.dataIncheiere}
                                    label="Execuţia completă a activităţilor prevăzute în prezentul contract se încheie la data de:"
                                    placeholder="date placeholder"
                                    onChange={this.onChange}
                                    grid={{ xs: 4 }}
                                    required
                                />

                                <Label id="labelStyle">Detalii Contract</Label>
                                <AvField grid={{ xs: 4 }} type="select" name="tip" id="tip" value={this.state.tip} onChange={this.onChange} label="Tip Contract" required>
                                    <option></option>
                                    <option>C-CDI</option>
                                    <option>C-ST</option>
                                    <option>CC-CDI</option>
                                    <option>CC-ST</option>
                                    <option>CS-CC-CDI</option>
                                </AvField>
                                <AvField type="number" grid={{ xs: 4 }} label="Număr Contract" value={this.state.numarContract} name="numarContract" id="numarContract"  onChange={this.onChange} min="0" required />

                                <AvField
                                    type="date"
                                    name="dataContract"
                                    id="dataContract"
                                    value={this.state.dataContract}
                                    label="Data Contract"
                                    placeholder="date placeholder"
                                    onChange={this.onChange}
                                    grid={{ xs: 4 }}
                                    required
                                />

                                <AvField name="numarPagini" grid={{ xs: 4 }} type="text"value={this.state.numarPagini} label="Număr de pagini " placeholder="ex:1-100" min="1" maxLength="3" onChange={this.onChange}  />

                                <AvField name="numarDeExemplare" grid={{ xs: 4 }} type="text" value={this.state.numarDeExemplare} label="Număr de exemplare originale" placeholder="ex:1-100" maxLength="3" min="1" onChange={this.onChange}  />

                                <AvField name="exemplareBeneficiar" grid={{ xs: 4 }}  type="text" value={this.state.exemplareBeneficiar} label = "Număr Exemplare Beneficiar" placeholder="ex:1-100" maxLength="3" min="1" onChange={this.onChange}  />

                                
                            <Button style={{ backgroundColor: '#790202' }} size="sm" type="submit">Modifica</Button>
                        

                        </AvForm>

                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.toggle}>Închide</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }

}


export default EditContract;