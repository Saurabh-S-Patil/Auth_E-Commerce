import { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import { getProductList } from '../services/product'
import { constants } from '../utils/constants'
import { Link,useNavigate } from 'react-router-dom';
import CategoryBar from './categorybar';

function Electronics() {
  const navigate = useNavigate()
  const [products, setProducts] = useState([])

  // useEffect(() => {
  //   // get the list of products from server
  //   loadProducts()
  // }, [])

  // const loadProducts = async () => {
  //   const response = await getProductList()
  //   if (response['status'] == 'success') {
  //     setProducts(response)
  //   } else {
  //     toast.error('Error while calling get /product api')
  //   }
  // }

  // const getproductdetails = async (e) => {
  //   console.log("check :"+e.target.value);
  //   navigate('/item'+e.target.value)
  //   // const response = await getProductList()
  //   // if (response['status'] === 'success') {
  //   //   setProducts(response['data'])
  //   // } else {
  //   //   toast.error('Error while calling get /product api')
  //   // }
  // }

  return (
    <>
   <CategoryBar />
    <div style={{position:"relative",minHeight:"100%" ,margin:"0"}}>
    <div className=''>

<div id="carouselExampleIndicators" style={{height:"300px",paddingTop:"10px"}} className="carousel slide" data-ride="carousel">
  <ol className="carousel-indicators">
    {/* <li data-target="#carouselExampleIndicators" data-slide-to="" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to=""></li>
    <li data-target="#carouselExampleIndicators" data-slide-to=""></li> */}
  </ol>
  <div className="carousel-inner">
    <div className="carousel-item active">
      <img className="d-block w-100" src="https://ik.imagekit.io/major/images/ad5.png?updatedAt=1692707224121" alt="First slide"></img>
    </div>
    <div className="carousel-item">
      <img className="d-block w-100" src="https://ik.imagekit.io/major/images/ad6.png?updatedAt=1692707047749" alt="Second slide"></img>
    </div>
    <div className="carousel-item">
      <img className="d-block w-100" src="https://ik.imagekit.io/major/images/ad4.png?updatedAt=1692707047586" alt="Third slide"></img>
    </div>
  </div>
  <a className="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
    <span className="sr-only">Previous</span>
  </a>
  <a className="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span className="carousel-control-next-icon" aria-hidden="true"></span>
    <span className="sr-only">Next</span>
  </a>
</div>
<br/><br/>

      <h1 style={{ textAlign: 'center', margin: 10 }}>Electronics Product Page</h1>
      <div className='row' style={{ marginTop: 50}}>
        {products.map((product) => {
          return (
            <div className='col-md-3'>
              <div className='card'>
              <img  src="https://ik.imagekit.io/major/images/51PuFBgBK4L._AC_UL640_FMwebp_QL65_.webp?updatedAt=1692689684780" alt="userimage"/>
                {/* <img
                  src={constants.serverUrl + '/' + product['image']}
                  style={{ height: 200 }}
                  alt=''
                /> */}
                <div className='card-body'>
                  <h5 className='card-title'>
                    
                    <Link to={`/item/${product.id}`}>
                      {/* {product['title']} */}
                      {product['name']}
                    </Link>
                    </h5>
                  <div className='card-text'>
                    <div>{product['company']}</div>
                    <div>₹ {product['price']}</div>                   
                  </div>
                </div>
              </div><br/>
            </div>
          )
        })}
      </div>
      </div>
    </div>
    </>
  )
}

export default Electronics
