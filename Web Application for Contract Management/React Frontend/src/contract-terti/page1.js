import React from 'react';
import { withRouter } from 'react-router-dom';
import { Button, Label, Row, Col, Container } from 'reactstrap';
import '../commons/styles/common-style.css'
import { AvForm, AvField } from 'availity-reactstrap-validation';
import swal from 'sweetalert2';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import { search } from "./searchRequests";
import { saveBeneficiar } from './api';
import ListaContracte from './listaContracte'



class Page1 extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            universitate: '',
            numeBeneficiar: '',
            numarTelefon: '',
            adresaEmailBeneficiar: '',
            tara: '',
            judet: '',
            localitate: '',
            strada: '',
            numarStrada: '',
            reprezentant: '',
            functieReprezentant: '',
            cif: '',
            cont: '',
            banca: '',

            searchNumeBeneficiar: '',
            searchCIF: '',

            beneficiarId: 0,
            beneficiarExistent: false,

            loading: false,
           
            universitati: [],
            beneficiari:[],
            cifDisabledField: false,


            errors: {}
        }

        this.onChange = this.onChange.bind(this)
        this.handleValidSubmit = this.handleValidSubmit.bind(this)
        this.handleInvalidSubmit = this.handleInvalidSubmit.bind(this);
        this.handleSearchBeneficiar = this.handleSearchBeneficiar.bind(this);
    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value })
        console.log(e.target.name, e.target.value);
    }

    search = async val => {
        this.setState({ loading: true });
        const results = await search(
          `http://localhost:8443/beneficiar/searchByDenumireContaining/${val}`
        );
       console.log(results);
       if (typeof results !== 'undefined'){
        this.setState({ beneficiari: results, loading: false });
       }
    
      };

      onChangeHandler = async e => {
        this.search(e.target.value);
      };

    
    handleSearchBeneficiar(beneficiar) {
       
            if (beneficiar) {
                this.setState({cifDisabledField:true});
               
                    this.setState({
                        numeBeneficiar: beneficiar.denumire,
                        numarTelefon: beneficiar.telefon,
                        adresaEmailBeneficiar: beneficiar.adresaEmail,
                        reprezentant: beneficiar.reprezentant,
                        functieReprezentant: beneficiar.functieReprezentant,
                        tara: beneficiar.adresa.tara,
                        judet: beneficiar.adresa.judet,
                        localitate: beneficiar.adresa.localitate,
                        strada: beneficiar.adresa.strada,
                        numarStrada: beneficiar.adresa.detaliiAdresaNr,
                        cif:beneficiar.cifCui,
                        banca: beneficiar.banci[0].nume,
                        cont: beneficiar.banci[0].contBancar,
                        beneficiarId: beneficiar.idBeneficiar,
                       
                    });
                
                
                
            } else {

                this.setState({
                    cifDisabledField:false,
                    numeBeneficiar: '',
                    numarTelefon: '',
                    adresaEmailBeneficiar: '',
                    reprezentant: '',
                    functieReprezentant: '',
                    tara: '',
                    judet: '',
                    localitate: '',
                    strada: '',
                    numarStrada: '',
                    cif: '',
                    banca: '',
                    cont: '',
                    beneficiarExistent: false
                });
            }
        }
       
    
  
    handleValidSubmit(e) {

        
        const beneficiar = {
            numeBeneficiar: this.state.numeBeneficiar,
            numarTelefon: this.state.numarTelefon,
            adresaEmailBeneficiar: this.state.adresaEmailBeneficiar,
            tara: this.state.tara,
            judet: this.state.judet,
            localitate: this.state.localitate,
            strada: this.state.strada,
            numarStrada: this.state.numarStrada,
            reprezentant: this.state.reprezentant,
            functieReprezentant: this.state.functieReprezentant,
            cif: this.state.cif,
            cont: this.state.cont,
            banca: this.state.banca,
            idBeneficiar: this.state.beneficiarId,
            beneficiarExistent: this.state.beneficiarExistent
        }
      
        if (window.confirm('Datele sunt completate corect?Sunteti sigur ca vreti sa salvati Beneficiarul?')){
            localStorage.setItem("cifCUIBeneficiar",this.state.cif);
            saveBeneficiar(beneficiar);
            swal.fire("Succes", "Beneficiarul a fost salvat", "success");
        }
        
        
        
    }
    handleInvalidSubmit(event, errors, values) {
        swal.fire("Error", "Formularul a fost completat gresit!Verificati tot inca o data", "error");
        this.setState({ error: true });
        return;

    }

  
    render() {

        return (
            <div id="pageBackground2" >

                <Container fluid >

                    <Row > 
                        <Col md={{ size: 6, offset: 1 }} >
                       
                            <AvForm id='avForm' onValidSubmit={this.handleValidSubmit} onInvalidSubmit={this.handleInvalidSubmit}>
                                <Label id="top">Părti Contractate</Label>
                                <br />
                                <Label for="universitate">Universitate</Label>
                                <AvField grid={{ xs: 8 }} type="text" name="universitate" id="universitate" required 
                                    value={localStorage.getItem('universitate')} disabled
                                />

                                <AvField onChange={this.onChange} type="text" id="numeBeneficiar" label="Denumire operator economic(Beneficiar) *" name="numeBeneficiar" value={this.state.numeBeneficiar} placeholder="Denumire beneficiar" required />


                                <Label id="labelStyle">Contact Beneficiar</Label>
                               
                                <AvField onChange={this.onChange} name="numarTelefon" value={this.state.numarTelefon} placeholder="Număr de Telefon *" type="text" grid={{ xs: 6 }}  required />

                                <AvField onChange={this.onChange} name="adresaEmailBeneficiar" value={this.state.adresaEmailBeneficiar} placeholder="Email beneficiar *" type="email" required />

                                <Label id="labelStyle">Adresă Beneficiar</Label>

                                <AvField onChange={this.onChange} name="tara" id="tara" value={this.state.tara} placeholder="Tară *" type="text" grid={{ xs: 6 }} required />
                                <h2 id="help-message">Abreviere județ(BN, CJ, AB etc)</h2>
                                <AvField onChange={this.onChange} name="judet" value={this.state.judet} placeholder="Județ *" type="text" maxLength="2" pattern="^[A-Z]*$" grid={{ xs: 3 }}  required />

                                <AvField onChange={this.onChange} name="localitate" id="localitate" value={this.state.localitate} placeholder="Oraș/Localitate *" type="text" grid={{ xs: 6 }}  required />

                                <AvField onChange={this.onChange} name="strada" id="strada" value={this.state.strada} placeholder="Strada *" type="text" grid={{ xs: 8 }}  required />

                                <AvField onChange={this.onChange} name="numarStrada" type="text" value={this.state.numarStrada} placeholder="Număr Stradă *" maxLength="6" grid={{ xs: 4 }}  required />

                                <Label id="labelStyle">Reprezentant Beneficiar *</Label>

                                <AvField onChange={this.onChange} name="reprezentant" id="reprezentant" value={this.state.reprezentant} placeholder="Reprezentant beneficiar" type="text" required />

                                <AvField onChange={this.onChange} name="functieReprezentant" id="functieReprezentant" value={this.state.functieReprezentant} placeholder="Funcție Reprezentant" type="text" grid={{ xs: 6 }}  required />

                                <Label id="labelStyle">CIF/CUI Beneficiar *</Label>

                                <AvField onChange={this.onChange} name="cif" value={this.state.cif} placeholder="CIF/CUI" type="text" maxLength="20" disabled={(this.state.cifDisabledField)} grid={{ xs: 6 }}  required />
                                <Label id="labelStyle">Banca aferentă contului bancar Beneficiar *</Label>
                                <AvField onChange={this.onChange} name="banca" id="banca" value={this.state.banca} placeholder="Banca Aferentă Contului" type="text" maxLength="20" grid={{ xs: 8 }}  required />
                               
                                <AvField onChange={this.onChange} name="cont" id="cont" value={this.state.cont} placeholder="Cont Bancar" type="text" maxLength="20" grid={{ xs: 8 }}  required />

                                 <Button className="btn btn-primary float-right " type="submit" id="btn"> Salvare Beneficiar</Button>
                                </AvForm>


                        </Col>

                        <Col md={5} >
                           
                        <ListaContracte />

                            <Label id="top">Căutare Beneficiar Existent</Label>    
                            <Autocomplete
                            options={this.state.beneficiari}
                            getOptionLabel={(option)=> option.denumire}
                           
                            style={{ width: 300, height:40,marginTop:15 }}
                               
                            renderInput={params => (
                                    <TextField {...params} label="Denumire" variant="outlined" fullWidth  onChange={(event, val)  => this.onChangeHandler(event)}/>
                            )}
                            onChange={(event, val)  => {this.handleSearchBeneficiar(val)}}
                                
                            />
 
                        </Col>

                    </Row>

                </Container>
               
               
            </div>

        );
    }
}
export default withRouter(Page1);