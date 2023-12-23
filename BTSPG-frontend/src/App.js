import React, { useEffect, useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import AdminDashboard from './components/AdminComponents/AdminDashboardComponent/AdminDashboard';
import UserDashboard from './components/UserComponents/UserDashboardComponent/UserDashboard';
import 'react-toastify/dist/ReactToastify.css';
import { LoginContext } from "./Contexts/LoginContext";
import Login from './components/LoginComponent/Login';
import Register from './components/RegisterComponent/Register';
import { ToastContainer } from 'react-toastify';

function App() {
  const [user, setUser] = useState({});

  useEffect(() => {
    if(localStorage.getItem('user') !== ""){
      setUser(JSON.parse(localStorage.getItem('user')));
    }
  }, []);

  return (
    <div>
      <Router>
        <LoginContext.Provider value={{ user, setUser }}>
          {user ?
            ((user.role === "ADMIN" && <AdminDashboard />) || (user.role === "USER" && <UserDashboard />))
            : (
              <Routes>
                <Route path="/" exact element={<Navigate to="/login" />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
              </Routes>
            )}
        </LoginContext.Provider>
      </Router>
      <ToastContainer />
    </div>

  );
}

export default App;
