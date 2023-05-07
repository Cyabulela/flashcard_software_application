package com.example.flashcardsoftwareapplication.data.mappers;

import com.example.flashcardsoftwareapplication.data.dto.FlashCardDto;
import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;

import java.util.ArrayList;
import java.util.List;

public class ListMappers {

    public static List<FlashCard> toFlashCardList(List<FlashCardDto> flashCardDtos) {
        List<FlashCard> flashCards = new ArrayList<>();
        for (FlashCardDto flashCardDto : flashCardDtos) {
            flashCards.add(flashCardDto.toFlashCard());
        }
        return flashCards;
    }
}
