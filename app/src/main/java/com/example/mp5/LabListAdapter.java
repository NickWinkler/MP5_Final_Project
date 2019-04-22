package com.example.mp5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class LabListAdapter extends RecyclerView.Adapter<LabListAdapter.LabListViewHolder> {

    private final LinkedList<String> wordList;
    private LayoutInflater inflater;

    public LabListAdapter(Context context, LinkedList<String> wordList) {
        inflater = LayoutInflater.from(context);
        this.wordList = wordList;
    }

    class LabListViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;
        final LabListAdapter adapter;
        public LabListViewHolder(@NonNull View itemView, LabListAdapter adapter) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
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
    public void onBindViewHolder(@NonNull LabListViewHolder labListViewHolder, int position) {
        String current = wordList.get(position);
        labListViewHolder.textView.setText(current);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
