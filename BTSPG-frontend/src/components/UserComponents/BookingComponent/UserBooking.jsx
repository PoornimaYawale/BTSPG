import React, { useContext, useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AllPaymentContext } from '../../../Contexts/AllPaymentContext';
import { toast } from 'react-toastify';
import axios from 'axios';

const UserBookings = () => {
    const [userBooking,setUserBookings] = useState();
    const { allPayments, setAllPayments } = useContext(AllPaymentContext);
    const userId = JSON.parse(localStorage.getItem('user')).userId;
    
    const navigate = useNavigate();
    const ShowUserBooking = async () => {
        try {
            console.log("bookings");
            const response = await axios.get(`http://localhost:8081/api/payment/getbyUserId/${userId}`);
              console.log(response.data);
              setUserBookings(response.data);
         
        } catch (err) {
            toast.error(err.message);
        }
    }

    useEffect(() =>{
        ShowUserBooking();
    },[]);

    const Delete =async(id)=>{
        try{
          const response = await axios.delete(`http://localhost:8081/api/payment/deleteById/${id}`);
          toast.dark("Booking deleted")
          setAllPayments(allPayments.filter(payment=> payment.paymentId!==id))
          ShowUserBooking();
          //navigate('/showBookings');
        }catch(err){
            toast.error(err.message);
        }

  }

    return(
        <div>
          
            <div className="container card-body "style={{marginTop:"8rem", boxShadow:"2px 2px grey",padding:'20px'}} >
                <h5 className="card-title">Your Bookings </h5>
                <hr/>

                <table className="table table-borderless">
                    <thead>
                        <tr>
                            <th scope="col">BookingId</th>
                            <th scope="col">No of Weeks</th>
                            <th scope="col">User email</th>
                            <th scope="col">Room Id</th>
                            <th scope="col">Room Description</th>
                            <th scope="col">TotalPrice</th>
                            <th scope="col">Payment Date</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                           
                        </tr>
                    </thead>
                    <tbody>
                                {userBooking && (userBooking.map((payment) => (
                                    <tr key={payment.paymentId} >

                                        <th scope="row">{payment.booking.bookingId}</th>
                                        <td>{payment.booking.noOfWeeks} </td>
                                        <td>{payment.user.email}</td>
                                        <td>{payment.booking.room.roomId}</td>
                                        <td>{payment.booking.room.roomDescription}</td>
                                        <td>{payment.amount}</td>
                                        <td>{payment.paymentDate}</td>
                                        <td><Link className="btn btn-primary" to={"/addReview/"+payment.booking.room.roomId } >Add Review</Link></td> 
                                        <td><button className="btn btn-danger" onClick={()=> Delete(payment.paymentId)} >Delete Booking</button></td> 
                                   
                                    </tr>

                                )))}
                            </tbody>
                </table>

            </div>

        </div>
    );
}

export default UserBookings;