package com.explicit.hoversdk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;
import com.hover.sdk.permissions.PermissionActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ImageView img_borrow;
    private ImageView buyData;
    private ImageView check_balance;
    private ImageView airtime;
    private String Tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Hover.initialize(this);
        Hover.getAllValidActions(this);

        check_balance = findViewById(R.id.balance);
        airtime = findViewById(R.id.airtime);
        buyData = findViewById(R.id.buydata);
        img_borrow = findViewById(R.id.borrow);

        img_borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TransferToOthers.class));
            }
        });
        buyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new HoverParameters.Builder(MainActivity.this)
                        .request("2de92a1c")
                        .setHeader("Working")
                        .initialProcessingMessage("please wait")
                        .buildIntent();
                startActivityForResult(intent, 0);
            }
        });
        check_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new HoverParameters.Builder(MainActivity.this)
                        .request("c64a36e0")
                        .setHeader("Working")
                        .initialProcessingMessage("please wait")
                        .buildIntent();
                startActivityForResult(intent, 0);
            }
        });
        airtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BuyAirtime.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            String[] Balance = data.getStringArrayExtra("session_messenger");
            String c64a36e0 = data.getStringExtra("c64a36e0");
            String[] BuyData = data.getStringArrayExtra("session_messenger");
            String buyData = data.getStringExtra("2de92a1c");
        } else if (requestCode == 0 && resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "." + data.getStringExtra("error"), Toast.LENGTH_LONG).show();
        }
        startActivityForResult(new Intent(this, PermissionActivity.class), 0);
    }

}
