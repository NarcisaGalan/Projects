import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter,Table } from 'reactstrap';
import {getImprumutTable } from './apiBuget'
import '../commons/styles/common-style.css'

class ModalDetaliiImprumut extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      modal: false,
      tableData:[],
        
    }
    this.toggle = this.toggle.bind(this);
    this.handleClick = this.handleClick.bind(this);
}
static defaultProps = {
  idContract: 0
         
}


  toggle = () => {
    this.setState({modal: !this.state.modal})
  } 

  handleClick=()=>{
    getImprumutTable(this.props.idContract).then((tableData)=>
    this.setState({tableData}));
    this.toggle();
    
   
  }

  
render(){
  return (
    <div>
      <Button color="danger"  outline  onClick={this.handleClick}>{this.props.buttonLabel}</Button>
      <Modal size='xl' isOpen={this.state.modal} toggle={this.toggle} className={this.props.className}>
        <ModalHeader toggle={this.toggle}>{this.props.modalTitle}</ModalHeader>
        <ModalBody>
          <Table responsive id="table">
            <thead>
              <tr>
                <th>Numar Document</th>
                <th>Data Document</th>
                <th>Realizari anul</th>
                <th>Etapa proiect</th>
                <th>Valoare Totala</th>
                <th>Salarii </th>
                <th>Achizitii</th>
                <th>Deplasari</th>
                <th>Taxe</th>
                <th>Data Returnarii</th>
               
              </tr>
            </thead>
            <tbody>
              {this.state.tableData.map(data => (
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
 

export default ModalDetaliiImprumut;