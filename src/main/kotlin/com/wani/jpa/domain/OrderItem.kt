package com.wani.jpa.domain

import com.wani.jpa.domain.item.Item
import javax.persistence.*

@Entity
class OrderItem(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    var id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,

    var orderPrice: Int,
    var count: Int
) {

}
