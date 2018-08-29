package com.example.shade.simplenote;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shade.simplenote.Model.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder>
{
    private Context context;
    private List<Note> notesList;
    private OnItemClick dataTransfer;


    public NotesAdapter(Context context, List<Note> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public NotesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_viewholder, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.MyViewHolder holder, final int position)
    {
        if(position % 7 == 0){
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorone));
        }else if(position % 7 == 1){
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colortwo));
        }else if(position % 7 == 2){
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorthree));
        }else if(position % 7 == 3){
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorfour));
        }else if(position % 7 == 4){
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorfive));
        }else if(position % 7 == 5){
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorsix));
        }else if(position % 7 == 6){
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorseven));
        }


        final Note note = notesList.get(position);
        holder.note.setText(note.getNote());
        holder.timestamp.setText(note.getTimestamp());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,NoteBody.class);
                intent.putExtra("id",note.getId());
                intent.putExtra("body",note.getNote());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView note;
        public TextView timestamp;
        public CardView constraintLayout;

        public MyViewHolder(View view) {
            super(view);
            note = view.findViewById(R.id.notetext);
            timestamp = view.findViewById(R.id.timestamp);
            constraintLayout=view.findViewById(R.id.constLayout);
        }
    }

    public void removeItem(int position) {
        notesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Note note, int position) {
        notesList.add(position, note);
        notifyItemInserted(position);
    }

    public interface OnItemClick {
        void onClick (String value , int position);
    }


}
