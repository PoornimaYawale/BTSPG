import React from 'react';
import { Link } from 'react-router-dom';
import "./AdminHeader.css";

const AdminHeader = () => {
    return (
        <>
        <div>
            <aside id="sidebar" className="sidebar">

                <ul className="sidebar-nav" id="sidebar-nav">

                    <li className="nav-item">
                        <Link className="nav-link " to="/">
                            <i className="bi bi-grid"></i>
                            <span>All Rooms</span>
                        </Link>
                    </li>

                    <li className="nav-item">
                        <Link className="nav-link" to="/add-room">
                        <i className="bi bi-layout-text-window-reverse"></i>
                            <span>Add Room</span>
                        </Link>
                    </li>
                
                    <li className="nav-item">
                        <Link className="nav-link" to="/view-bookings">
                            <i className="bi bi-layout-text-window-reverse"></i>
                            <span >All Bookings</span>
                        </Link>
                    </li>

                    <li className="nav-item">
                        <Link className="nav-link" to="/view-review">
                            <i className="bi bi-bar-chart"></i>
                            <span>View Reviews</span>
                        </Link>
                    </li>
                </ul>

            </aside>
        </div>
        </>
    )
}

export default AdminHeader;
