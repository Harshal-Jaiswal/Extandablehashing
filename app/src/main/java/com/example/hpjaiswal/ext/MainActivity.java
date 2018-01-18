package com.example.hpjaiswal.ext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView bsize, hf, keytv, key;
    ArrayList<Integer> keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bsize = (TextView) findViewById(R.id.bsize);
        hf = (TextView) findViewById(R.id.hf);
        keytv = (TextView) findViewById(R.id.keytv);
        key = (TextView) findViewById(R.id.key);
        keys = new ArrayList<>();
    }

    public void add(View v) {
        if (key.getText().length() <= 0) {
            Toast.makeText(this, "enter keys", Toast.LENGTH_SHORT).show();
        } else {
            if (keys.isEmpty()) {
                keytv.setText("Keys: " + key.getText().toString());
                keys.add(Integer.valueOf(key.getText().toString()));
            } else {
                keys.add(Integer.valueOf(key.getText().toString()));
                keytv.setText(keytv.getText() + ", " + key.getText().toString());
            }
            key.setText("");
            key.setHint("Add key");
        }
    }
    public void reset(View v){
        keys.clear();
        key.setText("");
        key.setHint("Add key");
        keytv.setText("Keys: ");
    }

    public void next(View v) {
        if (bsize.getText().length() <= 0 || hf.getText().length() <= 0 || keys.isEmpty()) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        } else {
            int i = 0, flag = 0;
            while (true) {
                if (Integer.valueOf(hf.getText().toString()) == Math.pow(2, i)) {
                    flag = 1;
                    break;
                } else {
                    if (Integer.valueOf(hf.getText().toString()) < Math.pow(2, i)) {
                        flag = 0;
                        break;
                    }
                }
                i++;
            }
            if (flag == 0) {
                Toast.makeText(this, "enter proper value for Hash function \n eg.:4 or 8 or 16...", Toast.LENGTH_SHORT).show();

            } else {
                Intent ii = new Intent(this, Main2Activity.class);
                ii.putExtra("bsize", Integer.valueOf(bsize.getText().toString()));
                ii.putExtra("hf", Integer.valueOf(hf.getText().toString()));
                ii.putExtra("keys", keys);
                startActivity(ii);
            }
        }
    }
}
