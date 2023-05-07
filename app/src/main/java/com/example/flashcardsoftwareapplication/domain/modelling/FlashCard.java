package com.example.flashcardsoftwareapplication.domain.modelling;

import com.example.flashcardsoftwareapplication.data.dto.FlashCardDto;

public class FlashCard {

    private final String title , notes , date;

    public FlashCard(String title, String notes, String date) {
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

    public FlashCardDto toFlashCardDto() {
        return new FlashCardDto(title , notes , date);
    }
}
