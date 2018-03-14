package com.pybeta.daymatter.signinandsharedemo.bean;

/**
 * 多语言对象
 * Created by luogj on 2018/2/9.
 */

public class LanguageBean {
    private String languageName;
    private int languageType;
    private boolean isSelect;

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public int getLanguageType() {
        return languageType;
    }

    public void setLanguageType(int languageType) {
        this.languageType = languageType;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
