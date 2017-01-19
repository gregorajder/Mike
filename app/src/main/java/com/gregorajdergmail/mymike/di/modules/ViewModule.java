package com.gregorajdergmail.mymike.di.modules;

import com.gregorajdergmail.mymike.viewmodel.viewObject.ActivityVO;
import com.gregorajdergmail.mymike.viewmodel.viewObject.NewListVO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {
   
    @Provides
    @Singleton
    NewListVO provideNewListVO() {
        return new NewListVO();
    }

    @Provides
    @Singleton
    ActivityVO provideActivityVO() {
        return new ActivityVO();
    }

}
