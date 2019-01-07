package com.noahclient.dev;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEventFailure;
import com.adjust.sdk.AdjustEventSuccess;
import com.adjust.sdk.AdjustSessionFailure;
import com.adjust.sdk.AdjustSessionSuccess;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnAttributionChangedListener;
import com.adjust.sdk.OnDeeplinkResponseListener;
import com.adjust.sdk.OnEventTrackingFailedListener;
import com.adjust.sdk.OnEventTrackingSucceededListener;
import com.adjust.sdk.OnSessionTrackingFailedListener;
import com.adjust.sdk.OnSessionTrackingSucceededListener;

public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        String appToken = "xcz1yz4uighs";
        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;
        AdjustConfig config = new AdjustConfig(this, appToken, environment);
        config.setLogLevel(LogLevel.VERBOSE);

        //  Adjust.get


        Log.d("rrobbie", "app create : " + environment );

        // Evaluate the deeplink to be launched.
        config.setOnDeeplinkResponseListener(new OnDeeplinkResponseListener() {
            @Override
            public boolean launchReceivedDeeplink(Uri deeplink) {
                Log.d("rrobbie", "launchReceivedDeeplink : " + deeplink );
                return true;
            }
        });

        Adjust.onCreate(config);

        registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());

    }

    private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            Log.d("rrobbie", "onActivityCreated : " + bundle );
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.d("rrobbie", "onActivityStarted : " + activity );
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Adjust.onResume();
            Log.d("rrobbie", "onActivityResumed : " + activity );

        }

        @Override
        public void onActivityPaused(Activity activity) {
            Adjust.onPause();
        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            Log.d("rrobbie", "onActivitySaveInstanceState : " + bundle );

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }


}