package com.sunbeaminfo.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class OrderDTO {

public static List<OrderDTO> orderDTOs;
private Long id;
private Date order_date;
private boolean order_status;
@Column(nullable = false)
private double order_total;
@Column(nullable = false)
private int payment_method_id;

public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}
public Date getOrder_date() {
return order_date;
}
public void setOrder_date(Date order_date) {
this.order_date = order_date;
}
public boolean isOrder_status() {
return order_status;
}
public void setOrder_status(boolean order_status) {
this.order_status = order_status;
}
public double getOrder_total() {
return order_total;
}
public void setOrder_total(double order_total) {
this.order_total = order_total;
}
public int getPayment_method_id() {
return payment_method_id;
}
public void setPayment_method_id(int payment_method_id) {
this.payment_method_id = payment_method_id;
}
public long getAddress_id() {
return address_id;
}
public void setAddress_id(long address_id) {
this.address_id = address_id;
}
public long getUser_id() {
return user_id;
}
public void setUser_id(long user_id) {
this.user_id = user_id;
}
@Column(nullable = false)
private long address_id;
@Column(nullable = false)
private long user_id;

}