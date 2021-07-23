package com.wani.jpa.domain.item

import com.wani.jpa.domain.Category
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

}
