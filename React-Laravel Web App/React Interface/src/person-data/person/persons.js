import React from 'react';
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Card, Col, Row} from 'reactstrap';
import Table from "../../commons/tables/table"
import PersonForm from "./person-form";

import * as API_USERS from "./api/person-api"

const columns = [
    {
        Header:  'Name',
        accessor: 'name',
    },
    {
        Header: 'Email',
        accessor: 'email',
    },

];

const filters = [
    {
        accessor: 'name',
    },
    {
        accessor: 'email',
    }
];

class Persons extends React.Component {

    constructor(props){
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.state = {
            collapseForm: true,
            loadPage: false,
            errorStatus: 0,
            error: null
        };

        this.tableData = [];
    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }

    componentDidMount() {
        this.fetchPersons();
    }

    fetchPersons() {
        return API_USERS.getPersons((result, status, err) => {
            console.log(result);
           if(result !== null && status === 200) {
               result.forEach( x => {
                   this.tableData.push({
                       name: x.name,
                       email: x.email,
                   });
               });
               this.forceUpdate();
           } else {
               console.log("Am prins o eroare!!!");
               this.state.errorStatus = status;
               this.state.error = err;
               this.forceUpdate();
           }
        });
    }

    refresh(){
        this.forceUpdate()
    }

    render() {
        let pageSize = 5;
        return (
            <div>
                <Row>
                    <Col>
                        <Card body>
                            <Table
                                data={this.tableData}
                                columns={columns}
                                search={filters}
                                pageSize={pageSize}
                            />
                        </Card>
                    </Col>
                </Row>

                <Row>
                    <Col>
                        <Card body>
                            <div>
                                <PersonForm registerPerson={this.refresh}>

                                </PersonForm>
                            </div>
                        </Card>
                    </Col>
                </Row>

                {this.state.errorStatus > 0 &&
                <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}

            </div>
        );
    };

}

export default Persons;
