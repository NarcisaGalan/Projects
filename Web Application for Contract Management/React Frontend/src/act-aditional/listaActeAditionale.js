import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Table } from 'reactstrap';
import { getActeAditionale } from './api'
import '../commons/styles/common-style.css'
import EditActAditional from './editActAditional'

class ModalDetalii extends React.Component {
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
    getActeAditionale().then((tableData) =>
      this.setState({ tableData }));
    this.toggle();
  }


  render() {
    return (
      <div>
        <Button color="danger" outline onClick={this.handleClick}>{this.props.buttonLabel}</Button>
        <Modal size='xl' isOpen={this.state.modal} toggle={this.toggle} className={this.props.className}>
          <ModalHeader toggle={this.toggle}>{this.props.modalTitle}</ModalHeader>
          <ModalBody>
            <Table responsive id="table">
              <thead>
                <tr>
                <th>Numar Document</th>
                <th>Data Document</th>
                <th>Data Inceput</th>
                <th>Data Sfarsit</th>
                <th>Modifica</th>
                </tr>
              </thead>
              <tbody>
                {this.state.tableData.map(data => (
                  <tr key={data.idActAditional}>
                  <td>{data.numar}</td>
                  <td>{data.data}</td>
                  <td>{data.dataInceput}</td>
                  <td>{data.dataSfarsit}</td>
                  <td><EditActAditional data={data}/></td>
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


export default ModalDetalii;