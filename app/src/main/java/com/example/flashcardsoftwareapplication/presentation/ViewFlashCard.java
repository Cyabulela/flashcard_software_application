package com.example.flashcardsoftwareapplication.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.flashcardsoftwareapplication.R;
import com.example.flashcardsoftwareapplication.databinding.ActivityViewFlashCardBinding;
import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;
import com.example.flashcardsoftwareapplication.util.ParcableConstant;

import org.parceler.Parcels;

public class ViewFlashCard extends AppCompatActivity {

    private ActivityViewFlashCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewFlashCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}