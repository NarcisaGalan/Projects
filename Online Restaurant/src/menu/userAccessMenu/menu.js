import React, { Component } from 'react';
import Modal from 'react-modal';
import { withRouter } from 'react-router-dom'
import { Button } from 'react-bootstrap'
import AllMenus from './allMenus';
import SuccessAlert from '../../reservation/ModalForRequest/SuccessAlert';
import ErrorAlert from '../../reservation/ModalForRequest/ErrorAlert';

import './MenuStyle.css'
import '../../principal/nav.css';
import '../../commons/styles/buttons.css';
import '../../reservation/styles/styleModal.css'
import '../../reservation/styles/styleSelect.css';

class Menu extends Component {

    constructor(props) {
        super(props)

        this.state = {
            idMenu: 0,
            details: '',
            price: 0.0,
            productId: 0,

            teams: [],
            isActive: false,
            validationError: "",
            alert_message: ''
        }

    }

   



    render() {
        return (
            <div id="backProducts">
                <section>
                    <AllMenus/>

                </section>
            </div>
        );
    }
}

export default withRouter(Menu);