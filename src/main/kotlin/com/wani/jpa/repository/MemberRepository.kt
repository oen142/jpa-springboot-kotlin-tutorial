package com.wani.jpa.repository

import com.wani.jpa.domain.Member
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class MemberRepository(
    @PersistenceContext
    private val em: EntityManager
) {
    fun save(member: Member) {
        em.persist(member)
    }

    fun findOne(id: Long): Member =
        em.find(Member::class.java, id)

    fun findAll(): List<Member> =
        em.createQuery(
            """
            select m from Member m
            """.trimIndent(), Member::class.java
        ).resultList

    fun findByName(name: String): List<Member> =
        em.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
}