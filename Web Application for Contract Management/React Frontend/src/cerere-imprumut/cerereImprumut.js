import React from 'react';
import { getTitluProiectByUser } from '../commons/api/api'
import { withRouter } from 'react-router-dom';
import { Button, Label, Row, Col, Container } from 'reactstrap';
import '../commons/styles/common-style.css'
import { AvForm, AvField} from 'availity-reactstrap-validation';
import swal from 'sweetalert2';

import axios from 'axios';



class CerereImprumut extends React.Component {

    constructor() {
        super()
        this.state = {
            numarDocument: '',
            dataCerere: new Date(),
            universitateNume: '',
            facultateNume: '',
            departamentNume: '',
            titluProiect: '',
            an: '',
            valoareTotalaImprumut: 0,
            salarii: 0,
            achizitii: 0,
            deplasari: 0,
            taxe: 0,
            total: 0,
            faza: '',
            dataReturnare: '',


            idUniversitate: 0,
            idFacultate: 0,
            titluri: [],


            errors: {}
        }

        this.onChange = this.onChange.bind(this)
        this.handleValidSubmit = this.handleValidSubmit.bind(this)
        this.handleInvalidSubmit = this.handleInvalidSubmit.bind(this);
        this.total=this.total.bind(this)     
    }

    onChange(e) {

        this.setState({ [e.target.name]: e.target.value })

    }
   
    
   
    componentDidMount() {/* toate universitatile */

        getTitluProiectByUser(localStorage.getItem("idAngajat")).then((titluri) => {
            this.setState({ titluri });
        });
    }

    handleValidSubmit() {

        const cerere = {
            numar: this.state.numarDocument,
            data: this.state.dataCerere,
            anul: this.state.an,
            etapa: this.state.faza,
            valoareaTotala: this.state.valoareTotalaImprumut,
            salarii: this.state.salarii,
            achizitii: this.state.achizitii,
            deplasari: this.state.deplasari,
            taxe: this.state.taxe,
            dataReturnarii: this.state.dataReturnare,
            aprobata: false,
            titluProiect: this.state.titluProiect,
            idUniversitate: this.state.idUniversitate,
            idFacultate: this.state.idFacultate,
            numeDepartament: this.state.departamentNume,
        }
       
        const url = 'http://localhost:8443/cerereImprumut/add';
        axios.post(url, cerere, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(cerere),
        })
            .then(res => {
                console.log(res);
                swal.fire("Succes", "Cererea de împrumut a fost salvată!", "success");
            })
            .catch(err => {
                swal.fire("Eroare", err.response.data, "error")
            });

      
            setTimeout(() => {   window.location.reload(false);}, 2000);
    }

    handleInvalidSubmit(event, errors, values) {
        swal.fire("Eroare", "Formularul a fost completat greșit!Verificați tot înca o data", "error")
        this.setState({ error: true });
        return;

    }
     
    total() 
    { 
        var s, a, t, d, total; 
        s = Number(document.getElementById("salarii").value, 10);
        a = Number(document.getElementById("achizitii").value, 10);
        t = Number(document.getElementById("taxe").value, 10);
        d = Number(document.getElementById("deplasari").value, 10);
        total = (s+a+t+d); 
        this.setState({total:total})
 
; 
    }

    render() {

        return (
            <div id="pageBackground" >
                <h1 id="pageTitle">Formular Cerere Împrumut</h1>
                <Container fluid >

                    <Row>

                        <Col sm="12" md={{ size: 4, offset: 4 }}>

                            <AvForm onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>
                                <Label id="top" for="numarDocument">Număr Document *</Label>
                                <AvField type="text" name="numarDocument" grid={{ xs: 3 }} id="numarDocument" onChange={this.onChange} required />

                                <AvField
                                    type="date"
                                    name="dataCerere"
                                    id="dataCerere"
                                    label="Dată Cerere *"
                                    placeholder="date placeholder"
                                    onChange={this.onChange}
                                    grid={{ xs: 6 }}
                                    required
                                />

                                <AvField type="text" name="universitate" id="universitate" 
                                    value={localStorage.getItem('universitate')} required disabled
                                    label="Universitate"
                                />
                                
                                <AvField type="text" name="facultate" id="facultate" required label="Facultate"
                                    value={localStorage.getItem('facultate')} disabled
                                />
                                
                                <AvField type="text" name="departament" id="departament" required label="Departament"
                                    value={localStorage.getItem('departament')} disabled/>

                                <AvField type="select" name="titluProiect *" id="titluProiect" required
                                    label="Proiect"
                                    onChange={e =>
                                        this.setState({
                                            titluProiect: e.target.value

                                        })
                                    }>
                                    <option> </option>
                                    {this.state.titluri.map((titlu, i) => (

                                        <option
                                            key={i}
                                            value={titlu}
                                        >
                                            {titlu}
                                        </option>

                                    ))}
                                </AvField>
                                <AvField type="number"  name="an"   grid={{ xs: 4 }} id="an" placeholder="An" label="Realizare obiective din anul *" onChange={this.onChange} required />
                                <AvField type="number"grid={{ xs: 6 }} name="valoareTotalaImprumut" label="Valoare totală împrumut *" id="valoareTotalaImprumut" onChange={this.onChange} required />
                                <AvField type="number"grid={{ xs: 6 }} name="salarii" label="Salarii *" id="salarii"  onChange={this.onChange} required />
                                <AvField type="number"grid={{ xs: 6 }} name="achizitii" label="Achiziții echipamente/materiale *" id="achizitii"  onChange={this.onChange} required />

                                <AvField type="number"grid={{ xs: 6 }} name="deplasari" label="Deplasări interne/externe *" id="deplasari"  onChange={this.onChange} required />

                                <AvField type="number"grid={{ xs: 6 }} name="taxe" label="Taxe *" id="taxe"  onChange={this.onChange} required />

                                <AvField type="number"grid={{ xs: 6 }} name="total" label="TOTAL *" id="total" placeholder="TOTAL"     required />
                                

                                <AvField type="text" inputClass="bg-gradient-primary" name="faza" label="Returnare la data intrării sumelor aferente fazei proiectului *" id="faza" placeholder="Faza proiectului" onChange={this.onChange} required />

                                <AvField
                                    type="date"
                                    name="dataReturnare"
                                    label="Data returnarii sumei *"
                                    id="dataReturnare"
                                    placeholder="mm/dd/yyyy"
                                    onChange={this.onChange}
                                    grid={{ xs: 6 }}
                                    required
                                />

                                <Button  style={{marginBottom:"20px"}}  outline color="primary" type="submit" id="button-space" > Salvare</Button>

                            </AvForm>

                        </Col>

                    </Row>

                </Container>
            </div>
        );
    }
}
export default withRouter(CerereImprumut);