import React from 'react';
import { getTitluProiectByUser } from '../commons/api/api'
import { withRouter } from 'react-router-dom';
import { Button, Label, Row, Col, Container } from 'reactstrap';
import '../commons/styles/common-style.css'
import { AvForm, AvField } from 'availity-reactstrap-validation';
import Swal from 'sweetalert2';
import axios from 'axios';
import ModalListaActeAditionale from './listaActeAditionale';

function changeDate(date) {
    var end = new Date(date);
    return new Date(end.setFullYear(end.getFullYear() + 10));
}


class ActAditional extends React.Component {

    constructor() {
        super()
        this.state = {
            numar: '',
            dataAct: new Date(),
            universitateNume: '',
            facultateNume: '',
            departamentNume: '',
            titluProiect: '',
            dataInceput: new Date(),
            dataSfarsit: new Date(),


            errorMessage: '',
            idUniversitate: 0,
            idFacultate: 0,
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

        const act = {
            numar: this.state.numar,
            dataAct: this.state.dataAct,
            dataInceput: this.state.dataInceput,
            dataSfarsit: this.state.dataSfarsit,
            titluProiect: this.state.titluProiect
        }
        const url = 'http://localhost:8443/act-aditional/add';
        axios.post(url, act, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(act),
        })
            .then(res => {
                console.log(res);
                Swal.fire("Succes", "Actul Aditional a fost salvat!", "success");
                
            })
            .catch(err => {
                this.setState({ errorMessage: err.response.data });
                Swal.fire("Eroare", err.response.data, "error")
                
            })



            setTimeout(() => {   window.location.reload(false);}, 2000);
    }

    handleInvalidSubmit(event, errors, values) {
        Swal.fire("Eroare", "Formularul a fost completat gresit!Verificati tot inca o data", "error")
      
        this.setState({ error: true });
        return;

    }

    render() {

        return (
            <div id="pageBackground" >
                <h1 id="pageTitle">Act Adițional</h1>
                <Container fluid >

                    < Row style={{marginLeft:"20px"}}>
                    <ModalListaActeAditionale buttonLabel="Modificare act adițional"  />
                        <Col sm="12" md={{ size: 4, offset: 3 }}>

                            <AvForm onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>

                                <Label id="top" for="numar">Numar Document *</Label>
                                <AvField grid={{ xs: 6 }} type="text" name="numar" id="numar" onChange={this.onChange} required />
                                <Label  for="dataAct">Data *</Label>
                                <AvField 
                                    type="date"
                                    name="dataAct"
                                   
                                    id="dataAct"
                                    placeholder="date placeholder"
                                    onChange={this.onChange}
                                    grid={{ xs: 6 }}
                                    required
                                />
                                <Label  for="universitate">Universitate</Label>
                                <AvField grid={{ xs: 8 }} type="text" name="universitate" id="universitate"
                                    value={localStorage.getItem('universitate')} required disabled
                                    
                                />

                                <AvField type="text" name="facultate" id="facultate" required label="Facultate" disabled
                                    value={localStorage.getItem('facultate')}
                                />
                                
                                <AvField  type="text" name="departament" id="departament" required label="Departament" disabled
                                    value={localStorage.getItem('departament')}/>

                                <AvField type="select" name="titluProiect" id="titluProiect" required label="Proiect *"
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
                                <Label  for="dataInceput">Dată Început *</Label>
                                <AvField
                                    type="date"
                                    name="dataInceput"
                                    id="dataInceput"
                                    placeholder="date placeholder"
                                    grid={{ xs: 8 }}
                                    onChange={e =>
                                        this.setState({
                                            dataInceput: e.target.value,
                                            validSfarsitDate: changeDate(e.target.value)
                                        })


                                    }
                                    required
                                />
                                <Label  for="dataSfarsit">Dată Sfârșit *</Label>
                                <AvField
                                    type="date"
                                    name="dataSfarsit"
                                    id="dataSfarsit"
                                    grid={{ xs: 8 }}
                                    placeholder="date placeholder"
                                    onChange={this.onChange}
                                    validate={{ dateRange: { format: 'YYYY-MM-DD', start: { value: this.state.dataInceput }, end: { value: this.state.validSfarsitDate } } }}
                                    required
                                />

                                <Button outline color="primary" type="submit" id="button-space" > Salvare</Button>

                            </AvForm>

                        </Col>

                    </Row>

                </Container>
            </div>
        );
    }
}
export default withRouter(ActAditional);