package com.example.android.musicapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicapp.R;
import com.example.android.musicapp.databases.TopSongModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valky on 7/20/2017.
 */

public class TopSongAdapter extends RecyclerView.Adapter<TopSongAdapter.TopSongViewHolder>{
    private List<TopSongModel> topSongModelList = new ArrayList<>();
    private Context context;

    public TopSongAdapter(List<TopSongModel> topSongModelList, Context context) {
        this.topSongModelList = topSongModelList;
        this.context = context;
    }


    @Override
    public TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_top_songs, parent, false);

        return new TopSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopSongViewHolder holder, int position) {
        holder.setData(topSongModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return topSongModelList.size();
    }

    public class TopSongViewHolder extends RecyclerView.ViewHolder{
        ImageView ivBackground;
        ImageView ivIcon;
        TextView tvTitle;
        TextView tvAuthor;
        View view;

        public TopSongViewHolder(View itemView) {
            super(itemView);
            ivBackground = (ImageView) itemView.findViewById(R.id.iv_item_top_song_background);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_item_top_song_icon);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            view = itemView;
        }

        public void setData(TopSongModel topSongModel){
            if(topSongModel != null){
                Picasso.with(context).load(topSongModel.getImage()).into(ivIcon);
                tvTitle.setText(topSongModel.getName());
                tvAuthor.setText((topSongModel.getAuthor()));

                view.setTag(topSongModel);
            }
        }
    }
}
