import axios from 'axios';
import React from 'react';
import { useContext } from 'react';
import { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import { AllReviewContext } from '../../../Contexts/AllReviewContext ';

const AddReview = () => {

    const userId = JSON.parse(localStorage.getItem('user')).userId;
    const {id} = useParams();
    console.log(id);
    const {allReviews, setAllReviews}= useContext(AllReviewContext);
    const navigate =useNavigate();
    const [addReview, setAddReview] = useState({
        rating: "",
        comment: ""
    })

    const handleChange = (e) => {
        setAddReview((prevData) => ({...prevData, [e.target.name]: e.target.value }))
    }

    const AddReview = async(e)=>{
        e.preventDefault();
        try{
             const response = await axios.post(`http://localhost:8081/api/review/add/${userId}/${id}`,addReview);
             setAllReviews(response.data);
             toast.success("Review Added Successfully");
             navigate(`/roomdetail/${id}`);

        }catch(err){
            console.log(err.response.data.message);
        }
    }
    
    return (
        <>
            <div className=" card top-selling overflow-auto">

            </div>
            <div className="container card-body " style={{marginTop:"8rem", boxShadow:"0px 2px grey"}} >
                <h5 className="card-title">Add Room</h5>
                <hr />
                <div className='card'>
                    <form className="row g-3 " style={{ padding: '1rem' }}  onSubmit={AddReview}>
                        
                        <div className="col-md-12">
                            <label for="comment" className="form-label"><b>comment</b></label>
                            <input
                                type="text"
                                className="form-control"
                                id="comment"
                                name="comment"
                                value={addReview.comment}
                                onChange={handleChange}
                                placeholder='enter comment'
                                required />
                        </div>
                        <div className="col-md-6">
                            <label for="rating" className="form-label"><b>Rating</b></label>
                            <input
                                type="text"
                                className="form-control"
                                id="rating"
                                name="rating"
                                value={addReview.rating}
                                onChange={handleChange}
                                placeholder='give rating between 0-5'
                                required />
                        </div>
                      
                        <div className="text-center">
                            <button
                               
                                type="submit"
                                className="btn btn-primary px-3 mx-3">
                                Add Review
                            </button>
                        </div>
                    </form>
                </div>

            </div>

        </>
    );
}

export default AddReview;