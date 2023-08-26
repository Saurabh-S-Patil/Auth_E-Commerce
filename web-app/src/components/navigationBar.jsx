import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { logout } from '../features/authSlice'
import CategoryBar from './categorybar'
import { itemdec,iteminc } from '../features/cartSlice'
import { useDispatch, useSelector } from 'react-redux'

function NavigationBar() {
// const crtcount = useSelector((count) => count.cartReducer.countstatus);

  const [username, setusername] = useState(sessionStorage.getItem('first_name'));
  // const [cartcounter, setcartcounter] = useState(crtcount); 
  const navigate = useNavigate()
  const dispatch = useDispatch()
  const cartItemCount = useSelector((state) => state.cart.itemCounter)
  sessionStorage.setItem['cartcount']=cartItemCount;
  // const cartItemCount = 0

  // logout the user
  const logoutUser = () => {
    // clear the session storage changes
    sessionStorage.removeItem('token')
    sessionStorage.removeItem('name')
    sessionStorage.removeItem('mobile')
    sessionStorage.removeItem('profileImage')

    // hide the navigation bar
    dispatch(logout())

    // redirect to login page
    navigate('/')
  }

  const gotoprofile = () => {
    navigate('/profile')
  }

  const gotomyorders = () => {
    navigate('/myorders')
  }

  const gotocart = () => {
    navigate('/cart')
  }


  return (
    <div>
      <nav className='navbar navbar-expand-lg bg-body-tertiary'>
        <div className='container-fluid'>
          <a className='navbar-brand'>E-Shopping</a>
          <button
            className='navbar-toggler'
            type='button'
            data-bs-toggle='collapse'
            data-bs-target='#navbarSupportedContent'
            aria-controls='navbarSupportedContent'
            aria-expanded='false'
            aria-label='Toggle navigation'
          >
            <span className='navbar-toggler-icon'></span>
          </button>
          <div className='collapse navbar-collapse' id='navbarSupportedContent'>
            <ul className='navbar-nav me-auto mb-2 mb-lg-0'>
              <li className='nav-item'>
                <Link className='nav-link' to='/product-gallery'>
                  Home Page
                </Link>
              </li>

              {/* <li className='nav-item'>
                <Link className='nav-link' to='/cart'>
                  Cart
                </Link>
              </li> */}

              {/* <li className='nav-item'>
                <Link className='nav-link' to='/cart'>
                  Orders
                </Link>
              </li> */}

              {/* <li className='nav-item'>
                <Link className='nav-link' to='/cart'>
                  Profile
                </Link>
              </li> */}

              <li className='nav-item'>
              <form class="form-inline my-2 my-lg-0" style={{display:'inline-flex'}}>
      <input style={{width:"600px"}} class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"></input>
      &nbsp;
      <button class="btn btn-outline-primary my-2 my-sm-0"  type="submit">Search</button>
    </form>
              </li>

              <li className='nav-item' >
              <button className='btn btn' style={{border:"1.2px solid #0074D9", marginLeft:"10", color:"#0074D9", height:'38'}}onClick={gotocart}>
                Cart {cartItemCount}&nbsp;
              <img width="30" height="27" src="https://img.icons8.com/3d-fluency/94/shopping-cart.png" alt="shopping-cart"/>
              </button>
              </li>

            </ul>
          </div>

          <div className='d-flex'>
<div class="dropdown">
  <button class="btn btn dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    {username}&nbsp;
    <img width="30" height="30" src="https://ik.imagekit.io/major/images/usernameimg.png?updatedAt=1692701128252" alt="userimage"/>  
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
    <button class="dropdown-item" type="button" onClick={gotoprofile}>Profile</button>
    <button class="dropdown-item" type="button" onClick={gotomyorders}>My Orders</button>
    <button class="dropdown-item" type="button" onClick={logoutUser}>Log out</button>
  </div>
</div>
      
  
                
                
            
          </div>
        
 {/* &nbsp;&nbsp; */}
          {/* <div className='d-flex'>
            <button onClick={logoutUser} className='btn btn-primary'>
              Logout
            </button>
          </div> */}
        </div>
      </nav>
     
    </div>
  )
}

export default NavigationBar
