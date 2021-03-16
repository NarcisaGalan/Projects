import React from 'react';
import { getContracteByUser } from '../commons/api/api'
import { withRouter } from 'react-router-dom';
import { Col, Form, FormGroup, Label, Input, Container} from 'reactstrap';
import '../commons/styles/common-style.css'
import { AvForm, AvField, AvGroup } from 'availity-reactstrap-validation';
import { getRaportBuget } from './apiBuget'
import ModalDetalii from './modalCheltuieli'
import ModalDetaliiImprumut from './modalImprumut'
import ModalDetaliiIncasare from './modalIncasare'

class Buget extends React.Component {

    constructor() {
        super()
        this.state = {

            contracte: [],
            beneficiar: { denumire: '' },

            raport: {
                numeProiect: '',
                valoareContract: 0.0,
                valoareCheltuita: 0.0,
                valoareImprumutata: 0.0,
                valoareIncasata: 0.0,
                valoareDeIncasat: 0.0,
            },


            errorMessage: '',
            errors: {}
        }

        this.onChange = this.onChange.bind(this);
        this.computeRaportValues = this.computeRaportValues.bind(this);

    }

    onChange(e) {

        this.setState({ [e.target.name]: e.target.value })

    }
    componentDidMount() {/* toate universitatile */
        
        getContracteByUser(localStorage.getItem("userID")).then((contracte) => {
            this.setState({ contracte });
            this.setState({ contractValid: true });
        });
    
    }
    computeRaportValues(idContract) {
        if (this.state.contractValid) {
            getRaportBuget(idContract).then((raport) =>
                this.setState({ raport })
            )
          
        } else {
            console.log("contractValid in get:  ", this.state.contractValid);
            setTimeout(this.computeRaportValues, 1000);
        }

    }

 

    render() {

        return (
            <div id="pageBackground" style={{marginBottom:"40px"}} >
                <h1 id="pageTitle">Raport Buget</h1>
               
                <Container fluid >
                    <AvForm  >
                    <AvGroup row>
                        <Col sm={9}>
                            <Label id="top" for="numarContract">Numar Contract: *</Label>
                            <AvField type="select" name="numarContract" id="numarContract"
                                required
                                grid={{ xs: 4 }}
                                onChange={e => (
                                    this.setState({
                                        idContract: e.target.value,
                                        contractValid: true

                                    }),
                                    this.computeRaportValues(e.target.value)

                                )}>
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
                                <h2 id="subtitleCommon" style={{ textAlign: 'center' }}>Proiect: {this.state.raport.numeProiect}  </h2>
                                <h3  style={{ textAlign: 'center' , fontFamily: 'Georgia, Times New Roman, Times, serif',fontSize:'15px' }}>Valoare: {this.state.raport.valoareContract} {this.state.raport.monedaContract}</h3>
                            </Col>
                            <Col sm={2}>
                                <iframe style={{ width: '200px', height: '95px' }} title="Curs Valutar" frameBorder="0" marginWidth="0" marginHeight="0" scrolling="no" src="https://www.cursbnr.ro/insert/cursvalutar.php?w=200&b=f7f7f7&bl=dcdcdc&ttc=0a6eab&tc=000000&diff=1&ron=1&cb=1&pics=1"></iframe>

                            </Col>

                        </AvGroup>
                    </AvForm>

              
                    <Form>
                        <FormGroup row>
                            <Label for="valoareContract" sm={2}>Valoare Contract - RON</Label>
                            <Col sm={3}>
                                <Input type="text" name="valoareContract" id="valoareContract" value={this.state.raport.valoareContractInLei} disabled />
                            </Col>
                            <Label style={{marginLeft:'200px'}}>Profit - RON</Label>
                            <Col sm={4}>
                               <Input type="text" name="profit" id="profit" value={this.state.raport.profitInLei}  disabled />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label for="valoareCheltuita" sm={2}>Valoare Cheltuita - RON</Label>
                            <Col sm={3}>
                                <Input type="number" name="valoareCheltuita" id="valoareCheltuita" value={this.state.raport.valoareCheltuitaInLei} disabled />
                            </Col>
                            <Col>
                                <ModalDetalii buttonLabel="Cheltuieli"  idContract={this.state.idContract}  className="Modal" modalTitle="Cheltuieli Inregistrate"/>
                              
                            </Col>
                        </FormGroup>

                        <FormGroup row>
                            <Label for="valoareImprumutata" sm={2}>Valoare Imprumutata - RON</Label>
                            <Col sm={3}>
                                <Input type="number" name="valoareImprumutata" id="valoareImprumutata" value={this.state.raport.valoareImprumutata} disabled />
                            </Col>
                            <Col>
                                <ModalDetaliiImprumut buttonLabel="Imprumuturi" className="Modal"  idContract={this.state.idContract}  modalTitle="Imprumuturi aprobate"/>
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label for="examplePassword" sm={2}>Valoare Incasata - RON</Label>
                            <Col sm={3}>
                                <Input type="text" name="valoareIncasata" id="valoareIncasata" value={this.state.raport.valoareIncasataInLei} disabled />
                            </Col>
                            <Col>
                                 <ModalDetaliiIncasare buttonLabel="Incasari" className="Modal"  idContract={this.state.idContract}    modalTitle="Incasari inregistrate"/>
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label for="examplePassword" sm={2}>Valoare de Incasat - RON</Label>
                            <Col sm={3}>
                                <Input type="text" name="valoareDeIncasat" id="valoareDeIncasat" value={this.state.raport.valoareDeIncasatInLei} disabled />
                            </Col>
                            
                        </FormGroup>

                    </Form>
                    <h1 id="help-message">  
                    *Fiecare cheltuială/încasare se convertește în RON atunci când este adăugată în aplicație, cu valoarea cursului valutar din data egală cu data documentului.
                </h1>
                </Container>
               
                
               
              
            </div>
        );
    }
}
export default withRouter(Buget);