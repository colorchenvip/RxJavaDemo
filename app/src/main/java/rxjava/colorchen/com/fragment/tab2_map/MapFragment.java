package rxjava.colorchen.com.fragment.tab2_map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rxjava.colorchen.com.BaseFragment;
import rxjava.colorchen.com.R;
import rxjava.colorchen.com.adapter.RecyclerItemAdapter;
import rxjava.colorchen.com.model.Item;
import rxjava.colorchen.com.net.GankBeautyResultToItemsMapper;
import rxjava.colorchen.com.net.NetWork;

/**
 * Created by color on 22/10/2016 22:15.
 */

public class MapFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private int page = 1;

    @Bind(R.id.pageTv)
    TextView pageTv;
    @Bind(R.id.previousPageBt)
    Button previousPageBt;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerViewMap)
    RecyclerView recyclerViewMap;

    RecyclerItemAdapter adapter = new RecyclerItemAdapter();
    Observer<List<Item>> observer = new Observer<List<Item>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(),"请求数据出错",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNext(List<Item> items) {
            swipeRefreshLayout.setRefreshing(false);
            pageTv.setText(getString(R.string.page_with_number,page));
            adapter.setImages(items);
        }
    };

    @OnClick(R.id.previousPageBt)
    void previousPage(){
        loadPage(--page);
        if (page == 1){
            previousPageBt.setEnabled(false);
        }
    }

    @OnClick(R.id.nextPageBt)
    void nextPage(){
        loadPage(++page);
        if (page == 2){
            previousPageBt.setEnabled(true);
        }
    }

    private void loadPage(int page) {
        swipeRefreshLayout.setRefreshing(true);
        unSubscribe();
        subscription = NetWork.getGankApi()
                .getBeauties(10,page)
                .map(GankBeautyResultToItemsMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        ButterKnife.bind(this, view);

        recyclerViewMap.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerViewMap.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onRefresh() {
        loadPage(page);
    }
}
