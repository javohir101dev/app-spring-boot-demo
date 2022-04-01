package demo.uz.controller;

import demo.uz.domain.Card;
import demo.uz.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/get/{id}")
    public Card get(@PathVariable(value = "id") Long id){
        return cardService.get(id);
    }

    @GetMapping("/get/all")
    public List<Card> getAll() {
       return cardService.getAllCards();
    }

}
