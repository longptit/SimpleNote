package com.example.firstapp;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class FragmentHome extends Fragment {
    private ArrayList<Folder> folders;
    private RecyclerView recyclerView;
    private FolderAdapter adapter;
    View v;
    private ImageView buttonAdd;
    int pos;
    private FolderDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.recyclerview);
        adapter = new FolderAdapter(getContext(), folders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        buttonAdd = v.findViewById(R.id.btn_add);
        recyclerView.setAdapter(adapter);
        adapter.setOnClick((position, folder) -> {
            this.pos = position;
            Intent intent = new Intent(v.getContext(), EditActivity.class);
            intent.putExtra("data", folders.get(position));
            startActivityForResult(intent, 69);

        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), AddActivity.class), 96);
            }
        });
        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 69 && resultCode == AddActivity.RESULT_OK) {
            Folder folder = (Folder) data.getSerializableExtra("resData");
            database.updateFolder(folder);
            folders.set(pos, folder);
            adapter.notifyDataSetChanged();

        }

        if(requestCode == 96 && resultCode ==   AddActivity.RESULT_OK){
            Folder folder = (Folder) data.getSerializableExtra("resData");
            database.addFolder(folder);
            folders.add(folder);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(folders.size()-1);
        }


        if (requestCode == 69 && resultCode == 100) {

            Folder folder = (Folder) data.getSerializableExtra("resData");
            database.deleteFolder(folder);
            folders.remove( folder);
            adapter.notifyItemRemoved(pos);

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Folder> folders;
        database = new FolderDatabase(getContext());


    }
}