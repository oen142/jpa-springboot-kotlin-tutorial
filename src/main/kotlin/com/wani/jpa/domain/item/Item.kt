package com.wani.jpa.domain.item

import com.wani.jpa.domain.Category
import com.wani.jpa.exception.NotEnoughStockException
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    var id: Long? = 0L,

    var name: String? = "",
    var price: Int? = 0,
    var stockQuantity: Int? = 0,

    @ManyToMany(mappedBy = "items")
    var categories: MutableList<Category> = mutableListOf()
) {

    // == 비즈니스 로직 == //
    fun addStockQuantity(quantity: Int) {
        this.stockQuantity = this.stockQuantity ?: 0 + quantity
    }

    fun removeStockQuantity(quantity: Int) {
        val restStock = this.stockQuantity ?: 0 - quantity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        this.stockQuantity = restStock
    }
}
