package jpabook.jpashop.dto;

import jpabook.jpashop.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberListDto {
    private String name;
    private Address address;
}
