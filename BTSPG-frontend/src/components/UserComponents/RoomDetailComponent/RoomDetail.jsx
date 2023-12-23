import React, { useContext, useEffect } from "react";

import { AllRoomContext } from "../../../Contexts/AllRoomContext";
import { Link, useParams } from "react-router-dom";
import { useState } from "react";
import RoomReview from "../ReviewComponent/RoomReview";
import axios from "axios";
import { toast } from "react-toastify";

const RoomDetail = () => {
    const [room, setRoom] = useState([]);
    const { allRooms, setAllRooms } = useContext(AllRoomContext);

    const { id } = useParams();

    const getRoom = async () => {
        try{
            const response = await axios.get(`http://localhost:8081/api/room/getById/${id}`);
            if(response.status === 200){
                setRoom(response.data);
            }
        } catch(err){
            toast.error(err.response.data.message);
        }
    }

    useEffect(() => {
        if(id){
            getRoom();
        }
    }, []);


    return (
        <section style={{paddingTop: "12rem !important", marginTop: '3rem'}}>
            <div className="container">
                {room !== null && (
                    <div key={room.roomId} className="row gx-5">
                        <aside className="col-lg-6">
                            <div className="border rounded-4 mb-3 d-flex justify-content-center" style={{ maxWidth: "800px", maxHeight: "1500px", margin: "auto" }} >
                                <img className="rounded-4 fit img-fluid" src={room.image} />
                            </div>
                        </aside>
                        <main className="col-lg-6 card" >
                            <div className="ps-lg-3 mt-2" style={{marginBottom: '15px'}} >
                                <h4 className="title text-dark">
                                    Room Detail
                                </h4>
                                <br />
                                <div className="mb-3">
                                    <span style={{color: 'green'}} className="h5">&#8377;{room.roomPrice}</span>
                                </div>
                                <p> {room.roomDescription} </p>

                                <div className="row">
                                    <dt className="col-3">Address:</dt>
                                    <dd className="col-9">{room.address}</dd>

                                    <dt className="col-3">City:</dt>
                                    <dd className="col-9">{room.city}</dd>

                                    <dt className="col-3">Capacity:</dt>
                                    <dd className="col-9">{room.capacity}</dd>
                                </div>
                                <hr />
                                <Link to={'/booking/' + room.roomId} className="btn btn-warning shadow-0"> Book now </Link>
                            </div>
                        </main>
                    </div>
                )}
            </div>
            <div className="container" >
                <Link to={"/showroomreview/" + room.roomId} /><RoomReview /></div>
        </section>
    );
}
export default RoomDetail;