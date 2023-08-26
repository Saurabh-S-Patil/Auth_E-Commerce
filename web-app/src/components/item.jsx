import { getProductDetails } from "../services/ProductDetails";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux'
import "./item.css";
import { toast } from 'react-toastify'
import { addToCart as addToCartAction } from '../features/cartSlice'
import { addToCart as addToCartApi } from '../services/cart'
import { removeFromCart } from '../features/cartSlice'

function Item(product) {
  const [itemdetails, setitemdetails] = useState([]);
  const [count, setcount] = useState(0); 
  const { id } = useParams();
  console.log("item id : " + id);

  const dispatch = useDispatch()

  const addToCart = async () => {
    // const response = await addToCartApi(product['id'], product['price'])
    const response = await addToCartApi(sessionStorage.getItem('id'),itemdetails['id'])
    // console.log("resp1: "+ sessionStorage.getItem('id'))
    // console.log("resp2: "+ itemdetails['id'])
    if (response == 'success'){
      
      // update the redux store
      dispatch(addToCartAction())
      toast.success('Successfully added this product to your cart')
    } else {
      console.log("resp: "+response)
      // toast.error(response['error'])
    }
  }

  useEffect(() => {
    // get the list of products from server
    loadProductsDetails();
  }, []);

  const loadProductsDetails = async () => {
    const response = await getProductDetails(id);
    console.log(response);
    setitemdetails(response);
  };

  const inc = () => {
    setcount(count + 1);
    dispatch(addToCartAction())
    if (sessionStorage.getItem['cartcount']!=undefined){
    sessionStorage.setItem['cartcount']=sessionStorage.getItem['cartcount'];

    }
    console.log("cartcount: "+sessionStorage.getItem['cartcount1'])
    console.log("count: "+count)
  };

  const dec = () => {
    if(count>0){
    setcount(count - 1);
    dispatch(removeFromCart())
    sessionStorage.setItem['cartcount']=count;
  }
  };

  const buynow = () => {
    // setcount(count + 1);
  };



  return (
    <div className="maindiv">
      <div className="imgdiv"></div>

      <div className="itemdiv">
        <h4>{itemdetails["company"]}</h4>
        <h3>{itemdetails["productName"]}</h3>
        <hr />
        <h5>M.R.P ₹&nbsp; {itemdetails["price"]}</h5>
        <h5 style={{ display: "flex" }}>
          Add quantity &nbsp;
          <div className="incdec">
            <button className="dec" onClick={dec}>
              -
            </button>
            <div className="mid">{count}</div>
            <button className="inc" onClick={inc}>
              +
            </button>
          </div>
        </h5>
        <h6>Description About this product:-</h6>
        <p>{itemdetails["productDescription"]}</p>
        <p>
          Solid color polyester/linen full blackout thick sunscreen floor
          curtain Type: General Pleat Applicable Window Type: Flat Window
          Format: Rope Opening and Closing Method: Left and Right Biparting Open
          Processing Accessories Cost: Included Installation Type: Built-in
          Function: High Shading(70%-90%) Material: Polyester / Cotton Style:
          Classic Pattern: Embroidered Location: Window Technics: Woven Use:
          Home, Hotel, Hospital, Cafe, Office Feature: Blackout, Insulated,
          Flame Retardant Place of Origin: India Name: Curtain Usage: Window
          Decoration Keywords: Ready Made Blackout Curtain
        </p>
        <button className="btn btn-primary" onClick={buynow}>
          Buy Now
        </button>
        &nbsp;
        <button className="btn btn-primary" onClick={addToCart}>
          Add to Cart
        </button>
      </div>
    </div>
  );
}

export default Item;
