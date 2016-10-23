package rxjava.colorchen.com.net.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rxjava.colorchen.com.model.ZhuangBiImage;

/**
 * 装逼网站的接口
 * 通过三方网络库RetroFit + RxJava 实现
 * Created by color on 23/10/2016 10:16.
 */

public interface ZhuangBiApi {
    @GET("search")
    Observable<List<ZhuangBiImage>> search(@Query("q") String query);
}
