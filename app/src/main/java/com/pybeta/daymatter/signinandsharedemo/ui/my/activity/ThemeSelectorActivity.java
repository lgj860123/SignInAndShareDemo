package com.pybeta.daymatter.signinandsharedemo.ui.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.base.BaseActivity;
import com.pybeta.daymatter.signinandsharedemo.bean.ColorThemeBean;
import com.pybeta.daymatter.signinandsharedemo.bean.EventBean;
import com.pybeta.daymatter.signinandsharedemo.bean.GridViewBean;
import com.pybeta.daymatter.signinandsharedemo.bean.PersonalityThemeBean;
import com.pybeta.daymatter.signinandsharedemo.custom.SkinCompatCircleImageView;
import com.pybeta.daymatter.signinandsharedemo.custom.StatusBarCustom;
import com.pybeta.daymatter.signinandsharedemo.ui.my.adapter.ThemeAdapter;
import com.pybeta.daymatter.signinandsharedemo.utils.GeneralUtils;
import com.pybeta.daymatter.signinandsharedemo.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import skin.support.SkinCompatManager;

/**
 * 个性主题选择界面
 * Created by luogj on 2018/2/23.
 */
@ContentView(R.layout.activity_theme_selector)
public class ThemeSelectorActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.rv_theme)
    RecyclerView rv_theme;

    @ViewInject(R.id.ll_theme_stateBar)
    LinearLayout ll_theme_stateBar;

    @ViewInject(R.id.view_themeStatusBar)
    StatusBarCustom view_themeStatusBar;

    @ViewInject(R.id.ib_theme_back)
    ImageButton ib_theme_back;

    @ViewInject(R.id.tv_themeStateBar_tile)
    TextView tv_themeStateBar_tile;

    @ViewInject(R.id.tv_themeStateBar_line)
    TextView tv_themeStateBar_line;

    private SpUtils spUtils;
    private SkinCompatManager skinCompatManager;
    private List<ColorThemeBean> colorThemeBeanList;
    private List<PersonalityThemeBean> personalityThemeBeanList;
    private List<GridViewBean> gridViewBeanList;
    private String skinPosition;
    private ThemeAdapter themeAdapter;
    private LinearLayoutManager linearLayoutManager;

    private ImageButton ib_theme_head_back;
    private TextView tv_currentTheme;
    private SkinCompatCircleImageView view_circleImage;
    private TextView tv_colorCircleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initColorThemeData();
        initView();
        initPersonalityData();

    }

    /**
     * 初始化颜色主题数据
     */
    private void initColorThemeData() {
        spUtils = SpUtils.getInstance(activity);
        skinPosition = spUtils.getSkinPosition();
        skinCompatManager = SkinCompatManager.getInstance();
        colorThemeBeanList = new ArrayList<>();
        personalityThemeBeanList = new ArrayList<>();
        gridViewBeanList = new ArrayList<>();

        String[] colorThemeNameArray = getResources().getStringArray(R.array.color_theme_name);
        String[] colorThemePositionArray = getResources().getStringArray(R.array.color_theme_position);
        List<String> colorThemeNameList = Arrays.asList(colorThemeNameArray);
        List<String> colorThemePositionList = Arrays.asList(colorThemePositionArray);

        for (int i = 0 ; i < colorThemeNameList.size(); i++){
            ColorThemeBean colorThemeBean = new ColorThemeBean();
            colorThemeBean.setThemeName(colorThemeNameList.get(i));
            colorThemeBean.setThemePosition(colorThemePositionList.get(i));
            if (colorThemeBean.getThemeName().equals(skinPosition)){
                colorThemeBean.setSelect(true);
            }else {
                colorThemeBean.setSelect(false);
            }
            colorThemeBeanList.add(colorThemeBean);
        }

        for (int i = 0; i < 2; i++){
            GridViewBean gridViewBean = new GridViewBean();
            if (i == 0){
                gridViewBean.setThemeType(GridViewBean.ITEM_TITLE_VIEW);
                gridViewBean.setThemeName(getResources().getString(R.string.color_theme));
            }else {
                gridViewBean.setThemeType(GridViewBean.ITEM_COLOR_THEME_VIEW);
                gridViewBean.setColorThemeBeanList(colorThemeBeanList);
            }
            gridViewBeanList.add(gridViewBean);
        }

    }


    /**
     * 初始化主题界面
     */
    private void initView() {
        ib_theme_back.setOnClickListener(this);
        View headView = LayoutInflater.from(activity).inflate(R.layout.view_theme_head_view, null);
        ib_theme_head_back = (ImageButton) headView.findViewById(R.id.ib_theme_head_back);
        view_circleImage = (SkinCompatCircleImageView) headView.findViewById(R.id.view_circleImage);
        tv_colorCircleImage = (TextView) headView.findViewById(R.id.tv_colorCircleImage);
        tv_currentTheme = (TextView) headView.findViewById(R.id.tv_currentTheme);

        ib_theme_head_back.setOnClickListener(this);

        for (ColorThemeBean colorThemeBean : colorThemeBeanList){
            if (colorThemeBean.getThemePosition().equals(skinPosition)){
                view_circleImage.setVisibility(View.INVISIBLE);
                tv_colorCircleImage.setVisibility(View.VISIBLE);
                colorThemeBean.setSelect(true);
                tv_currentTheme.setText(getResources().getString(R.string.current_theme) + colorThemeBean.getThemeName());
                switch (colorThemeBean.getThemePosition()){
                    case "1":
                        tv_colorCircleImage.setBackgroundResource(R.drawable.view_theme_head_circle_color_blue);
                        break;
                    case "2":
                        tv_colorCircleImage.setBackgroundResource(R.drawable.view_theme_head_circle_color_green);
                        break;
                    case "3":
                        tv_colorCircleImage.setBackgroundResource(R.drawable.view_theme_head_circle_color_pink);
                        break;
                    case "4":
                        tv_colorCircleImage.setBackgroundResource(R.drawable.view_theme_head_circle_color_yellow);
                        break;
                }
            }else {
                colorThemeBean.setSelect(false);
            }
        }


        themeAdapter = new ThemeAdapter(gridViewBeanList);
        themeAdapter.openLoadAnimation();
        linearLayoutManager = new LinearLayoutManager(activity);
        rv_theme.setLayoutManager(linearLayoutManager);
        themeAdapter.addHeaderView(headView);
        rv_theme.setAdapter(themeAdapter);

        rv_theme.addOnScrollListener(onScrollListener);

        themeAdapter.notifyDataSetChanged();

    }

    //RecyclerView滑动监听器
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int position = linearLayoutManager.findFirstVisibleItemPosition();
            View firstVisibleChildView = linearLayoutManager.findViewByPosition(0);
            if(firstVisibleChildView != null){
                int itemHeight = firstVisibleChildView.getHeight();
                int scrollSize = (position) * itemHeight - firstVisibleChildView.getTop();
                if(scrollSize <= 255 && scrollSize > 0){
                    ib_theme_head_back.setAlpha(1 - (scrollSize + 185) * 1.0f / 255);
                    ll_theme_stateBar.setVisibility(View.VISIBLE);
                    tv_themeStateBar_line.setVisibility(View.VISIBLE);
                    ll_theme_stateBar.getBackground().setAlpha(scrollSize);
                    tv_themeStateBar_line.getBackground().setAlpha(scrollSize);
                    tv_themeStateBar_tile.setAlpha(scrollSize * 1.0f / 255);
                    ib_theme_back.setAlpha(scrollSize * 1.0f / 255);
                    view_themeStatusBar.getBackground().setAlpha(scrollSize);
                }else if (scrollSize == 0){
                    ib_theme_head_back.setAlpha(1);
                    ll_theme_stateBar.getBackground().setAlpha(0);
                    tv_themeStateBar_line.getBackground().setAlpha(0);
                    tv_themeStateBar_tile.setAlpha(0);
                    ib_theme_back.setAlpha(0);
                    view_themeStatusBar.getBackground().setAlpha(0);
                    ll_theme_stateBar.setVisibility(View.INVISIBLE);
                    tv_themeStateBar_line.setVisibility(View.INVISIBLE);
                }else if (scrollSize > 255){
                    ll_theme_stateBar.setVisibility(View.VISIBLE);
                    tv_themeStateBar_line.setVisibility(View.VISIBLE);
                    ll_theme_stateBar.getBackground().setAlpha(255);
                    tv_themeStateBar_line.getBackground().setAlpha(255);
                    tv_themeStateBar_tile.setAlpha(1);
                    ib_theme_back.setAlpha(1);
                    view_themeStatusBar.getBackground().setAlpha(255);
                }
            }
        }
    };


    /**
     * 初始化个性主题数据
     */
    private void initPersonalityData() {
        String[] personalityThemeNameArray = getResources().getStringArray(R.array.personality_theme_name);
        String[] personalityThemePositionArray = getResources().getStringArray(R.array.personality_theme_position);
        List<String> personalityNameList = Arrays.asList(personalityThemeNameArray);
        List<String> personalityThemePositionList = Arrays.asList(personalityThemePositionArray);

        for (int i = 0; i < personalityNameList.size(); i++){
            PersonalityThemeBean personalityThemeBean = new PersonalityThemeBean();
            personalityThemeBean.setThemeName(personalityNameList.get(i));
            personalityThemeBean.setId(personalityThemePositionList.get(i));
            if (personalityThemeBean.getThemeName().equals(skinPosition)){
                personalityThemeBean.setSelect(true);
            }else {
                personalityThemeBean.setSelect(false);
            }
            personalityThemeBeanList.add(personalityThemeBean);
        }

        for (int i = 0; i < 2; i++){
            GridViewBean gridViewBean = new GridViewBean();
            if (i == 0){
                gridViewBean.setThemeType(GridViewBean.ITEM_TITLE_VIEW);
                gridViewBean.setThemeName(getResources().getString(R.string.personality_theme));
            }else {
                gridViewBean.setThemeType(GridViewBean.ITEM_PERSONALITY_THEME_VIEW);
                gridViewBean.setPersonalityThemeBeanList(personalityThemeBeanList);
            }
            gridViewBeanList.add(gridViewBean);
        }
        themeAdapter.setNewData(gridViewBeanList);

        for (PersonalityThemeBean personalityThemeBean : personalityThemeBeanList){
            if (personalityThemeBean.getId().equals(skinPosition)){
                view_circleImage.setVisibility(View.VISIBLE);
                tv_colorCircleImage.setVisibility(View.INVISIBLE);
                personalityThemeBean.setSelect(true);
                tv_currentTheme.setText(getResources().getString(R.string.current_theme) + personalityThemeBean.getThemeName());
                switch (personalityThemeBean.getId()){
                    case "5":
                        Glide.with(activity).load(R.mipmap.personality_five).into(view_circleImage);
                        break;
                    case "6":
                        Glide.with(activity).load(R.mipmap.personality_six).into(view_circleImage);
                        break;
                    case "7":
                        Glide.with(activity).load(R.mipmap.personality_seven).into(view_circleImage);
                        break;
                    case "8":
                        Glide.with(activity).load(R.mipmap.personality_eight).into(view_circleImage);
                        break;
                    case "9":
                        Glide.with(activity).load(R.mipmap.personality_nine).into(view_circleImage);
                        break;
                    case "10":
                        Glide.with(activity).load(R.mipmap.personality_ten).into(view_circleImage);
                        break;
                }
            }else {
                personalityThemeBean.setSelect(false);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onMainEvent(EventBean eventBean) {
        String eventDesc = eventBean.getEventDesc();
        if (eventDesc.contains(GeneralUtils.THEME_CHANGE)){
            Log.i("onItemClick", "##### onMainEvent:更新 ");
            finish();
            Intent intent = new Intent(activity,ThemeSelectorActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ib_theme_back:
                finish();
                break;
            case R.id.ib_theme_head_back:
                finish();
                break;
        }
    }
}
