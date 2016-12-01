package com.feicuiedu.gitdroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.Fragment.HotRepoFragment;
import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    //侧滑布局
    @BindView(R.id.navigationView)
    NavigationView mNavgView;
    //抽屉效果,侧滑
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private Button mBtnLogin;
    private ImageView mIvIcon;
    private ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前视图(更改了当前视图内容,将导致onContentChanged方法触发)
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);
        mActivityUtils.showToast("凯凯是个大傻逼");
        //设置actionbar
        setSupportActionBar(mToolbar);
        replaceFragment(new HotRepoFragment());

        //设置监听
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        //设置DrawerLayout监听
        mDrawerLayout.addDrawerListener(toggle);
        mNavgView.setNavigationItemSelectedListener(this);
        mBtnLogin = ButterKnife.findById(mNavgView.getHeaderView(0), R.id.btnLogin);
        mIvIcon = ButterKnife.findById(mNavgView.getHeaderView(0), R.id.ivIcon);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityUtils.startActivity(LoginActivity.class);
                finish();
            }
        });
    }

    //主要做了我们基本登录信息的改变
    @Override
    protected void onStart() {
        super.onStart();
        // TODO: 2016/12/1 展示登录用户信息
    }

    //提供一个方法,根据传入的fragment替换
    private void replaceFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.isChecked()) {
            item.setChecked(false);
        }
        switch (item.getItemId()) {
            //最热门
            case R.id.github_hot_repo:
                // TODO: 2016/12/1
                mActivityUtils.showToast("最热门");
//                replaceFragment(new HotRepoFragment());

                break;
            //开发者
//            case R.id.github_hot_coder:
//                mActivityUtils.showToast("开发者");
//                break;
//            case R.id.github_trend:
//                mActivityUtils.showToast("流行趋势");
//                break;
//            case R.id.arsenal_my_repo:
//                mActivityUtils.showToast("我的收藏");
//                break;
//            case R.id.arsenal_recommend:
//                mActivityUtils.showToast("推荐");
//                break;
            case R.id.tips_daily:
                mActivityUtils.showToast("每日干货");
                break;
            case R.id.tips_share:
                mActivityUtils.showToast("分享");
                break;
        }
        //选择某项之后关闭抽屉,切换相应的frament
        mDrawerLayout.closeDrawer(GravityCompat.START);
        ///返回true表示事件已处理
        return true;
    }

}
