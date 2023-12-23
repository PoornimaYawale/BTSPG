import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from 'react-toastify';
import { AllRoomContext } from "../../../Contexts/AllRoomContext";


const Booking = () => {
  const [room, setRoom] = useState({});
  const navigate = useNavigate();

  const [bookingDetail, setBookingDetail] = useState({
    noOfWeeks: 0
  })

  const { id } = useParams();
  const userid = JSON.parse(localStorage.getItem('user')).userId;

  const getRoom = async () => {
    try {
      const response = await axios.get(`http://localhost:8081/api/room/getById/${id}`);
      if (response.status === 200) {
        setRoom(response.data);
      }
    } catch (err) {
      toast.error(err.response.data.message);
    }
  }

  const BookRoom = async (e) => {

    try {
      const response = await axios.post(`http://localhost:8081/api/booking/add/${userid}/${id}`, bookingDetail);
      console.log(response.data);
      const bookId = response.data.bookingId;
      navigate("/payment/" + bookId);

    } catch (err) {
      console.log(err.response.data.message);
    }
  }

  useEffect(() => {
    getRoom();
  }, []);

  return (
    <div className="container" style={{ marginTop: '8rem', marginBottom: '2rem' }}>
      <div className="card-body" style={{borderStyle:'groove', padding:'20px'}}>
        <h3 className="card-title">Enter Booking Details</h3>
        <hr />
        {room !== null && (
          <div key={room.roomId}>
            <form className="row g-3">
              <div className="col-md-12">
                <label for="id" className="form-label">Room Id</label>
                <input
                  type="number"
                  className="form-control"
                  id="id"
                  placeholder={room.roomId}
                  disabled />
              </div>
              <div className="col-md-12">
                <label for="city" className="form-label">City</label>
                <input
                  type="text"
                  className="form-control"
                  id="city"
                  placeholder={room.city}
                  disabled />
              </div>
              <div className="col-md-12">
                <label for="price" className="form-label">Room Price</label>
                <input
                  type="number"
                  className="form-control"
                  id="price"
                  placeholder={room.roomPrice}
                  disabled />
              </div>
              <div className="col-12">
                <label for="noOfWeeks" className="form-label">No of weeks</label>
                <input
                  type="number"
                  placeholder='enter weeks between 1-5'
                  className="form-control"
                  id="noOfWeeks"
                  min="1"
                  max="5"
                  name='noOfWeeks'
                  value={bookingDetail.noOfWeeks}
                  onChange={(e) => setBookingDetail((prevState) => ({ ...prevState, [e.target.name]: e.target.value }))}
                  required />
              </div>
              <div className="text-center">
                <Link type="submit" onClick={BookRoom} className="btn btn-primary">Pay Here</Link>
              </div>
            </form>
          </div>
        )}
      </div>
    </div>

  );

}

export default Booking;