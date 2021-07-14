package com.example.fingerpushapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import com.fingerpush.android.FingerNotification;
import com.fingerpush.android.FingerPushFcmListener;
import com.fingerpush.android.FingerPushManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class IntentService extends FingerPushFcmListener {
    String deTitle, deMessage;
    String mesImgUrl;
    Bitmap imgUrl;

    @Override
    public void onMessage(Context context, Bundle data) {

        try {
            deTitle = URLDecoder.decode(data.getString("data.title"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            deMessage = URLDecoder.decode(data.getString("data.message"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            mesImgUrl = data.getString("data.imgUrl");
            URL url = new URL(mesImgUrl);
            imgUrl = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        createNotificationChannel(deTitle, deMessage, imgUrl);

/*      onMessage Bundle 데이터

        data.msgTag : 메세지 번호
        data.code : CD:1;IM:0;PT:DEFT   <!-- (CD:커스텀데이터 여부(0없음, 1있음), IM:이미지여부(0없음, 1있음) ,PT:메세지타입 (DEFT:일반, LNGT:롱푸시, STOS:타겟푸시)  -->
                data.time : 보낸시간
        data.appTitle : 핑거푸시 앱이름
        data.badge : 뱃지
        data.sound : 사운드
        data.title : 메세지 제목
        data.message : 메세지내용
        data.weblink : 웹링크 url
        data.labelCode : 라벨코드
        data.img : 이미지여부    <!-- (0:없음;1:있음) -->
                data.imgUrl : 이미지url
        data.cd1 : 커스텀 데이터
        data.cd2 : 커스텀 데이터
        data.cd3 : 커스텀 데이터
  */

    }

    private void createNotificationChannel(/*Bundle data*/String deTitle, String deMessage, Bitmap imgUrl) {

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel("Channel ID", "Channel Name", NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription(null);
            mNotificationManager.createNotificationChannel(mChannel);
        }

        Intent intent = new Intent(IntentService.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pi = PendingIntent.getActivity(IntentService.this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder;


        mBuilder = new NotificationCompat.Builder(this, "Channel ID")

                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle(deTitle)
                .setContentText(deMessage)
                /* .setContentTitle(data.getString("data.title"))
                 .setContentText(data.getString("data.message"))*/
                .setVibrate(new long[]{0, 500, 600, 1000})
                .setDefaults(Notification.DEFAULT_SOUND)
                .setLights(Color.parseColor("#ffff00ff"), 500, 500)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(imgUrl))
                .setContentIntent(pi);


        mNotificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());

        //System.out.println(deTitle);
        //System.out.println(deMessage);
        //   mBuilder.setContentIntent(pi);



      /*  } catch (Exception e) {

        }*/
    }

    //FigerNotification
    private void createNotificationChannel2(Bundle data) {
        Intent intent = new Intent(IntentService.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

     /*   String messageId = FingerPushManager.getMessageId(data);
        String messageLabel = FingerPushManager.getMessageLabel(data);
        String pushMode = FingerPushManager.getPushMode(data);*/


        intent.putExtra("msgTag", FingerPushManager.getMessageId(data));
        intent.putExtra("mode", FingerPushManager.getPushMode(data));
        intent.putExtra("lCode", FingerPushManager.getMessageLabel(data));


        PendingIntent pi = PendingIntent.getActivity(IntentService.this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);

        FingerNotification fingerNotification = new FingerNotification(this);
        fingerNotification.setNotificationIdentifier((int) System.currentTimeMillis());
        fingerNotification.setIcon(android.R.drawable.arrow_up_float); // Notification small icon
        fingerNotification.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        fingerNotification.setVibrate(new long[]{0, 500, 600, 1000});
        fingerNotification.setLights(Color.parseColor("#ffff00ff"), 500, 500);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fingerNotification.setColor(Color.rgb(0, 114, 162));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            fingerNotification.createChannel("channel_id", "channel_name");
        }
        fingerNotification.showNotification(data, pi);
    }


}

