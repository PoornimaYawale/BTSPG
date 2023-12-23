import axios from "axios";
import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { LoginContext } from "../../Contexts/LoginContext";
import NavbarBasic from "../NavbarComponent/NavbarBasic";

const Register = () => {
 
    const {user, setUser }= useContext(LoginContext);
    const navigate =useNavigate();
    const [adduser, setAdduser] = useState({
        userName: "",
        email: "",
        password: "",
        
    })

    const handleChange = (e) => {
        setAdduser((prevData) => ({...prevData, [e.target.name]: e.target.value }))
    }

    const AddUser = async(e)=>{
        e.preventDefault();
        try{
             const response = await axios.post("http://localhost:8081/api/user/create",adduser);
             setUser(response.data);
             toast.success("Register successfully!!!")
             navigate("/login");

        }catch(err){
            console.log(err.response.data.message);
        }
        console.log(adduser);
    }
    

    return (
        <>
        <NavbarBasic />
        <section className="ftco-section">
            <div className="container">

                <div className="row justify-content-center">
                    <div className="col-md-7 col-lg-5">
                        <div className="wrap">
                            <div className="img" style={{ 'backgroundImage': 'url(images/4561913_93904.jpg)' }}></div>
                            <div className="login-wrap p-4 p-md-5">
                                <div className="d-flex">
                                    <div className="w-100 h-100">
                                        <h3 className="mb-4">Register</h3>
                                    </div>

                                </div>
                                <form onSubmit={AddUser} className="signup-form">
										<div className="form-group mt-3">
											<input 
                                            type="text"
                                             className="form-control" 
                                             name="userName"
                                             value={adduser.userName}
                                             onChange={handleChange}
                                             required />
											<label className="form-control-placeholder" htmlFor="username">UserName<span style={{'color':'red'}} >*</span></label>
										</div>
                                        <div className="form-group mt-3">
											<input 
                                            type="text" 
                                            className="form-control" 
                                            name="email"
                                            value={adduser.email}
                                            onChange={handleChange}
                                            required />
											<label className="form-control-placeholder" htmlFor="username">Email<span style={{'color':'red'}} >*</span></label>
										</div>
                                       
										<div className="form-group">
											<input 
                                            id="password-field" 
                                            type="password" 
                                            className="form-control" 
                                            name="password"
                                            value={adduser.password}
                                            onChange={handleChange}
                                            required />
											<label className="form-control-placeholder" htmlFor="password">Password<span style={{'color':'red'}} >*</span></label>
											<span toggle="#password-field" className="fa fa-fw fa-eye field-icon toggle-password"></span>
										</div>
                                        {/* <div className="form-group">
											<input 
                                            id="password-field" 
                                            type="password" 
                                            className="form-control"
                                             required />
											<label className="form-control-placeholder" htmlFor="password"> Repeat Password<span style={{'color':'red'}} >*</span></label>
											<span toggle="#password-field" className="fa fa-fw fa-eye field-icon toggle-password"></span>
										</div> */}
                                        
                                        <p>By creating an account you agree to our <a href="/">Terms & Privacy</a>.</p>
										<div className="form-group">
											<button type="submit" className="form-control btn btn-primary rounded submit px-3">Register</button>
										</div>
										

                                        <div className="signin">
                                            <p>Already have an account? <Link to="/login">Sign in</Link>.</p>
                                        </div>
                                    
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        </>
    );
}

export default Register;
