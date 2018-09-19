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
import com.example.dell.qunlsch.model.Sach;
import com.example.dell.qunlsch.R;

import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    private List<Sach> sachList;
    private OnDelete onDelete;
    private OnEdit onEdit;

    public SachAdapter(List<Sach> sachList, OnDelete onDelete, OnEdit onEdit) {
        this.sachList = sachList;
        this.onDelete = onDelete;
        this.onEdit = onEdit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_sach,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sach st = sachList.get(position);
        holder.tvName.setText(st.getName());
        holder.tvSoluong.setText(st.getSoluong());
        holder.tvMa.setText(st.getMasach());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete.OnDelete();
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEdit.OnEdit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvatar,imgEdit,imgDelete;
        public TextView tvName;
        public TextView tvSoluong,tvMa;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar_Sach);
            tvName = itemView.findViewById(R.id.tvTenSach_Sach);
            tvSoluong = itemView.findViewById(R.id.tvSoluong_Sach);
            tvMa=itemView.findViewById(R.id.tvMaSach_Sach);
            imgEdit = itemView.findViewById(R.id.imgEditSach_item);
            imgDelete = itemView.findViewById(R.id.imgDeleteSach_item);
        }

    }
}
