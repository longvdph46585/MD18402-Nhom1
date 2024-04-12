package com.example.java_story_bk.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.java_story_bk.R;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.models.StoryInfoWithIdChapterUpdate;
import com.example.java_story_bk.screens.StoryInfoScreen;

import java.util.ArrayList;

public class AdapterListVerticalFollowedStories extends RecyclerView.Adapter<AdapterListVerticalFollowedStories.ViewHolder> {
    private ArrayList<StoryInfoWithIdChapterUpdate> dataList;
    private Context context;
    public AdapterListVerticalFollowedStories(ArrayList<StoryInfoWithIdChapterUpdate> list, Context context) {
        this.dataList = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_story_vertical,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StoryInfoWithIdChapterUpdate currentStory = this.dataList.get(position);

        Glide
                .with(context)
                .load(currentStory.getStory_picture())
                .centerCrop()
//                .placeholder(R.drawable.dashboard_icon_black)
                .into(holder.imageStory);

        holder.linearLayoutWrapItemVertical_name.setText(currentStory.getStory_name());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoryInfoScreen.class);
                intent.putExtra("story_info",currentStory );
                context.startActivity(intent);
            }
        });
        holder.linearLayoutWrapItemVertical_author.setText(currentStory.getAuhtor_name());
        holder.linearLayoutWrapItemVertical_genres.setText("Thể loại: " +currentStory.getStory_genre());
        holder.linearLayoutWrapItemVertical_countRead.setText("Lượt theo dõi: " +currentStory.getCount_followers_story());
        holder.linearLayoutWrapItemVertical_chapters.setText( "Số chap: " +currentStory.getCount_chapters());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        ImageView imageStory;
        TextView linearLayoutWrapItemVertical_name,linearLayoutWrapItemVertical_author,linearLayoutWrapItemVertical_countRead,linearLayoutWrapItemVertical_genres,linearLayoutWrapItemVertical_chapters;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout= itemView.findViewById(R.id.linearLayoutWrapItemVertical);

            imageStory = itemView.findViewById(R.id.linearLayoutWrapItemVertical_img);
            linearLayoutWrapItemVertical_name = itemView.findViewById(R.id.linearLayoutWrapItemVertical_name);
            linearLayoutWrapItemVertical_author = itemView.findViewById(R.id.linearLayoutWrapItemVertical_author);
            linearLayoutWrapItemVertical_countRead = itemView.findViewById(R.id.linearLayoutWrapItemVertical_countRead);
            linearLayoutWrapItemVertical_genres = itemView.findViewById(R.id.linearLayoutWrapItemVertical_genres);
            linearLayoutWrapItemVertical_chapters = itemView.findViewById(R.id.linearLayoutWrapItemVertical_chapters);



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
