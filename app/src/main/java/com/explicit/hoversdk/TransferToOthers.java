package com.explicit.hoversdk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransferToOthers extends AppCompatActivity {
    private Button access, firstbank, zenithbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_others);

        access = findViewById(R.id.acessbank);
        firstbank = findViewById(R.id.FirstBank);
        zenithbank = findViewById(R.id.ZenithBank);
        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AccessBank.class));
            }
        });
        firstbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FirstBank.class));
            }
        });
        zenithbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ZenithBank.class));
            }
        });
    }
}
