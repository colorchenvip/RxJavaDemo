package rxjava.colorchen.com.fragment.tab1_first;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rxjava.colorchen.com.BaseFragment;
import rxjava.colorchen.com.R;
import rxjava.colorchen.com.adapter.RecyclerAdapter;
import rxjava.colorchen.com.model.ZhuangBiImage;
import rxjava.colorchen.com.net.NetWork;

/**
 * Created by color on 22/10/2016 19:45.
 */

public class FirstFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.gridView)
    RecyclerView recyclerView;

    RecyclerAdapter adapter = new RecyclerAdapter();

    Observer<List<ZhuangBiImage>> observer = new Observer<List<ZhuangBiImage>>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<ZhuangBiImage> zhuangBiImages) {
            swipeRefreshLayout.setRefreshing(false);
            adapter.setImages(zhuangBiImages);
        }
    };

    @OnCheckedChanged({R.id.radioBt1,R.id.radioBt2,R.id.radioBt3,R.id.radioBt4})
    void  onTagCheaked(RadioButton radioButton,boolean checked){
        if (checked){
            unSubscribe();
            adapter.setImages(null);
            swipeRefreshLayout.setRefreshing(true);
            searchWord = radioButton.getText().toString().trim();
            search(searchWord);
        }
    }

    private void search(String key){
        subscription = NetWork.getZhuangBiApi().search(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first,container,false);
        ButterKnife.bind(this,view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(this);
        search(searchWord);
        return view;
    }

    private String searchWord = "可爱" ;
    @Override
    public void onRefresh() {
        search(searchWord);
    }
}
