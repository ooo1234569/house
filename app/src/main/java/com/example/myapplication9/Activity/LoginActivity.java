package com.example.myapplication9.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.myapplication9.ActivityCollector;
import com.example.myapplication9.R;
import com.example.myapplication9.TestSignupFragment;
import com.example.myapplication9.adapter.MultiFragmentPagerAdapter;
import com.example.myapplication9.fragment.LoginFragment;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/5/20.
 */
public class LoginActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private MultiFragmentPagerAdapter multiFragmentPagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private LoginFragment loginFragment;
    private TestSignupFragment signupFragment;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        loginFragment=new LoginFragment();
        signupFragment=new TestSignupFragment();
        fragments.add(signupFragment);
        fragments.add(loginFragment);
        fragmentManager = getSupportFragmentManager();
        multiFragmentPagerAdapter = new MultiFragmentPagerAdapter(fragmentManager, this,fragments);
        mViewPager.setAdapter(multiFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        one = mTabLayout.getTabAt(0);
        one.setText("注册");
        two = mTabLayout.getTabAt(1);
        two.setText("登录");
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            ActivityCollector.finishAll();
        }
    }
}
