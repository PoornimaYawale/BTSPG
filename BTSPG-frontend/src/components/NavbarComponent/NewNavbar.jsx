import React, { useState } from "react";
import "../UserComponents/HomeComponent/NewHome.css";
import { Link, useNavigate } from "react-router-dom";

const NewNavbar = () => {


  const auth = localStorage.getItem('user');
  const navigate = useNavigate();
  const logout = () => {
    localStorage.clear();
    window.location.href = '/';
    navigate("/login");
  }
  const [header, setHeader] = useState(false);
  const chnagebg = () => {
    if (window.scrollY >= 100) {
      setHeader(true);
    } else {
      setHeader(false);
    }
  };
  window.addEventListener("scroll", chnagebg);
  
  function scrollRoomlist() {
    if (window.scrollY < 400) {

      window.scrollBy({
        top: 500,
        behavior: 'smooth'
      });
    }
    else if (window.scrollY !== 0) {
      window.scrollBy(100, -window.scrollY);
    }
  }
  function scrollContact() {
    if (window.scrollY < 400) {

      window.scrollBy({
        top: 2500,
        behavior: 'smooth'
      });
    }
    else if (window.scrollY !== 0) {
      window.scrollBy(100, -window.scrollY);
    }
  }

  function smoothscroll() {
    if (window.scrollY < 400) {

      window.scrollBy({
        top: 1200,
        behavior: 'smooth'
      });

    }

    else if (window.scrollY > 400) {

      window.scrollBy({
        top: 500,
        behaviour: 'smooth'
      });

    }
  }
  function scrollToTop() {

    if (window.scrollY !== 0) {
      window.scrollBy(0, -window.scrollY);
    }
  }
  return (
    <header id="header"className={header ? "scroll header d-flex align-items-center fixed-top" : "header d-flex align-items-center fixed-top"} >

    {/* <header id="header" className="header d-flex align-items-center fixed-top"> */}
      <div className="container-fluid container-xl d-flex align-items-center justify-content-between">

        <a className="logo d-flex align-items-center">
          <h1>BTSPG</h1>
        </a>

        <i className="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
        <i className="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

        <nav id="navbar" className="navbar">
          <ul>
            <li><Link className="nav-link " aria-current="page" onClick={scrollToTop} to="/">Home</Link></li>
            <li> <Link className="nav-link" to="/" onClick={smoothscroll}>About us</Link></li>
            <li><Link className="nav-link" to="/" onClick={scrollRoomlist}>Our Services</Link></li>
            <li><Link className="nav-link" to="/" onClick={scrollContact}>Contact</Link></li>
            {auth ? <li>
                <Link className="nav-link" to="/showBookings" >My Bookings</Link>
              </li>
              : ""}
          
              {auth ? <li><Link onClick={logout} to="/login">Logout</Link></li>
                : ""}</ul>
        </nav>

      </div>
    </header>
  );
}
export default NewNavbar;