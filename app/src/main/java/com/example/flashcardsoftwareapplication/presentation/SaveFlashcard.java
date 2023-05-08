package com.example.flashcardsoftwareapplication.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.flashcardsoftwareapplication.R;
import com.example.flashcardsoftwareapplication.databinding.ActivitySaveFlashcardBinding;
import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;
import com.example.flashcardsoftwareapplication.util.ParcableConstant;

import org.parceler.Parcels;

public class SaveFlashcard extends AppCompatActivity {

    private ActivitySaveFlashcardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaveFlashcardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FlashCard flashCard = Parcels.unwrap(getIntent().getParcelableExtra(ParcableConstant.PARCEL_NAME));
        if (flashCard != null) {
            setContent(flashCard);
        }
    }

    private void setContent(FlashCard flashCard) {

    }
}