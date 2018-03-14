package com.pybeta.daymatter.signinandsharedemo.ui.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.bean.ColorThemeBean;
import com.pybeta.daymatter.signinandsharedemo.bean.EventBean;
import com.pybeta.daymatter.signinandsharedemo.bean.GridViewBean;
import com.pybeta.daymatter.signinandsharedemo.bean.PersonalityThemeBean;
import com.pybeta.daymatter.signinandsharedemo.custom.CustomRecyclerView;
import com.pybeta.daymatter.signinandsharedemo.custom.SpaceItemDecoration;
import com.pybeta.daymatter.signinandsharedemo.ui.my.activity.ThemePreviewActivity;
import com.pybeta.daymatter.signinandsharedemo.utils.GeneralUtils;
import com.pybeta.daymatter.signinandsharedemo.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 主题适配器
 * Created by luogj on 2018/2/26.
 */

public class ThemeAdapter extends BaseMultiItemQuickAdapter<GridViewBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ThemeAdapter(List<GridViewBean> data) {
        super(data);
        addItemType(GridViewBean.ITEM_TITLE_VIEW, R.layout.view_grid_view_title_view);
        addItemType(GridViewBean.ITEM_COLOR_THEME_VIEW,R.layout.view_grid_view_content_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, GridViewBean item) {
        Context context = helper.itemView.getContext();
        switch (helper.getItemViewType()){
            case GridViewBean.ITEM_TITLE_VIEW:
                initGridViewTitle(helper);
                break;
            case GridViewBean.ITEM_COLOR_THEME_VIEW:
                initGridViewContent(helper,item,context);
                break;
        }
    }

    /**
     * 初始化gridView的标题
     * @param helper
     */
    private void initGridViewTitle(BaseViewHolder helper) {
        if (helper.getLayoutPosition() == 1){
            helper.setBackgroundRes(R.id.iv_gridView_titleIcon,R.mipmap.gxzt_color);
            helper.setText(R.id.tv_gridView_titleDesc,R.string.color_theme);
        }else {
            helper.setBackgroundRes(R.id.iv_gridView_titleIcon,R.mipmap.gxzt_personality);
            helper.setText(R.id.tv_gridView_titleDesc,R.string.personality_theme);
        }
    }

    /**
     * 初始化gridView的内容
     * @param helper
     * @param item
     * @param context
     */
    private void initGridViewContent(BaseViewHolder helper, GridViewBean item, Context context) {
        CustomRecyclerView rv_customRecyclerView = helper.getView(R.id.rv_customRecyclerView);
        if (helper.getLayoutPosition() == 2){
            ColorThemeAdapter colorThemeAdapter = new ColorThemeAdapter(R.layout.view_color_theme_item_view,item.getColorThemeBeanList());
            rv_customRecyclerView.setLayoutManager(new GridLayoutManager(context,4));
            rv_customRecyclerView.setHasFixedSize(true);
            rv_customRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
            rv_customRecyclerView.setAdapter(colorThemeAdapter);
            colorThemeItemOnClickListener(colorThemeAdapter,item,context);

        }else {
            PersonalityThemeAdapter personalityThemeAdapter = new PersonalityThemeAdapter(R.layout.view_personolity_theme_item_view,item.getPersonalityThemeBeanList());
            rv_customRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
            rv_customRecyclerView.setHasFixedSize(true);
            rv_customRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
            rv_customRecyclerView.setAdapter(personalityThemeAdapter);
            personalityThemeItemOnClickListener(personalityThemeAdapter,item,context);

        }
    }

    private void colorThemeItemOnClickListener(final ColorThemeAdapter colorThemeAdapter, final GridViewBean item, final Context context) {
        colorThemeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ColorThemeBean colorThemeBean = item.getColorThemeBeanList().get(position);
                Intent intent = new Intent(context, ThemePreviewActivity.class);
                intent.putExtra(ThemePreviewActivity.COLOR_THEME,colorThemeBean);
                context.startActivity(intent);

//                Log.i("onItemClick", "##### onItemClick:颜色主题点击的： " +position);
//                SpUtils spUtils = SpUtils.getInstance(context);
//                spUtils.saveSkinPosition(colorThemeBean.getThemePosition());
//                colorThemeAdapter.notifyDataSetChanged();
//                EventBean eventBean = new EventBean(GeneralUtils.THEME_CHANGE);
//                EventBus.getDefault().post(eventBean);
            }
        });
    }

    private void personalityThemeItemOnClickListener(final PersonalityThemeAdapter personalityThemeAdapter, final GridViewBean item, final Context context) {
        personalityThemeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PersonalityThemeBean personalityThemeBean = item.getPersonalityThemeBeanList().get(position);

                Intent intent = new Intent(context, ThemePreviewActivity.class);
                intent.putExtra(ThemePreviewActivity.PERSONALITY_THEME,personalityThemeBean);
                context.startActivity(intent);


//                Log.i("onItemClick", "##### onItemClick:个性主题点击的： " +position + "，个性主题的：" +personalityThemeBean.getId());
//                SpUtils spUtils = SpUtils.getInstance(context);
//                spUtils.saveSkinPosition(personalityThemeBean.getId());
//                personalityThemeAdapter.notifyDataSetChanged();
//                EventBean eventBean = new EventBean(GeneralUtils.THEME_CHANGE);
//                EventBus.getDefault().post(eventBean);
            }
        });
    }
}
