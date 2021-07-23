package com.wani.jpa.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    var author : String,
    var isbn : String
) : Item() {
}