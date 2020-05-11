package com.explicit.hoversdk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hover.sdk.api.HoverParameters;
import com.hover.sdk.permissions.PermissionActivity;

public class AirtimeOthers extends AppCompatActivity {
    private EditText amount,thirdPartyNumber;
    private Button buyAirtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airtime_others);

        amount = findViewById(R.id.amount);
        thirdPartyNumber = findViewById(R.id.phoneNumber);
        buyAirtime = findViewById(R.id.buyAirtime);

        buyAirtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountId = amount.getText().toString();
                String PhoneNumber = thirdPartyNumber.getText().toString();
                if (amountId.isEmpty()){
                    amount.setError("Enter amount");
                    amount.requestFocus();
                }else if (PhoneNumber.isEmpty()){
                    thirdPartyNumber.setError("Enter Recipient Number");
                    thirdPartyNumber.requestFocus();
                }else if (!(amountId.isEmpty() && PhoneNumber.isEmpty())){
                    Intent intent = new HoverParameters.Builder(AirtimeOthers.this)
                            .request("6e662a08")
                            .extra("amount",amountId)
                            .extra("RecipientNumber", PhoneNumber)
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
        if (requestCode == 0 && resultCode == AirtimeOthers.RESULT_OK) {
            String[] AirtimeOthers = data.getStringArrayExtra("session_messenger");
            String others = data.getStringExtra("6e662a08");
        } else if (requestCode == 0 && resultCode == AirtimeOthers.RESULT_CANCELED) {
            Toast.makeText(this, "." + data.getStringExtra("error"), Toast.LENGTH_LONG).show();
        }
        startActivityForResult(new Intent(this, PermissionActivity.class), 0);
    }
}
