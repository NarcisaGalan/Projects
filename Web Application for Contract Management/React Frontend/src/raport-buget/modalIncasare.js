import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter,Table } from 'reactstrap';
import {getIncasariTable } from './apiBuget'
import '../commons/styles/common-style.css'

class ModalDetaliiIncasare extends React.Component {
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
    getIncasariTable(this.props.idContract).then((tableData)=>
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
                <th>Tip Document</th>
                <th>Valoare Incasare RON</th>
                <th>Valoare Incasare</th>
                <th>Valoare TVA </th>
                <th>Moneda</th>
                <th>Explicatii</th>
                <th>Incadrare Incasare</th>
              </tr>
            </thead>
            <tbody>
              {this.state.tableData.map(data => (
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
        </ModalBody>
        <ModalFooter>
          <Button color="primary" onClick={this.toggle}>Închide</Button>
        </ModalFooter>
      </Modal>
    </div>
  );
}

}
 

export default ModalDetaliiIncasare;