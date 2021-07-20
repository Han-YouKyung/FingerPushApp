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


public class NoticeRecyclerAdapter extends RecyclerView.Adapter<NoticeRecyclerAdapter.MyViewHolder> {
    public ArrayList<NoticeItem> list = new ArrayList<>();


    public NoticeRecyclerAdapter(Context context, ArrayList<NoticeItem> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notice_recylcer, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView message;
        TextView date;


        public MyViewHolder(View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.text_message);
            date = itemView.findViewById(R.id.text_date);


        }
    }

    @Override
    public void onBindViewHolder(NoticeRecyclerAdapter.MyViewHolder holder, int position) {
        holder.message.setText(list.get(position).getMessage());
        holder.date.setText(list.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
