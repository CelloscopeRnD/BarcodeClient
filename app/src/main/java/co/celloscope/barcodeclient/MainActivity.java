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
    public static final String BARCODE_READER_PACKEAGE_NAME = "co.celloscope.barcodereader";
    public static final int REQUEST_CODE_READ_BARCODE = 1;
    public static final String NAME = "NAME";
    private static final String PIN = "PIN";
    private static final String DOB = "DOB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.readBarcodeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(BARCODE_READER_PACKEAGE_NAME, BARCODE_READER_CLASS_NAME));
                startActivityForResult(intent, REQUEST_CODE_READ_BARCODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_READ_BARCODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, new StringBuilder(data.getStringExtra(NAME))
                        .append(data.getStringExtra(PIN))
                        .append(data.getStringExtra(DOB))
                        .toString(), Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(this)
                        .setMessage(new StringBuilder("Name: ")
                                .append(data.getStringExtra(NAME))
                                .append("\nPin: ")
                                .append(data.getStringExtra(PIN))
                                .append("\nDOB: ")
                                .append(data.getStringExtra(DOB))
                                .toString())
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
