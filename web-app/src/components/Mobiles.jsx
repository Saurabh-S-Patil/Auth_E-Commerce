import { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import { getProductList } from '../services/product'
import { constants } from '../utils/constants'
import { Link,useNavigate } from 'react-router-dom';
import CategoryBar from './categorybar';

function Mobiles() {
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


<br/><br/>

      <h1 style={{ textAlign: 'center', margin: 10 }}>Mobiles Product Page</h1>
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

export default Mobiles
