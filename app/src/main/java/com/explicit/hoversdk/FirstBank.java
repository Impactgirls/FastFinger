package com.explicit.hoversdk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;
import com.hover.sdk.permissions.PermissionActivity;

public class FirstBank extends AppCompatActivity {
    private EditText amount, accountNumber;
    private Button transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_bank);

        Hover.initialize(this);
        amount = findViewById(R.id.amount);
        accountNumber = findViewById(R.id.accountNumber);
        transfer = findViewById(R.id.transfer);

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountId = amount.getText().toString();
                String Nuban = accountNumber.getText().toString();
                if (amountId.isEmpty()) {
                    amount.setError("Please enter amount");
                    amount.requestFocus();
                }else if (Nuban.isEmpty()){
                    accountNumber.setError("Please enter account number");
                    accountNumber.requestFocus();
                }else if (!(amountId.isEmpty() && Nuban.isEmpty())){
                    Intent intent = new HoverParameters.Builder(FirstBank.this)
                            .request("2bdc22ff")
                            .extra("amount", amountId)
                            .extra("RecipientNumber", Nuban)
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
            String[] Transfer = data.getStringArrayExtra("session_messenger");
            String trans = data.getStringExtra("2bdc22ff");
        } else if (requestCode == 0 && resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "." + data.getStringExtra("error"), Toast.LENGTH_LONG).show();
        }
        startActivityForResult(new Intent(this, PermissionActivity.class), 0);
    }
}
