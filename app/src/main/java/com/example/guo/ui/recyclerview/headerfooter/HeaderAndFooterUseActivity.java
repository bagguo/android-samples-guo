package com.example.guo.ui.recyclerview.headerfooter;


import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guo.R;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HeaderAndFooterUseActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, HeaderAndFooterUseActivity.class);
        context.startActivity(intent);
    }

//    private RecyclerView mRecyclerView;
//    private HeaderAndFooterAdapter headerAndFooterAdapter;
//    private static final int PAGE_SIZE = 3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setBackBtn();
//        setTitle("HeaderAndFooter Use");
//
//        setContentView(R.layout.activity_header_and_footer_use);
//        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        initAdapter();
//
//        View headerView = getHeaderView(0, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                headerAndFooterAdapter.addHeaderView(getHeaderView(1, getRemoveHeaderListener()), 0);
//            }
//        });
//        headerAndFooterAdapter.addHeaderView(headerView);
//
//
//        View footerView = getFooterView(0, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                headerAndFooterAdapter.addFooterView(getFooterView(1, getRemoveFooterListener()), 0);
//            }
//        });
//        headerAndFooterAdapter.addFooterView(footerView, 0);
//
//        mRecyclerView.setAdapter(headerAndFooterAdapter);
//
//    }
//
//
//    private View getHeaderView(int type, View.OnClickListener listener) {
//        View view = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
//        if (type == 1) {
//            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
//            imageView.setImageResource(R.mipmap.rm_icon);
//        }
//        view.setOnClickListener(listener);
//        return view;
//    }
//
//    private View getFooterView(int type, View.OnClickListener listener) {
//        View view = getLayoutInflater().inflate(R.layout.footer_view, (ViewGroup) mRecyclerView.getParent(), false);
//        if (type == 1) {
//            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
//            imageView.setImageResource(R.mipmap.rm_icon);
//        }
//        view.setOnClickListener(listener);
//        return view;
//    }
//
//    private View.OnClickListener getRemoveHeaderListener() {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                headerAndFooterAdapter.removeHeaderView(v);
//            }
//        };
//    }
//
//
//    private View.OnClickListener getRemoveFooterListener() {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                headerAndFooterAdapter.removeFooterView(v);
//            }
//        };
//    }
//
//    private void initAdapter() {
//        headerAndFooterAdapter = new HeaderAndFooterAdapter(PAGE_SIZE);
//        headerAndFooterAdapter.openLoadAnimation();
//        mRecyclerView.setAdapter(headerAndFooterAdapter);
////        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
////            @Override
////            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
////                Toast.makeText(HeaderAndFooterUseActivity.this, "" + Integer.toString(position), Toast.LENGTH_LONG).show();
////            }
////        });
//        headerAndFooterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                adapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
//                Toast.makeText(HeaderAndFooterUseActivity.this, "" + Integer.toString(position), Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

}
