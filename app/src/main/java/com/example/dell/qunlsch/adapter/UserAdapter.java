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
import com.example.dell.qunlsch.model.User;
import com.example.dell.qunlsch.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> userList;
    private OnDelete onDelete;
    private OnEdit onEdit;

    public UserAdapter(List<User> userList, OnDelete onDelete, OnEdit onEdit) {
        this.userList = userList;
        this.onDelete = onDelete;
        this.onEdit = onEdit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_nguoidung, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User st = userList.get(position);
        holder.tvName.setText(st.getName());
        holder.tvSDT.setText(st.getSdt());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete.OnDelete(st);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEdit.OnEdit(st);
            }
        });
    }

    @Override
    public int getItemCount() {

        if (userList == null) return 0;

        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvatar, imgEdit, imgDelete;
        public TextView tvName;
        public TextView tvSDT;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar_NguoiDung);
            tvSDT = itemView.findViewById(R.id.tvSDT_NguoiDung);
            tvName = itemView.findViewById(R.id.tvName_NguoiDung);
            imgEdit = itemView.findViewById(R.id.imgEditNguoiDung_item);
            imgDelete = itemView.findViewById(R.id.imgDeleteNguoiDung_item);
        }

    }
}
