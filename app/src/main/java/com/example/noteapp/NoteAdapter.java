package com.example.noteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NoteAdapter extends FirebaseRecyclerAdapter<Note, NoteAdapter.noteViewHolder>{
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NoteAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }


    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note, parent, false);
        return new NoteAdapter.noteViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull noteViewHolder holder, int position, @NonNull Note model) {
        holder.noteInfo.setText(model.getNoteInfo());
        holder.noteHead.setText(model.getNoteHead());

    }

    static class noteViewHolder extends RecyclerView.ViewHolder{
        TextView noteInfo,noteHead;
        public noteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteHead = itemView.findViewById(R.id.textViewTitle);
            noteInfo = itemView.findViewById(R.id.textViewDescription);


        }
    }
}