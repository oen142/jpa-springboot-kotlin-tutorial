package com.wani.jpa.domain

import java.lang.IllegalStateException
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

    /* 비즈니스 로직 */
    fun cancel() {
        if (delivery.status == DeliveryStatus.COMPLETE) {
            throw IllegalStateException("이미 배송 완료된 상품은 취소가 불가능합니다.")
        }

        this.status = OrderStatus.CANCEL
        for (orderItem in orderItems) {
            orderItem.cancel()
        }
    }

    /* 조회 로직 */
    /*
    *  전체 주문 가격 조회
    * */
    fun getTotalPrice(): Int =
        orderItems.sumOf {
            it.getTotalPrice()
        }


    companion object {

        // == 생성 메서드 ==

        fun createOrder(member: Member, delivery: Delivery, vararg orderItems: OrderItem): Order {
            return Order(
                member = member,
                delivery = delivery,
                orderItems = mutableListOf(*orderItems),
                status = OrderStatus.ORDER,
                orderDateTime = LocalDateTime.now()
            )
        }
    }

}
