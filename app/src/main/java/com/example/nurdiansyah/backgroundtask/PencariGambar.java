package com.example.nurdiansyah.backgroundtask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class PencariGambar extends AppCompatActivity {

    //deklarasi komponen variabel
    private EditText mURLgambar;
    private ImageView mGambar;
    private Button mButtonLoad;
    private ProgressDialog mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);

        //Mendefinisikan gambar dan edittext pada layout activity cari gambar
        mURLgambar = (EditText) findViewById(R.id.URL);
        mGambar = (ImageView) findViewById(R.id.gambar);
        mButtonLoad = (Button) findViewById(R.id.button3);
    }


    public void search(View view) {
        loadImagetInit();
    }

    private void loadImagetInit() {
        String ImgUrl = mURLgambar.getText().toString();
        //AsyncTask mencari gambar di internet
        new loadImage().execute(ImgUrl);
    }



    private class loadImage extends AsyncTask<String, Void, Bitmap> {
        //method ketika proses asynctask belum dimulai
        @Override
        protected void onPreExecute() { //awal dari pengerjaan dalam menampilkan loading
            super.onPreExecute();

            // Membuat Progress Dialog
            mLoading = new ProgressDialog(PencariGambar.this);

            // Judul Progress Dialog
            mLoading.setTitle("Downloading image");

            // Seting message Progress Dialog
            mLoading.setMessage("Loading...");

            // menampilkan Progress Dialog
            mLoading.show();
        }
        //method saat proses asynctask dijalankan
        @Override
        protected Bitmap doInBackground(String... params) { //apa yang sedang terjadi ketika dijalankan memasukkan data ke list view
            Bitmap a = null;
            try {
                //download gambar dr url
                URL x = new URL(params[0]);
                //konversi gambar ke bitmap (decode to bitmap)

                a = BitmapFactory.decodeStream((InputStream) x.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return a;
        }

        //method sesudah asynctask sudah dijalankan
        @Override
        protected void onPostExecute(Bitmap a) { // akhir dari pengerjaan ketika di klik lg button maka akan menampilkan gambar yang telah dicari
            super.onPostExecute(a);
            //menampung gambar ke imageview dan menampilkan
            mGambar.setImageBitmap(a);
            //menghilangkan progress dialog
            mLoading.dismiss();
        }
    }
}
