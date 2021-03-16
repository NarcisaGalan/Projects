import React from 'react';
import { withRouter } from 'react-router-dom';
import { Button,Table } from 'reactstrap';
import './raportContract.css'
import {getContracteByUser} from '../commons/api/api'
import ModalActeAditionale from './modalActeAditionale'
import ModalImprumut from './modalImprumut'
import ModalBeneficiar from './modalBeneficiar'
import "bootstrap/dist/css/bootstrap.min.css";
import IncarcareFisier from '../incarcare-fisiere/incarcare'
import axios from 'axios';


class RaportContract extends React.Component {
    constructor() {
        super();
        this.state = {
            
            contracte: [],
            alert_message: '',
            errors: {}
        }
        
       
        this.handleDescarcare = this.handleDescarcare.bind(this);
    }

    componentDidMount() {/* toate contractele by user */

        getContracteByUser(localStorage.getItem("userID")).then((contracte) => {
            this.setState({ contracte });
        });
    }
    
    handleDescarcare  (idContractTerti, titlu) {
        axios({
            url: `http://localhost:8443/fisiere/${idContractTerti}`,
            method: 'GET',
            responseType: 'blob', 
            headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
          }).then((response) => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', titlu+'.pdf');
            document.body.appendChild(link);
            link.click();
          });

        
    }

    render() {

        return (
            <div id="pageBackground" style={{marginBottom:"40px"}}>
                <h1 id='pageTitle'>Raport Contracte</h1>
                <Table responsive striped bordered size="xxl"> 
                    <thead>
                        <tr>
                            <th>Număr</th>
                            <th>Data Contract</th>
                            <th>Tip</th>
                            <th>Titlu Proiect</th>
                            <th>Valoare</th>
                            <th>Moneda</th>
                            <th>Data Încheierii</th>
                            <th>Număr Pagini</th>
                            <th>Număr Exemplare</th>
                            <th>Număr Exemplare Beneficiar</th>
                            <th>Data început</th>
                            <th>Data Sfârșit</th>
                            <th>Partener Coordonator</th>
                            <th>Regie</th>
                            <th>TVA</th>
                            <th>Descarcare PDF</th>
                            <th>Acte Adiționale</th>
                            <th>Beneficiar</th>
                            <th>Cereri Împrumut</th>
                            <th>Încarcare Contract</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.contracte.map(contract => (
                            <tr key={contract.numar}>
                                <td>{contract.numar}</td>
                                <td>{contract.data}</td>
                                <td>{contract.tip}</td>
                                <td>{contract.titluProiect}</td>
                                <td>{contract.valoare}</td>
                                <td>{contract.moneda}</td>
                                <td>{contract.dataIncheierii}</td>
                                <td>{contract.nrPagini}</td>
                                <td>{contract.nrExemplare}</td>
                                <td>{contract.nrExemplareBeneficiar}</td>
                                <td>{contract.dataInceput}</td>
                                <td>{contract.dataSfarsit}</td>
                                <td>{contract.coordonatorPartener}</td>
                                <td>{contract.regie}</td>
                                <td>{contract.tva}</td>
                                <td><Button color="primary" onClick={() => { this.handleDescarcare(contract.idContractTerti,contract.titluProiect) }}>Descarcare</Button></td>
                                <td><ModalActeAditionale tableData={contract.actAditionalLista} /></td>
                                <td><ModalBeneficiar idContract={contract.idContractTerti}/> </td>
                                <td><ModalImprumut tableData={contract.cerereImprumutLista}/></td>
                                <td><IncarcareFisier idContractTerti={contract.idContractTerti}/></td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
                
            </div>
        )
       
    }
}

export default withRouter(RaportContract);