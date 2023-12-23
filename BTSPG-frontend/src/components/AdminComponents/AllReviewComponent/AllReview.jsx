import axios from 'axios';
import React, { useContext } from 'react';
import { useEffect } from 'react';
import '../AdminDashboardComponent/AdminDashboard.css';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import {AllReviewContext} from "../../../Contexts/AllReviewContext ";


const AllReviews = () => {
    const { allReviews, setAllReviews } = useContext(AllReviewContext);
    const navigate = useNavigate();
    const ShowAllReview = async () => {
        try {
            console.log("reviews");
            const response = await axios.get('http://localhost:8081/api/review/getAll');
            if (response.status === 200) {
                setAllReviews(response.data);
            }
        } catch (err) {
            toast.error('no review available' + err.response.data.message);
        }
    }

    useEffect(() =>{
        ShowAllReview();
    },[]);

    const Delete =async(id)=>{
        try{
          const response = await axios.delete(`http://localhost:8081/api/review/deleteById/${id}`);
          setAllReviews(allReviews.filter(review=> review.reviewId!==id))
          navigate('/view-review');
        }catch(err){
            toast.error('no review available' + err.response.data.message);
        }

  }
    

    return (<div>
       
        <div className="card-body pb-0">
            <h5 className="card-title">All Reviews </h5>

            <table className="table table-borderless">
                <thead>
                    <tr>
                        <th scope="col">ReviewId</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Comment</th>
                        <th scope="col">User Id</th>
                        <th scope="col">Room Id</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                            {allReviews && (allReviews.map((review) => (
                                <tr key={review.reviewId} >

                                    <th scope="row">{review.reviewId}</th>
                                    <td>{review.rating} </td>
                                    <td>{review.comment}</td>
                                    <td>{review.user.userId}</td>
                                    <td>{review.room.roomId}</td>
                                    <td><button className="btn btn-danger" onClick={()=> Delete(review.reviewId)} >Delete Review</button></td> 
                                    
                                </tr>

                            )))}
                        </tbody>
            </table>

        </div>

    </div>);
    
}

export default AllReviews
