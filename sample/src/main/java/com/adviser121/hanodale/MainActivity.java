package com.adviser121.hanodale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tekinarslan.sample.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 6/8/2016.
 */
public class MainActivity extends Activity {
    private static final int REQUEST_PICK_FILE = 1;
    private File selectedFile;
    String fileextension;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button browse=(Button)findViewById(R.id.browse_btn);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FilePicker.class);
                startActivityForResult(intent, REQUEST_PICK_FILE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case REQUEST_PICK_FILE:
                    if (data.hasExtra(FilePicker.EXTRA_FILE_PATH)) {
                        selectedFile = new File
                                (data.getStringExtra(FilePicker.EXTRA_FILE_PATH));
                        fileextension = selectedFile.getAbsolutePath();
                        String fileext = fileextension.substring(fileextension.lastIndexOf(".") + 1);

                        if (fileext.equalsIgnoreCase("pdf")) {

                            Intent Pdfintent=new Intent(MainActivity.this,PdfActivity.class);
                            Pdfintent.putExtra("filepath",selectedFile.getAbsolutePath());
                            startActivity(Pdfintent);
                        } else {
                            Toast.makeText(MainActivity.this, "Plz Select PDF File", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
            }
        }
    }
}
