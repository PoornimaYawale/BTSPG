import React, { useContext, useEffect, useState } from 'react';
import { AllRoomContext } from "../../../Contexts/AllRoomContext";
import { useNavigate, useParams } from "react-router-dom";
import axios from 'axios';
import { toast } from 'react-toastify';

const UpdateProduct = () => {
    const [room, setRoom] = useState([]);
    const { allRooms, setAllRooms } = useContext(AllRoomContext);
    const { id } = useParams();
    const navigate = useNavigate();

    const getRoom = () => {
        setRoom(allRooms.filter((roomDetail) => roomDetail.roomId == id)[0]);
       
    }

    useEffect(() => {
        if (allRooms) {
            getRoom();
        }
    }, [allRooms]);

    const handleChange = (e) => {
        setRoom((prevRoom) => ({ ...prevRoom, [e.target.name]: e.target.value }));
    }

    const handleupdateRoom = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.put(`http://localhost:8081/api/room/update/${id}`, room);
            setAllRooms((prevState) => ([...prevState, response.data]));
            toast.success("Room Updated Successfully");
            navigate("/allRooms");

        } catch (err) {
            console.log(err.response.data.message);
        }
    }

    return (
        <>
            <div className="card-body pb-3">
                <h5 className="card-title">Update Room</h5>
                {room !== null && (
                    <div key={room.roomId} className='card'>
                        <form className="row g-3 " style={{ padding: '1rem' }} onSubmit={handleupdateRoom}>
                            <div className="col-md-12">
                                <label htmlFor="roomDescription" className="form-label"><b>Room Description</b></label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="roomDescription"
                                    placeholder="Room Desc"
                                    name='roomDescription'
                                    value={room.roomDescription}
                                    onChange={handleChange}
                                    required />
                            </div>
                            <div className="col-md-6">
                                <label htmlFor="capacity" className="form-label"><b>Capacity</b></label>
                                <input
                                    type="number"
                                    className="form-control"
                                    id="capacity"
                                    name="capacity"
                                    placeholder='enter capacity between 1 -6'
                                    min="1"
                                    max="6"
                                    value={room.capacity}
                                    onChange={handleChange}
                                    required />
                            </div>
                            <div className="col-md-6">
                                <label htmlFor="roomPrice" className="form-label"><b>Price</b></label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="roomPrice"
                                    name="roomPrice"
                                    placeholder='room price'
                                    value={room.roomPrice}
                                    onChange={handleChange}
                                    required />
                            </div>
                            <div className="col-12">
                                <label htmlFor="address" className="form-label"><b>Address</b></label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="address"
                                    name="address"
                                    placeholder='enter address'
                                    value={room.address}
                                    onChange={handleChange}
                                    required />
                            </div>

                            <div className="col-md-6">
                                <label htmlFor="city" className="form-label"><b>City</b></label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="city"
                                    name="city"
                                    placeholder='enter city'
                                    value={room.city}
                                    onChange={handleChange}
                                    required />
                            </div>

                            <div className="col-md-6">
                                <label htmlFor="image" className="form-label"><b>image url </b></label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="image"
                                    name="image"
                                    placeholder='enter image url'
                                    value={room.image}
                                    onChange={handleChange}
                                    required />
                            </div>

                            <div className="text-center">
                                <button type="submit" className="btn btn-primary px-3 mx-3">Update Room</button>
                            </div>
                        </form>
                    </div>
                )}

            </div>

        </>
    )
}

export default UpdateProduct;
