package com.example.flashcardsoftwareapplication.presentation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.flashcardsoftwareapplication.data.repository.FlashCardSQLiteDatabaseRepository;
import com.example.flashcardsoftwareapplication.databinding.ActivitySaveFlashcardBinding;
import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;
import com.example.flashcardsoftwareapplication.domain.repository.FlashCardRepository;
import com.example.flashcardsoftwareapplication.util.ParcableConstant;
import org.parceler.Parcels;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SaveFlashcard extends AppCompatActivity {

    private ActivitySaveFlashcardBinding binding;
    private FlashCardRepository repository;
    private FlashCard flashCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaveFlashcardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = new FlashCardSQLiteDatabaseRepository(this);
        flashCard = Parcels.unwrap(getIntent().getParcelableExtra(ParcableConstant.PARCEL_NAME));
        init();
    }

    private void init() {
        if (flashCard != null) {
            binding.flashcardEditTitle.setText(flashCard.getTitle());
            binding.flashcardEditNotes.setText(flashCard.getNotes());
        }
        binding.saveFlashcard.setOnClickListener(view -> onSaveFlashcard());
    }

    private boolean isValidContent(String title) {
        return !title.isBlank();
    }


    private void onSaveFlashcard() {
        String title = binding.flashcardEditTitle.getText().toString();
        String notes = binding.flashcardEditNotes.getText().toString();
        if (!isValidContent(title)) {
            binding.flashcardEditTitle.setError("Title can not be blank.");
            return;
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm");
        String lastUpdateDate = LocalDateTime.now().format(dateFormat);
        FlashCard newFlashCard = new FlashCard(
                title,
                notes,
                lastUpdateDate,
                (flashCard != null) ? flashCard.getFlashcardID() : -1
        );
        saveFlashCard(newFlashCard);
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveFlashCard(FlashCard flashCard) {
        if (flashCard.getFlashcardID() != -1) {
            repository.updateFlashCard(flashCard);
        }else {
            repository.insectFlashCard(flashCard);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent;
        if (flashCard == null) {
            intent = new Intent(this , MainActivity.class);
        } else {
            intent = new Intent(this , ViewFlashCard.class);
        }
        startActivity(intent);
        finish();
    }
}