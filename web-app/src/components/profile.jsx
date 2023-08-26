import {Link} from 'react-router-dom';
import profileIcon from './images/img2.jpeg';
import { useState } from 'react';


function Profile(){
    const [username, setusername] = useState(sessionStorage.getItem('first_name'));
    const [email, setemail] = useState(sessionStorage.getItem('email'));
    const imageDimensions = { 
        width: '70px', // Adjust as needed
        height: '70px', // Adjust as needed
        borderRadius:'70px'
      };
  
  return(
<>
{/* <div className="profile-container">
      
      <div style={{display:'flex'}}>
      <img src={profileIcon} alt="Profile" style={imageDimensions}/>&nbsp;<h1>{username}</h1><br></br><br></br>
      </div>

      <div>
      <div className="profile-content" style={{display:'flex'}}>
        <div className="profile-info">
      
      
      
          <table className="profile-table" style={{width:200 ,height:300 , fontWeight:'bold',borderRadius: '10px'}}>
            <tbody>
              <tr>
                <th>
                  <Link to="/profile">Profile</Link>
                </th>
              </tr>
              <tr>
                <td>
                  <Link to="/orders">Orders</Link>
                </td>
              </tr>
              <tr>
                <td>
                  <Link to="/address">Address</Link>
                </td>
              </tr>
              <tr>
                <td>
                  <Link to="/payment">Payment</Link>
                </td>
              </tr>
              <tr>
                <td>
                  <Link to="/wishlist">Wishlist</Link>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div className="profile-form" style={{marginLeft:100,fontWeight:'bold'}}>
          <form>
            <label htmlFor="name">Name</label><br></br>
            <input type="text" id="name" /><br></br>
            <label htmlFor="email">Email-id</label><br></br>
            <input type="email" id="email" /><br></br>
            <label htmlFor="mobile">Mobile No.</label><br></br>
            <input type="tel" id="mobile" /><br></br>
            <label htmlFor="city">City</label><br></br>
            <input type="text" id="city" /><br></br>
            <label htmlFor="pincode">Pincode</label><br></br>
            <input type="text" id="pincode" /><br></br>
            <label htmlFor="dob">DOB</label><br></br>
            <input type="date" id="dob" /><br></br>
          </form>
        </div>
      </div>
      </div>
    </div> */}



    <div style={{position:"relative",minHeight:"100%" ,margin:"0"}}>
      <center><h1>My Profile</h1></center>
      <div style={{display:'flex'}}>
      <img src={profileIcon} alt="Profile" style={imageDimensions}/>&nbsp;&nbsp;&nbsp;<h3 style={{paddingTop:'10px'}}>{username}<h6>{email}</h6></h3>
      
      </div>

      <div>
        <div className='col'></div>
        <div className='col'>
          <div className='form'>
            <div className='mb-3'>
              <label htmlFor=''>First Name</label>
              <input
                type='text'
                className='form-control'
                onChange={(e) => {
                //   setFirstName(e.target.value)
                }}
              />
            </div>

            <div className='mb-3'>
              <label htmlFor=''>Last Name</label>
              <input
                type='text'
                className='form-control'
                onChange={(e) => {
                //   setLastName(e.target.value)
                }}
              />
            </div>

            <div className='mb-3'>
              <label htmlFor=''>Email</label>
              <input
                type='text'
                className='form-control'
                onChange={(e) => {
                //   setEmail(e.target.value)
                }}
              />
            </div>

            <div className='mb-3'>
              <label htmlFor=''>Mobile Number</label>
              <input
                type='tel'
                className='form-control'
                onChange={(e) => {
                //   setMobile(e.target.value)
                }}
              />
            </div>

            <div className='mb-3'>
              <label htmlFor=''>City</label>
              <input
                type='password'
                className='form-control'
                onChange={(e) => {
                //   setPassword(e.target.value)
                }}
              />
            </div>

            <div className='mb-3'>
              <label htmlFor=''>State</label>
              <input
                type='password'
                className='form-control'
                onChange={(e) => {
                //   setConfirmPassword(e.target.value)
                }}
              />
            </div>

            <div className='mb-3'>
             
              <button className='btn btn-success'>
                Update
              </button>
            </div>
          </div>
        </div>
        <div className='col'></div>
      </div>
    </div>

</>

    );
}

export default Profile;