package jpabook.jpashop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberListResponse<T> {
    private T data;
    private int count;
}
