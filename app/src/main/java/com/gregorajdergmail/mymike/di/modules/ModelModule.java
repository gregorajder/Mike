package com.gregorajdergmail.mymike.di.modules;

import com.gregorajdergmail.mymike.model.audio.MikeModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    @Provides
    @Singleton
    MikeModel provideNewModel() {
        return new MikeModel();
    }

}
