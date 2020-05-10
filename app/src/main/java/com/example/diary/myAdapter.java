package com.example.diary;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.RecyclerViewHolder>{
    private List<Note> notes;
    private Context context;

    public myAdapter(Context context) {
        this.context = context;
        notes = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note, parent, false);
        return new RecyclerViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.bind (notes.get (position));
    }

    @Override
    public int getItemCount() {
        return notes.size ();
    }

    public void addNote (Note note) {
        notes.add (note);
        notifyItemChanged (getItemCount() - 1);
    }

    public void clearNotes () {
        notes.clear ();
        notifyDataSetChanged();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private TextView important;
        private CheckBox checkBox;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById (R.id.noteText);
            important = itemView.findViewById (R.id.noteImportant);
            checkBox = itemView.findViewById (R.id.noteDone);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int pos = getAdapterPosition();
                    notes.get (pos).setDone(isChecked);
                }
            });
        }

        public void bind (Note note) {
            text.setText (note.getText());
            important.setText (note.getImportant ());
            checkBox.setChecked(note.getDone());
        }
    }
}
