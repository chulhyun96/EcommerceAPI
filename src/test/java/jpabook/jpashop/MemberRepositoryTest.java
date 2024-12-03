package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testMember() {
        //given
        Member member = new Member();
        member.setName("MemberA");
        //when
        Long memberId = memberRepository.save(member);
        Member findMember = memberRepository.findById(memberId);
        //then
        assertThat(findMember.getName()).isEqualTo("MemberA");
        assertThat(findMember.getId()).isEqualTo(memberId);
    }
}