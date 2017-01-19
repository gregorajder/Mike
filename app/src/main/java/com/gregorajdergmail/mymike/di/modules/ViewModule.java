package com.gregorajdergmail.mymike.di.modules;

import com.gregorajdergmail.mymike.vm.viewObject.ActivityVO;
import com.gregorajdergmail.mymike.vm.viewObject.NewListVO;

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
