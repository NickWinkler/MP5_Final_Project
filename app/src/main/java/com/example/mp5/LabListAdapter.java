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

import java.util.List;

public class LabListAdapter extends RecyclerView.Adapter<LabListAdapter.LabListViewHolder> {

    private final List<LocationItem> wordList;
    protected LayoutInflater inflater;

    public LabListAdapter(Context context, List<LocationItem> wordList) {
        inflater = LayoutInflater.from(context);
        this.wordList = wordList;
    }

    class LabListViewHolder extends RecyclerView.ViewHolder {
        public final TextView lab_name;
        public final TextView machine_count;
        public final TextView walk_time;
        public final ImageButton fav_button;
        final LabListAdapter adapter;
        public LabListViewHolder(@NonNull View itemView, LabListAdapter adapter) {
            super(itemView);
            lab_name = itemView.findViewById(R.id.lab_name_text);
            machine_count = itemView.findViewById(R.id.machine_count_text);
            walk_time = itemView.findViewById(R.id.walk_time_text);
            fav_button = itemView.findViewById(R.id.favorite_button);
            this.adapter = adapter;
        }
    }

    @NonNull
    @Override
    public LabListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        System.out.println("View holder created");
        View view = inflater.inflate(R.layout.lab_list_item, viewGroup, false);
        return new LabListViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final LabListViewHolder labListViewHolder, final int position) {
        final LocationItem location = DataManager.getLocationItems().get(position);
        System.out.println("Setting text");
        labListViewHolder.lab_name.setText(location.getName());
        labListViewHolder.walk_time.setText(location.getTime());
        labListViewHolder.machine_count.setText(Integer.toString(location.getMachineUsage()) +
                "/" + Integer.toString(location.getMachineCount()));
        labListViewHolder.fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.addFavorite(DataManager.getLocationItems().get(labListViewHolder.getAdapterPosition()));
                Toast toast = Toast.makeText(inflater.getContext(), "Flipping Favorite State for " + wordList.get(position),
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 175);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataManager.getLocationItems().size();
    }
}
