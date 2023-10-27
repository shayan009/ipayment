package com.onetechsol.ipayment.firebase;

import static kotlin.random.RandomKt.Random;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.onetechsol.ipayment.data.locale.pref.PrefManager;
import com.onetechsol.ipayment.widgets.NotificationUtils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import javax.inject.Inject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Inject
    PrefManager prefManager;


    @Inject
    NotificationUtils notificationUtils;

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        new PrefManager(getApplicationContext()).setFirebaseToken(token);

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Log.d("remoteMessage", "" + message.getData().toString());
        Log.d("remoteMessage", "" + message.getNotification().getBody());
        Log.d("remoteMessage", "" + message.getMessageId());
        Log.d("remoteMessage", "" + message.getSenderId());
        Log.d("remoteMessage", "" + message.getFrom());

        if (!message.getData().isEmpty()) {


            String content = "";
            String title = "";
            String img = "";
            try {
                JSONObject dataPayload = new JSONObject(Objects.requireNonNull(message.getData().get("data_payload")));

                if (StringUtils.equals(dataPayload.getString("type"), "1")) {
                    content = dataPayload.getString("content");
                    title = dataPayload.getString("title");
                    img = dataPayload.getString("img");
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            Log.d("title", title);
            Log.d("content", content);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                new NotificationUtils(getApplicationContext()).showCustomBothContentViewNotification(Random(99).nextInt(99), title, content, "", null);
            } else {
                new NotificationUtils(getApplicationContext()).showStandardHeadsUpNotification(Random(99).nextInt(99), title, content, null);
            }

        }
    }

}
