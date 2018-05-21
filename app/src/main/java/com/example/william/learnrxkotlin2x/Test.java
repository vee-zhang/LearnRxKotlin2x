package com.example.william.learnrxkotlin2x;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

/**
 * Created by william on 2018/2/8.
 */

public class Test {
    public void test() {
        final ArrayList<ObservableSource<Integer>> sources = new ArrayList();
        Observable.range(0, 10)
                .groupBy(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) throws Exception {
                        return true;
                    }
                })
                .subscribe(new Consumer<GroupedObservable<Boolean, Integer>>() {
                    @Override
                    public void accept(GroupedObservable<Boolean, Integer> integerGroupedObservable) throws Exception {
                        sources.add(integerGroupedObservable);
                    }
                });
        Observable.zip(sources, new Function<Object[], String>() {
            @Override
            public String apply(Object[] objects) throws Exception {
                return null;
            }
        });
    }
}
