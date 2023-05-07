package com.example.flashcardsoftwareapplication.data.dto;

import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;

public class FlashCardDto {

    private final String title , notes, date;
    private final int flashcardID;

    public FlashCardDto(String title, String notes, String date, int flashcardID) {
        this.title = title;
        this.notes = notes;
        this.date = date;
        this.flashcardID = flashcardID;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public String getDate() {
        return date;
    }

    public int getFlashcardID() {
        return flashcardID;
    }

    public FlashCard toFlashCard() {
        return new FlashCard(title , notes , date, flashcardID);
    }

}
