package com.example.mp5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lib.LocationNames;

import java.util.LinkedList;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView emptyText;
    private LabListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_labs, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);

        adapter = new FavLabListAdapter(container.getContext(), DataManager.getFavoriteItems());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Resuming Fav Fragment");
        adapter.notifyItemRangeChanged(0, DataManager.getFavoriteItems().size());
    }
}
