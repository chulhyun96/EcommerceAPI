package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.*;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members/new")
    public ResponseEntity<?> create(@RequestBody @Validated MemberForm memberForm,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    BindingResultErrors.createBindingResultErrors(bindingResult.getFieldErrors()));
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
        List<MemberListDto> collect = members.stream()
                .map(m -> new MemberListDto(m.getName(), m.getAddress()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new MemberListResponse(collect, collect.size()));
    }

    @PutMapping("/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable Long id,
            @RequestBody @Validated UpdateMemberRequest request) {

        memberService.update(id, request.getName());
        Member findMember = memberService.getMemberById(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }
}
