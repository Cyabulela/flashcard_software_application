package com.example.flashcardsoftwareapplication.domain.repository;

import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;
import java.util.List;

public interface FlashCardRepository {

    void insectFlashCard(FlashCard flashCard);

    void updateFlashCard(FlashCard flashCard);

    boolean deleteFlashCard(FlashCard flashCard);

    List<FlashCard> getFlashCards();
}
