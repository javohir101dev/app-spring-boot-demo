package demo.uz.service;

import demo.uz.domain.Card;

import java.util.List;

public interface CardService {


    Card get(Long id);

    List<Card> getAllCards();
}
