package com.example.rohit.retrofitdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class MainActivity extends AppCompatActivity {
Button button;
TextView textView;
ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.text);
        imageView=(ImageView)findViewById(R.id.image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                API.getSingleInstance(getApplicationContext()).checkNumberAlreadyLogin( new CallBacks.LoginCallback() {

                    @Override
                    public void onSuccess(ModelClass token) {


                        String sts=token.status;
                        textView.setText(sts);
                        String img=token.message;

                        glideimageview(MainActivity.this, img, imageView);



                    }
                });


            }
        });
    }


    public static  void glideimageview(Context mContext, String url, final ImageView mCircleImageView) {
        if (mContext != null) {

            Glide.with(mContext)
                    .load(url)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(150, 150)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            if (e != null) {
                                e.printStackTrace();
                            }
                            return false; // important to return false so the error placeholder can be placed

                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                       boolean isFromMemoryCache, boolean isFirstResource) {
                            mCircleImageView.setImageDrawable(resource);
                            return false;
                        }
                    })
                    .into(mCircleImageView);


        }
    }


}
