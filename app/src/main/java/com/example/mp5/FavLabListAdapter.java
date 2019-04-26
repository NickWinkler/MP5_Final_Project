package com.example.mp5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class FavLabListAdapter extends LabListAdapter {

    public FavLabListAdapter(Context context, List<LocationItem> wordList) {
        super(context, wordList);
    }

    @Override
    public void onBindViewHolder(@NonNull final LabListViewHolder labListViewHolder, final int position) {
        final LocationItem location = DataManager.getFavoriteItems().get(position);
        System.out.println("Setting text");
        labListViewHolder.lab_name.setText(location.getName());
        labListViewHolder.walk_time.setText(location.getTime());
        labListViewHolder.machine_count.setText(Integer.toString(location.getMachineUsage()) +
                "/" + Integer.toString(location.getMachineCount()));
        labListViewHolder.fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = labListViewHolder.getAdapterPosition();
                Toast toast = Toast.makeText(inflater.getContext(), "Flipping Favorite State for " +
                        DataManager.getFavoriteItems().get(itemPosition), Toast.LENGTH_SHORT);
                DataManager.removeFavorite(itemPosition);
                labListViewHolder.adapter.notifyItemRemoved(itemPosition);
                labListViewHolder.adapter.notifyItemRangeChanged(itemPosition, getItemCount());
                System.out.println(itemPosition + ", " + getItemCount());
                toast.setGravity(Gravity.BOTTOM|Gravity.BOTTOM, 0, 175);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataManager.getFavoriteItems().size();
    }
}
