package com.example.hpjaiswal.ext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ArrayList<Integer> keys;
    //int[] key={128,12,15,26,36,9};
    int hf, bsize;
    TextView keyss, hx, bin, output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        keys = intent.getIntegerArrayListExtra("keys");
        hf = intent.getIntExtra("hf", -1);
        bsize = intent.getIntExtra("bsize", -1);

        keyss = (TextView) findViewById(R.id.keyss);
        hx = (TextView) findViewById(R.id.hx);
        bin = (TextView) findViewById(R.id.bin);
        output = (TextView) findViewById(R.id.output);

        //Toast.makeText(this,keys+"/n"+hf+"/n"+bsize,Toast.LENGTH_SHORT).show();
        int l = 0, p = 0, dd = 0, cnt, i;
        boolean result = true;
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<String> h = new ArrayList<String>();
        a.add("0");
        a.add("1");
        ArrayList<String> m = new ArrayList<String>();
        int[] op = new int[keys.size()];
        String[] bits = new String[keys.size()];

        for (i = 0; i < keys.size(); i++) {
            keyss.setText(keyss.getText().toString() + "\n" + String.valueOf(keys.get(i)));
            op[i] = keys.get(i) % hf;
            hx.setText(hx.getText().toString() + "\n" + String.valueOf(op[i]));
            bits[i] = String.valueOf(Integer.toBinaryString(op[i]));
            while (bits[i].length() < Integer.toBinaryString(hf).length() - 1) {
                bits[i] = '0' + bits[i];
            }
            bin.setText(bin.getText().toString() + "\n" + bits[i]);
        }

        i = 1;
        while (result) {
            result = false;
            m.clear();
            i = i * 2;
            h = findfun(i, a);
            a = h;
            dd++;

            for (l = 0; l < a.size(); l++) {
                m.add(a.get(l));
                for (p = 0, cnt = 0; p < bits.length; p++) {
                    if (bits[p].substring(0, dd + 1).matches(a.get(l))) {
                        //System.out.println(op[p] + " " + bits[p].substring(0, dd + 1) + " " + a.get(l));
                        m.add(String.valueOf(keys.get(p)));
                        cnt++;
                        if (cnt > bsize) {
                            result = true;
                        }
                    }
                }
            }

        }
        l = 0;
        for (i = 0; i < m.size(); i++) {
            if (l<a.size() && m.get(i).matches(a.get(l))) {
                output.setText(output.getText().subSequence(0,output.getText().length()-1)+" \n [" +String.valueOf(a.get(l))+"]: ");
                l++;
            } else {
                output.setText(output.getText()+" "+String.valueOf(m.get(i))+",");
            }
        }
        output.setText(output.getText().subSequence(0,output.getText().length()-1)+" \n\n\n\n enjoy [L9]");
    }

    public ArrayList<String> findfun(int i, ArrayList<String> a) {
        ArrayList<String> c = new ArrayList<String>();
        for (int z = 0; z < (i); z = z + 1) {
            c.add(a.get(z) + "0");
            c.add(a.get(z) + "1");
        }
        return c;
    }


}
