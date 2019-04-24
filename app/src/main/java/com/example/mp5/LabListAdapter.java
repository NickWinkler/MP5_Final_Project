package com.example.mp5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class LabListAdapter extends RecyclerView.Adapter<LabListAdapter.LabListViewHolder> {

    private final LinkedList<String> wordList;
    private LayoutInflater inflater;

    public LabListAdapter(Context context, LinkedList<String> wordList) {
        inflater = LayoutInflater.from(context);
        this.wordList = wordList;
    }

    class LabListViewHolder extends RecyclerView.ViewHolder {
        public final TextView lab_name;
        public final ImageButton fav_button;
        final LabListAdapter adapter;
        public LabListViewHolder(@NonNull View itemView, LabListAdapter adapter) {
            super(itemView);
            lab_name = itemView.findViewById(R.id.lab_name_text);
            fav_button = itemView.findViewById(R.id.favorite_button);
            this.adapter = adapter;
        }
    }

    @NonNull
    @Override
    public LabListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.lab_list_item, viewGroup, false);
        return new LabListViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final LabListViewHolder labListViewHolder, final int position) {
        String current = wordList.get(position);
        labListViewHolder.lab_name.setText(current);
        labListViewHolder.fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(inflater.getContext(), "Flipping Favorite State for " + wordList.get(position),
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.BOTTOM, 0, 175);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
