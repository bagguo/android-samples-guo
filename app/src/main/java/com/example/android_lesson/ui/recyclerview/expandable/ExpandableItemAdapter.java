//package com.example.android_lesson.ui.recyclerview.expandable;
//
//import android.util.Log;
//import android.view.View;
//
//import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.chad.library.adapter.base.entity.MultiItemEntity;
//import com.example.android_lesson.R;
//
//import java.util.List;
//
///**
// * Created by luoxw on 2016/8/9.
// */
//public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
//    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();
//
//    public static final int TYPE_LEVEL_0 = 0;
//    public static final int TYPE_LEVEL_1 = 1;
//    public static final int TYPE_PERSON = 2;
//
//    /**
//     * Same as QuickAdapter#QuickAdapter(Context,int) but with
//     * some initialization data.
//     *
//     * @param data A new list is created out of this one to avoid mutable list
//     */
//    public ExpandableItemAdapter(List<MultiItemEntity> data) {
//        super(data);
//        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
//        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
//        addItemType(TYPE_PERSON, R.layout.item_expandable_lv2);
//    }
//
//
//    @Override
//    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
//        switch (holder.getItemViewType()) {
//            case TYPE_LEVEL_0:
//                switch (holder.getLayoutPosition() % 3) {
//                    case 0:
//                        holder.setImageResource(R.id.iv_head, R.mipmap.head_img0);
//                        break;
//                    case 1:
//                        holder.setImageResource(R.id.iv_head, R.mipmap.head_img1);
//                        break;
//                    case 2:
//                        holder.setImageResource(R.id.iv_head, R.mipmap.head_img2);
//                        break;
//                }
//                final Level0Item lv0 = (Level0Item) item;
//                holder.setText(R.id.title, lv0.title)
//                        .setText(R.id.sub_title, lv0.subTitle)
//                        .setImageResource(R.id.iv, lv0.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int pos = holder.getAdapterPosition();
//                        Log.d(TAG, "Level 0 item pos: " + pos);
//                        if (lv0.isExpanded()) {
//                            collapse(pos);
//                        } else {
////                            if (pos % 3 == 0) {
////                                expandAll(pos, false);
////                            } else {
//                            expand(pos);
////                            }
//                        }
//                    }
//                });
//                break;
//            case TYPE_LEVEL_1:
//                final Level1Item lv1 = (Level1Item) item;
//                holder.setText(R.id.title, lv1.title)
//                        .setText(R.id.sub_title, lv1.subTitle)
//                        .setImageResource(R.id.iv, lv1.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int pos = holder.getAdapterPosition();
//                        Log.d(TAG, "Level 1 item pos: " + pos);
//                        if (lv1.isExpanded()) {
//                            collapse(pos, false);
//                        } else {
//                            expand(pos, false);
//                        }
//                    }
//                });
//                break;
//            case TYPE_PERSON:
//                final Person person = (Person) item;
//                holder.setText(R.id.tv, person.name + " parent pos: " + getParentPosition(person));
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        int cp = getParentPosition(person);
//                        if (cp != -1) {
//                            ((Level1Item) getData().get(cp)).removeSubItem(person);
//                            getData().remove(holder.getLayoutPosition());
//                            notifyItemRemoved(holder.getLayoutPosition());
//                        }
//                    }
//                });
//                break;
//        }
//    }
//}
