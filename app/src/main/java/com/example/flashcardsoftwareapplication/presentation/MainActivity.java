package com.example.flashcardsoftwareapplication.presentation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.flashcardsoftwareapplication.data.repository.FlashCardSQLiteDatabaseRepository;
import com.example.flashcardsoftwareapplication.databinding.ActivityMainBinding;
import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;
import com.example.flashcardsoftwareapplication.domain.repository.FlashCardRepository;
import com.example.flashcardsoftwareapplication.presentation.adapter.FlashcardAdapter;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<FlashCard> flashCardList;
    private FlashCardRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = new FlashCardSQLiteDatabaseRepository(this);
        init();
        setActionListeners();
    }

    private void init() {
        flashCardList = repository.getFlashCards();
        if (flashCardList.size() == 0) {
            binding.flashcardLists.setVisibility(View.GONE);
            binding.emptyListText.setVisibility(View.VISIBLE);
        } else {
            binding.flashcardLists.setVisibility(View.VISIBLE);
            binding.emptyListText.setVisibility(View.GONE);
            setAdapter();
        }
    }

    private void setActionListeners() {
        binding.addFlashcard.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.clear();
            Intent intent = new Intent(this , SaveFlashcard.class);
            startActivity(intent);
        });
    }

    private void  setAdapter() {
        FlashcardAdapter adapter = new FlashcardAdapter(this , flashCardList , binding , repository);
        binding.flashcardLists.setAdapter(adapter);
    }
}