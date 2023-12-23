import React, { useState } from "react";
import AboutUs from "../AboutComponent/AboutUs";
import RoomDetail from "../RoomDetailComponent/RoomDetail";
import Booking from "../BookingComponent/Booking";
import Payment from "../PaymentComponent/Payment";
import Login from "../../LoginComponent/Login";
import Register from "../../RegisterComponent/Register";
import UserBookings from "../BookingComponent/UserBooking";
import { Navigate, Route,  Routes } from "react-router-dom";
import NewHome from "../HomeComponent/NewHome";
import NewNavbar from "../../NavbarComponent/NewNavbar";
import { AllRoomContext } from "../../../Contexts/AllRoomContext";
import { AllReviewContext } from "../../../Contexts/AllReviewContext ";
import { LoginContext } from "../../../Contexts/LoginContext";
import RoomReview from "../ReviewComponent/RoomReview";
import AddReview from "../ReviewComponent/AddReview";

const UserDashboard = () => {
    const [allRooms, setAllRooms] = useState();
    const [allReviews, setAllReviews] = useState();
    const [user ,setUser] = useState();
    return (
        <LoginContext.Provider value={{user, setUser}}>
        <AllReviewContext.Provider value={{ allReviews, setAllReviews }}>
            <AllRoomContext.Provider value={{ allRooms, setAllRooms }}>

                <div>
                    <NewNavbar />
                    <Routes>
                        <Route path="/" element={<Navigate to="/home" />} />
                        <Route path="/home" element={<NewHome />} />
                        <Route path="/aboutus" element={<AboutUs />} />
                        <Route path="/roomdetail/:id" element={<RoomDetail />} />
                        <Route path="/showroomreview/:id" element={<RoomReview />} />
                        <Route path="/booking/:id" element={<Booking />} />
                        <Route path="/payment/:id" element={<Payment />} />
                        <Route path="/addReview/:id" element={<AddReview />} />
                        <Route path="/showBookings" element={<UserBookings />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Register />} />
                    </Routes>

                </div>
            </ AllRoomContext.Provider>
        </AllReviewContext.Provider>
        </LoginContext.Provider>
    );
}

export default UserDashboard;