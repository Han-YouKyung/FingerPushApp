package com.example.fingerpushapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.fingerpush.android.FingerPushManager;
import com.fingerpush.android.NetworkUtility;
import com.fingerpush.android.dataset.TagList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public NoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();
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

        noticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //알림 수신 설정 ON
                    recyclerView.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "알림설정 ON", Toast.LENGTH_SHORT).show();
                    FingerPushManager.getInstance(getContext()).getAllTag(
                            new NetworkUtility.ObjectListener() { // 비동기 이벤트 리스너

                                @Override
                                public void onError(String code, String message) {
                                    System.out.println("오류");
                                }

                                @Override
                                public void onComplete(String code, String message, JSONObject data) {
                                    try {
                                        JSONArray ArrayData = data.getJSONArray(TagList.TAGLIST);
                                        if (ArrayData.length() > 0) {
                                            ArrayList<TagList> dataList = new ArrayList<>();
                                            TagList list = null;
                                            for (int i = 0; i < ArrayData.length(); i++) {
                                                list = new TagList();
                                                list.date = ArrayData.getJSONObject(i).optString("date");
                                                list.tag = ArrayData.getJSONObject(i).optString("tag");
                                                dataList.add(list);
                                                System.out.println(list); // 메세지 잘 받아짐
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                    );

                } else {
                    // 알림 수신 설정 OFF
                    recyclerView.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "알림설정 OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
    }
}