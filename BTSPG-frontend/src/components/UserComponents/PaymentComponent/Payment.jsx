import React, { useContext, useEffect, useState } from 'react';
import './Payment.css';
import { Link, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';

const Payment = () => {
    const { id } = useParams();

    const userId = JSON.parse(localStorage.getItem('user')).userId;
    const [booking, setBooking] = useState([]);
    console.log("payment pag booking id:" + id);
    const [payment, setPayment] = useState({
        paymentMethod: "cash",
        paymentDate: "2023-06-12"
    })

    const navigate = useNavigate();
    const showBookingDetails = async () => {
        try {
            const response = await axios.get(`http://localhost:8081/api/booking/getById/${id}`);
            setBooking(response.data);
        } catch (err) {
            toast.error(err.response.data.message);
        }

    }
    const DonePayment = async (e) => {
        e.preventDefault();
        console.log(payment);
        try {
            const response = await axios.post(`http://localhost:8081/api/payment/addPayment/${userId}/${id}`, payment);
            console.log(response.data);
            toast.success("Booking Successfully")
            navigate("/showBookings");

        } catch (err) {
            console.log(err);
            console.log(err.response.data.message);
        }
    }

    const handleChange = (e) => {
        setPayment((prevState) => ({...prevState, [e.target.name]: e.target.value }));
    }

    useEffect(() => {
        showBookingDetails();
    }, []);

    return (
        <div className="container" style={{ marginTop: '8rem', marginBottom: '2rem' }}>
            <div className="card-body"  style={{borderStyle:'groove', padding:'20px'}}>
                <h3 className="card-title">Enter Booking Details</h3>
                <hr />
                {booking !== null && (
                    <div key={booking.bookingId} >
                        <form className="row g-3" onSubmit={DonePayment}>
                            <div className="col-md-12">
                                <label for="inputName5" className="form-label">Booking Id</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="inputName5"
                                    placeholder={booking.bookingId}
                                    disabled />
                            </div>

                            <div className="col-12">
                                <label for="weeks" className="form-label">No of weeks</label>
                                <input
                                    type="number"
                                    className="form-control"
                                    id="weeks"
                                    placeholder={booking.noOfWeeks}
                                    disabled />
                            </div>
                            <div className="col-md-12">
                                <label for="method">Payment Method</label>
                                <br />
                                <select
                                    name="paymentMethod"
                                    value={payment.paymentMethod}
                                    onChange={handleChange}
                                    className="form-control"
                                    style={{ marginTop: "1rem", paddingRight: "3rem", paddingLeft: "3rem" }}
                                    id="method">
                                    <option value="cash">Cash</option>
                                    <option value="card">Card</option>
                                </select>
                            </div>
                            <div className="col-12">
                                <label for="date" className="form-label">Date</label>
                                <input
                                    type="date"
                                    className="form-control"
                                    id="date"
                                    name="paymentDate"
                                    value={payment.paymentDate}
                                    onChange={handleChange}
                                    placeholder='write date in "YYYY-MM-DD" format'
                                    required />
                            </div>
                            <div className="col-12">
                                <label for="price" className="form-label">Amount</label>
                                <input
                                    type="number "
                                    className="form-control"
                                    id="price"
                                    placeholder={booking.totalPrice}
                                    disabled />
                            </div>
                            <div className="text-center">
                                <button type="submit" className='btn btn-primary'>Pay</button>
                            </div>
                        </form>
                    </div>)}
            </div>
        </div>

    );

}

export default Payment;