import axios from 'axios';
import React, { useContext, } from 'react';
import '../AdminDashboardComponent/AdminDashboard.css';
import { useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { AllRoomContext } from '../../../Contexts/AllRoomContext';

const AllRooms = () => {
    
    const { allRooms, setAllRooms } = useContext(AllRoomContext);
    const navigate = useNavigate();

    const ShowAllRoom = async () => {
        try {
           
            const response = await axios.get('http://localhost:8081/api/room/getAll');
            if (response.status === 200) {
                setAllRooms(response.data);
            }
        } catch (err) {
            toast.error('no Bookings available' + err.message);
        }
    }

    useEffect(() => {
        ShowAllRoom();
    }, []);

    const Delete =async(id)=>{
          try{
            const response = await axios.delete(`http://localhost:8081/api/room/deleteById/${id}`);
            setAllRooms(allRooms.filter(room=> room.roomId !==id))
            navigate('/allRooms');
          }catch(err){
            toast.error(err.response.data.message);
          }

    }
    return (
        <div>
        
            <div className="card-body pb-0">
                <h5 className="card-title">All Rooms </h5>

                <table className="table table-borderless">
                    <thead>
                        <tr>
                            <th scope="col">Room_id</th>
                            <th scope="col">Description</th>
                            <th scope="col">Address</th>
                            <th scope="col">City</th>
                            <th scope="col">Capacity</th>
                            <th scope="col">Price</th>
                            <th scope="col">image</th>
                            <th scope="col"></th>
                            <th scope="col"></th>

                        </tr>
                    </thead>
                    <tbody>
                                {allRooms && (allRooms.map((room) => (
                                    <tr key={room.roomId} >

                                        <th scope="row">{room.roomId}</th>
                                        <td>{room.roomDescription} </td>
                                        <td>{room.address}</td>
                                        <td>{room.city}</td>
                                        <td>{room.capacity}</td>
                                        <td>{room.roomPrice}</td>
                                        <td><img style={{ height: "60px", width: "80px" }} src={room.image} /></td>
                                        <td><Link className="btn btn-primary"to={`/update-room/${room.roomId}`}>Update Detail</Link></td>
                                         <td><button className="btn btn-danger" onClick={()=> Delete(room.roomId)} >Delete Room</button></td> 
                                    </tr>

                                )))}
                            </tbody>
                </table>

            </div>

        </div>
    );
}

export default AllRooms;
