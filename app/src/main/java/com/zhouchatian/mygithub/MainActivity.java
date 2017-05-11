package com.zhouchatian.mygithub;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.zhouchatian.mygithub.fragment.DynamicFragment;
import com.zhouchatian.mygithub.fragment.MeFragment;
import com.zhouchatian.mygithub.fragment.NotificationsFragment;
import com.zhouchatian.mygithub.fragment.TrendingFragment;


public class MainActivity extends AppCompatActivity {


    private BottomNavigationView mNavigationView;
    private TrendingFragment trendingFragment;
    private NotificationsFragment notificationsFragment;
    private DynamicFragment dynamicFragment;
    private MeFragment meFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        setDefaultFragment();

        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        if(trendingFragment != null){
                            trendingFragment = new TrendingFragment();
                        }
                        transaction.replace(R.id.id_fragment_content,trendingFragment);
                        break;
                    case R.id.bottom_book:

                        if(trendingFragment != null){
                            notificationsFragment = new NotificationsFragment();
                        }
                        transaction.replace(R.id.id_fragment_content,notificationsFragment);

                        break;
                    case R.id.bottom_collection:

                        if(trendingFragment != null){
                            dynamicFragment = new DynamicFragment();
                        }
                        transaction.replace(R.id.id_fragment_content,dynamicFragment);

                        break;
                    case R.id.bottom_setting:

                        if(trendingFragment != null){
                            meFragment = new MeFragment();
                        }
                        transaction.replace(R.id.id_fragment_content,meFragment);

                        break;
                }
                // 事务提交
                transaction.commit();
                return true;
            }
        });
    }
    private void setDefaultFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        trendingFragment = new TrendingFragment();
        transaction.replace(R.id.id_fragment_content, trendingFragment);
        transaction.commit();
    }
}
