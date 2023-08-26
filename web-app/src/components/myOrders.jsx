import {Link} from 'react-router-dom'

function MyOrders() {
  return (
    <div>
<center>
      <h2>My Orders</h2></center>
  <hr/>
      <div>
      
      <table class="table table-borderless">
  <thead>
    <tr>
      <th colSpan={2} scope="col">Item</th>
      <th scope="col">Order No</th>
      <th scope="col">Order Status</th>
      <th scope="col">Order Image</th>
    </tr>
  </thead>
  <tbody style={{border:"2px solid gray", borderRadius:"2px"}}>
    <tr>
      {/* <th scope="row">1</th> */}
      <td><h4>Apple Iphone<br/></h4>
      <Link to="/orderdetails" style={{color:'green'}}>view order </Link> 
      </td>
      <td></td>
      <td></td>
    </tr>
  </tbody>
</table>
    </div>
    </div>
  )
}

export default MyOrders
