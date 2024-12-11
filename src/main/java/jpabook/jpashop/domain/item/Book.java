package jpabook.jpashop.domain.item;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;
}


