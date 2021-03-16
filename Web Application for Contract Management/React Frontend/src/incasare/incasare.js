import React from 'react';
import { getTitluProiectByUser } from '../commons/api/api'
import { withRouter } from 'react-router-dom';
import { Button, Label, Row, Col, Container,Input } from 'reactstrap';
import '../commons/styles/common-style.css'
import { AvForm, AvField } from 'availity-reactstrap-validation';
import swal from 'sweetalert2';

import axios from 'axios';


class Incasare extends React.Component {

    constructor() {
        super()
        this.state = {

            titluProiect: '',
            nrDocument: '',
            dataDocument: new Date(),
            tipDocument: '',
            valoareIncasata: 0,
            valoareTVA: 0,
            moneda: '',
            explicatii: '',
            incadrareIncasare: '',

            titluri: [],
            errors: {}
        }

        this.onChange = this.onChange.bind(this)

        this.handleValidSubmit = this.handleValidSubmit.bind(this)
        this.handleInvalidSubmit = this.handleInvalidSubmit.bind(this);
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

        const incasare = {
            titluProiect: this.state.titluProiect,
            nrDocument: this.state.nrDocument,
            dataDocument: this.state.dataDocument,
            tipDocument: this.state.tipDocument,
            valoareIncasata: this.state.valoareIncasata,
            valoareTVA: this.state.valoareTVA,
            moneda: this.state.moneda,
            explicatii: this.state.explicatii,
            incadrareIncasare: this.state.incadrareIncasare,

        }
        const url = 'http://localhost:8443/incasare/add';
        axios.post(url, incasare, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(incasare),
        })
            .then(res => {
                console.log(res)
                swal.fire("Succes", "Incasarea a fost salvata", "success");
            })
            .catch(err => {
               
                swal.fire("Error", err.response.data, "error");
            });

        
            setTimeout(() => {   window.location.reload(false);}, 2000);
    }

    handleInvalidSubmit(event, errors, values) {
        swal.fire("Error", "Formularul a fost completat gresit!Verificati tot inca o data", "error");
        this.setState({ error: true });
        return;

    }

    render() {

        return (


            <div id="pageBackground" >
                <h1 id="pageTitle">Formular Încasare</h1>

                <Container fluid >

                    <Row>

                        <Col sm="12" md={{ size: 4, offset: 4 }}>

                            <AvForm onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>

                                <Label id="top" for="titluProiect">Proiect *</Label>
                                <AvField type="select" name="titluProiect" id="titluProiect" required
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
                                <AvField type="text" name="nrDocument" id="nrDocument" label="Număr Document *" placeholder="ex:142/242" onChange={this.onChange} required />

                                <AvField
                                    type="date"
                                    name="dataDocument"
                                    id="dataDocument"
                                    label="Dată Document *"
                                    placeholder="date placeholder"
                                    grid={{ xs: 4 }}
                                    onChange={this.onChange}
                                    required
                                />

                                <AvField type="text" name="tipDocument" id="tipDocument" label="Tip Document *" onChange={this.onChange} placeholder="factură/chitanță" required />

                                <AvField type="text" name="incadrareIncasare" id="incadrareIncasare" label="Natura Încasării *" placeholder="numerar/card" onChange={this.onChange}  required />
                                <Label for="explicatii">Explicații/Detalii Încasare *</Label>
                                <br></br>
                                <Input  type="textarea" name="explicatii" id="explicatii" onChange={this.onChange} placeholder="detalii " required />

                                <AvField type="select" value={this.state.moneda} name="moneda" id="moneda" label="Moneda *" onChange={this.onChange} required>
                                    <option> </option>
                                    <option>RON</option>
                                    <option>EUR</option>
                                    <option>USD</option>
                                    <option>GBP</option>
                                    <option>CHF</option>
                                </AvField>

                                <AvField type="number" name="valoareIncasata" id="valoareIncasata" label="Valoare Încasată *" onChange={this.onChange}  required />

                                <AvField type="number" name="valoareTVA" id="valoareTVA" label="Valoare TVA *" placeholder="TVA" onChange={this.onChange} required />

                                <Button  style={{marginBottom:"20px"}}  outline color="primary" type="submit" id="button-space" > Salvare</Button>

                            </AvForm>

                        </Col>

                    </Row>

                </Container>
            </div>


        );
    }
}
export default withRouter(Incasare);