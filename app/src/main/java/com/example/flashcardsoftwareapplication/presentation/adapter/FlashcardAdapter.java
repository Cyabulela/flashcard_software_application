package com.example.flashcardsoftwareapplication.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.flashcardsoftwareapplication.databinding.ActivityMainBinding;
import com.example.flashcardsoftwareapplication.databinding.NotesBinding;
import com.example.flashcardsoftwareapplication.domain.modelling.FlashCard;
import com.example.flashcardsoftwareapplication.domain.repository.FlashCardRepository;
import com.example.flashcardsoftwareapplication.presentation.ViewFlashCard;
import com.example.flashcardsoftwareapplication.util.ParcableConstant;

import org.parceler.Parcels;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder>{

    private final List<FlashCard> flashCards;
    private final ActivityMainBinding mainBinding;
    private final FlashCardRepository repository;
    private final Context context;

    public FlashcardAdapter(Context context , List<FlashCard> flashCards, ActivityMainBinding binding , FlashCardRepository repository) {
        this.flashCards = flashCards;
        this.mainBinding = binding;
        this.context = context;
        this.repository = repository;
    }

    @NonNull
    @Override
    public FlashcardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotesBinding binding = NotesBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new FlashcardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FlashcardViewHolder holder, int position) {
        holder.setData(flashCards.get(position));
    }

    @Override
    public int getItemCount() {
        return flashCards.size();
    }

    class FlashcardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final NotesBinding binding;

        public FlashcardViewHolder(NotesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(FlashCard flashCard) {
            binding.flashcardTitle.setText(flashCard.getTitle());
            String lastUpdateDate = binding.flashcardLastupdate.getText().toString() + flashCard.getDate();
            binding.flashcardLastupdate.setText(lastUpdateDate);
            binding.flashcardNotes.setText(flashCard.getNotes());
            binding.flashcardDelete.setOnClickListener(view -> {
                notifyItemRemoved(flashCards.indexOf(flashCard));
                repository.deleteFlashCard(flashCard);
                if (flashCards.size() == 0) {
                    mainBinding.flashcardLists.setVisibility(View.GONE);
                    mainBinding.emptyListText.setVisibility(View.VISIBLE);
                }
            });
        }

        public void setOnClickListener(FlashCard flashCard) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(ParcableConstant.PARCEL_NAME , Parcels.wrap(flashCard));
            Intent intent = new Intent(context , ViewFlashCard.class);
            context.startActivity(intent);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            setOnClickListener(flashCards.get(position));
        }
    }
}
