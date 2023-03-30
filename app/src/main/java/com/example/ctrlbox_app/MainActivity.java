package com.example.ctrlbox_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView boxid = findViewById(R.id.TextBoxId);
        final TextView Vendor = findViewById(R.id.ViewVendor);
        final TextView Vendorname = findViewById(R.id.ViewVendorName);

        final String scannedData = getIntent().getStringExtra("scannedData");

        if (scannedData.contains("||")) {
            String[] split = scannedData.split("\\|\\|");
            boxid.setText(split[0]);
            Vendor.setText(split[1]);
            Vendorname.setText(split[2]);

        }else {
            Toast.makeText(MainActivity.this, "Box data is empty Reject", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, ScanActivity.class);
            intent.putExtra("showLayout", false);
            startActivity(intent);
            finish();
        }

        final TextView Trandate = findViewById(R.id.TranDateView);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        Trandate.setText(currentDateString);

        Button bbtn = findViewById(R.id.Backbtn);
        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent);
                finish();
            }
        });


        String boxIdString = boxid.getText().toString();
        String VendorString = Vendor.getText().toString();
        String VendornameString = Vendorname.getText().toString();
        String TrandateString = Trandate.getText().toString();

        Oncrud oncrud = new Oncrud(boxIdString, VendorString, VendornameString, TrandateString);
        oncrud.fetchBoxData(Integer.parseInt(boxIdString));
    }
}