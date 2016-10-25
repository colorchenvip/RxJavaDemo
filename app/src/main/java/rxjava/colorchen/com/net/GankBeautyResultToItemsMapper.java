package rxjava.colorchen.com.net;


import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.functions.Func1;
import rxjava.colorchen.com.model.GankBeauty;
import rxjava.colorchen.com.model.GankResult;
import rxjava.colorchen.com.model.Item;

/**
 * 转换这里是重点
 * 通过这个转换器，把2016-08-21T13:43:58.241Z转换成16/08/21 13:43:58
 * Created by color on 23/10/2016 13:38.
 */

public class GankBeautyResultToItemsMapper implements Func1<GankResult, List<Item>> {
    private static GankBeautyResultToItemsMapper INSTANCE = new GankBeautyResultToItemsMapper();

    private GankBeautyResultToItemsMapper() {
    }

    public static GankBeautyResultToItemsMapper getInstance() {
        return INSTANCE;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Item> call(GankResult gankBeautyResult) {
        List<GankBeauty> gankBeauties = gankBeautyResult.beauties;
        List<Item> items = new ArrayList<>(gankBeauties.size());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (GankBeauty gankBeauty : gankBeauties) {
            Item item = new Item();
            try {
                Date date = inputFormat.parse(gankBeauty.createdAt);
                item.description = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                item.description = "unknown date";
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            item.imageUrl = gankBeauty.url;
            items.add(item);
        }
        return items;
    }
}