package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.BindingResultErrors;
import jpabook.jpashop.dto.MemberForm;
import jpabook.jpashop.dto.MemberListResponse;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members/new")
    public ResponseEntity<?> create(@RequestBody @Validated MemberForm memberForm,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(BindingResultErrors.createBindingResultErrors(bindingResult.getFieldErrors()));
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        Long memberId = memberService.join(member);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/members")
    public ResponseEntity<?> list() {
        List<Member> members = memberService.getMembers();
        return ResponseEntity.ok(MemberListResponse.createMemberListResponse(members));
    }
}
