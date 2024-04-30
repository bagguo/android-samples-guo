package com.example.android_lesson.ui.recyclerview.headerfooter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android_lesson.R;

public class FooterAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {

    public FooterAdapter(int dataSize) {
        super(R.layout.item_footer, DataServer.getSampleData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        switch (helper.getLayoutPosition()%
                3){
            case 0:
                helper.setImageResource(R.id.iv,R.mipmap.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv,R.mipmap.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.iv,R.mipmap.animation_img3);
                break;
        }
    }


}
