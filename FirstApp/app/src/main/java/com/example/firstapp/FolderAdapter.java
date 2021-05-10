package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Folder> folders;
    private OnClickRecyclerView onClickRecyclerView;

    public FolderAdapter(Context context, ArrayList<Folder> folders) {
        this.context = context;
        this.folders = folders;
    }
    public void setOnClick(OnClickRecyclerView onClickRecyclerView) {
        this.onClickRecyclerView = onClickRecyclerView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_folder, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Folder folder = folders.get(position);
        holder.title.setText(folder.getTitle());
        holder.detail.setText(folder.getDetail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRecyclerView.clickItem(position, folder);
            }
        });
        //holder.textClock.setFormat12Hour(null);

    }

    @Override
    public int getItemCount() {
        return folders.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView detail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            detail = itemView.findViewById(R.id.tv_detail);
        }
    }
    public interface OnClickRecyclerView{
        void clickItem(int position,Folder folder);
    }

}
