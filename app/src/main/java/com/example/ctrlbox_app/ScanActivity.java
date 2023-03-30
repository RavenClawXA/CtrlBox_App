package com.example.ctrlbox_app;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScanActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setCaptureActivity(CaptureActivity.class);
        integrator.initiateScan();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Scan cancelled", Toast.LENGTH_LONG).show();
            } else {
                String scannedData = result.getContents();
                int BoxId =Oncrud.extractBoxIdFromScannedData(scannedData); // Extract the box ID from the scanned data
                Oncrud oncrud = new Oncrud("BoxId","Vendor", "Vendorname", "Trandate"); // pass appropriate values here
                oncrud.fetchBoxData(BoxId);
                 Intent intent = new Intent(ScanActivity.this, Oncrud.class);
                intent.putExtra("scannedData", BoxId);
                startActivity(intent);
                finish();

            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        Button scnb = findViewById(R.id.Scanbtn);
        scnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScanActivity.this, CaptureActivity.class);
                startActivity(getIntent());
                finish();
            }

        });


    }
}

