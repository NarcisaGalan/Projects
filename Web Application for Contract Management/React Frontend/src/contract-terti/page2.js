import React from 'react';
import { withRouter } from 'react-router-dom';
import { Button, Label, Row, Col, Container } from 'reactstrap';
import '../commons/styles/common-style.css'
import { AvForm, AvField } from 'availity-reactstrap-validation';
import swal from 'sweetalert2';
import { addContract } from './api';
import axios from 'axios';


class Page2 extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            director: localStorage.getItem('numeAngajat'),
            partener: '',
            titlu: '',
            moneda: '',
            valTotala: '',
            valFaraTVA: '',
            valTVA: '',
            tip: '',
            numarContract: 0,
            dataContract: new Date(),
            dataIncheiere: new Date(),
            dataSfarsit: new Date(),
            numarPagini: 1,
            numarDeExemplare: 1,
            exemplareBeneficiar: 1,
            exemplareExecutant: 1,
            descarcareValid:true,

            validSfarsitDate: new Date(),
            errors: {}
        }

        this.onChange = this.onChange.bind(this);
        this.handleValidSubmit = this.handleValidSubmit.bind(this);
        this.handleInvalidSubmit = this.handleInvalidSubmit.bind(this);

    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value })
    }
    handleValidSubmit() {
            const newContract={
              cifCuiBeneficiar:localStorage.getItem("cifCUIBeneficiar"),
              idDirector: localStorage.getItem("idAngajat"),
              partener: this.state.partener,
              titlu: this.state.titlu,
              moneda: this.state.moneda,
              valTotala: this.state.valTotala,
              valFaraTVA: this.state.valFaraTVA,
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
              exemplareExecutant: this.state.exemplareExecutant
            }
        
           addContract(newContract);
           swal.fire("Succes", "Contractul a fost salvat cu succes", "success");
           this.setState({descarcareValid : false})
    }

    handleInvalidSubmit() {
        swal.fire("Error", "Formularul a fost completat gresit!Verificati tot inca o data", "error");
        this.setState({ error: true });
        return;

    }
    handleDescarcare  () {
        var fileName = this.state.titlu;
        axios({
            url: `http://localhost:8443/fisiere/getByName/${fileName}`,
            method: 'GET',
            responseType: 'blob', 
            headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
          }).then((response) => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', this.state.titlu+'.pdf');
            document.body.appendChild(link);
            link.click();
          });
    
        
    }

    render() {
        
        return (
            <div id="pageBackground2" >

                <Container fluid >

                    <Row>

                        <Col sm="12" md={{ size: 8, offset: 4 }}>

                            <AvForm onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>
                                <Label id="top">Director Proiect *</Label>
                                <AvField type="text" name="director" id="director" value={localStorage.getItem('numeAngajat')} required grid={{ xs: 6 }}  disabled />
                                <Label for="partener">Coordonator/Partener *</Label>
                                <AvField type="text" name="partener" id="partener" placeholder="Coordonator/Partener" onChange={this.onChange} grid={{ xs: 6 }}  required />

                                <Label id="labelStyle">Obiectul Contractului *</Label>
                                <AvField type="text" name="titlu" id="titlu" placeholder="Titlu Proiect *" onChange={this.onChange} required />
                                <Label id="labelStyle">Valoarea Contractului</Label>
                                <br />
                                <Label for="moneda">Moneda *</Label>
                                <AvField type="select" value={this.state.moneda} name="moneda"  id="moneda" onChange={this.onChange} grid={{ xs: 3 }}  required>
                                    <option> </option>
                                    <option>RON</option>
                                    <option>EUR</option>
                                    <option>USD</option>
                                    <option>GBP</option>
                                    <option>CHF</option>
                                </AvField>
                                <AvField type="text" name="valTotala" id="valTotala" grid={{ xs: 4 }}  placeholder="Valoarea totală *" onChange={this.onChange} required />

                                <AvField type="text" name="valFaraTVA" id="valFaraTVA" grid={{ xs: 4 }}  placeholder="Valoare fără TVA *" onChange={this.onChange} required />

                                <AvField type="text" name="valTVA" id="valTVA" grid={{ xs: 4 }}  placeholder="Valoare TVA *" onChange={this.onChange} required />

                                <Label id="labelStyle">Durata Contractului</Label>

                                <AvField
                                    type="date"
                                    name="dataIncheiere"
                                    id="dataIncheiere"
                                    label="Execuţia completă a activităţilor prevăzute în prezentul contract se încheie la data de: *"
                                    placeholder="date placeholder"
                                    onChange={this.onChange}
                                    grid={{ xs: 6 }}
                                    required
                                />

                                <Label id="labelStyle">Detalii Contract</Label>
                                <AvField grid={{ xs: 4 }} type="select" name="tip" id="tip" onChange={this.onChange} label="Tip Contract *" required>
                                    <option></option>
                                    <option>C-CDI</option>
                                    <option>C-ST</option>
                                    <option>CC-CDI</option>
                                    <option>CC-ST</option>
                                    <option>CS-CC-CDI</option>
                                </AvField>
                                <AvField type="number" grid={{ xs: 4 }} label="Număr Contract *" name="numarContract" id="numarContract"  onChange={this.onChange} min="0" required />

                                <AvField
                                    type="date"
                                    name="dataContract"
                                    id="dataContract"
                                    label="Data Contract"
                                    placeholder="date placeholder"
                                    onChange={this.onChange}
                                    grid={{ xs: 6 }}
                                    required
                                />

                                <AvField name="numarPagini" grid={{ xs: 4 }} type="number" label="Număr de pagini *" placeholder="ex:1-100" min="1" maxLength="3" onChange={this.onChange} required />

                                <AvField name="numarDeExemplare" grid={{ xs: 4 }} type="number" label="Număr de exemplare originale *" placeholder="ex:1-100" maxLength="3" min="1" onChange={this.onChange} required />

                                <AvField name="exemplareBeneficiar" grid={{ xs: 4 }}  type="number" label = "Număr Exemplare Beneficiar *" placeholder="ex:1-100" maxLength="3" min="1" onChange={this.onChange} required />

                                <AvField name="exemplareExecutant" grid={{ xs: 4 }} type="number" label="Număr Exemplare Executant *" placeholder="ex:1-100" maxLength="3" min="1" onChange={this.onChange} required />

                                <Button  color="danger" type="submit" id="button-space" >Salvează Contract</Button>
                                <br></br>
                                <Button style={{marginBottom:"20px"}}  color="danger"  onClick={() => { this.handleDescarcare() }} disabled={this.state.descarcareValid}>Descărcare Contract</Button>
                            </AvForm>

                        </Col>

                    </Row>

                </Container>
            </div>

        );
    }
}
export default withRouter(Page2);