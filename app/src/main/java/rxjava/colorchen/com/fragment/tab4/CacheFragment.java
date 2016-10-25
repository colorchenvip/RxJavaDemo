package rxjava.colorchen.com.fragment.tab4;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rxjava.colorchen.com.BaseFragment;
import rxjava.colorchen.com.R;
import rxjava.colorchen.com.adapter.RecyclerItemAdapter;
import rxjava.colorchen.com.fragment.tab4.data.ImageData;
import rxjava.colorchen.com.model.Item;

/**
 * 数据缓存
 * Created by color on 24/10/2016 01:0.
 */

public class CacheFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.loadingTimeTv)
    TextView loadingTimeTv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.cacheRv)
    RecyclerView cacheRv;

    RecyclerItemAdapter adapter = new RecyclerItemAdapter();
    private long startingTime;

    @OnClick(R.id.clearMemoryCacheBt)
    void clearMemoryCache() {
        ImageData.getInstance().clearMemoryCache();
        adapter.setImages(null);
        Toast.makeText(getActivity(), R.string.memory_cache_cleared, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.clearMemoryAndDiskCacheBt)
    void clearMemoryAndDiskCache() {
        ImageData.getInstance().clearMemoryAndDiskCache();
        adapter.setImages(null);
        Toast.makeText(getActivity(), R.string.memory_and_disk_cache_cleared, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cache,container,false);
        ButterKnife.bind(this,view);

        cacheRv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        cacheRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
        return view;
    }

    @Override
    public void onRefresh() {
        load();
    }

    public void load(){
        swipeRefreshLayout.setRefreshing(true);
        startingTime = System.currentTimeMillis();
        unSubscribe();
        subscription = ImageData.getInstance()
                .subscriptionData(new Observer<List<Item>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "加载失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        swipeRefreshLayout.setRefreshing(false);
                        int loadingTime = (int) (System.currentTimeMillis() - startingTime);
                        loadingTimeTv.setText(getString(R.string.loading_time_and_source, loadingTime, ImageData.getInstance().getDataSourceText()));
                        adapter.setImages(items);
                    }
                });
    }
}
