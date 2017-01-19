package com.gregorajdergmail.mymike.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gregorajdergmail.mymike.App;
import com.gregorajdergmail.mymike.R;
import com.gregorajdergmail.mymike.databinding.ActivityMainBinding;
import com.gregorajdergmail.mymike.util.Log;
import com.gregorajdergmail.mymike.view.fragment.NewRecyclerFragment;
import com.gregorajdergmail.mymike.vm.viewObject.ActivityVO;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    protected ActivityVO activityVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d();
        App.getComponent().inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVo(activityVO);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            NewRecyclerFragment newRecyclerFragment = NewRecyclerFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, newRecyclerFragment).commit();
        }
    }


}
