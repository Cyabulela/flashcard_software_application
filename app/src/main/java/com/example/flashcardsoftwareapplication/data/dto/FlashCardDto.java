package com.example.flashcardsoftwareapplication.data.dto;

import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;

public class FlashCardDto {

    private final String title , notes, date;

    public FlashCardDto(String title, String notes, String date) {
        this.title = title;
        this.notes = notes;
        this.date = date;
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

    public FlashCard toFlashCard() {
        return new FlashCard(title , notes , date);
    }

}
