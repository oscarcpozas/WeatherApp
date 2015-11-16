package com.study.jam.weather.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.jam.weather.R;
import com.study.jam.weather.model.Weather;
import com.study.jam.weather.ui.activity.DetailsActivity;

import java.net.ConnectException;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<Weather> data;
    private MyViewHolder viewHolder;
    private Context context;

    public RecyclerAdapter(Context context, ArrayList<Weather> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        viewHolder = new MyViewHolder(itemView, new MyViewHolder.MyViewHolderClicks() {
            @Override
            public void onTextViewIsClicked(View view) { // Esto es el Listener que salta cuando se clicka nuestro TextView de alguno de los item de la lista
                TextView textView = (TextView) view;

                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("TITLE", textView.getText().toString());
                context.startActivity(i);
            }

            @Override
            public void onImageViewIsClicked(View view) { }
        });

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

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private ImageView weather_img;
        private MyViewHolderClicks listener;

        public MyViewHolder(View itemView, MyViewHolderClicks listener) {
            super(itemView);

            this.listener = listener;

            title = (TextView) itemView.findViewById(R.id.title_item);
            title.setOnClickListener(this);

            weather_img = (ImageView) itemView.findViewById(R.id.image_item);
            weather_img.setOnClickListener(this);
        }

        public void bindData(String titleStr) {
            title.setText(titleStr);
        }

        @Override
        public void onClick(View v) {
            if(v instanceof TextView) {
                listener.onTextViewIsClicked(v);
            } else if (v instanceof ImageView) {
                listener.onImageViewIsClicked(v);
            }
        }

        public interface MyViewHolderClicks {
            void onTextViewIsClicked(View v);
            void onImageViewIsClicked(View v);
        }
    }

}
