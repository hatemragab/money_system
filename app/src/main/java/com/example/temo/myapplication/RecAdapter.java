package com.example.temo.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>{
    ArrayList<Mitem> arrayList;
    Context context;
    cominucation listnner;
    RecAdapter(Context context, ArrayList<Mitem> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rec_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Mitem p = arrayList.get(position);
        holder.name.setText(p.getName());
        holder.phone.setText(p.getPhone());
        holder.cost.setText(p.getCost());
        holder.method.setText(p.getMethod());
        holder.date.setText(p.getDate());
    }
    interface cominucation {
        void transfer(int postion);
    }
    public void Adaptorsync(cominucation listnner) {
        this.listnner = listnner;
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, phone, cost, method, date;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            cost = itemView.findViewById(R.id.cost);
            method = itemView.findViewById(R.id.method);
            date = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listnner.transfer(getAdapterPosition());
        }
    }

    public void setFilter(ArrayList<Mitem> newFilter)
    {
        arrayList=new ArrayList<>();
        arrayList.addAll(newFilter);
        notifyDataSetChanged();

    }
}
