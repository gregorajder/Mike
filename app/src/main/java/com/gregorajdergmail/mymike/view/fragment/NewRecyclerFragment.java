package com.gregorajdergmail.mymike.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gregorajdergmail.mymike.App;
import com.gregorajdergmail.mymike.R;
import com.gregorajdergmail.mymike.databinding.NewFragmentRecyclerListBinding;
import com.gregorajdergmail.mymike.util.Log;
import com.gregorajdergmail.mymike.vm.viewObject.NewListVO;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;


public class NewRecyclerFragment extends Fragment {

    @Inject
    protected NewListVO newListVO;

    public NewRecyclerFragment() {
    }

    public static NewRecyclerFragment newInstance() {
        NewRecyclerFragment fragment = new NewRecyclerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        NewFragmentRecyclerListBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.new_fragment_recycler_list, container, false);
        View view = binding.getRoot();
        binding.setViewObject(newListVO);
        newListVO.setVisualizer(binding.visualizerView);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d();
        App.getComponent().inject(this);
        newListVO.onAttach(getContext());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d();
        newListVO.onDetach(getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = App.getRefWatcher(getActivity());
//        refWatcher.watch(this);
    }
}
