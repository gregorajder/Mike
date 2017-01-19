package com.gregorajdergmail.mymike;

import org.junit.Test;

import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void kjsdf() throws Exception {
        PublishSubject<String> asd = PublishSubject.create();
        asd.startWith("startWith", "startWith2").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
        asd.startWith("startWith");
        asd.onNext("asd");
        asd.onNext("zxc");
        asd.onNext("qwe");
    }

    @Test
    public void kjsdf2() throws Exception {
        PublishSubject<String> asd = PublishSubject.create();
        asd.startWith("startWith", "startWith2").subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
        asd.startWith("startWith");
        asd.onNext("asd");
        asd.onNext("zxc");
        asd.onNext("qwe");
    }

    @Test
    public void kjsdf3() throws Exception {
        PublishSubject<String> asd = PublishSubject.create();
        asd.startWith("startWith", "startWith2").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
        asd.startWith("startWith");
        asd.onNext("asd");
        asd.onNext("zxc");
        asd.onNext("qwe");
    }


}