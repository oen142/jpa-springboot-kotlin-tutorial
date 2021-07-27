package com.wani.jpa.repository

import com.wani.jpa.domain.item.Item
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class ItemRepository(
    @PersistenceContext
    private val em: EntityManager
) {

    fun save(item: Item) {
        if (item.id == null) {
            em.persist(item)
        } else {
            em.merge(item)
        }
    }

    fun findOne(id: Long): Item {
        return em.find(Item::class.java, id)
    }

    fun findAll(): List<Item> {
        return em.createQuery(
            """
                SELECT i FROM Item i
            """.trimIndent(), Item::class.java
        ).resultList
    }
}