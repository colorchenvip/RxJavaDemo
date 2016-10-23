package rxjava.colorchen.com.net;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rxjava.colorchen.com.net.api.ZhuangBiApi;

/**
 * 网络请求接口管理工具
 * 通过三方网络库RetroFit + RxJava 实现
 * Created by color on 23/10/2016 10:10.
 */

public class NetWork {
    private static ZhuangBiApi zhuangBiApi;

    private static OkHttpClient mOkHttpClient = new OkHttpClient();
    private static Converter.Factory mGsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory mRxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    /**
     * 请求装逼网站数据
     * @return
     */
    public static ZhuangBiApi getZhuangBiApi(){
        if (zhuangBiApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl("http://www.zhuangbi.info/")
                    .addConverterFactory(mGsonConverterFactory)
                    .addCallAdapterFactory(mRxJavaCallAdapterFactory)
                    .build();
            zhuangBiApi = retrofit.create(ZhuangBiApi.class);
        }
        return zhuangBiApi;
    }
}
