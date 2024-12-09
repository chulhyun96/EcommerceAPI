package jpabook.jpashop.dto;

import jpabook.jpashop.domain.Member;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberListResponse {

    private final List<String> nameList = new ArrayList<>();

    private MemberListResponse(List<Member> members) {
        for (Member member : members) {
            System.out.println("member.getName() = " + member.getName());
            nameList.add(member.getName());
        }
    }

    public static MemberListResponse createMemberListResponse(List<Member> members) {
        return new MemberListResponse(members);
    }
}
