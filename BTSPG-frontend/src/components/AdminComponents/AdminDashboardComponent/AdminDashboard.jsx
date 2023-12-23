import React ,{ useState}from 'react';
import './AdminDashboard.css';
import AllRooms from "../AllRoomsComponent/AllRooms";
import AllBookings from '../AllBookingsComponent/AllBookings';
import AddRoom from '../AddRoomComponent/AddRoom';
import { Routes, Route } from 'react-router';
import AdminHeader from '../AdminHeaderComponent/AdminHeader';
import AllReview from "../AllReviewComponent/AllReview";
import UpdateRoom from "../UpdateRoomComponent/UpdateRoom";
import { AllRoomContext } from '../../../Contexts/AllRoomContext';
import { AllBookingContext } from '../../../Contexts/AllBookingContext';
import { AllReviewContext } from '../../../Contexts/AllReviewContext ' ;
import NavbarBasic from '../../NavbarComponent/NavbarBasic';
import AdminFooter from '../../FooterComponent/AdminFooter';

const AdminDashboard = () => {

    
    const [allRooms, setAllRooms] = useState();
    const [allBookings, setAllBookings] = useState();
    const [allReviews, setAllReviews] = useState();

    return (
            <AllRoomContext.Provider value={{ allRooms, setAllRooms }}>
                <AllBookingContext.Provider value={{ allBookings, setAllBookings }}>
                    <AllReviewContext.Provider value={{ allReviews, setAllReviews }}>
                        
                            <NavbarBasic />
                            <AdminHeader />
                            <main id="main" className="main">

                                <Routes>
                                    <Route path="/" exact element={<AllRooms />} />
                                    <Route path="/allRooms" element={<AllRooms />} />
                                    <Route path="/add-room" element={<AddRoom />} />
                                    <Route path="/view-bookings" element={<AllBookings />} />
                                    <Route path="/view-review" element={<AllReview />} />
                                    <Route path="/update-room/:id" element={<UpdateRoom />} />
                                </Routes>
                            </main>
                            <AdminFooter />
                    </AllReviewContext.Provider>
                </AllBookingContext.Provider>
            </AllRoomContext.Provider>
    )
}

export default AdminDashboard;
