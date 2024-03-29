package com.example.java_story_bk.adapters;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.java_story_bk.R;
import com.example.java_story_bk.models.StoryInfo;

import java.util.ArrayList;

public class AdapterTopStories extends RecyclerView.Adapter<AdapterTopStories.ViewHolder> {
    private ArrayList<StoryInfo> dataList;
    private Context context;
    public  AdapterTopStories (ArrayList<StoryInfo> list, Context context) {
            this.dataList = list;
            this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top_story_horizontal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StoryInfo currentStory = this.dataList.get(position);

        Glide
                .with(context)
                .load(currentStory.getStory_picture())
                .centerCrop()
//                .placeholder(R.drawable.dashboard_icon_black)
                .into(holder.imageStory);
        holder.nameStory_el.setText(currentStory.getStory_name());
        holder.genre_el.setText(currentStory.getStory_genre().split(",")[0]);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    RelativeLayout wrapItemStoryTop_el;
    TextView nameStory_el,genre_el;
    ImageView imageStory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wrapItemStoryTop_el = itemView.findViewById(R.id.wrapItemStoryTop_el);
            nameStory_el = itemView.findViewById(R.id.nameStory_el);
            genre_el = itemView.findViewById(R.id.genre_el);
            imageStory = itemView.findViewById(R.id.imageStory);
        }
    }
    @Override
    public int getItemCount() {
        if(dataList == null) {
        return 0;}
        else {
            return dataList.size();
        }
    }
}
