package com.example.flashcardsoftwareapplication.domain.repository;

import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;
import java.util.List;

public interface FlashCardRepository {

    void insectFlashCard(FlashCard flashCard);

    void updateFlashCard(FlashCard oldFlashCard , FlashCard newFlashCard);

    List<FlashCard> getFlashCards();
}
