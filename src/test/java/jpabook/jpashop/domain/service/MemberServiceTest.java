package jpabook.jpashop.domain.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;


    @Test
    @DisplayName("회원 가입 - 성공")
    public void success_member_join() {
        //given
        Member memberA = new Member("memberA");
        //when
        Long savedId = memberService.join(memberA);
        //then
        Assertions.assertEquals(savedId, memberRepository.findById(savedId).getId());
    }

    @Test(expected = IllegalStateException.class)
    @DisplayName("회원 가입 실패 - 회원 아이디 중복")
    public void fail_member_join() {
        //given
        Member member1 = new Member("member");
        Member member2 = new Member("member");

        //when
        memberService.join(member1);
        memberService.join(member2);
        //then
        fail("예외 발생");
    }
}