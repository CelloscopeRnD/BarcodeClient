package co.celloscope.barcodeclient;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String BARCODE_READER_CLASS_NAME = "co.celloscope.barcodereader.MainActivity";
    public static final String BARCODE_READER_PACKEAGE_NAME = "co.celloscope.barcodereader";
    public static final int REQUEST_CODE_READ_BARCODE = 1;

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
        if (requestCode == REQUEST_CODE_READ_BARCODE && resultCode == RESULT_OK) {
            Toast.makeText(MainActivity.this, "result", Toast.LENGTH_SHORT).show();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
