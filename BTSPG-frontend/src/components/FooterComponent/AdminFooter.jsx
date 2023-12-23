import React from "react";
import "../AdminComponents/AdminDashboardComponent/AdminDashboard.css";

const AdminFooter=()=>{

    return(
        <footer id="footer" className="footer fixed-bottom">
    <div className="copyright">
      &copy; Copyright <strong><span>BTSPG</span></strong>. All Rights Reserved
    </div>
    <div className="credits">
     
      Designed by <a href="https://github.com/PoornimaYawale">PoornimaYawale</a>
    </div>
  </footer>
    );
}
export default AdminFooter;