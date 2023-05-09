package com.example.flashcardsoftwareapplication.presentation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.flashcardsoftwareapplication.R;
import com.example.flashcardsoftwareapplication.databinding.ActivityViewFlashCardBinding;
import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;
import com.example.flashcardsoftwareapplication.util.ParcableConstant;

import org.parceler.Parcels;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ViewFlashCard extends AppCompatActivity {

    private ActivityViewFlashCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewFlashCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FlashCard flashCard = Parcels.unwrap(getIntent().getParcelableExtra(ParcableConstant.PARCEL_NAME));
        init(flashCard);
    }

    private void init(FlashCard flashCard) {
        binding.flashcardViewTitle.setText(flashCard.getTitle());
        binding.flashcardViewNotes.setText(flashCard.getNotes());
        binding.editFlashcard.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(ParcableConstant.PARCEL_NAME , Parcels.wrap(flashCard));
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        finish();
    }
}