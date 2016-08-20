package com.example.android.sunshine.app.messaging;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MessagingInstanceIdService extends FirebaseInstanceIdService {


    private static final String TAG = MessagingInstanceIdService.class.getCanonicalName();

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
}
