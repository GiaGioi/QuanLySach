package com.example.dell.qunlsch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.listener.OnEdit;
import com.example.dell.qunlsch.model.TypeBook;
import com.example.dell.qunlsch.R;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    private List<TypeBook> typeBookList;
    private OnDelete onDelete;
    private OnEdit onEdit;


    public TheLoaiAdapter(List<TypeBook> typeBookList, OnDelete onDelete, OnEdit onEdit) {
        this.typeBookList = typeBookList;
        this.onDelete = onDelete;
        this.onEdit = onEdit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_theloai, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final TypeBook typeBook = typeBookList.get(position);
        holder.tvMa.setText(typeBook.id);
        holder.tvName.setText(typeBook.name);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete.OnDelete(typeBook);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEdit.OnEdit(typeBook);
            }
        });
    }

    @Override
    public int getItemCount() {
        return typeBookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvatar, imgEdit, imgDelete;
        public TextView tvName;
        public TextView tvMa;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar_TheLoai);
            tvMa = itemView.findViewById(R.id.tvMaTheLoai_TheLoai);
            tvName = itemView.findViewById(R.id.tvName_TheLoai);
            imgEdit = itemView.findViewById(R.id.imgEditTheLoai_item);
            imgDelete = itemView.findViewById(R.id.imgDeleteTheLoai_item);
        }

    }
}
