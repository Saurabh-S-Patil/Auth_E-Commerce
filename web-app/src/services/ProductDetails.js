import axios from 'axios'
import { createUrl, log } from '../utils/utils'

export async function getProductDetails(id) {
  const url = createUrl(`/admin/products/${id}`)
  // admin/products

  try {
    // get the current user's token from session storage
    const token = sessionStorage.getItem("jwt");
    console.log("jwt: "+token);

    // create a header to send the token
    const header = {
      headers: {
        token,
      },
    }

    const config = {
      headers:{Authorization:`Bearer ${token}`}
    };

    // make the api call using the token in the header
    const response = await axios.get(url, config)
    log(response.data)
    return response.data
  } catch (ex) {
    log(ex)
    return null
  }
}
