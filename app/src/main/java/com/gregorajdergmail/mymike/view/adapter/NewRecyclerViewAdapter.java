package com.gregorajdergmail.mymike.view.adapter;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gregorajdergmail.mymike.R;
import com.gregorajdergmail.mymike.databinding.NewListItemBinding;
import com.gregorajdergmail.mymike.vm.viewObject.TrackVO;

import java.util.List;


public class NewRecyclerViewAdapter extends RecyclerView.Adapter<NewRecyclerViewAdapter.ViewHolder> {

    private List<TrackVO> mValues;
    private LayoutInflater layoutInflater;

    public NewRecyclerViewAdapter(List<TrackVO> items
    ) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        NewListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.new_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TrackVO item = mValues.get(position);
        holder.binding.setVo(item);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final NewListItemBinding binding;

        ViewHolder(NewListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
