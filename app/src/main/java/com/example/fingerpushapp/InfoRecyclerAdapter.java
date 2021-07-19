package com.example.fingerpushapp;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fingerpush.android.FingerPushManager;
import com.fingerpush.android.NetworkUtility;

import org.json.JSONObject;

import java.util.ArrayList;


public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.MyViewHolder> {
    public ArrayList<InfoItem> list = new ArrayList<>();


    public InfoRecyclerAdapter(ArrayList<InfoItem> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.info_recycler, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;


        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_Title);
            content = itemView.findViewById(R.id.text_content);


        }
    }

    @Override
    public void onBindViewHolder(InfoRecyclerAdapter.MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getAppKey());
        holder.content.setText(list.get(position).getAppName());
        System.out.println("타이틀" + holder.title);
        System.out.println("내용" + holder.content);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
