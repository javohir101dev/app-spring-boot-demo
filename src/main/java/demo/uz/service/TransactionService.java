package demo.uz.service;

import demo.uz.domain.Card;
import demo.uz.domain.Operation;

public interface TransactionService {

    void debit(Operation operation, Card senderCard, Long fullAmount);

    void credit(Operation operation, Card receiverCard, long receiverAmount);
}
