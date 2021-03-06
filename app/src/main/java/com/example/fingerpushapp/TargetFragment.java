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
                editTarget.setHint("?????????(identity) ??????");
                //System.out.println("?????????");

                FingerPushManager.getInstance(v.getContext()).setIdentity(
                        identity,  // ????????? ???
                        new NetworkUtility.ObjectListener() { // ????????? ????????? ?????????

                            @Override
                            public void onError(String code, String message) {
                                System.out.println("??????!");
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
                        new NetworkUtility.ObjectListener() { // ????????? ????????? ?????????

                            @Override
                            public void onError(String code, String message) {

                            }

                            @Override
                            public void onComplete(String code, String message, JSONObject ObjectData) {
                                AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());
                                dlg.setTitle("????????????"); //??????
                                dlg.setMessage("?????? ???????????????????"); // ?????????
                                dlg.setIcon(android.R.drawable.star_big_on); // ????????? ??????
                                dlg.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                //????????? ?????????
                                                Toast.makeText(getContext(), "???????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                                                targetView.setText("???????????? ???????????????.");
                                            }

                                        }
                                );
                                dlg.setNegativeButton("??????", new DialogInterface.OnClickListener() {
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