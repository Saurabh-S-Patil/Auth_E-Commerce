import axios from 'axios'
import { createUrl, log } from '../utils/utils'

export async function placeOrder(totalPrice) {
  const url = createUrl('/order')
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
      totalPrice,
    }

    // make the api call using the token in the header
    const response = await axios.post(url, body, header)
    log(response.data)
    return response.data
  } catch (ex) {
    log(ex)
    return null
  }
}

export async function getMyOrders() {
  const url = createUrl('/order')
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
