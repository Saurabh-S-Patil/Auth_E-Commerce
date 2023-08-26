import { useDispatch } from 'react-redux'
import { toast } from 'react-toastify'
import { removeFromCart as removeFromCartAction } from '../features/cartSlice'
import {
  removeItemFromCart as removeItemFromCartApi,
  updateItemQuantity as updateItemQuantityApi,
} from '../services/cart'
import { createUrl } from '../utils/utils'

export function CartItem({ item, getCartItems }) {
  const url = createUrl('/' + item['image'])

  const dispatch = useDispatch()

  const updateQuantity = async (quantity) => {
    if (quantity == 0) {
      // delete the item
      const response = await removeItemFromCartApi(item['id'])
      if (response['status'] == 'success') {
        // update redux store
        dispatch(removeFromCartAction())

        toast.success('removed item')
        getCartItems()
      } else {
        toast.error(response['error'])
      }
    } else {
      // update the new quantity
      const response = await updateItemQuantityApi(item['id'], quantity)
      if (response['status'] == 'success') {
        toast.success('updated quantity')
        getCartItems()
      } else {
        toast.error(response['error'])
      }
    }
  }

  return (
    <div className='row' style={{}}>
      <img style={{ width: 180 }} className='col-3' src={url} alt='' />
      <div className='col-9'>
        <h5>{item['title']}</h5>
        <div>{item['company']}</div>
        <div>
          ₹{' '}
          <span style={{ fontWeight: 'bold', fontSize: 20 }}>
            {item['price']}
          </span>
        </div>

        <div>
          Quantities:{' '}
          <span style={{ fontWeight: 'bold', fontSize: 20 }}>
            {item['quantity']}
          </span>
        </div>
        <div style={{ marginTop: 10 }}>
          <button
            onClick={() => {
              updateQuantity(item['quantity'] + 1)
            }}
            className='btn btn-success'
          >
            +
          </button>
          <button
            onClick={() => {
              updateQuantity(item['quantity'] - 1)
            }}
            className='btn btn-warning'
            style={{ marginLeft: 10 }}
          >
            -
          </button>
        </div>
      </div>
      <div
        style={{
          marginTop: 20,
          marginBottom: 20,
          height: 2,
          backgroundColor: 'lightgray',
        }}
      ></div>
    </div>
  )
}

export default CartItem
