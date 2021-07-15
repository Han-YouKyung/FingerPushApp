package com.example.fingerpushapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fingerpush.android.FingerPushManager;
import com.fingerpush.android.NetworkUtility;
import com.fingerpush.android.dataset.DeviceInfo;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {
    // TODO: Rename and change types of parameters

    public SettingFragment() {
        // Required empty public constructor
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

                        String activity = ObjectData.optString(DeviceInfo.ACTIVITY);
                        System.out.println(activity);

                        if (activity.equals("A")) {
                            setNoticeSwitch.setChecked(true);
                        } else {
                            setNoticeSwitch.setChecked(false);
                        }


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
                                    setNoticeSwitch.setOnCheckedChangeListener(this);
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


        FingerPushManager.getInstance(getContext()).getDeviceInfo(
                new NetworkUtility.ObjectListener() { // 비동기 이벤트 리스너

                    @Override
                    public void onError(String code, String message) {

                    }

                    @Override
                    public void onComplete(String code, String message, JSONObject ObjectData) {

                        String ad_activity = ObjectData.optString(DeviceInfo.AD_ACTIVITY);


                        if (ad_activity.equals("A")) {
                            adSwitch.setChecked(true);

                        } else {
                            adSwitch.setChecked(false);
                        }


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
                                                    adSwitch.setChecked(false);
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

        return v;
    }


}
