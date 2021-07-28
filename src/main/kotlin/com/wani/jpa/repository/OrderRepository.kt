package com.wani.jpa.repository

import com.wani.jpa.domain.Order
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class OrderRepository(
    private val em: EntityManager
) {

    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(id: Long): Order {
        return em.find(Order::class.java, id)
    }

    /*fun findAll (orderSearch : OrderSearch) : List<Order> {
        
    }*/
}