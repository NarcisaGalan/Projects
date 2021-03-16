import React, { Component } from "react";
import { incarcareFisier } from "./apiIncarcare";
import swal from 'sweetalert2';



export default class IncarcareFisier extends Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedFiles: null,
      currentFile: '',
    };

    this.selectFile = this.selectFile.bind(this);
    this.upload = this.upload.bind(this)
  }

  selectFile(event) {
    this.setState({
      selectedFiles: event.target.files,
    });
  }

  upload() {
    let currentFile = this.state.selectedFiles[0];
    let idContractTerti = this.props.idContractTerti;

    this.setState({
      currentFile: currentFile,
    });

    incarcareFisier(currentFile,idContractTerti)
      .then(res => {
        console.log(res)
        swal.fire("Succes", "Contractul a fost salvat", "success");
      })
      .catch(err => {
        this.setState({
          currentFile: undefined
        });
        swal.fire("Error","Ceva nu a mers bine, mai incercati!", "error");
      });


    this.setState({
      selectedFiles: undefined,
    });
  }


  render() {

    return (
      <div>
      
        <label className="btn btn-primary btn-sm">Alegeți PDF
          <input type="file" style={{ display: "none" }} onChange={this.selectFile} accept="application/pdf" />
        </label>

        <button className="btn btn-success btn-sm"
          disabled={!this.state.selectedFiles}
          onClick={() => { this.upload() }}
          
        >
          Încărcare
            </button>

      </div>
    );
  }

}