package com.example.fingerpushapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.fingerpush.android.FingerPushManager;
import com.fingerpush.android.NetworkUtility;
import com.fingerpush.android.dataset.DeviceInfo;
import com.fingerpush.android.dataset.TagList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeFragment extends Fragment {

    // TODO: Rename and change types and number of parameters
    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();

        return fragment;
    }


    Switch noticeSwitch;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_notice, container, false);

        noticeSwitch = (Switch) v.findViewById(R.id.switch1);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView2);

        recyclerView.setVisibility(View.INVISIBLE);


        FingerPushManager.getInstance(getContext()).getDeviceInfo(
                new NetworkUtility.ObjectListener() { // 비동기 이벤트 리스너

                    @Override
                    public void onError(String code, String message) {

                    }

                    @Override
                    public void onComplete(String code, String message, JSONObject ObjectData) {
                       /* String appkey = ObjectData.optString(DeviceInfo.APPKEY);
                        String device_type = ObjectData.optString(DeviceInfo.DEVICE_TYPE);
                        String activity = ObjectData.optString(DeviceInfo.ACTIVITY);
                       // activity : 푸시 수신 활성화 상태(A : 활성화, D : 비활성화)
                   //      ad_activity : 광고 푸시 수신 활성화 상태(A : 활성화, D : 비활성화)
                        String ad_activity = ObjectData.optString(DeviceInfo.AD_ACTIVITY);
                        String identity = ObjectData.optString(DeviceInfo.IDENTITY);
                        String timezone = ObjectData.optString(DeviceInfo.TIMEZONE);
                        String country = ObjectData.optString(DeviceInfo.COUNTRY);
                        String version_code = ObjectData.optString(DeviceInfo.VERCODE);
                        String version_name = ObjectData.optString(DeviceInfo.VERNAME);
                        String os_version = ObjectData.optString(DeviceInfo.OSVER);*/

                        noticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked/* && activity.equals("A")*/) { // activity 불러내지 않아도 설정 입력 됨! (설정 저장 아직)

                                    Toast.makeText(getContext(), "알림설정 ON", Toast.LENGTH_SHORT).show();

                                    FingerPushManager.getInstance(getContext()).setPushEnable(
                                            true, // 푸시 활성화 여부 (true : 활성화, false : 비활성화)
                                            new NetworkUtility.ObjectListener() {
                                                @Override
                                                public void onComplete(String s, String s1, JSONObject jsonObject) {

                                                }

                                                @Override
                                                public void onError(String s, String s1) {

                                                }
                                            }); // 비동기 이벤트 리스너
                                } else {
                                    Toast.makeText(getContext(), "알림설정 OFF", Toast.LENGTH_SHORT).show();

                                    FingerPushManager.getInstance(getContext()).setPushEnable(
                                            false, // 푸시 활성화 여부 (true : 활성화, false : 비활성화)
                                            new NetworkUtility.ObjectListener() {
                                                @Override
                                                public void onComplete(String s, String s1, JSONObject jsonObject) {

                                                }

                                                @Override
                                                public void onError(String s, String s1) {

                                                }
                                            });// 비동기 이벤트 리스너
                                }
                            }
                        });

                    }

                }
        );


        return v;
    }
}

/*
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

    Context context;
    ArrayList<TagList> dataList = new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<TagList> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notice, parent, false);
        Holder holder = new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}*/