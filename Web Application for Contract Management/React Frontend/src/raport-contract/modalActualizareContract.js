import { getBeneficiarByContract } from '../commons/api/api'
import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Label } from 'reactstrap';
import { AvForm, AvField, AvGroup } from 'availity-reactstrap-validation';
import { getCheltuileiTable } from './apiRaportContract'
import './raportContract.css'

class ModalActualizareContract extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            modal: false,
            beneficiar:{denumire: ''},
            adresa:{tara: ''},
            banca:[{cont:''}],
            
        }

        this.toggle = this.toggle.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.onChange=this.onChange.bind(this);
    }
    static defaultProps = {
        contract: {numar: ''}

    }

    toggle = () => {
        
        this.setState({ modal: !this.state.modal })
    }

    handleClick = () => {
        
        getBeneficiarByContract(this.props.idContract).then((beneficiar)=>{
            this.setState({ beneficiar:beneficiar,
                            adresa:beneficiar.adresa,
                            banca:beneficiar.banci}, () => {
                console.log(this.state.adresa.tara);
                this.toggle();
              });
        
        });
        
        
    }
    onChange(e) {
        this.setState({ [e.target.name]: e.target.value })
    }

    render() {
        return (
            <div>
                <Button color="danger" outline onClick={this.handleClick}>Actualizare</Button>
                <Modal size='xl' isOpen={this.state.modal} toggle={this.toggle} className="Modal">
                    <ModalHeader toggle={this.toggle}>{this.props.contractData.titluProiect}</ModalHeader>
                    <ModalBody>
                        <AvForm>
                            <Label id="labelStyle">Denumire operator economic(Beneficiar)</Label>
                            <AvField onChange={this.onChange} type="text" id="numeBeneficiar"  name="numeBeneficiar" value={this.state.beneficiar.denumire} placeholder="Denumire beneficiar" required />

                            <Label id="labelStyle">Contact Beneficiar</Label>

                            <AvField name="numarTelefon" value={this.state.beneficiar.telefon} placeholder="Numar de Telefon" type="text" required />

                            <AvField name="adresaEmailBeneficiar" value={this.state.beneficiar.adresaEmail} placeholder="Email beneficiar" type="email" required />

                            <Label id="labelStyle">Adresa Beneficiar</Label>

                            <AvField name="tara" id="tara" value={this.state.adresa.tara} placeholder="Tara" type="text" required />

                            <AvField name="judet" value={this.state.adresa.judet} placeholder="Judet" type="text" maxLength="2" pattern="^[A-Z]*$" required />

                            <AvField name="localitate" id="localitate" value={this.state.adresa.localitate} placeholder="Oras/Localitate" type="text" required />

                            <AvField name="strada" id="strada" value={this.state.adresa.strada} placeholder="Strada" type="text" required />

                            <AvField name="numarStrada" type="text" value={this.state.adresa.detaliiAdresaNr} placeholder="Numar Strada" maxLength="6" required />

                            <Label id="labelStyle">Reprezentant Beneficiar</Label>

                            <AvField name="reprezentant" id="reprezentant" value={this.state.beneficiar.reprezentant} placeholder="Reprezentant beneficiar" type="text" required />

                            <AvField name="functieReprezentant" id="functieReprezentant" value={this.state.beneficiar.functieReprezentant} placeholder="Functie Reprezentant" type="text" required />

                            <Label id="labelStyle">CIF/CUI Beneficiar</Label>

                            <AvField name="cif" value={this.state.beneficiar.cifCui} placeholder="CIF/CUI" type="text" maxLength="20" disabled required />
                            <Label id="labelStyle">Banca aferenta contului bancar</Label>
                            <AvField name="banca" id="banca" value={this.state.banca[0].nume} placeholder="Banca Aferenta Contului" type="text" maxLength="20" required />
                            <AvField name="cont" id="cont" value={this.state.banca[0].contBancar} placeholder="Cont Bancar" type="text" maxLength="20" required />
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


export default ModalActualizareContract;
