package rxjava.colorchen.com;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import rxjava.colorchen.com.fragment.tab1_first.FirstFragment;
import rxjava.colorchen.com.fragment.tab2_map.MapFragment;
import rxjava.colorchen.com.fragment.tab3_zip.ZipFragment;

public class MainActivity extends AppCompatActivity {

    @Bind(android.R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initViewPager() {
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new FirstFragment();
                    case 1:
                        return new MapFragment();
                    case 2:
                        return new ZipFragment();
                    case 3:
                        return new ZipFragment();
                    default:
                        return new FirstFragment();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "默认";
                    case 1:
                        return "转换（map）";
                    case 2:
                        return "压缩（zip）";
                    case 3:
                        return "缓存）";
                    default:
                        return "默认";
                }
            }

        });
    }
}
