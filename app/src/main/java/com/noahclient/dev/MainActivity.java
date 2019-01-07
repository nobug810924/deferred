package com.noahclient.dev;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.OnDeviceIdsRead;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private TextView textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textField = findViewById(R.id.textField);

        Intent intent = getIntent();
        Uri data = intent.getData();
        //  Adjust.appWillOpenUrl(data, getApplicationContext());



        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Log.e("adjust", "id : " + Adjust.getAdid() + " / android id : " + androidId );


        Adjust.getGoogleAdId(this, new OnDeviceIdsRead() {
            @Override
            public void onGoogleAdIdRead(String googleAdId) {
                Log.e("adjust", "googleAdId : " + googleAdId );
            }
        });

        Log.d("rrobbie", "main onCreate : " + data );

        if(intent != null) {
            if(intent.getData() != null) {

                Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                String jsonOutput = gson.toJson(intent.getData());

                textField.setText( "main getData : " + jsonOutput );

                Log.d("rrobbie", "main getData : " + jsonOutput );


            } else {
                if(intent.getExtras() != null) {
                    Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                    //  String jsonOutput = gson.toJson(intent.getExtras().);

                    String output = "";

                    for(String key : intent.getExtras().keySet()){
                        output += key + " : " + intent.getExtras().get(key) + "\n";
                    }

                    textField.setText( "main getExtras : " + output );

                    Log.d("rrobbie", "main getExtras : " + output );

                }
            }
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Uri data = intent.getData();
        //  Adjust.appWillOpenUrl(data, getApplicationContext());

        Log.d("rrobbie", "main onNewIntent : " + data );

    }


}
