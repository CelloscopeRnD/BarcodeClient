package co.celloscope.barcodeclient;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String BARCODE_READER_CLASS_NAME = "co.celloscope.barcodereader.MainActivity";
    public static final String BARCODE_READER_PACKAGE_NAME = "co.celloscope.barcodereader";
    public static final int REQUEST_CODE_READ_BARCODE = 1;
    public static final String NAME = "NAME";
    private static final String PIN = "PIN";
    private static final String DOB = "DOB";
    private static final String BARCODE_TYPE = "BarcodeType";
    private static final String BARCODE_CONTENT = "BarcodeContent";
    private static final String QR = "QR";
    private static final String PDF417 = "PDF417";
    private static final String ABC = "ABC";
    private static final String NID = "NID";
    private boolean isPDF417RequestSend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.readBarcodeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBarcodeReader(PDF417, NID);
                isPDF417RequestSend = true;
            }
        });

        findViewById(R.id.readQRCodeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBarcodeReader(QR, ABC);
                isPDF417RequestSend = false;
            }
        });
    }

    private void startBarcodeReader(String barcodeType, String barcodeContent) {
        Intent intent = new Intent();
        intent.putExtra(BARCODE_TYPE, barcodeType);
        intent.putExtra(BARCODE_CONTENT, barcodeContent);
        intent.setComponent(new ComponentName(BARCODE_READER_PACKAGE_NAME, BARCODE_READER_CLASS_NAME));
        startActivityForResult(intent, REQUEST_CODE_READ_BARCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_READ_BARCODE) {
            if (resultCode == RESULT_OK) {
                String message;
                if (isPDF417RequestSend) {
                    message = "Name: " + data.getStringExtra(NAME) + "\nPin: " + data.getStringExtra(PIN) + "\nDOB: " + data.getStringExtra(DOB);
                } else {
                    message = "Pin: " + data.getStringExtra(PIN);
                }
                new AlertDialog.Builder(this)
                        .setMessage(message)
                        .setCancelable(false)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            } else {
                Toast.makeText(MainActivity.this, R.string.noResultFound, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
