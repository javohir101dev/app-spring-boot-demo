package demo.uz.common;

import demo.uz.domain.Card;
import demo.uz.domain.Operation;
import demo.uz.domain.User;
import demo.uz.model.CardDto;
import demo.uz.model.OperationDto;
import demo.uz.model.UserCrudDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapstructMapper {

    @Mapping(target = "userId", source = "user_id")
//    @Mapping(target = "userId", source = "user.id") when user_id non exists case
    CardDto toCardDto(Card card);

    List<CardDto> toCardDtoList(List<Card> cardList);

    @Mapping(target = "senderCardNumber", source = "sender.number")
    @Mapping(target = "receiverCardNumber", source = "receiver.number")
    OperationDto toOperationDto(Operation operation);

    List<OperationDto> toOperationDto(List<Operation> operationList);

    @Mapping(target ="active", source = "active", defaultValue = "true")
    User toUser(UserCrudDto userCrudDto);

}
