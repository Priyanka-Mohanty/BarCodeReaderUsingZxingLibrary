package com.example.newpc.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ReaderActivity extends AppCompatActivity {
    private Button scan_btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        scan_btn = (Button) findViewById(R.id.scan_btn);
        tv = (TextView) findViewById(R.id.scanResult);
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        "com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                String formatName = data.getStringExtra("SCAN_RESULT_FORMAT");
               // byte[] rawBytes = data.getByteArrayExtra("SCAN_RESULT_BYTES");
               // int intentOrientation = data.getIntExtra("SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
                String errorCorrectionLevel = data.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL");
                tv.setText(data.getStringExtra("SCAN_RESULT_FORMAT") +"\n"+ data.getStringExtra("SCAN_RESULT")
                +"\n contents= "+contents  +
                        "\n formatName= "+formatName
                                +"\n errorCorrectionLevel= "+errorCorrectionLevel
                );

            } else if (resultCode == RESULT_CANCELED) {
                tv.setText("Press a button to start a scan. \n Scan cancelled.");
            }
        }
    }

}
