import React from 'react';
import "./NewHome.css";
import HomePng from '../../../images/hero-img.svg';
import RoomList from '../RoomsListComponent/RoomList';
import AboutUs from '../AboutComponent/AboutUs';
import Footer from '../../FooterComponent/Footer';

const NewHome = () => {
    return (
        <>
            <section id="hero" className="hero d-flex align-items-center" style={{ height: '110vh !important' }}>
                <div className="container">
                    <div className="row gy-4 d-flex justify-content-between">
                        <div className="col-lg-6 order-2 order-lg-1 d-flex flex-column justify-content-center">
                            <h2 data-aos="fade-up">Beautiful Living.&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; Made Easy.</h2>
                            <p data-aos="fade-up" data-aos-delay="100"> Exciting offers with a comfortable stay.Come, live the new kind of Living.Life at a professionally managed accommodation awaits you. Facilities that give local PGs an inferiority complex</p>

                        </div>

                        <div className="col-lg-5 order-1 order-lg-2 hero-img" data-aos="zoom-out">
                            <img src={HomePng} className="img-fluid mb-3 mb-lg-0" alt="" />
                        </div>

                    </div>
                </div>
            </section>
            <RoomList />
            <AboutUs />
            <Footer />
        </>
    )
}

export default NewHome;