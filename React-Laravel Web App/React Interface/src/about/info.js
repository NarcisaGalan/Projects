import React from 'react';
import { Container, Jumbotron, Table } from 'reactstrap';
import img from '../commons/images/about.jpg';
import { withNamespaces } from 'react-i18next';
import { withRouter } from 'react-router-dom';
import axios from 'axios';
import './info.css'

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "650px",
    margin: 0,
};


class Info extends React.Component {
    constructor(props) {

        super(props);

        this.state = {
            error: null,
            comments: [],
            content: '',
            alert_message: '',
            userPhoto: '',
            userName: ''
        }

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

    }
    onChange(e) {
        this.setState({ [e.target.name]: e.target.value })
    }

    componentDidMount() {
        const apiUrl = 'http://localhost:8000/api/comments';

        fetch(apiUrl)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        comments: result.data,

                    });
                    console.log(result);
                },
                (error) => {
                    this.setState({ error });
                }
            )
        this.state.userName = localStorage.getItem("userName");
        this.state.userPhoto = localStorage.getItem("userPhoto");
        console.log(localStorage.getItem("userPhoto"));



    }
    onSubmit(e) {
        e.preventDefault()


        const newComment = {

            content: this.state.content,

        }


        const url = 'http://localhost:8000/api/comments';


        axios.post(url, newComment, {
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newComment),

        })
            .then(res => {
                console.log(res)
            });

        this.setState({
            alert_message: "success"
        });

        

    }

    render() {
        const { error, comments } = this.state;
        return (

            <div>

                <Jumbotron fluid style={backgroundStyle} id="backA">
                    <Container fluid >
                        <h1 id="titleA">{this.props.t("about.title")}</h1>
                        <h2 id="paragraphA">{this.props.t("about.des")}</h2>
                        <img src={img} width="150" height="200" />
                    </Container>
                </Jumbotron>

                <div>
                    <h1>Comments</h1>

                    <Table dark>
                        <thead>
                            <tr>
                                <th>User Photo</th>
                                <th>User Name</th>
                                <th>Comment</th>
                            </tr>
                        </thead>
                        <tbody>
                            {comments.map(comment => (
                                <tr key={comment.id}>
                                    <td>
                                        <img id = "img2"
                                            src={this.state.userPhoto}
                                            alt="new"
                                        />

                                    </td>
                                    <td>{this.state.userName}</td>
                                    <td>{comment.content}</td>
                                </tr>))}


                        </tbody>
                    </Table>



                    <div className="container">
                        <div className="row">
                            <div className="col-md-6 mt-5 mx-auto">
                                <form noValidate onSubmit={this.onSubmit}>
                                    <h1 className="h3 mb-3 font-weight-normal">
                                        Comments
                            </h1>

                                    <div className="form-group">
                                        <label htmlFor="content">Your comment</label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            name="content"
                                            placeholder="Add your comm here"
                                            value={this.state.content}
                                            onChange={this.onChange}
                                        />
                                    </div>

                                    <button
                                        type="submit"
                                        className="btn btn-lg btn-primary btn-block"
                                    >
                                        Add Comment
                            </button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        )
    }
};


export default withRouter(withNamespaces()(Info)) 