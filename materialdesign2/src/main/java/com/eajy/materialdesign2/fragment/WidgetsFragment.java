package com.eajy.materialdesign2.fragment;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.eajy.materialdesign2.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zhang on 2016.08.07.
 */
public class WidgetsFragment extends Fragment {

    private AdView ad_view_widget;
    private CardView card_ad_widget;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_widgets, container, false);
        ad_view_widget = nestedScrollView.findViewById(R.id.ad_view_widget);
        card_ad_widget = nestedScrollView.findViewById(R.id.card_ad_widget);

        RadioGroup radioGroup = nestedScrollView.findViewById(R.id.radioGroup);
        radioGroup.clearCheck(); //默认不选中

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = nestedScrollView.findViewById(checkedId);

                //设置字体
                String fontPath = "fonts/DINNextLTPro-Regular.otf";
                if (!TextUtils.isEmpty(fontPath)) {
                    Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), fontPath);
                    radioButton.setTypeface(typeFace);
                }

                CharSequence text = radioButton.getText();//获取radiobutton的文字
                Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
            }
        });

        return nestedScrollView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showAd();
    }

    private void showAd() {
        try {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("app", MODE_PRIVATE);
            if (!sharedPreferences.getBoolean("isDonated", false)) {
                AdRequest adRequest = new AdRequest.Builder().build();
                ad_view_widget.loadAd(adRequest);

                Animation animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(500);
                card_ad_widget.setVisibility(View.VISIBLE);
                card_ad_widget.startAnimation(animation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
