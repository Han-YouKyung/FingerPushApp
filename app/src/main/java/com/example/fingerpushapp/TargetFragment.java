package com.example.fingerpushapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fingerpush.android.FingerPushManager;
import com.fingerpush.android.NetworkUtility;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TargetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TargetFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText editTarget;
    Button button;
    TextView targetView;
    ImageButton xbtn;
    String identity;

    public TargetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TargetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TargetFragment newInstance(String param1, String param2) {
        TargetFragment fragment = new TargetFragment();
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
        View v = inflater.inflate(R.layout.fragment_target, container, false);

        editTarget = (EditText) v.findViewById(R.id.editTarget);
        button = (Button) v.findViewById(R.id.button);
        targetView = (TextView) v.findViewById(R.id.targetView);
        xbtn = (ImageButton) v.findViewById(R.id.XBtn);


        xbtn.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identity = editTarget.getText().toString();
                editTarget.setText(null);
                editTarget.setHint("식별자(identity) 설정");
                //System.out.println("테스트");

                FingerPushManager.getInstance(v.getContext()).setIdentity(
                        identity,  // 식별자 값
                        new NetworkUtility.ObjectListener() { // 비동기 이벤트 리스너

                            @Override
                            public void onError(String code, String message) {
                                System.out.println("오류!");
                            }

                            @Override
                            public void onComplete(String code, String message, JSONObject ObjectData) {
                                targetView.setText(identity);
                                xbtn.setVisibility(View.VISIBLE);
                                System.out.println(identity);

                            }

                        });


            }


        });

        xbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xbtn.setVisibility(View.INVISIBLE);
                FingerPushManager.getInstance(getContext()).removeIdentity(
                        new NetworkUtility.ObjectListener() { // 비동기 이벤트 리스너

                            @Override
                            public void onError(String code, String message) {

                            }

                            @Override
                            public void onComplete(String code, String message, JSONObject ObjectData) {
                                AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());
                                dlg.setTitle("핑거푸시"); //제목
                                dlg.setMessage("삭제 하시겠습니까?"); // 메시지
                                dlg.setIcon(android.R.drawable.star_big_on); // 아이콘 설정
                                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                //토스트 메시지
                                                Toast.makeText(getContext(), "식별자를 삭제했습니다.", Toast.LENGTH_SHORT).show();
                                                targetView.setText("식별자를 등록하세요.");
                                            }

                                        }
                                );
                                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                dlg.show();
                            }

                        });
            }
        });

        return v;
    }


}