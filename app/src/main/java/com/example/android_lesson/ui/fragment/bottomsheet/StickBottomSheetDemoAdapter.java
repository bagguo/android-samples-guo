package com.example.android_lesson.ui.fragment.bottomsheet;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_lesson.databinding.ItemStickBottomSheetDemoBinding;

import java.util.List;


public class StickBottomSheetDemoAdapter extends RecyclerView.Adapter<StickBottomSheetDemoAdapter.MyViewHolder> {

    private final List<String> strings;

    public StickBottomSheetDemoAdapter(List<String> strings) {
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemStickBottomSheetDemoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.itemTextview.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemStickBottomSheetDemoBinding binding;

        public MyViewHolder(ItemStickBottomSheetDemoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}