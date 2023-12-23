import axios from 'axios';
import React from 'react';
import '../AdminDashboardComponent/AdminDashboard.css';
import { useContext } from 'react';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { AllRoomContext } from '../../../Contexts/AllRoomContext';

const AddRoom = () => {

    const {allRooms, setAllRooms }= useContext(AllRoomContext);
    const navigate =useNavigate();
    const [addRoom, setAddRoom] = useState({
        roomDescription: "",
        capacity: "",
        roomPrice: "",
        address: "",
        city: "",
        image: ""
    })

    const handleChange = (e) => {
        setAddRoom((prevData) => ({ ...prevData, [e.target.name]: e.target.value }))
    }

    const AddRoom = async(e)=>{
        e.preventDefault();
        console.log(allRooms);
        try{
             const response = await axios.post("http://localhost:8081/api/room/add",addRoom);
             setAllRooms((prevState)=>([...prevState,response.data]));
             toast.success("Room Added Successfully");
             navigate("/allRooms");

        }catch(err){
            console.log(err.response.data.message);
        }
        console.log(addRoom);
    }
    
    return (
        <>
           
            <div className="card-body pb-3">
                <h5 className="card-title">Add Room</h5>
                <div className='card'>
                    <form className="row g-3 " style={{ padding: '1rem' }}  onSubmit={AddRoom}>
                        <div className="col-md-12">
                            <label for="roomDescription" className="form-label"><b>Room Description</b></label>
                            <input
                                type="text"
                                className="form-control"
                                id="roomDescription"
                                name="roomDescription"
                                value={addRoom.roomDescription}
                                onChange={handleChange}
                                placeholder='enter room description'
                                required />
                        </div>
                        <div className="col-md-6">
                            <label for="capacity" className="form-label"><b>Capacity</b></label>
                            <input
                                type="number"
                                className="form-control"
                                id="capacity"
                                name="capacity"
                                value={addRoom.capacity}
                                onChange={handleChange}
                                placeholder='enter capacity between 1 -6'
                                min="1"
                                max="6"
                                required />
                        </div>
                        <div className="col-md-6">
                            <label for="roomPrice" className="form-label"><b>Price</b></label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder='room price'
                                id="roomPrice"
                                name="roomPrice"
                                onChange={handleChange}
                                value={addRoom.roomPrice}
                                required />
                        </div>
                        <div className="col-12">
                            <label for="address" className="form-label"><b>Address</b></label>
                            <input
                                type="text"
                                className="form-control"
                                id="address"
                                name="address"
                                value={addRoom.address}
                                onChange={handleChange}
                                placeholder='enter address'
                                required />
                        </div>

                        <div className="col-md-6">
                            <label for="city" className="form-label"><b>City</b></label>
                            <input
                                type="text"
                                className="form-control"
                                id="city"
                                name="city"
                                value={addRoom.city}
                                onChange={handleChange}
                                placeholder='enter city'
                                required />
                        </div>

                        <div className="col-md-6">
                            <label for="image" className="form-label"><b>image url </b></label>
                            <input
                                type="text"
                                className="form-control"
                                id="image"
                                name="image"
                                value={addRoom.image}
                                onChange={handleChange}
                                placeholder='enter image url'
                                required />
                        </div>

                        <div className="text-center">
                            <button
                               
                                type="submit"
                                className="btn btn-primary px-3 mx-3">
                                Add Room
                            </button>
                        </div>
                    </form>
                </div>

            </div>

        </>
    );
}

export default AddRoom;
