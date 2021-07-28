package com.wani.jpa.domain

import com.wani.jpa.domain.item.Item
import javax.persistence.*

@Entity
class OrderItem(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    var id: Long? = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,

    var orderPrice: Int,
    var count: Int
) {

    /* 비즈니스 로직 */
    fun cancel() {
        item.addStockQuantity(count)
    }

    fun getTotalPrice(): Int =
        count * orderPrice

    companion object {

        fun createOrderItem(item: Item, orderPrice: Int, count: Int, order: Order): OrderItem {
            item.removeStockQuantity(count)
            return OrderItem(
                item = item,
                orderPrice = orderPrice,
                count = count,
                order = order
            )
        }
    }
}
