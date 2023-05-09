package com.example.flashcardsoftwareapplication.domain.modelling;

import com.example.flashcardsoftwareapplication.data.dto.FlashCardDto;
import org.parceler.Parcel;

@Parcel
public class FlashCard {

    String title , notes , date ;
    int flashcardID;

    public FlashCard() {}

    public FlashCard(String title, String notes, String date, int flashcardID) {
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

    public FlashCardDto toFlashCardDto() {
        return new FlashCardDto(title , notes , date , flashcardID);
    }

    public int getFlashcardID() {
        return flashcardID;
    }
}
