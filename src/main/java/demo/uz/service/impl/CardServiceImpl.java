package demo.uz.service.impl;

import demo.uz.domain.Card;
import demo.uz.repository.CardRepo;
import demo.uz.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepo cardRepo;

    @Override
    public Card get(Long id) {
        Optional<Card> optionalCard = cardRepo.findById(id);
        return optionalCard.orElse(null);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepo.findAll();
    }
}
