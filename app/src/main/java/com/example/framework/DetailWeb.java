package com.example.framework;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.framework.model.DataWeb;

public class DetailWeb extends AppCompatActivity {
    DataWeb web;
    ImageView logo;
    TextView titleWeb, baca_lebih_lanjut, description,author;
    LinearLayout detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        web = (DataWeb) intent.getSerializableExtra("Web");
        inisialisasiView();
    }

    void inisialisasiView() {
        logo = findViewById(R.id.logo);
        titleWeb = findViewById(R.id.jenisweb);
        author = findViewById(R.id.author);
        description = findViewById(R.id.deskripsi);
        description.setMovementMethod(new ScrollingMovementMethod());
        baca_lebih_lanjut = findViewById(R.id.lihat_detail);
        detail = findViewById(R.id.detail);


        titleWeb.setText(web.getNameWeb());;
        author.setText(web.getAuthor());
        baca_lebih_lanjut.setText(web.getBaca_lebih_lanjut());
        description.setText(web.getDescription());
        Glide
                .with(this)
                .load(web.getLogo())
                .into(logo);
    }

    public void clikToDetail(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(web.getBaca_lebih_lanjut()));
        startActivity(intent);
    }
}

