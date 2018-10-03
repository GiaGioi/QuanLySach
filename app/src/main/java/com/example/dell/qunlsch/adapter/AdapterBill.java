package com.example.dell.qunlsch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.qunlsch.listener.OnAddBillListener;
import com.example.dell.qunlsch.listener.OnBillDeleteListener;
import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.listener.OnClick;
import com.example.dell.qunlsch.model.Bill;
import com.example.dell.qunlsch.R;
import com.example.dell.qunlsch.sqlitedao.BillDAO;

import java.util.Date;
import java.util.List;

public class AdapterBill extends RecyclerView.Adapter<AdapterBill.ViewHolder> {
    private List<Bill> billList;
    private OnBillDeleteListener onDelete;
    private OnAddBillListener onClick;
    private BillDAO billDAO;

    public AdapterBill(BillDAO billDAO, List<Bill> billList, OnBillDeleteListener onDelete, OnAddBillListener onClick) {
        this.billDAO = billDAO;
        this.billList = billList;
        this.onDelete = onDelete;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View itemView = inflater.inflate(R.layout.item_hoadon, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Bill st = billList.get(position);
        holder.tvMa.setText(st.id);
        holder.tvNgay.setText(new Date(st.date).toString());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete.onDelete(st.id, position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onAddBill(billList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvatar, imgDelete;
        public TextView tvNgay, tvMa;


        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar_HoaDon);
            tvMa = itemView.findViewById(R.id.tvMaHoaDon_HoaDon);
            tvNgay = itemView.findViewById(R.id.tvNgay_HoaDon);
            imgDelete = itemView.findViewById(R.id.imgDeleteHoaDon_item);
        }

    }
}
