import React from "react";
import '../UserComponents/HomeComponent/NewHome.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTwitter, faFacebook, faInstagram, faLinkedin } from '@fortawesome/free-brands-svg-icons';
import { Link } from "react-router-dom";


const Footer = () => {
    function scrollToTop() {

        if (window.scrollY !== 0) {
            window.scrollBy(0, -window.scrollY);
        }
    }

    return (
        <footer className="footer">
            <div className="container">
                <div className="row gy-4">
                    <div className="col-lg-5 col-md-12 footer-info">
                        <a href="index.html" className="logo d-flex align-items-center">
                            <span>BTSPG</span>
                        </a>
                        <p>Zolostays is India's largest co-living platform that provides easy, delightful, and beautiful stays for those who want to make a difference.</p>
                        <div className="social-links d-flex mt-4">
                        <a href="https://www.linkedin.com/in/poornima-yawale-43263920a/" className="linkedin"><FontAwesomeIcon icon={faLinkedin} /></a>
                        <a href="#" className="twitter"><FontAwesomeIcon icon={faTwitter} /></a>
                            <a href="#" className="facebook"><FontAwesomeIcon icon={faFacebook} /></a>
                            <a href="#" className="instagram"><FontAwesomeIcon icon={faInstagram} /></a>
                            
                        </div>
                    </div>

                    <div className="col-lg-2 col-6 footer-links">
                        <h4>Useful Links</h4>
                        <ul>
                            <li><Link className="nav-link " aria-current="page" onClick={scrollToTop} to="/">Home</Link></li>
                            <li> <Link className="nav-link" to="/" >About us</Link></li>
                            <li><Link className="nav-link" to="/" >Our Services</Link></li>
                            <li><a href="#">Terms of service</a></li>
                            <li><a href="#">Privacy policy</a></li>
                        </ul>
                    </div>

                    <div className="col-lg-2 col-6 footer-links">
                        <h4>Our Services</h4>
                        <ul>
                            <li>Luxury Room</li>
                            <li>Coliving Buildings</li>
                            <li>Good Management</li>
                            <li>Food Facility</li>
                            <li>Fun Areas</li>
                        </ul>
                    </div>

                    <div className="col-lg-3 col-md-12 footer-contact text-center text-md-start">
                        <h4>Contact Us</h4>
                        <p>
                            F21 Bramhin Nagar <br />
                            Shivaji Phase 1,PUNE<br />
                            INDIA <br /><br />
                            <strong>Phone:</strong> +91 5589 55488 55<br />
                            <strong>Email:</strong> BTSPG@email.com<br />
                        </p>

                    </div>

                </div>
            </div>

            <div className="container mt-4">
                <div className="copyright">
                    &copy; Copyright <strong><span>BTSPG</span></strong>. All Rights Reserved
                </div>
                <div className="credits">
                    Designed by <a href="https://github.com/PoornimaYawale">PoornimaYawale</a>
                </div>
            </div>

        </footer>
    );


}

export default Footer;