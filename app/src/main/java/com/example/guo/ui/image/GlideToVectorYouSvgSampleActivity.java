package com.example.guo.ui.image;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guo.R;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;

public class GlideToVectorYouSvgSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_to_vector_you_svg_sample);
        ImageView iv = findViewById(R.id.iv);

//        String url = "https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/Es9vMFrzaCERmJfrF4H2FYD4KCoNkY11McCe8BenwNYB/logo.svg";
    String url = "https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/7Q2afV64in6N6SeZsAAB81TJzwDoD6zpqmHkzi9Dcavn/logo.svg";

    // smaple 1
//        GlideToVectorYou.justLoadImage(GlideToVectorYouSvgSampleActivity.this, Uri.parse(url), iv);

        // sample 2
        GlideToVectorYou
                .init()
                .with(this)
                .withListener(new GlideToVectorYouListener() {
                    @Override
                    public void onLoadFailed() {
                        Toast.makeText(GlideToVectorYouSvgSampleActivity.this, "Load failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResourceReady() {
                        Toast.makeText(GlideToVectorYouSvgSampleActivity.this, "Image ready", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPlaceHolder(R.drawable.loading, R.drawable.ic_load_error)
                .load(Uri.parse(url), iv);
    }
}