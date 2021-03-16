import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter,Table } from 'reactstrap';
import './raportContract.css'

class ModalActAditional extends React.Component {
  constructor(props) {
    super(props);
    
    this.state = {
      modal: false,
      
    }
    
    this.toggle = this.toggle.bind(this);
    
}
static defaultProps = {
  tableData: []
         
}

toggle = () => {
    this.setState({modal: !this.state.modal})
  } 

render(){
  return (
    <div>
      <Button color="danger"  outline  onClick={this.toggle}>Detalii</Button>
      <Modal size='xl' isOpen={this.state.modal} toggle={this.toggle} className="Modal">
        <ModalHeader toggle={this.toggle}>Acte Aditionale</ModalHeader>
        <ModalBody>
          <Table responsive id="table">
            <thead>
              <tr>
                <th>Numar Document</th>
                <th>Data Document</th>
                <th>Data Inceput</th>
                <th>Data Sfarsit</th>
              </tr>
            </thead>
            <tbody>
              {this.props.tableData.map(data => (
                <tr key={data.numar}>
                  <td>{data.numar}</td>
                  <td>{data.data}</td>
                  <td>{data.dataInceput}</td>
                  <td>{data.dataSfarsit}</td>
               
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
 

export default ModalActAditional;