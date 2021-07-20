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
import com.fingerpush.android.dataset.PushList;
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
    NoticeRecyclerAdapter noticeRecyclerAdapter;
    ArrayList<NoticeItem> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_notice, container, false);

        noticeSwitch = (Switch) v.findViewById(R.id.switch1);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView2);


        FingerPushManager.getInstance(getContext()).getDeviceInfo(
                new NetworkUtility.ObjectListener() { // 비동기 이벤트 리스너


                    @Override
                    public void onError(String code, String message) {

                    }

                    @Override
                    public void onComplete(String code, String message, JSONObject ObjectData) {

                        String activity = ObjectData.optString(DeviceInfo.ACTIVITY);
                        // System.out.println(activity);

                        if (activity.equals("A")) {
                            noticeSwitch.setChecked(true);
                        } else {
                            noticeSwitch.setChecked(false);
                        }


                        noticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
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
                                    noticeSwitch.setOnCheckedChangeListener(this);
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
                    //}

                }
        );

        FingerPushManager.getInstance(getContext()).getPushList(
                new NetworkUtility.ObjectListener() { // 비동기 이벤트 리스너

                    @Override
                    public void onError(String code, String message) {

                    }

                    @Override
                    public void onComplete(String code, String message, JSONObject object) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = object.getJSONArray(PushList.PUSHLIST);


                           /* msgTag : 메세지 고유 태그
                            date : 메세지 보낸 날짜
                            title : 메세지 제목
                            content : 메세지 내용
                            opened : 메세지 수신 확인 여부(Y/N)
                            mode : 메세지 모드(DEFT : 일반 서버, LNGT : 롱 푸시, STOS : 타겟 푸시)
                            image_yn : 이미지 첨부 여부(Y/N)
                            imgUrl : 이미지 url
                            lcode : 메세지 라벨 코드
                            link : 웹 링크
                            customKeyCheck : 커스텀 데이터 사용 여부(Y/N)
※ 커스텀 데이터가 있을 경우만 노출
                            custom_key_1 : custom_value_1
                            custom_key_2 : custom_value_2
                            custom_key_3 : custom_value_3
                            *
                            */

                            for (int i = 0; i < jsonArray.length(); i++) {
                                String date = jsonArray.getJSONObject(i).optString(PushList.DATE);
                                String content = jsonArray.getJSONObject(i).optString(PushList.CONTENT);
                              /*  String msgTag = jsonArray.getJSONObject(i).optString(PushList.MSGTAG);
                                String title = jsonArray.getJSONObject(i).optString(PushList.TITLE);
                                String opend = jsonArray.getJSONObject(i).optString(PushList.OPENED);
                                String mode = jsonArray.getJSONObject(i).optString(PushList.MODE);
                                String imgCheck = jsonArray.getJSONObject(i).optString(PushList.IMGCHECK);
                                String imgUrl = jsonArray.getJSONObject(i).optString(PushList.IMGURL);
                                String labelCode = jsonArray.getJSONObject(i).optString(PushList.LABELCODE);
                                String link = jsonArray.getJSONObject(i).optString(PushList.LINK);
                                String customKeyCheck = jsonArray.getJSONObject(i).optString(PushList.CODE);*/
                                // ※ 커스텀 데이터가 있을 경우만 노출
                /*
                String customValue1 = jsonArray.getJSONObject(i).optString("custom_key_1");
                String customValue2 = jsonArray.getJSONObject(i).optString("custom_key_2");
                String customValue3 = jsonArray.getJSONObject(i).optString("custom_key_3");
                */
                                list.add(new NoticeItem(content, date.substring(0, 4) + " " + date.substring(4, 6) + " " + date.substring(6, 8) + " " + date.substring(8, 10)
                                        + ":" + date.substring(10, 12) + ":" + date.substring(12)));


                                //System.out.println("msgTag : " + msgTag);
                             /*   System.out.println("date : " + date.substring(0, 4) + " " + date.substring(4, 6) + " " + date.substring(6, 8) + " " + date.substring(8, 10)
                                        + ":" + date.substring(10, 12) + ":" + date.substring(12)); //날짜 자르기!
                                System.out.println("title : " + title);
                                System.out.println("content : " + content);
                                System.out.println("opend : " + opend);*/

                                // 2021 07 20 14:23:50

                                noticeRecyclerAdapter = new NoticeRecyclerAdapter(v.getContext(), list);
                                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
                                recyclerView.setAdapter(noticeRecyclerAdapter);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }
        );

        return v;
    }
}


/*public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

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