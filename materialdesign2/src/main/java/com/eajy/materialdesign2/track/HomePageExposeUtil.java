package com.eajy.materialdesign2.track;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class HomePageExposeUtil {

   private OnItemExposeListener mItemOnExposeListener;

   /**
    * 列表是否逻辑上可见
    *
    * 默认true：意思是 RecyclerView的可见性没有外部逻辑的判断
    * false：例如，人气商品模块，横滑的商品RecyclerView，逻辑上是 人气商品模块 出现一半 时 商品RecyclerView才算可见。
    *          所以一开始设置为false，人气商品模块 出现 大于一半时，设置为true。
    */
   private boolean mIsRecyclerViewVisibleInLogic = true;

   private RecyclerView mRecyclerView;

   /**
    * 一般使用这个即可
    */
   public HomePageExposeUtil() {
      mIsRecyclerViewVisibleInLogic = true;
   }

   /**
    * 当RecyclerView本身的可见性 受外部逻辑控制时 使用，
    * @param isRecyclerViewVisibleInLogic
    */
   public HomePageExposeUtil(boolean isRecyclerViewVisibleInLogic) {
      mIsRecyclerViewVisibleInLogic = isRecyclerViewVisibleInLogic;
   }


   /**
    * 设置RecyclerView的item可见状态的监听
    * @param recyclerView recyclerView
    * @param onExposeListener 列表中的item可见性的回调
    */
   public void setRecyclerItemExposeListener(RecyclerView recyclerView, OnItemExposeListener onExposeListener) {

      mItemOnExposeListener = onExposeListener;
      mRecyclerView = recyclerView;

      if (mRecyclerView == null || mRecyclerView.getVisibility() != View.VISIBLE) {
         return;
      }
      //检测recyclerView的滚动事件
      mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
         @Override
         public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            //关注：SCROLL_STATE_IDLE:停止滚动；  SCROLL_STATE_DRAGGING: 用户慢慢拖动
            // 关注：SCROLL_STATE_SETTLING：惯性滚动
//            if (newState == RecyclerView.SCROLL_STATE_IDLE
//                    || newState == RecyclerView.SCROLL_STATE_DRAGGING
//                    || newState == RecyclerView.SCROLL_STATE_SETTLING) {
//               handleCurrentVisibleItems();
//            }
         }

         @Override
         public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //包括刚进入列表时统计当前屏幕可见views
            handleCurrentVisibleItems();
         }

      });
   }

   /**
    * 处理 当前屏幕上mRecyclerView可见的item view
    */
   public void handleCurrentVisibleItems() {
      //View.getGlobalVisibleRect(new Rect())，true表示view视觉可见，无论可见多少。
      if (mRecyclerView == null || mRecyclerView.getVisibility() != View.VISIBLE ||
              !mRecyclerView.isShown() || !mRecyclerView.getGlobalVisibleRect(new Rect())) {
         return;
      }
      //保险起见，为了不让统计影响正常业务，这里做下try-catch
      try {
         int[] range = new int[2];
         int orientation = -1;
         RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
         if (manager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
            range = findRangeLinear(linearLayoutManager);
            orientation = linearLayoutManager.getOrientation();
         } else if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            range = findRangeGrid(gridLayoutManager);
            orientation = gridLayoutManager.getOrientation();
         } else if (manager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
            range = findRangeStaggeredGrid(staggeredGridLayoutManager);
            orientation = staggeredGridLayoutManager.getOrientation();
         }
         if (range == null || range.length < 2) {
            return;
         }
         Log.d("", "屏幕内可见条目的起始位置：" + range[0] + "---" + range[1]);
         // 注意，这里 会处理此刻 滑动过程中 所有可见的view
         for (int i = range[0]; i <= range[1]; i++) {
            View view = manager.findViewByPosition(i);
            setCallbackForLogicVisibleView(view, i, orientation);
         }
      } catch (Exception e) {
         e.printStackTrace();
         Log.d("", e.getMessage());
      }
   }

   /**
    * 为 逻辑上可见的view设置 可见性回调
    * 说明：逻辑上可见--可见且可见高度（宽度）>view高度（宽度）的50%
    * @param view 可见item的view
    * @param position 可见item的position
    * @param orientation recyclerView的方向
    */
   private void setCallbackForLogicVisibleView(View view, int position, int orientation) {
      if (view == null || view.getVisibility() != View.VISIBLE ||
              !view.isShown() || !view.getGlobalVisibleRect(new Rect())) {
         return;
      }

      Rect rect = new Rect();

      boolean cover = view.getGlobalVisibleRect(rect);

      //item逻辑上可见：可见且可见高度（宽度）>view高度（宽度）50%才行
      boolean visibleHeightEnough = orientation == OrientationHelper.VERTICAL && rect.height() > view.getMeasuredHeight() / 2;
      boolean visibleWidthEnough = orientation == OrientationHelper.HORIZONTAL && rect.width() > view.getMeasuredWidth() / 2;
      boolean isItemViewVisibleInLogic = visibleHeightEnough || visibleWidthEnough;

      if (cover && mIsRecyclerViewVisibleInLogic && isItemViewVisibleInLogic) {
         mItemOnExposeListener.onItemViewVisible(true, position);
      }else {
         mItemOnExposeListener.onItemViewVisible(false, position);
      }
   }


   private int[] findRangeLinear(LinearLayoutManager manager) {
      int[] range = new int[2];
      range[0] = manager.findFirstVisibleItemPosition();
      range[1] = manager.findLastVisibleItemPosition();
      return range;
   }

   private int[] findRangeGrid(GridLayoutManager manager) {
      int[] range = new int[2];
      range[0] = manager.findFirstVisibleItemPosition();
      range[1] = manager.findLastVisibleItemPosition();
      return range;

   }

   private int[] findRangeStaggeredGrid(StaggeredGridLayoutManager manager) {
      int[] startPos = new int[manager.getSpanCount()];
      int[] endPos = new int[manager.getSpanCount()];
      manager.findFirstVisibleItemPositions(startPos);
      manager.findLastVisibleItemPositions(endPos);
      int[] range = findRange(startPos, endPos);
      return range;
   }

   private int[] findRange(int[] startPos, int[] endPos) {
      int start = startPos[0];
      int end = endPos[0];
      for (int i = 1; i < startPos.length; i++) {
         if (start > startPos[i]) {
            start = startPos[i];
         }
      }
      for (int i = 1; i < endPos.length; i++) {
         if (end < endPos[i]) {
            end = endPos[i];
         }
      }
      int[] res = new int[]{start, end};
      return res;
   }


   public void setIsRecyclerViewVisibleInLogic(boolean mIsRecyclerViewVisibleInLogic) {
      this.mIsRecyclerViewVisibleInLogic = mIsRecyclerViewVisibleInLogic;
   }
}
