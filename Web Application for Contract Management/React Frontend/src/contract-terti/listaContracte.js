import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Table } from 'reactstrap';
import '../commons/styles/common-style.css'
import {getContracteByUser} from '../commons/api/api'
import EditContract from './editContract'

class ListaContracte extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      modal: false,
      tableData: [],

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
    getContracteByUser(localStorage.getItem("userID")).then((tableData) => {
        this.setState({ tableData });
    });
    this.toggle();
  }


  render() {
    return (
      <div>
        <Button color="danger"  outline onClick={this.handleClick} id="top">Modificare Contract</Button>
        <Modal size='xl' isOpen={this.state.modal} toggle={this.toggle} className={this.props.className}>
          <ModalHeader toggle={this.toggle}>Modificare Contract</ModalHeader>
          <ModalBody>
            <Table responsive id="table">
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
                                <th>Modifica</th>
                            </tr>
                        </thead>
              <tbody>
              {this.state.tableData.map(contract => (
                            <tr key={contract.idContractTerti}>
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
                                <td><EditContract data={contract}/></td>
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


export default ListaContracte;