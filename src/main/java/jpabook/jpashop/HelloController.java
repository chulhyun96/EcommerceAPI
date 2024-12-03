package jpabook.jpashop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public ResponseEntity<?> deleteSomething() {
        return ResponseEntity.ok("Hello World!");
    }
}
