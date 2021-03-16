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
            idFunctie:this.props.data.idFunctie,
            functie:this.props.data.functie,
            dataInceput:this.props.data.dataInceput,
            dataSfarsit:this.props.data.dataSfarsit,
            incetareActivitate:this.props.data.incetareActivitate
          
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

        const functieUpdate = {
            idStatFunctii:this.state.idFunctie,
            functie:this.state.functie,
            dataInceput:this.state.dataInceput,
            dataSfarsit:this.state.dataSfarsit,
            incetareActivitate:this.state.incetareActivitate
        }
      
        const url = 'http://localhost:8443/statFunctii/update';
        axios.put(url, functieUpdate, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(functieUpdate),
        })
            .then(res => {
                console.log(res);
                Swal.fire("Succes", "Datele au fot modificate!", "success");
                
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
                    <ModalHeader toggle={this.toggle}>Modificare Functie</ModalHeader>
                    <ModalBody>
                        <AvForm onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>
                            <Label id="labelStyle">Functie</Label>
                            <AvField onChange={this.onChange} type="text" id="functie"value={this.state.functie} onChange={this.onChange} name="functie"  placeholder="Denumire beneficiar" required />

                            <Label id="labelStyle">Incetare Activitate</Label>
                            <AvField type="select" value={this.state.incetareActivitate} name="incetareActivitate"  id="incetareActivitate" onChange={this.onChange} grid={{ xs: 3 }}  required>
                                    <option> </option>
                                    <option>true</option>
                                    <option>false</option>
                                   
                                </AvField>
                            <Label id="labelStyle">Dată Început</Label>
                            <AvField name="dataInceput" id="dataInceput" value={this.state.dataInceput} onChange={this.onChange} type="date" required />
                            <Label id="labelStyle">Dată Sfarșit</Label>
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