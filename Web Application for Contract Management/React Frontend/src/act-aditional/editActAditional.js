import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Label } from 'reactstrap';
import { AvForm, AvField } from 'availity-reactstrap-validation';
import axios from 'axios';
import Swal from 'sweetalert2';


class EditActAditional extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modal: false,
            numarDocument:this.props.data.numar,
            dataDocument:this.props.data.data,
            dataInceput:this.props.data.dataInceput,
            dataSfarsit:this.props.data.dataSfarsit,
          
        }
        this.toggle = this.toggle.bind(this);
        this.onChange = this.onChange.bind(this);

    }
 

    onChange(e) {

        this.setState({ [e.target.name]: e.target.value })
        console.log(e.target.name,e.target.value)
       
    }

    toggle = () => {
        this.setState({ modal: !this.state.modal })
    }

    handleValidSubmit = () =>  {

        const act = {
            idActAditional:this.props.data.idActAditional,
            numar: this.state.numarDocument,
            data: this.state.dataDocument,
            dataInceput: this.state.dataInceput,
            dataSfarsit: this.state.dataSfarsit
        }
        const url = 'http://localhost:8443/act-aditional/edit';
        axios.put(url, act, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(act),
        })
            .then(res => {
                console.log(res);
                Swal.fire("Succes", "Actul Aditional a fost modificat!", "success");
                
            })
            .catch(err => {
              
                Swal.fire("Eroare", "Ceva nu a mers bine", "error")
                
            })



            setTimeout(() => {   window.location.reload(false);}, 2000);
    }

    handleInvalidSubmit(event, errors, values) {
        Swal.fire("Eroare", "Formularul a fost completat gresit!Verificati tot inca o data", "error")
      
        this.setState({ error: true });
        return;

    }


    render() {
        return (
            <div>
                <Button color="danger" outline onClick={this.toggle}>Modifica</Button>
                <Modal size='m' isOpen={this.state.modal} toggle={this.toggle} className="Modal">
                    <ModalHeader toggle={this.toggle}>Modificare Act Aditional</ModalHeader>
                    <ModalBody>
                        <AvForm onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>
                            <Label id="labelStyle">Număr Document</Label>
                            <AvField onChange={this.onChange} type="text" id="numarDocument"value={this.state.numarDocument} onChange={this.onChange} name="numarDocument"  placeholder="Denumire beneficiar" required />

                            <Label id="labelStyle">Dată Document</Label>

                            <AvField id="dataDocument" name="dataDocument" value={this.state.dataDocument} onChange={this.onChange} type="date" required />
                            <Label id="labelStyle">Dată Început</Label>

                            <AvField name="dataInceput" id="dataInceput" value={this.state.dataInceput} onChange={this.onChange} type="date" required />
                            <Label id="labelStyle">Dată SfÂrșit</Label>
                            <AvField name="dataSfarsit" id="dataSfarsit" value={this.state.dataSfarsit} onChange={this.onChange} type="date" required />
                            <Button style={{ backgroundColor: '#790202' }} size="sm" type="submit">Modifica</Button>
                        

                        </AvForm>

                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.toggle}>Închide</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }

}


export default EditActAditional ;