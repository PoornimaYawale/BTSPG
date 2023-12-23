import React, { useState } from "react";
import "./NewNavbar.css";
import { Link, useNavigate } from "react-router-dom";

const NavbarBasic = () => {

    const auth = localStorage.getItem('user');
    const navigate = useNavigate();

    const logout = () => {
        localStorage.clear();
        window.location.href = '/';
        navigate("/login");
    }


    const [header, setheader] = useState(false);
    const chnagebg = () => {
        if (window.scrollY >= 100) {
            setheader(true);
        } else {
            setheader(false);
        }
    };
    window.addEventListener("scroll", chnagebg);
    return (
        <div>
            <header id="header" className={header ? "scroll header d-flex align-items-center fixed-top" : "header d-flex align-items-center fixed-top"} >

                {/* <header id="header" className="header d-flex align-items-center fixed-top"> */}
                <div className="container-fluid container-xl d-flex align-items-center justify-content-between">

                    <a  className="logo d-flex align-items-center">
                        <h1>BTSPG</h1>
                    </a>

                    <i className="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
                    <i className="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>
                    <nav className="navbar" >

                        <div id="navbarSupportedContent">

                            <ul className="d-flex " >
                                {auth ? <Link onClick={logout} to="/login">Logout</Link>
                                    : <>
                                        <Link className="nav-link" to="/login">Login</Link>
                                        <Link className="nav-link" to="/register">Register</Link>
                                    </>}</ul>
                </div>

            </nav>
        </div>
            </header >

        </div >
    );
}

export default NavbarBasic;