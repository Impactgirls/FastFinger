package com.explicit.hoversdk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;
import com.hover.sdk.permissions.PermissionActivity;

public class AirtimeForSelf extends AppCompatActivity {

    private EditText airtimeSelf;
    private Button BuyAirtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airtime_for_self);

        Hover.initialize(this);
        airtimeSelf = findViewById(R.id.amount);
        BuyAirtime = findViewById(R.id.buyAirtime);


        BuyAirtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String amount = airtimeSelf.getText().toString();
                if (amount.isEmpty()){
                    airtimeSelf.setError("Please enter amount");
                    airtimeSelf.requestFocus();
            }else if ((!(amount.isEmpty())) ){
                    Intent intent = new HoverParameters.Builder(AirtimeForSelf.this)
                            .request("a744914e")
                            .extra("amount",amount)
                            .setHeader("Working")
                            .initialProcessingMessage("please wait")
                            .buildIntent();
                    startActivityForResult(intent, 0);
                }
                }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            String[] Airtime = data.getStringArrayExtra("session_messenger");
            String a744914e = data.getStringExtra("a744914e");
        } else if (requestCode == 0 && resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "." + data.getStringExtra("error"), Toast.LENGTH_LONG).show();
        }
        startActivityForResult(new Intent(this, PermissionActivity.class), 0);
    }
}
