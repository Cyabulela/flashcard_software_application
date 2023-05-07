package com.example.flashcardsoftwareapplication.data.repository;

import android.content.Context;

import com.example.flashcardsoftwareapplication.data.dto.FlashCardDto;
import com.example.flashcardsoftwareapplication.data.local.FlashCardDatabase;
import com.example.flashcardsoftwareapplication.data.mappers.ListMappers;
import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;
import com.example.flashcardsoftwareapplication.domain.repository.FlashCardRepository;

import java.util.List;

public class FlashCardSQLiteDatabaseRepository implements FlashCardRepository {

    private final FlashCardDatabase database;

    public FlashCardSQLiteDatabaseRepository(Context context) {
        database = new FlashCardDatabase(context);
    }
    @Override
    public void insectFlashCard(FlashCard flashCard) {
        database.insectFlashcard(flashCard.toFlashCardDto());
    }

    @Override
    public void updateFlashCard(FlashCard flashCard) {
        database.updateFlashcard(flashCard.toFlashCardDto());
    }

    @Override
    public boolean deleteFlashCard(FlashCard flashCard) {
        return database.deleteFlashcard(flashCard.toFlashCardDto());
    }

    @Override
    public List<FlashCard> getFlashCards() {
        List<FlashCardDto> flashCardDtos = database.getAllFlashcards();
        return ListMappers.toFlashCardList(flashCardDtos);
    }
}
