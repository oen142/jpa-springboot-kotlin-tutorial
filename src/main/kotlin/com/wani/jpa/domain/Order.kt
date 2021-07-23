package com.wani.jpa.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: MutableList<OrderItem> = mutableListOf(),

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery,

    var orderDateTime: LocalDateTime,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus
) {

    // == 연관관계 메서드 ==

    fun initMember(member: Member) {
        this.member = member
        member.orders.add(this)
    }

    fun initOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        orderItem.order = this
    }

    fun initDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }
}
