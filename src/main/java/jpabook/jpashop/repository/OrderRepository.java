package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    } 

    public List<Order> findAll(OrderSearch orderSearch) {
        // 동적 쿼리 해결 방법
        // -> JPQL을 생자로 작성
        // -> Criteria 작성
        // -> QueryDSL
        return em.createQuery("SELECT o FROM Order o JOIN o.member m" +
                        " Where o.status = :status" +
                        " And m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();
    }
}
