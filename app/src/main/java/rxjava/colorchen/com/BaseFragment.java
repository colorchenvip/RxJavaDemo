package rxjava.colorchen.com;

import android.app.Fragment;

import rx.Subscription;

/**
 * fragment
 * description:及时的释放不用资源 rxJava
 * Created by color on 22/10/2016 18:36.
 */
public abstract class BaseFragment extends Fragment {
    protected Subscription subscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }

    protected void unSubscribe() {
        if (subscription != null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

}
