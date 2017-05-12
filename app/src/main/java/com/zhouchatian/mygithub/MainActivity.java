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


        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        if(trendingFragment == null){
                            trendingFragment = new TrendingFragment();
                        }
                        getFragmentManager().beginTransaction().replace(R.id.id_fragment_content,trendingFragment).commit();
                        break;
                    case R.id.bottom_book:

                        if(notificationsFragment == null){
                            notificationsFragment = new NotificationsFragment();
                        }
                        getFragmentManager().beginTransaction().replace(R.id.id_fragment_content,notificationsFragment).commit();

                        break;
                    case R.id.bottom_collection:

                        if(dynamicFragment == null){
                            dynamicFragment = new DynamicFragment();
                        }
                        getFragmentManager().beginTransaction().replace(R.id.id_fragment_content,dynamicFragment).commit();

                        break;
                    case R.id.bottom_setting:

                        if(meFragment == null){
                            meFragment = new MeFragment();
                        }
                        getFragmentManager().beginTransaction().replace(R.id.id_fragment_content,meFragment).commit();

                        break;
                }
                return true;
            }
        });
    }


}
