import axios from 'axios'
import { createUrl, log } from '../utils/utils'

export async function registerUser(
  firstName,
  lastName,
  email,
  password,
  gender,
  mobile
) {
  const url = createUrl('/auth/register')
  const body = {
    first_name:firstName,
    last_name:lastName,
    email,
    password,
    gender,
    mob_no:mobile,
  }

  // wait till axios is making the api call and getting response from server
  try {
    const response = await axios.post(url, body)
    log(response.data)
    return response.data
  } catch (ex) {
    log(ex)
    return null
  }
}

export async function loginUser(email, password) {
  // const url = createUrl('/user/login')
  // const [token, settoken] = useState(sessionStorage.getItem('jwt'));
  const token = sessionStorage.getItem('jwt')
  
  const url = createUrl('/auth/login')
  const body = {
    email,
    password,
  }

  // wait till axios is making the api call and getting response from server
  try {
    const response = await axios.post(url, body)
    log(response.data)
    return response.data
  } catch (ex) {
    log(ex)
    return null
  }
}
