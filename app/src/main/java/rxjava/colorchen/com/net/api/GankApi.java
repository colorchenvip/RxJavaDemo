package rxjava.colorchen.com.net.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rxjava.colorchen.com.model.GankResult;

/**
 * gank数据请求接口
 * Created by color on 23/10/2016 12:21.
 */

public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<GankResult> getBeauties(@Path("number") int number, @Path("page") int page);
}
