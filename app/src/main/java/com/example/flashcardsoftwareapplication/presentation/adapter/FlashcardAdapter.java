package com.example.flashcardsoftwareapplication.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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
            this.binding.flashcardItem.setOnClickListener(this);
        }

        public void setData(FlashCard flashCard) {
            binding.flashcardTitle.setText(flashCard.getTitle());
            String lastUpdateDate = binding.flashcardLastupdate.getText().toString();
            String labal = "latest update:";
            binding.flashcardLastupdate.setText((lastUpdateDate.equals(labal)) ? lastUpdateDate + flashCard.getDate() : lastUpdateDate);
            binding.flashcardNotes.setText(flashCard.getNotes());
            binding.flashcardDelete.setOnClickListener(view -> {
                boolean isItemRemoved = repository.deleteFlashCard(flashCard);
                if (isItemRemoved) {
                    flashCards.remove(flashCard);
                    notifyDataSetChanged();
                    if (flashCards.size() == 0) {
                        mainBinding.flashcardLists.setVisibility(View.GONE);
                        mainBinding.emptyListText.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        public void setOnClickListener(FlashCard flashCard) {
            Intent intent = new Intent(context , ViewFlashCard.class);
            intent.putExtra(ParcableConstant.PARCEL_NAME , flashCard);
            context.startActivity(intent);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            setOnClickListener(flashCards.get(position));
        }
    }
}
