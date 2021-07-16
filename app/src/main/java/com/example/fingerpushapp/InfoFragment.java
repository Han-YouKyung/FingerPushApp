package com.example.fingerpushapp;

import android.app.LauncherActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fingerpush.android.FingerPushManager;
import com.fingerpush.android.NetworkUtility;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    InfoRecyclerAdapter infoRecyclerAdapter;
    RecyclerView recyclerView;
    ArrayList<InfoItem> list;
    TextView title;
    TextView content;

    //getAppReport 사용


    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      /*  View v = inflater.inflate(R.layout.fragment_info, container, false);
        recyclerView = v.findViewById(R.id.recyclerView2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ArrayList<LauncherActivity.ListItem> list = new ArrayList<>();
        infoRecyclerAdapter = new InfoRecyclerAdapter(list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(infoRecyclerAdapter);*/

        FingerPushManager.getInstance(getContext()).getAppReport(
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

                        //ArrayList<data>
                        //title.setText(list.get(position));

                        View v = inflater.inflate(R.layout.fragment_info, container, false);
                       /* title = v.findViewById(R.id.textView5);
                        content = v.findViewById(R.id.textView6);*/
                        //  InfoItem item = list.get(position);
                        //ArrayList<data>
                        //title.setText(list.get(position));

                     /*   System.out.println(AppKey);
                        System.out.println(AppName);*/
                       // View view = inflater.inflate(R.layout.info_recycler, container, false);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        infoRecyclerAdapter = new InfoRecyclerAdapter(list);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(infoRecyclerAdapter);


                      /*  title.setText(AppKey);
                        content.setText(AppName);*/

//                        recyclerView.setLayoutManager(linearLayoutManager);


                        // recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView3);

                        infoRecyclerAdapter = new InfoRecyclerAdapter(list);
                        recyclerView.setAdapter(infoRecyclerAdapter);


                    }

                }
        );


        return inflater.inflate(R.layout.fragment_info, container, false);
    }
}

/*

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
                        title.setText(AppKey);
                        System.out.println(title);
                  /*      holder.subTitle_item.setText("부제 : " + item.getSubTitle());
                        holder.pubDate_item.setText("재작년도 : " + item.getPubDate());
                        holder.director_item.setText("감독 : " + item.getDirector());
                        holder.actor_item.setText("배우 : " + item.getActor());
                        holder.rating_item.setText("평점 : " + item.getRating());*/

                          /*  System.out.println(AppKey);
                            System.out.println(AppName);

                    }

                            }
                            );

                            }

@Override
public int getItemCount() {
        return list.size();
        }


        }

        */