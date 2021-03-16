import React from 'react';
import { withRouter } from 'react-router-dom';
import {Table, Label, Container} from 'reactstrap';
import '../commons/styles/common-style.css'
import {getContracteLunare} from './apiRaportAdmin'
import { AvForm, AvField } from 'availity-reactstrap-validation';
import ModalActeAditionale from '../raport-contract/modalActeAditionale'
import ModalRaportLunar from './modalRaportAdmin'
import moment from 'moment'





class RaportAdmin extends React.Component {

    constructor() {
        super();
        this.state = {
            
            contracte: [],
            alert_message: '',
            errors: {},
            dataLunara: null,
            startDate: new Date(),
            endDate: new Date()
        } 
        
        this.onChange = this.onChange.bind(this);
    }

    onChange(e) {
        var data = new Date(e.target.value);

        console.log(e.target.value)
        getContracteLunare(e.target.value).then((contracte) =>
            this.setState({ contracte }));

        var startDate =
            new Date(data.getFullYear(), data.getMonth(), 1);

        var ultimaZi =
            new Date(data.getFullYear(), data.getMonth() + 1, 0);

        startDate = moment(startDate).format('YYYY-MM-DD');
        ultimaZi = moment(ultimaZi).format('YYYY-MM-DD');

        this.setState({
            [e.target.name]: data,
            startDate: startDate,
            endDate: ultimaZi
        });

    }

   

    render() {

        return (
            <div id="pageBackground" >
                <Container fluid>
                <h1 id='pageTitle'>Contracte în derulare</h1>
                <AvForm>
                <Label id="top" for="numar">Data</Label>
                <h2 id="help-message">*Selectați o dată din luna pentru care doriți să vedeți raportul </h2>
                    <AvField
                        type="date"
                        name="dataLunara"
                        id="dataLunara"
                        placeholder="date placeholder"
                        grid={{ xs: 4 }}
                        onChange={this.onChange}
                        required
                    />
                </AvForm>
                <Label id="top" >Contracte în derulare</Label>
                <Table responsive striped bordered size="xxl"> 
                    <thead>
                        <tr>
                            <th>Număr</th>
                            <th>Data Contract</th>
                            <th>Tip</th>
                            <th>Titlu Proiect</th>
                            <th>Valoare</th>
                            <th>Valoare TVA</th>
                            <th>Moneda</th>
                            <th>Valoare în RON</th>
                            <th>Număr Pagini</th>
                            <th>Număr Exemplare</th>
                            <th>Număr Exemplare Beneficiar</th>
                            <th>Data început</th>
                            <th>Data Sfârșit</th>
                            <th>Partener Coordonator</th>
                            <th>Regie</th>
                            <th>Beneficiar</th>
                            <th>Acte Adiționale</th>
                            <th>Raport Lunar</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.contracte.map(contract => (
                            <tr key={contract.numarContract}>
                                <td>{contract.numarContract}</td>
                                <td>{contract.dataContract}</td>
                                <td>{contract.tip}</td>
                                <td>{contract.titlu}</td>
                                <td>{contract.valTotala}</td>
                                <td>{contract.valTVA}</td>
                                <td>{contract.moneda}</td>
                                <td>{contract.valInLei}</td>
                                <td>{contract.numarPagini}</td>
                                <td>{contract.numarDeExemplare}</td>
                                <td>{contract.exemplareBeneficiar}</td>
                                <td>{contract.dataInceput}</td>
                                <td>{contract.dataSfarsit}</td>
                                <td>{contract.partener}</td>
                                <td>{contract.regie}</td>
                                <td>{contract.numeBeneficiar}</td>
                                <td><ModalActeAditionale tableData={contract.actAditionalList} /></td>
                                <td><ModalRaportLunar buttonLabel="Raport" className="Modal"  idContract={contract.idContractTerti} startDate={this.state.startDate} endDate={this.state.endDate} modalTitle="Raport Lunar"/></td>
                               
                            </tr>
                        ))}
                    </tbody>
                </Table>
                </Container>
               
            </div>
        )
       
    }
}
export default withRouter(RaportAdmin);