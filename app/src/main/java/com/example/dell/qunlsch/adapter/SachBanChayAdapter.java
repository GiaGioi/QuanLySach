package com.example.dell.qunlsch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.qunlsch.model.Book;
import com.example.dell.qunlsch.R;

import java.util.List;

public class SachBanChayAdapter extends RecyclerView.Adapter<SachBanChayAdapter.ViewHolder> {
    private List<Book> bookList;

    public SachBanChayAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public SachBanChayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_sachbanchay, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SachBanChayAdapter.ViewHolder holder, int position) {
        Book st = bookList.get(position);

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvatar;
        public TextView tvName;
        public TextView tvSoluong, tvMa;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar_SachBanChay);
            tvName = itemView.findViewById(R.id.tvTenSach_SachBanChay);
            tvSoluong = itemView.findViewById(R.id.tvSoluong_SachBanChay);
            tvMa = itemView.findViewById(R.id.tvMaSach_SachBanChay);
        }

    }
}
