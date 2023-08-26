import { useDispatch, useSelector } from 'react-redux'
import { Route, Routes } from 'react-router-dom'
import './App.css'
import Cart from './components/cart'
import LoginUser from './components/loginUser'
import MyOrders from './components/myOrders'
import NavigationBar from './components/navigationBar'
import ProductGallery from './components/product-gallery'
import RegisterUser from './components/registerUser'
import Item from './components/item'
import Footer from './components/footer'

// used to register react-toastify
import { useEffect } from 'react'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import { login } from './features/authSlice'
import Profile from './components/profile'
import CategoryBar from './components/categorybar'
import FAQ from './components/FAQ'
import Aboutus from './components/aboutus'
import Contactus from './components/contactus'
import Help from './components/Help'
import Careers from './components/Careers'
import Appliances from './components/Appliances'
import Electronics from './components/Electronics'
import Fashion from './components/Fashion'
import Mobiles from './components/Mobiles'
import Grocery from './components/Grocery'
import cartCountSetter from './features/cartSlice'

function App() {
  // use selector accepts a function which passes the store global state
  // at the moment we are interested only in auth slice
  const loginStatus = useSelector((state) => state.auth.status)
  const dispatch = useDispatch()
  const cartItemCount = useSelector((state) => state.cart.itemCounter)

  useEffect(() => {
    // first read the current sessionStorage and see if user is logged in
    if (sessionStorage['jwt'] && sessionStorage['jwt'].length > 0) {
      // update the auth slice status to true
      dispatch(login())   
    }
  }, [])

  return (
    <div className='container-fluid'>
      {/* navigation bar here */}
      {/* conditional rendering */}
      {/* <NavigationBar /> */}
      {loginStatus && <NavigationBar />}
      {/* {<ProductGallery/> && <CategoryBar />}   */}
      {/* {loginStatus && <Footer />} */}
      <div className='container-fluid'>
        <Routes>
          {/* home component  */}
          {/* <Route path='/' element={} /> */}

          {/* login component */}
          <Route path='/' element={<LoginUser />} />

          {/* register component */}
          <Route path='/register' element={<RegisterUser />} />

          {/* product-gallery component */}
          <Route path='/product-gallery' element={<ProductGallery />} />

          {/* cart component */}
          <Route path='/item/:id' element={<Item />} />

          {/* cart component */}
          <Route path='/cart' element={<Cart />} />

          {/* my orders component */}
          <Route path='/my-orders' element={<MyOrders />} />

          <Route path='/profile' element={<Profile />} />

          <Route path='/grocery' element={<Grocery />} />
          <Route path='/mobiles' element={<Mobiles />} />
          <Route path='/fashion' element={<Fashion />} />
          <Route path='/electronics' element={<Electronics />} />
          <Route path='/appliances' element={<Appliances />} />

          <Route path='/aboutus' element={<Aboutus />} />

          <Route path='/careers' element={<Careers />} />

          <Route path='/faq' element={<FAQ />} />

          <Route path='/help' element={<Help />} />

          <Route path='/contactus' element={<Contactus />} />

        </Routes>
       
        {/* <Footer/>   */}
      </div>
      
      <ToastContainer />
      
    </div>
    
  )
}

export default App
