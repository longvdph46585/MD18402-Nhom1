package com.example.java_story_bk.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_story_bk.R;
import com.example.java_story_bk.models.Chapter;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.screens.ReadChapterInStory;

import java.util.ArrayList;

public class AdapterChaptersStory extends RecyclerView.Adapter<AdapterChaptersStory.ViewHolder> {
    private ArrayList<Chapter> dataList;
    private Context context;
    private StoryInfo storyInfo;

    public AdapterChaptersStory(ArrayList<Chapter> dataList, Context context, StoryInfo storyInfo) {
        this.dataList = dataList;
        this.storyInfo = storyInfo;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_chapter,parent,false);
        return new AdapterChaptersStory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chapter currentChapter = dataList.get(position);
        holder.text_index_chapter.setText(currentChapter.getIndex() + "");
        holder.text_name_chapter.setText(currentChapter.getChapter_name() + "");
holder.layoutWrapItemChapter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ReadChapterInStory.class);
        intent.putExtra("id_chapter", currentChapter.get_id()); // Dữ liệu cần truyền
        intent.putExtra("story_info",storyInfo );
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        } else {
            return dataList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_index_chapter,text_name_chapter;
        LinearLayout layoutWrapItemChapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            text_index_chapter = itemView.findViewById(R.id.text_index_chapter);
            text_name_chapter = itemView.findViewById(R.id.text_name_chapter);
            layoutWrapItemChapter = itemView.findViewById(R.id.layoutWrapItemChapter);
        }
    }
}
