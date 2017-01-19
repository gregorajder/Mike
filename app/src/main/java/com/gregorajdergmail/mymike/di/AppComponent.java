package com.gregorajdergmail.mymike.di;

import com.gregorajdergmail.mymike.di.modules.ModelModule;
import com.gregorajdergmail.mymike.di.modules.ViewModule;
import com.gregorajdergmail.mymike.view.activity.MainActivity;
import com.gregorajdergmail.mymike.view.fragment.NewRecyclerFragment;
import com.gregorajdergmail.mymike.vm.viewObject.ActivityVO;
import com.gregorajdergmail.mymike.vm.viewObject.NewListVO;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewModule.class, ModelModule.class})
public interface AppComponent {

    void inject(NewRecyclerFragment newRecyclerFragment);

    void inject(NewListVO newListVO);

    void inject(MainActivity mainActivity);

    void inject(ActivityVO activityVO);


}

