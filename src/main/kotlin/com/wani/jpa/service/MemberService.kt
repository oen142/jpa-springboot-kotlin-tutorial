package com.wani.jpa.service

import com.wani.jpa.domain.Member
import com.wani.jpa.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException

@Service
@Transactional
class MemberService(
    val memberRepository: MemberRepository
) {

    /**
     * 회원 가입
     * */
    fun join(member: Member): Long {
        validateDuplicateMember(member) // 중복 회원 검증
        memberRepository.save(member)
        return member.id
    }

    private fun validateDuplicateMember(member: Member) {
        // Exception
        val findMembers = memberRepository.findByName(member.name)
        if (findMembers.isNotEmpty()) {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }

    // 회원 전체 조회
    @Transactional(readOnly = true)
    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun findOne(memberId: Long): Member {
        return memberRepository.findOne(memberId)
    }

}