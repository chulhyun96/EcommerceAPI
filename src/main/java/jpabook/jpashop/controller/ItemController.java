package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.BookForm;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/items/new")
    public ResponseEntity<?> createItem(@RequestBody BookForm bookForm) {
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        itemService.saveItem(book);
        return ResponseEntity.ok(book.getId());
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) {
        Item item = itemService.getItem(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/items")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(itemService.getItems());
    }

}
