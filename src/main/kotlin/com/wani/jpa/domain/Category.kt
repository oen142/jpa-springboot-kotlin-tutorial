package com.wani.jpa.domain

import com.wani.jpa.domain.item.Item
import javax.persistence.*

@Entity
class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    var id: Long,

    var name: String,

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    var items: MutableList<Item> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category?,

    @OneToMany(mappedBy = "parent")
    var children: MutableList<Category> = mutableListOf()
) {

    fun initChildCategory(child: Category) {
        this.children.add(child)
        child.parent = this
    }
}