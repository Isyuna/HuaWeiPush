package com.mcp.rxjava;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * author : zhangyun.
 * date  : 2022/4/24  16:56.
 * description :
 **/
public class OneText {

    private static String TAG = OneText.class.getSimpleName();
    public static void main(String[] args) {

    }

    private static void textMap(){
     List<String> testList = new ArrayList<>();
        Observable.just(testList)
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<String>, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(List<String> strings) throws Throwable {
                        return null;
                    }
                }).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Throwable {

                    }
                });


    }



}
