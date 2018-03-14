package com.pybeta.daymatter.signinandsharedemo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * 九宫格bean对象
 * Created by luogj on 2018/2/24.
 */

public class GridViewBean implements MultiItemEntity {

    public static final int ITEM_TITLE_VIEW = 2;
    public static final int ITEM_COLOR_THEME_VIEW = 3;
    public static final int ITEM_PERSONALITY_THEME_VIEW = 3;

    private int themeType;
    private String themeName;
    private List<ColorThemeBean> colorThemeBeanList;
    private List<PersonalityThemeBean> personalityThemeBeanList;


    public int getThemeType() {
        return themeType;
    }

    public void setThemeType(int themeType) {
        this.themeType = themeType;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public List<ColorThemeBean> getColorThemeBeanList() {
        return colorThemeBeanList;
    }

    public void setColorThemeBeanList(List<ColorThemeBean> colorThemeBeanList) {
        this.colorThemeBeanList = colorThemeBeanList;
    }

    public List<PersonalityThemeBean> getPersonalityThemeBeanList() {
        return personalityThemeBeanList;
    }

    public void setPersonalityThemeBeanList(List<PersonalityThemeBean> personalityThemeBeanList) {
        this.personalityThemeBeanList = personalityThemeBeanList;
    }

    @Override
    public int getItemType() {
        return themeType;
    }
}
