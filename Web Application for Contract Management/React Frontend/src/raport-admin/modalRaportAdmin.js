import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Table } from 'reactstrap';
import { getRaportLunar } from './apiRaportAdmin'
import '../commons/styles/common-style.css'
import Label from 'reactstrap/lib/Label';

class ModalRaportLunar extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            modal: false,
            tableData: {
                "cheltuieli": [],
                "incasari": [],
                "cereriImprumut": []
            },

        }

        this.toggle = this.toggle.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }
    static defaultProps = {
        idContract: 0
    }

    toggle = () => {
        this.setState({ modal: !this.state.modal })
    }

    handleClick = () => {
        getRaportLunar(this.props.idContract, this.props.startDate, this.props.endDate).then((tableData) =>
            this.setState({ tableData }));
            console.log("handleData"+this.state.tableData)
        this.toggle();
    }


    render() {
     
        return (
            <div>
                <Button color="danger" outline onClick={this.handleClick}>{this.props.buttonLabel}</Button>
                <Modal size='xl' isOpen={this.state.modal} toggle={this.toggle} className={this.props.className}>
                    <ModalHeader toggle={this.toggle}>{this.props.modalTitle}</ModalHeader>
                    <ModalBody>
                        <Label id="top">Cheltuieli</Label>
                        <Table responsive id="table">
                            <thead>
                                <tr>
                                    <th>Număr Document</th>
                                    <th>Dată Document</th>
                                    <th>Tip Document</th>
                                    <th>Valoare Cheltuială RON</th>
                                    <th>Valoare Cheltuială</th>
                                    <th>Valoare TVA </th>
                                    <th>Monedă</th>
                                    <th>Explicații</th>
                                    <th>Încadrare Cheltuială</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.tableData.cheltuieli.map(data => (
                                    <tr key={data.nrDocument}>
                                        <td>{data.nrDocument}</td>
                                        <td>{data.dataDocument}</td>
                                        <td>{data.tipDocument}</td>
                                        <td>{data.valoareInLei}</td>
                                        <td>{data.valoareCheltuiala}</td>
                                        <td>{data.valoareTva}</td>
                                        <td>{data.moneda}</td>
                                        <td>{data.explicatii}</td>
                                        <td>{data.incadrareCheltuiala}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                        <Label id="top">Încasări</Label>
                        <Table responsive id="table">
                            <thead>
                                <tr>
                                    <th>Număr Document</th>
                                    <th>Dată Document</th>
                                    <th>Tip Document</th>
                                    <th>Valoare Încasare RON</th>
                                    <th>Valoare Încasare</th>
                                    <th>Valoare TVA </th>
                                    <th>Monedă</th>
                                    <th>Explicații</th>
                                    <th>Încadrare Încasare</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.tableData.incasari.map(data => (
                                    <tr key={data.nrDocument}>
                                        <td>{data.nrDocument}</td>
                                        <td>{data.dataDocument}</td>
                                        <td>{data.tipDocument}</td>
                                        <td>{data.valoareInLei}</td>
                                        <td>{data.valoareIncasata}</td>
                                        <td>{data.valoareTVA}</td>
                                        <td>{data.moneda}</td>
                                        <td>{data.explicatii}</td>
                                        <td>{data.incadrareIncasare}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                        <Label id="top">Cereri de Împrumut</Label>
                        <Table responsive id="table">
                            <thead>
                                <tr>
                                    <th>Număr Document</th>
                                    <th>Dată Document</th>
                                    <th>Realizări anul</th>
                                    <th>Etapă proiect</th>
                                    <th>Valoare Totală</th>
                                    <th>Salarii </th>
                                    <th>Achiziții</th>
                                    <th>Deplasări</th>
                                    <th>Taxe</th>
                                    <th>Data Returnării</th>
                                    <th>Aprobată</th>

                                </tr>
                            </thead>
                            <tbody>
                                {this.state.tableData.cereriImprumut.map(data => (
                                    <tr key={data.numar}>
                                        <td>{data.numar}</td>
                                        <td>{data.data}</td>
                                        <td>{data.anul}</td>
                                        <td>{data.etapa}</td>
                                        <td>{data.valoareaTotala}</td>
                                        <td>{data.salarii}</td>
                                        <td>{data.achizitii}</td>
                                        <td>{data.deplasari}</td>
                                        <td>{data.taxe}</td>
                                        <td>{data.dataReturnarii}</td>
                                        <td>{String(data.aprobata)}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>

                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.toggle}>Închide</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }

}


export default ModalRaportLunar;