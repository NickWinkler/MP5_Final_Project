package com.example.mp5;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.lib.DataManager;
import com.example.lib.LocationItem;
import com.example.lib.LocationNames;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllLabsFragment extends Fragment {

    private RecyclerView recyclerView;
    private LabListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_labs, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);

        adapter = new LabListAdapter(container.getContext(), DataManager.getLocationItems());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }
}
