package com.gregorajdergmail.mymike.viewmodel.viewObject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;

import com.gregorajdergmail.mymike.App;
import com.gregorajdergmail.mymike.BR;
import com.gregorajdergmail.mymike.model.audio.MikeModel;
import com.gregorajdergmail.mymike.model.json.JsonFileSaver;
import com.gregorajdergmail.mymike.service.PlayerService;
import com.gregorajdergmail.mymike.util.Log;
import com.gregorajdergmail.mymike.util.rx.ByteToPointFunction;
import com.gregorajdergmail.mymike.view.adapter.NewRecyclerViewAdapter;
import com.gregorajdergmail.mymike.view.vizualiser.NewVisualizerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;


public class NewListVO extends BaseObservable implements TrackVO.TrackClickListener {
    private static NewRecyclerViewAdapter myRecyclerViewAdapter;
    @Inject
    protected MikeModel mikeModel;
    private NewVisualizerView myVisualizerView;
    private ArrayList<TrackVO> list = new ArrayList<>();
    private String Name;
    private boolean first = true;
    private Subscription trackSubscription;
    private PlayerService mService;
    private Subscription subscription;
    private ServiceConnection mConnection = new MyServiceConnection();
    private boolean mBound = false;
    private JsonFileSaver jsonFileHelper = new JsonFileSaver();
    private PlayerService.LocalBinder binder;

    public NewListVO() {
        App.getComponent().inject(this);
    }

    @BindingAdapter("ListData")
    public static void setListData(RecyclerView recyclerView, List<TrackVO> newListTrackVOList) {
        if (newListTrackVOList != null) {
            Log.d();
            myRecyclerViewAdapter = new NewRecyclerViewAdapter(newListTrackVOList);
            recyclerView.setAdapter(myRecyclerViewAdapter);
        }
    }

    public void onAttach(Context context) {
        Intent intent = new Intent(context, PlayerService.class);
        if (first) {
            first = false;
            getTracks();
            context.startService(intent);
        }
        if (!mBound) {
            context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
    }

    private void getTracks() {
        unSubscribe(trackSubscription);
        trackSubscription = mikeModel.getNewTracks()
                .subscribe(new Action1<TrackVO>() {
                    @Override
                    public void call(TrackVO trackVO) {
                        trackVO.setClickListener(NewListVO.this);
                        list.add(trackVO);
                        notifyPropertyChanged(BR.list);
//                        myRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Bindable
    public List<TrackVO> getList() {
        return list;
    }

    public void setList(ArrayList<TrackVO> list) {
        this.list = list;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private void unSubscribe(Subscription trackSubscription) {
        if (trackSubscription != null && !trackSubscription.isUnsubscribed()) {
            trackSubscription.unsubscribe();
        }
    }

    @Override
    public void startPlay(TrackVO trackVO) {
        Log.d();
        mService.startPlay(trackVO);
        subscribe();
    }

    @Override
    public void stopPlay(TrackVO trackVO) {
        Log.d();
        mService.stopPlay();
    }

    public void onDetach(Context context) {
        Log.d();
        myVisualizerView = null;
        if (mBound) {
            context.unbindService(mConnection);
            mBound = false;
        }

    }

    public void setVisualizer(NewVisualizerView visualizer) {
        this.myVisualizerView = visualizer;
    }

    private void subscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        subscription = binder.getVisualizerObservable().map(new ByteToPointFunction()).subscribe(
                new Subscriber<float[]>() {

                    @Override
                    public void onCompleted() {
                        jsonFileHelper.completed(mService.getTrackVO().getName());
                        if (myVisualizerView != null) myVisualizerView.update(null);
                        subscription.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscription.unsubscribe();
                    }

                    @Override
                    public void onNext(float[] floats) {

                        if (myVisualizerView != null) myVisualizerView.update(floats);
                        jsonFileHelper.add(floats, mService.getTrackVO().getName());
                    }
                });
    }

    private class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (PlayerService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    }
}
