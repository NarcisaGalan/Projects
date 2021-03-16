import React from 'react';
import { getTitluProiectByUser } from '../commons/api/api'
import { withRouter } from 'react-router-dom';
import { Button, Label, Row, Col, Container,Input } from 'reactstrap';
import '../commons/styles/common-style.css'
import { AvForm, AvField } from 'availity-reactstrap-validation';
import swal from 'sweetalert2';

import axios from 'axios';



class Cheltuiala extends React.Component {

    constructor() {
        super()
        this.state = {

            titluProiect: '',
            numarDocument: '',
            tipDocument: '',
            dataCheltuiala: new Date(),
            incadrareCheltuiala: '',
            explicatii: '',
            moneda: '',
            valoareCheltuiala: 0,
            valoareTVA: 0,

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

        const cerere = {
            titluProiect: this.state.titluProiect,
            numarDocument: this.state.numarDocument,
            tipDocument: this.state.tipDocument,
            dataCheltuiala: this.state.dataCheltuiala,
            incadrareCheltuiala: this.state.incadrareCheltuiala,
            explicatii: this.state.explicatii,
            moneda: this.state.moneda,
            valoareCheltuiala: this.state.valoareCheltuiala,
            valoareTVA: this.state.valoareTVA,

        }
        const url = 'http://localhost:8443/cheltuiala/add';
        axios.post(url, cerere, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(cerere),
        })
            .then(res => {
                console.log(res);
                swal.fire("Succes", "Cheltuiala a fost salvata!", "success");
            })
            .catch(err => {
                swal.fire("Eroare", err.response.data, "error")
            });

       
            setTimeout(() => {   window.location.reload(false);}, 2000);
    }

    handleInvalidSubmit(event, errors, values) {
       
        swal.fire("Eroare", "Formularul a fost completat gresit!Verificati tot inca o data", "error")
        this.setState({ error: true });
        return;

    }

    render() {

        return (


            <div id="pageBackground" >
                <h1 id="pageTitle">Formular Cheltuială</h1>

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
                                <AvField type="text" grid={{ xs: 4 }} name="numarDocument" id="numarDocument" label="Număr Document *" placeholder="ex:15242" onChange={this.onChange} required />

                                <AvField
                                    type="date"
                                    name="dataCheltuiala"
                                    id="dataCheltuiala"
                                    label="Dată Cheltuială *"
                                    placeholder="date placeholder"
                                    grid={{ xs: 6 }}
                                    onChange={this.onChange}
                                    required
                                />

                                <AvField type="text"grid={{ xs: 6 }} name="tipDocument" id="tipDocument" label="Tip Document *" onChange={this.onChange} placeholder="factura/chitanta/bon fiscal" required />


                                <AvField type="text" grid={{ xs: 6 }} name="incadrareCheltuiala" label="Natura Cheltuielii *" id="incadrareCheltuiala" onChange={this.onChange} placeholder="deplasare/achizitie/salar " required />
                                <Label for="explicatii">Explicații/Detalii Cheltuială</Label>
                                <br></br>
                              
                                <Input  type="textarea" name="explicatii" id="explicatii" style={{height:"100px"}} onChange={this.onChange} placeholder="detalii " required />
                               <br></br>
                                <AvField type="select" grid={{ xs: 4 }} value={this.state.moneda} name="moneda" label="Moneda *" id="moneda" onChange={this.onChange} required>
                                    <option> </option>
                                    <option>RON</option>
                                    <option>EUR</option>
                                    <option>USD</option>
                                    <option>GBP</option>
                                    <option>CHF</option>
                                </AvField>

                                <AvField type="number" grid={{ xs: 6 }} name="valoareCheltuiala" label="Valoare Cheltuială *" id="valoareCheltuiala" onChange={this.onChange} placeholder="Valoare Cheltuiala" required />

                                <AvField type="number" grid={{ xs: 6 }} name="valoareTVA" label="Valoare TVA *" id="valoareTVA" placeholder="TVA" onChange={this.onChange} required />


                                <Button  style={{marginBottom:"20px"}}  outline color="primary" type="submit" id="button-space" > Salvare</Button>

                            </AvForm>

                        </Col>

                    </Row>

                </Container>
            </div>


        );
    }
}
export default withRouter(Cheltuiala);