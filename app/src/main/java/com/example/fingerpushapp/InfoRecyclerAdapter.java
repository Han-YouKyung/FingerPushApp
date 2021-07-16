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
    public ArrayList<InfoItem> list;

    public InfoRecyclerAdapter(ArrayList<InfoItem> list) {
        this.list = list;
    }

    TextView title;
    TextView content;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.info_recycler, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_Title);
            content = itemView.findViewById(R.id.text_content);



        }
    }

    @Override
    public void onBindViewHolder(InfoRecyclerAdapter.MyViewHolder holder, int position) {
        FingerPushManager.getInstance(holder.itemView.getContext()).getAppReport(
                new NetworkUtility.ObjectListener() { // 비동기 이벤트 리스너

                    @Override
                    public void onError(String code, String message) {

                    }

                    @Override
                    public void onComplete(String code, String message, JSONObject data) {
                        String AppKey = data.optString("appid");
                        String AppName = data.optString("app_name");
                        String User_Id = data.optString("user_id");
                        String Icon = data.optString("icon");
                        String Category = data.optString("category");
                        String Environments = data.optString("environments");
                        String BeAndroid = data.optString("beandroid");
                        String VersionName = data.optString("android_version");
                        int VersionCode = data.optInt("android_int_version");
                        String AndroidUpdateLink = data.optString("android_upd_link");
                        String BeUpdateLink = data.optString("beupdalert_a");
                        String UpdateDate = data.optString("ver_update_date_a");


                        title = holder.itemView.findViewById(R.id.text_Title);
                        content = holder.itemView.findViewById(R.id.text_content);


                        InfoItem item = list.get(position);

                        //ArrayList<data>
                        //title.setText(list.get(position));

                        title.setText(item.getAppKey());
                        //title.setText(AppKey);
                        content.setText(item.getAppName());
                        System.out.println(title);
                        System.out.println(content);
                  /*      holder.subTitle_item.setText("부제 : " + item.getSubTitle());
                        holder.pubDate_item.setText("재작년도 : " + item.getPubDate());
                        holder.director_item.setText("감독 : " + item.getDirector());
                        holder.actor_item.setText("배우 : " + item.getActor());
                        holder.rating_item.setText("평점 : " + item.getRating());*/

                          /*  System.out.println(AppKey);
                            System.out.println(AppName);*/

                    }

                }
        );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
