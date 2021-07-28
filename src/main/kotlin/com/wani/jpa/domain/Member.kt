package com.wani.jpa.domain

import javax.persistence.*

@Entity
class Member(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long = 0L,

    var name: String = "",

    @Embedded
    var address: Address,

    @OneToMany(mappedBy = "member")
    var orders: MutableList<Order> = mutableListOf()
) {


}