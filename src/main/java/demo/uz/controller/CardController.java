package demo.uz.controller;

import demo.uz.domain.Card;
import demo.uz.model.OperationDto;
import demo.uz.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/get/{id}")
    public Card get(@PathVariable(value = "id") Long id) {
        return cardService.get(id);
    }

    @GetMapping("/get/all")
    public List<Card> getAll() {
        return cardService.getAllCards();
    }

    @PostMapping("/operations")
    public List<OperationDto> cardOperations(@RequestParam(name = "id") Long id,
                                             @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                             @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        return cardService.getOperations(id, page, size);
    }



}
