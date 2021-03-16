import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Table } from 'reactstrap';
import axios from 'axios'; 
import swal from 'sweetalert2';
import './raportContract.css'

class ModalImprumut extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      modal: false


    }
    this.toggle = this.toggle.bind(this);

 
  }
  static defaultProps = {
    tableData: []

  }
  toggle = () => {
    this.setState({ modal: !this.state.modal })
  }

  handleAprobare(id, aprobare) {
    const config = {
      headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
    };

    console.log(localStorage.getItem("token"))
  
    let data={}
    axios.put(`http://localhost:8443/cerereImprumut/update/${id}/${aprobare}`,data, config)
      .then(res => {
        console.log(res)
        swal.fire("Success", "Modificarea efectuata!", "success");
        setTimeout(() => {   window.location.reload(false);}, 2000);
      })
      .catch(err => {
        this.setState({ errorMessage: err.response.data });
        swal.fire("Error", err.response.data.errorMessage, "error");
      });



  }
 

  render() {
    return (
      <div>
        <Button color="danger" outline onClick={this.toggle}>Detalii</Button>
        <Modal size='xl' isOpen={this.state.modal} toggle={this.toggle} className="Modal">
          <ModalHeader toggle={this.toggle}>Imprumuturi</ModalHeader>
          <ModalBody>
            <Table responsive id="table">
              <thead>
                <tr>
                  <th>Numar Document</th>
                  <th>Data Document</th>
                  <th>Aprobare</th>
                  <th>Realizari anul</th>
                  <th>Etapa proiect</th>
                  <th>Valoare Totala</th>
                  <th>Salarii </th>
                  <th>Achizitii</th>
                  <th>Deplasari</th>
                  <th>Taxe</th>
                  <th>Data Returnarii</th>
                  <th>Seteaza Aprobare</th>
                </tr>
              </thead>
              <tbody>
                {this.props.tableData.map(data => (
                  <tr key={data.numar}>
                    <td>{data.numar}</td>
                    <td>{data.data}</td>
                    <td>{String(data.aprobata)}</td>
                    <td>{data.anul}</td>
                    <td>{data.etapa}</td>
                    <td>{data.valoareaTotala}</td>
                    <td>{data.salarii}</td>
                    <td>{data.achizitii}</td>
                    <td>{data.deplasari}</td>
                    <td>{data.taxe}</td>
                    <td>{data.dataReturnarii}</td>
                    <td>
                      <Button style={{ backgroundColor: '#790202' }} size="sm"  onClick={this.handleAprobare.bind(this, data.idCerereImprumut,'true')}>aprobată</Button>
                      <Button style={{ backgroundColor: '#694d4e' }} size="sm" onClick={this.handleAprobare.bind(this, data.idCerereImprumut,'false')}>neaprobată</Button>
                    </td>
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


export default ModalImprumut;