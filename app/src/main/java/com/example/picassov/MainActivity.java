package com.example.picassov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    int i = 0;
    AppCompatImageView imageView;
    AppCompatButton btnDrawable,btnPlaceholder,btnUrl,btnError,btnCallback,btnResize,btnRotate,btnScale,btnTargets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView(){


        imageView = findViewById(R.id.imageview);
        btnDrawable = findViewById(R.id.btnDrawable);
        btnUrl = findViewById(R.id.btnUrl);
        btnPlaceholder = findViewById(R.id.btnPlaceholder);
        btnError = findViewById(R.id.btnError);
        btnCallback = findViewById(R.id.btnCallback);
        btnResize = findViewById(R.id.btnResize);
        btnRotate = findViewById(R.id.btnRotate);
        btnScale = findViewById(R.id.btnScale);
        btnTargets = findViewById(R.id.btnTargets);

        btnDrawable.setOnClickListener(this);
        btnUrl.setOnClickListener(this);
        btnPlaceholder.setOnClickListener(this);
        btnError.setOnClickListener(this);
        btnCallback.setOnClickListener(this);
        btnResize.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnTargets.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnDrawable :
                Picasso.with(this).load(R.drawable.image).into(imageView);
                break;
            case R.id.btnPlaceholder :
                Picasso.with(this).load("www.journaldev.com").placeholder(R.drawable.placeholder).into(imageView);
                break;
            case R.id.btnUrl :
                Picasso.with(this).load("http://cdn.journaldev.com/wp-content/uploads/2017/01/android-constraint-layout-sdk-tool-install.png").placeholder(R.drawable.placeholder).into(imageView);
                break;
            case R.id.btnError :
                Picasso.with(this).load("www.journaldev.com").placeholder(R.drawable.placeholder).error(R.drawable.erorr).into(imageView);
                break;
            case R.id.btnCallback :
                Picasso.with(this).load("www.journaldev.com").error(R.mipmap.ic_launcher).into(imageView, new Callback() {

                    @Override
                    public void onSuccess() {
                        Log.d("TAG", "onSuccess");
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                    }

                    });
            case R.id.btnResize :
                Picasso.with(this).load(R.drawable.image).resize(200,200).into(imageView);
                break;

            case R.id.btnRotate :
                Picasso.with(this).load(R.drawable.image).rotate(90f).into(imageView);
                break;
            case R.id.btnScale :
                if (i == 3)
                    i = 0;
                else {
                    if (i == 0){
                        Picasso.with(this).load(R.drawable.image).fit().into(imageView);
                        Toast.makeText(getApplicationContext(), "Fit", Toast.LENGTH_SHORT).show();
                    } else if (i == 1){
                        Picasso.with(this).load(R.drawable.image).resize(200, 200).centerCrop().into(imageView);
                        Toast.makeText(getApplicationContext(), "Center Crop", Toast.LENGTH_SHORT).show();
                    } else if (i == 2){
                        Picasso.with(this).load(R.drawable.image).resize(200, 200).centerInside().into(imageView);
                        Toast.makeText(getApplicationContext(), "Center Inside", Toast.LENGTH_SHORT).show();
                    }
                    i++;
                }
                break;
            case R.id.btnTargets :
                Picasso.with(this).load("http://cdn.journaldev.com/wp-content/uploads/2017/01/android-constraint-layout-sdk-tool-install.png").placeholder(R.drawable.placeholder).error(R.drawable.erorr).into(target);
                break;
                }

    }

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            imageView.setImageBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            imageView.setImageDrawable(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            imageView.setImageDrawable(placeHolderDrawable);
        }
    };
}
