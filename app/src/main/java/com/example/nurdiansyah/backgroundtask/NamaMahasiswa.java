package com.example.nurdiansyah.backgroundtask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NamaMahasiswa extends AppCompatActivity {

    //deklarasi komponen variabel
     ProgressDialog progressDialog;
     ListView listView;
     Button mulai, pindah;
    private String[] nama = {"Dina", "Rian", "Gina", "Osas", "Reno", "Lina", "Roi", "Shifa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nama_mahasiswa);

        getSupportActionBar().setTitle("AsynTask");

        //memberikan nilai kepada id
        listView = (ListView)findViewById(R.id.listMahasiswa);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        pindah = (Button)findViewById(R.id.btnPindah);
        mulai = (Button)findViewById(R.id.btnStart);

        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(NamaMahasiswa.this, PencariGambar.class);
                startActivity(next);
            }
        });
        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyTask().execute();
            }
        });

    }

    class MyTask extends AsyncTask<Void, String, String> {


        ArrayAdapter <String> adapter;
        int count;
        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            adapter = (ArrayAdapter<String>)listView.getAdapter();
            progressDialog = ProgressDialog.show(NamaMahasiswa.this,"Loading Data","Wait for 10 Seconds...");
            progressDialog.setCancelable(true);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            count = 0;
        }

        @Override
        protected String doInBackground(Void... voids) {

            for (String Name : nama){
                publishProgress(Name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Names Successfully Added";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //super.onProgressUpdate(values);
            adapter.add(values[0]);
            count ++;
            progressDialog.setProgress(count);
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }
}
