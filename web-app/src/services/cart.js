import axios from 'axios'
import { createUrl, log } from '../utils/utils'

export async function removeItemFromCart(productId) {
  const url = createUrl('/cart/' + productId)
  try {
    // get the current user's token from session storage
    const { token } = sessionStorage

    // create a header to send the token
    const header = {
      headers: {
        token,
      },
    }

    // make the api call using the token in the header
    const response = await axios.delete(url, header)
    log(response.data)
    return response.data
  } catch (ex) {
    log(ex)
    return null
  }
}

export async function updateItemQuantity(productId, quantity) {
  const url = createUrl('/cart/quantity/' + productId)
  try {
    // get the current user's token from session storage
    const { token } = sessionStorage

    // create a header to send the token
    const header = {
      headers: {
        token,
      },
    }

    const body = {
      quantity,
    }

    // make the api call using the token in the header
    const response = await axios.put(url, body, header)
    log(response.data)
    return response.data
  } catch (ex) {
    log(ex)
    return null
  }
}

export async function addToCart(userId,productId) {
  // const url = createUrl('/cart/')
  const url = createUrl(`/carts/${userId}/addProduct/${productId}`)
  
  try {
    // get the current user's token from session storage
    const token = sessionStorage.getItem('jwt');

    // create a header to send the token
    const header = {
      headers: {
        token,
      },
    }

    const config = {
      headers:{Authorization:`Bearer ${token}`}
    };
   console.log("token : "+token);

    const body = {
      // productId,
      // quantity: 1,
    }

    // make the api call using the token in the header
    const response = await axios.post(url,body,config)
    log(response.data)
    return response.data
  } catch (ex) {
    log(ex)
    return null
  }
}

export async function getCartItems() {
  const url = createUrl('/cart/')
  try {
    // get the current user's token from session storage
    const { token } = sessionStorage

    // create a header to send the token
    const header = {
      headers: {
        token,
      },
    }

    // make the api call using the token in the header
    const response = await axios.get(url, header)
    log(response.data)
    return response.data
  } catch (ex) {
    log(ex)
    return null
  }
}
