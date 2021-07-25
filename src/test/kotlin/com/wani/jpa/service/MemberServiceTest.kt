package com.wani.jpa.service

import com.wani.jpa.domain.Address
import com.wani.jpa.domain.Member
import com.wani.jpa.repository.MemberRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException

@SpringBootTest
@Transactional
internal class MemberServiceTest(
    @Autowired
    private val memberService: MemberService,
    @Autowired
    private val memberRepository: MemberRepository
) {

    @Test
    fun `회원가입`() {
        val member = Member(
            id = 0L,
            name = "kim",
            address = Address("a", "b", "c")
        )

        val saveId = memberService.join(member)

        assertThat(member).isEqualTo(memberRepository.findOne(saveId))
    }

    @Test
    fun `중복_회원_예외`() {
        val member1 = Member(
            id = 0L,
            name = "kim1",
            address = Address("a", "b", "c")
        )
        val member2 = Member(
            id = 0L,
            name = "kim1",
            address = Address("a", "b", "c")
        )

        memberService.join(member1)
        assertThatThrownBy {
            memberService.join(member2)
        }.isInstanceOf(IllegalStateException::class.java)

    }
}