import React from "react";
import Img1 from '../../../images/35604.jpg';
import "../HomeComponent/NewHome.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouseUser, faChildren ,faMapLocation} from '@fortawesome/free-solid-svg-icons';

const AboutUs = () => {

    return (
        <section id="about" className="about pt-0">
            <div className="container" data-aos="fade-up">

                <div className="row gy-4">
                    <div className="col-lg-6 position-relative align-self-start order-lg-last order-first">
                        <img src={Img1} className="img-fluid" alt="" />
                    </div>
                    <div className="col-lg-6  content order-last  order-lg-first">
                        <h3>About Us</h3>
                        <p>A place that feels like 'home' as soon as you enter it. A place where we steal away your chores and replace them with care. A place where you'll have technology in your pocket and your tribe by your side. A place that's all about you. 
                        </p>
                        <ul>
                            <li data-aos="fade-up" data-aos-delay="100">
                            <FontAwesomeIcon icon={faMapLocation} />
                                <div>
                                    <h5>
                                        You moved to a new city,
                                        so we moved there too.</h5>
                                    <p>
                                        Today, we've come a long way - from the two residences in Delhi to an impressive 450+ residences in more than 24+ cities across the country, and we promise we'll soon be ready to welcome you in many more</p>
                                </div>
                            </li>
                            <li data-aos="fade-up" data-aos-delay="200">
                                <FontAwesomeIcon icon={faChildren} />
                                <div>
                                    <h5>We are India’s Most Celebrated Coliving Spaces</h5>
                                    <p>Eat, Sleep, Co-game, Co-live, Repeat!</p>
                                </div>
                            </li>
                            <li data-aos="fade-up" data-aos-delay="300">
                                <FontAwesomeIcon icon={faHouseUser} />
                                <div>
                                    <h5>
                                        You needed a place like home,
                                        so we moved back home
                                    </h5>
                                    <p>Reminiscing about the 'good old hostel days', you will realise a lot of that 'good'. So to give youngsters that 'better', we set up a base , and the rest, as we say, is the present.</p>
                                </div>
                            </li>
                            <li data-aos="fade-up" data-aos-delay="200">
                                <FontAwesomeIcon icon={faChildren} />
                                <div>
                                    <h5>We are India’s Most Celebrated Coliving Spaces</h5>
                                    <p>Eat, Sleep, Co-game, Co-live, Repeat!</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </section>
    );
}

export default AboutUs;