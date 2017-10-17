package com.jcsar.seccion_03_lab.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcsar.seccion_03_lab.R;
import com.jcsar.seccion_03_lab.models.Fruit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> fruits;
    private int layout;
    private Activity activity;
    private OnItemClickListener listener;

    public FruitAdapter(List<Fruit> fruits, int layout, Activity activity, OnItemClickListener listener) {
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(fruits.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView textViewName;
        public TextView textViewDescription;
        public TextView textViewQuantity;
        public ImageView imageViewBackground;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            textViewQuantity = (TextView) itemView.findViewById(R.id.textViewQuantity);
            imageViewBackground = (ImageView) itemView.findViewById(R.id.imageViewBackground);

            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruit fruit, final OnItemClickListener listener){
            this.textViewName.setText(fruit.getName());
            this.textViewDescription.setText(fruit.getDescription());
            this.textViewQuantity.setText(fruit.getQuantity()+"");

            if(fruit.getQuantity() == Fruit.LIMIT_QUANTITY){
                textViewQuantity.setTextColor(ContextCompat.getColor(activity,R.color.colorAlert));
                textViewQuantity.setTypeface(null, Typeface.BOLD);
            }else{
                textViewQuantity.setTextColor(ContextCompat.getColor(activity,R.color.defaultTextColor));
                textViewQuantity.setTypeface(null, Typeface.NORMAL);
            }

            Picasso.with(activity).load(fruit.getImgBackground()).fit().into(this.imageViewBackground);

            this.imageViewBackground.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    listener.OnItemClick(fruit,getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            Fruit fruitSelected = fruits.get(this.getAdapterPosition());

            contextMenu.setHeaderTitle(fruitSelected.getName());
            contextMenu.setHeaderIcon(fruitSelected.getImgIcon());

            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.context_menu_fruit,contextMenu);

            for(int i = 0; i < contextMenu.size(); i++){
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            switch (menuItem.getItemId()){
                case R.id.delete_fruit :
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.reset_fruit_quantity:
                    fruits.get(getAdapterPosition()).resetQuantity();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return true;
            }

        }
    }

    public interface OnItemClickListener {
        void OnItemClick(Fruit fruit, int position);
    }
}
