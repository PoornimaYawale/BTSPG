import axios from 'axios';
import React, { useContext} from 'react';
import '../AdminDashboardComponent/AdminDashboard.css';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import {AllBookingContext} from '../../../Contexts/AllBookingContext';

const AllBookings = () => {
    const { allBookings, setAllBookings } = useContext(AllBookingContext);
    const navigate = useNavigate();
    const ShowAllBooking = async () => {
        try {
            console.log("bookings");
            const response = await axios.get('http://localhost:8081/api/booking/getAll');
            if (response.status === 200) {
                setAllBookings(response.data);
            }
        } catch (err) {
            toast.error( err.response.data.message);
        }
    }

    useEffect(() =>{
        ShowAllBooking();
    },[]);

    const Delete =async(id)=>{
        try{
          const response = await axios.delete(`http://localhost:8081/api/booking/deleteById/${id}`);
          setAllBookings(allBookings.filter(booking=> booking.bookingId!==id))
          navigate('/view-bookings');
        }catch(err){
            toast.error( err.response.data.message);
        }

  }

    return(
        <div>
            
            <div className="card-body pb-0">
                <h5 className="card-title">All Booking </h5>

                <table className="table table-borderless">
                    <thead>
                        <tr>
                            <th scope="col">BookingId</th>
                            <th scope="col">No of Weeks</th>
                            <th scope="col">User email</th>
                            <th scope="col">Room Id</th>
                            <th scope="col">Room Description</th>
                            <th scope="col">TotalPrice</th>
                            <th scope="col"></th>
                           
                        </tr>
                    </thead>
                    <tbody>
                                {allBookings && (allBookings.map((booking) => (
                                    <tr key={booking.bookingId} >

                                        <th scope="row">{booking.bookingId}</th>
                                        <td>{booking.noOfWeeks} </td>
                                        <td>{booking.user.email}</td>
                                        <td>{booking.room.roomId}</td>
                                        <td>{booking.room.roomDescription}</td>
                                        <td>{booking.totalPrice}</td>
                                        <td><button className="btn btn-danger" onClick={()=> Delete(booking.bookingId)} >Delete Booking</button></td> 
                                   
                                    </tr>

                                )))}
                            </tbody>
                </table>

            </div>

        </div>
    );

}

export default AllBookings;
