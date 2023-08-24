package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeaminfo.entities.Orders;


public interface OrderRepository extends JpaRepository<Orders, Long> {
}