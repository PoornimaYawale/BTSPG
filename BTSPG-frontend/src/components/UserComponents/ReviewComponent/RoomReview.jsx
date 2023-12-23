import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import Star from './Star';

const RoomReview =()=> {
    const [room, setRoom] = useState([]);
    const [roomReview,setRoomReview] = useState([]);
    const{id} = useParams();

    const ShowReview = async () => {
        try {
            const response = await axios.get(`http://localhost:8081/api/review/getbyRoomId/${id}`);
              console.log(response.data);
              setRoomReview(response.data);
         
        } catch (err) {
            toast.error(err.response.data.message);
        }
    }
    useEffect(() =>{
        ShowReview();
    },[]);

    return(
            <div>
                
                <div className="container card-body " style={{boxShadow:'2px 2px 10px' , paddingLeft:'10px', paddingBottom:'2px'}}>
                    <h5 className="card-title">All Review </h5>
                    <hr/>
                            {roomReview.length !== 0 ? (roomReview.map((review) => (
                                <div className="col" style={{padding:' 0 30px '}} key={review.reviewId}>
                                   <div className="review-box card row" >
                                   <h6>UserName :{review.user.email}</h6>
                                   <h6>Comment : {review.comment}</h6>
                                   <h6>Rating: <span style={{color:'green'}}><Star stars={review.rating} /></span></h6>
                                     {/* <span style={{'color':'green'}}>{review.rating} /5 </span></h6> */}
                                   </div>
                                </div>
                            ))):<p>Review Not Available!</p>}
                </div>
            </div>
        
  );
}

export default RoomReview;