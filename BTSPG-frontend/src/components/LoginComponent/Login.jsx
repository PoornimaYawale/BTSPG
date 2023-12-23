import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import './Login.css';
import { toast } from 'react-toastify';
import axios from 'axios';
import { useContext } from "react";
import { LoginContext } from "../../Contexts/LoginContext";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope } from '@fortawesome/free-solid-svg-icons';
import NavbarBasic from "../NavbarComponent/NavbarBasic";



const Login = () => {
	const { user, setUser } = useContext(LoginContext);


	const [email, emailupdate] = useState('');
	const [password, passwordupdate] = useState('');

	const usenavigate = useNavigate();

	const ProceedLogin = async (e) => {
		e.preventDefault();
		try {
			const response = await axios.post('http://localhost:8081/api/user/Login',{email,password});
         
			if (response.status === 200) {
				toast.success("Login Successfully")
				console.log(response.data)
				setUser(response.data);
				localStorage.setItem('user', JSON.stringify(response.data));
				usenavigate('/');
			}
		} catch (err) {
			toast.error('Login Failed due to:' + err.response.data.message);
		}
	}



	return (
		<div>
			<NavbarBasic />
			<section className="ftco-section">
				<div className="container">

					<div className="row justify-content-center">
						<div className="col-md-7 col-lg-5">
							<div className="wrap">
								<div className="img" style={{ 'backgroundImage': 'url(images/7652324.jpg)' }}></div>
								<div className="login-wrap p-4 p-md-5">
									<div className="d-flex">
										<div className="w-100 h-100" style={{marginTop:'2px'}}>
											<h3 className="mb-4">Sign In</h3>
										</div>

									</div>
									<form className="signin-form" onSubmit={ProceedLogin}>
										<div className="form-group mt-3">
											<input
												value={email}
												onChange={e => emailupdate(e.target.value)}
												id="email-field"
												name="email-field"
												type="text"
												className="form-control"
												required />
											<label className="form-control-placeholder" htmlFor="email">Email<span className="errmsg" style={{ 'color': 'red' }} >*</span></label>
											<span toggle="#email-field" className="fa  field-icon toggle-password"><FontAwesomeIcon icon={faEnvelope} /></span>
										</div>
										<div className="form-group">
											<input
												value={password}
												onChange={e => passwordupdate(e.target.value)}
												id="password-field"
												type="password"
												name="password"
												className="form-control"
												required />
											<label className="form-control-placeholder" htmlFor="password">Password<span className="errmsg" style={{ 'color': 'red' }} >*</span></label>
											<span toggle="#password-field" className="fa  field-icon toggle-password"></span>
										</div>
										<div className="form-group">
											<button type="submit" className="form-control btn btn-primary rounded submit px-3">Sign In</button>
										</div>
										<div className="form-group d-md-flex">
											<div className="w-50 text-left">
												<label className="checkbox-wrap checkbox-primary mb-0">
													<p>New User ? <Link data-toggle="tab" to="/register" >Sign Up</Link></p>
												</label>
											</div>

										</div>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	);
}

export default Login;