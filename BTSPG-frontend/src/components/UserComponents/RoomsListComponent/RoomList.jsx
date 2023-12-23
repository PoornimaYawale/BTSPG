import React, { useContext, useState } from "react";
import './RoomList.css';
import { Link } from "react-router-dom";
import axios from 'axios';
import { useEffect } from 'react';
import { toast } from 'react-toastify';
import { AllRoomContext } from "../../../Contexts/AllRoomContext";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faLocationDot } from '@fortawesome/free-solid-svg-icons';


const RoomList = () => {
  const { allRooms, setAllRooms } = useContext(AllRoomContext);
  const [city, setCity] = useState('');
  const [price, setPrice] = useState(0);



  const ShowAllRooms = async () => {
    try {
      console.log("rooms");
      const response = await axios.get('http://localhost:8081/api/room/getAll');
      if (response.status === 200) {
        setAllRooms(response.data);
      }
    } catch (err) {
      toast.error('no Bookings available' + err.response.data.message);
    }
  }

  const filterCapacity = async (capacity) => {
    try {
      const response = await axios.get(`http://localhost:8081/api/room/getByCapacity/${capacity}`);
      if(response.data === ""){
        setAllRooms([]);
      }else{
        setAllRooms([response.data]);
      }
    }
    catch (err) {
      toast.error(err.message);
    }

  }
  const filterPrice = async () => {
    try {
      const response = await axios.get(`http://localhost:8081/api/room/getByPriceRange/${price}`);
      console.log("filter by price");
      setAllRooms(response.data);
    }
    catch (err) {
      toast.error(err.message);
    }

  }

  useEffect(() => {
    ShowAllRooms();
  }, []);

  return (
    <section id="service" className="services pt-0">
      <div className="container" data-aos="fade-up">

        <div className="section-header">
          <span>Our Services</span>
          <h2>Our Services</h2>

        </div>
        {/* <div className="container py-3">
          <div>
            <select className="form-control col-4" onChange={(e) =>{filterCapacity(e.target.value)} }>
              <option className="dropdown-item">Select The Capacity</option>
              <option className="dropdown-item" value="1">1</option>
              <option className="dropdown-item" value="2">2</option>
              <option className="dropdown-item" value="3">3</option>
            </select>
          </div>

          <div>
            <select className="form-control col-4" onChange={(e) => {setPrice(e.target.value);
            filterPrice();}}>
              <option className="dropdown-item">Select The Price Range</option>
              <option className="dropdown-item" value="20000">less than 20000</option>
              <option className="dropdown-item" value="40000">less than 40000</option>
              <option className="dropdown-item"value="60000">less than 60000</option>
            </select>
          </div>

          </div> */}

      <div className="row gy-2">
        {allRooms && (allRooms.map((room) => (
          <div key={room.roomId} className="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="100">
            <div className="card" >
              <div className="card-img">
                <img style={{ height: '250px', width: '100%' }} src={room.image} alt="" className="img-fluid" />
              </div>
              <h3><FontAwesomeIcon icon={faLocationDot} />&nbsp; &nbsp;  {room.city}</h3>
              <h4>{room.roomDescription}</h4>
              <h4>Only at :<b style={{ color: 'green' }}> &#8377;{room.roomPrice}</b></h4>
              <Link className="btn btn-primary" style={{ marginLeft: '5rem', marginRight: '5rem' }} to={'/roomdetail/' + room.roomId}>View more</Link>
            </div>
          </div>)
        ))}

      </div>

    </div>
    </section >
  );
}

export default RoomList;