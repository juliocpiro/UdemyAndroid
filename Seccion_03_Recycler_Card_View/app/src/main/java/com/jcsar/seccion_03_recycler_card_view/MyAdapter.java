package com.jcsar.seccion_03_recycler_card_view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> names;
    private int layout;
    private OnItemClickListener itemClickListener;

    public MyAdapter(List<String> names, int layout, OnItemClickListener itemClickListener) {
        this.names = names;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(names.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTexView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemTexView = (TextView) itemView.findViewById(R.id.textViewName);
        }
        public void bind(final String name, final OnItemClickListener listener){
            this.itemTexView.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(name,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{
        public void OnItemClick(String name, int position);
    }
}
