package rxjava.colorchen.com.fragment.tab4.data;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rxjava.colorchen.com.App;
import rxjava.colorchen.com.R;
import rxjava.colorchen.com.model.Item;
import rxjava.colorchen.com.net.GankBeautyResultToItemsMapper;
import rxjava.colorchen.com.net.NetWork;

/**
 *
 * Created by color on 24/10/2016 11:56.
 */

public class ImageData {
    private static ImageData instance;
    private static final int DATA_SOURCE_MEMORY = 1;
    private static final int DATA_SOURCE_DISK = 2;
    private static final int DATA_SOURCE_NETWORK = 3;
    @IntDef({DATA_SOURCE_NETWORK,DATA_SOURCE_DISK,DATA_SOURCE_MEMORY}) @interface DataSource{}

    BehaviorSubject<List<Item>> cache;
    private int dataSource;

    private ImageData(){

    }

    public static ImageData getInstance(){
        if (instance == null){
            instance = new ImageData();
        }
        return instance;
    }

    private void setDataSource(@DataSource int dataSource){
        this.dataSource = dataSource;
    }

    public String getDataSourceText(){
        int dataSourceTextRes;
        switch (dataSource){
            case DATA_SOURCE_MEMORY:
                dataSourceTextRes = R.string.data_source_memory;
                break;
            case DATA_SOURCE_DISK:
                dataSourceTextRes = R.string.data_source_disk;
                break;
            case DATA_SOURCE_NETWORK:
                dataSourceTextRes = R.string.data_source_network;
                break;
            default:
                dataSourceTextRes = R.string.data_source_network;
                break;
        }
        return App.getInstance().getString(dataSourceTextRes);
    }

    public void loadFromNetWork(){
        NetWork.getGankApi().getBeauties(100,1)
                .subscribeOn(Schedulers.io())
                .map(GankBeautyResultToItemsMapper.getInstance())
                .doOnNext(new Action1<List<Item>>() {
                    @Override
                    public void call(List<Item> items) {
                        ImageDataBase.getInstance().writeItems(items);
                    }
                })
                .subscribe(new Action1<List<Item>>() {
                    @Override
                    public void call(List<Item> items) {
                        cache.onNext(items);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }
    public Subscription subscriptionData(@NonNull Observer<List<Item>> observer){
        if (cache == null){
            cache = BehaviorSubject.create();
            Observable.create(new Observable.OnSubscribe<List<Item>>() {
                @Override
                public void call(Subscriber<? super List<Item>> subscriber) {
                    List<Item> items = ImageDataBase.getInstance().readItems();
                    if (items == null){
                        setDataSource(DATA_SOURCE_NETWORK);
                        loadFromNetWork();
                    }else{
                        setDataSource(DATA_SOURCE_DISK);
                        subscriber.onNext(items);
                    }
                }
            })
            .subscribeOn(Schedulers.io())
            .subscribe(cache);
        }else{
            setDataSource(DATA_SOURCE_MEMORY);
        }
        return cache.observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void clearMemoryCache(){
        cache = null;
    }
    public void clearMemoryAndDiskCache(){
        clearMemoryCache();
        ImageDataBase.getInstance().deleteData();
    }

}
