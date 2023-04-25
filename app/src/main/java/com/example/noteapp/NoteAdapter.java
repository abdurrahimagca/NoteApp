package com.example.noteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder>{
    Context context;
    List<Note> note;

    public NoteAdapter(Context context, List<Note> note) {
        this.context = context;
        this.note = note;
    }



    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.note,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note noteL = note.get(position);

        holder.noteHeader.setText(noteL.getNoteHead());
        holder.notetv.setText(noteL.getNoteHead());

    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView noteHeader,notetv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            noteHeader = itemView.findViewById(R.id.textViewTitle);
            notetv = itemView.findViewById(R.id.textViewDescription);

        }
    }
}