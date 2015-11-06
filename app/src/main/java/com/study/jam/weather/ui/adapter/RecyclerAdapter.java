package com.study.jam.weather.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.jam.weather.R;
import com.study.jam.weather.model.Weather;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<Weather> data;
    MyViewHolder viewHolder;

    public RecyclerAdapter(ArrayList<Weather> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        viewHolder = new MyViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder.bindData(data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView weather_img;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title_item);
            weather_img = (ImageView) itemView.findViewById(R.id.image_item);
        }

        public void bindData(String titleStr) {
            title.setText(titleStr);
        }

    }

}
