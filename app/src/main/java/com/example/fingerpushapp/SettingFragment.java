package com.example.fingerpushapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.fingerpush.android.FingerPushManager;
import com.fingerpush.android.NetworkUtility;
import com.fingerpush.android.dataset.DeviceInfo;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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

    Switch setNoticeSwitch, adSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        setNoticeSwitch = (Switch) v.findViewById(R.id.settingSwitch1);
        adSwitch = (Switch) v.findViewById(R.id.settingSwitch2);


        FingerPushManager.getInstance(getContext()).getDeviceInfo(
                new NetworkUtility.ObjectListener() { // 비동기 이벤트 리스너

                    @Override
                    public void onError(String code, String message) {

                    }

                    @Override
                    public void onComplete(String code, String message, JSONObject ObjectData) {
                        String appkey = ObjectData.optString(DeviceInfo.APPKEY);
                        String device_type = ObjectData.optString(DeviceInfo.DEVICE_TYPE);
                        String activity = ObjectData.optString(DeviceInfo.ACTIVITY);
                        String ad_activity = ObjectData.optString(DeviceInfo.AD_ACTIVITY);
                        String identity = ObjectData.optString(DeviceInfo.IDENTITY);
                        String timezone = ObjectData.optString(DeviceInfo.TIMEZONE);
                        String country = ObjectData.optString(DeviceInfo.COUNTRY);
                        String version_code = ObjectData.optString(DeviceInfo.VERCODE);
                        String version_name = ObjectData.optString(DeviceInfo.VERNAME);
                        String os_version = ObjectData.optString(DeviceInfo.OSVER);

                        setNoticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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


        FingerPushManager.getInstance(getContext()).getDeviceInfo(
                new NetworkUtility.ObjectListener() { // 비동기 이벤트 리스너

                    @Override
                    public void onError(String code, String message) {

                    }

                    @Override
                    public void onComplete(String code, String message, JSONObject ObjectData) {
                        String appkey = ObjectData.optString(DeviceInfo.APPKEY);
                        String device_type = ObjectData.optString(DeviceInfo.DEVICE_TYPE);
                        String activity = ObjectData.optString(DeviceInfo.ACTIVITY);
                        String ad_activity = ObjectData.optString(DeviceInfo.AD_ACTIVITY);
                        String identity = ObjectData.optString(DeviceInfo.IDENTITY);
                        String timezone = ObjectData.optString(DeviceInfo.TIMEZONE);
                        String country = ObjectData.optString(DeviceInfo.COUNTRY);
                        String version_code = ObjectData.optString(DeviceInfo.VERCODE);
                        String version_name = ObjectData.optString(DeviceInfo.VERNAME);
                        String os_version = ObjectData.optString(DeviceInfo.OSVER);

                        adSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    Toast.makeText(getContext(), "광고 알림설정 ON", Toast.LENGTH_SHORT).show();
                                    FingerPushManager.getInstance(getContext()).setAdvertisePushEnable(
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
                                    Toast.makeText(getContext(), "광고 알림설정 OFF", Toast.LENGTH_SHORT).show();

                                    FingerPushManager.getInstance(getContext()).setAdvertisePushEnable(
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