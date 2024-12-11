package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.SimpleOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.*;

/**
 * xToOne 관계에서의 성능 최적화
 * Order와 Member이 관계 -> ManyToOne
 * Order와 Delivey의 관계 -> OneToOne 결국 둘다 xToOne의 관계
 * Order와 OrderItems는 OneToMany의 관계(이는 데이터 타입이 컬렉션임)
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/v1/simple-orders")
    public ResponseEntity<?> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return ResponseEntity.ok(all);
    }

    @GetMapping("/v2/simple-orders")
    public List<SimpleOrderDto>  ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders.stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    @GetMapping("/v3/simple-orders")
    public List<SimpleOrderDto>  ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    /*@GetMapping("/v4/simple-orders")
    public List<SimpleOrderDto> ordersV4() {
        return orderRepository.findOrdersDtos();
    }*/
}
