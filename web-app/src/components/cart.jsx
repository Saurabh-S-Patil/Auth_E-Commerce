// import { useState } from 'react'

// function Cart() {
//   const [cartitems, setcartitems] = useState(4);
//   return (
//     <div>
//       <center><h3>Your Cart ({cartitems})</h3></center>
      
//       <table class="table table-borderless">
//   <thead>
//     <tr>
//       <th colSpan={2} scope="col">Items Description</th>
//       <th colSpan={2} scope="col">Items Image</th>
//       <th scope="col">Price</th>
//       <th scope="col">Quantity</th>
//       <th scope="col">Total</th>
//     </tr>
//   </thead>
//   <tbody>
//     <tr>
//       <th scope="row">1</th>
//       <td>Apple Iphone</td>
//       <td></td>
//       <td>₹</td>
//       <td>
//       <div className="incdec">
//             <button className="dec">-</button>
//             <div className="mid">1</div>
//             <button className="inc">+</button>
//         </div>
//       </td>
//     </tr>
//   </tbody>
// </table>
//     </div>
//   )
// }

// export default Cart



import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { toast } from 'react-toastify'
import { getCartItems as getCartItemsApi } from '../services/cart'
import { placeOrder as placeOrderApi } from '../services/order'
import CartItem from './cartItem'

function Cart() {
  const [items, setItems] = useState([])
  const [totalPrice, setTotalPrice] = useState(0)

  const getCartItems = async () => {
    const response = await getCartItemsApi()
    if (response['status'] === 'success') {
      setItems(response['data'])
      let price = 0
      for (const item of response['data']) {
        price += item['quantity'] * item['price']
      }
      setTotalPrice(price)
    } else {
    }
  }

  // useEffect(() => {
  //   getCartItems()
  // }, [])

  const placeOrder = async () => {
    const response = await placeOrderApi(totalPrice)
    if (response['status'] === 'success') {
      toast.success('Successfully placed your order')
      getCartItems()
    } else {
      toast.error(response['error'])
    }
  }

  return (
    <div>
      <h1 style={{ textAlign: 'center', margin: 20 }}>Cart</h1>
      {items.length == 0 && (
        <div style={{ textAlign: 'center' }}>
          <h5>
            Sorry!! Your cart is empty at the moment. Please add few products to
            place an order.
          </h5>
          <Link to='/product-gallery'>Browse Products</Link>
        </div>
      )}

      {items.map((item) => {
        return <CartItem getCartItems={getCartItems} item={item} />
      })}
      {items.length > 0 && (
        <div>
          Subtotal ({items.length} items): ₹{' '}
          <span style={{ marginRight: 20, fontSize: 20, fontWeight: 'bold' }}>
            {totalPrice}
          </span>
          <button onClick={placeOrder} className='btn btn-success'>
            Checkout
          </button>
        </div>
      )}
    </div>
  )
}

export default Cart

