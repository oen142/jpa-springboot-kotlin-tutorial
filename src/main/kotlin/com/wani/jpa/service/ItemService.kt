package com.wani.jpa.service

import com.wani.jpa.domain.item.Item
import com.wani.jpa.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemService(
    private val itemRepository: ItemRepository
) {

    @Transactional
    fun saveItem(item: Item) {
        itemRepository.save(item)
    }

    fun findItem(id: Long): Item =
        itemRepository.findOne(id)


    fun findItems(): List<Item> =
        itemRepository.findAll()

}