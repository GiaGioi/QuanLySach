package com.example.dell.qunlsch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.listener.OnClick;
import com.example.dell.qunlsch.model.HoaDon;
import com.example.dell.qunlsch.R;

import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder>{
    private List<HoaDon> hoaDonList;
    private OnDelete onDelete;
    private OnClick onClick;

    public HoaDonAdapter(List<HoaDon> hoaDonList, OnDelete onDelete, OnClick onClick) {
        this.hoaDonList = hoaDonList;
        this.onDelete = onDelete;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_hoadon,parent,false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick();
            }
        });
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoaDon st = hoaDonList.get(position);
        holder.tvMa.setText(st.getMa());
        holder.tvNgay.setText(st.getNgay());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete.OnDelete();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvatar,imgDelete;
        public TextView tvNgay,tvMa;


        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar_HoaDon);
            tvMa = itemView.findViewById(R.id.tvMaHoaDon_HoaDon);
            tvNgay = itemView.findViewById(R.id.tvNgay_HoaDon);
            imgDelete=itemView.findViewById(R.id.imgDeleteHoaDon_item);
        }

    }
}
