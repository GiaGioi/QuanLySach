package com.example.dell.qunlsch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.model.BillDetail;
import com.example.dell.qunlsch.R;

import java.util.List;

public class HoaDonChiTietAdapter extends RecyclerView.Adapter<HoaDonChiTietAdapter.ViewHolder> {
    private List<BillDetail> hoaDonList;
    private OnDelete onDelete;

    public HoaDonChiTietAdapter(List<BillDetail> hoaDonList, OnDelete onDelete) {
        this.hoaDonList = hoaDonList;
        this.onDelete = onDelete;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_hoadonchitiet, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonChiTietAdapter.ViewHolder holder, int position) {
        final BillDetail st = hoaDonList.get(position);

        holder.tvName.setText(st.bookID);
        holder.tvQuality.setText(st.quality + "");
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete.OnDelete(st);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgDelete;
        public TextView tvName, tvQuality;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvQuality = itemView.findViewById(R.id.tvQuality);

            imgDelete = itemView.findViewById(R.id.imgDeleteHoaDonChiTiet_item);
        }

    }
}
