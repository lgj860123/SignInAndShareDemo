package com.pybeta.daymatter.signinandsharedemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 颜色主题样式
 * Created by luogj on 2018/2/24.
 */

public class ColorThemeBean implements Parcelable{

    private String themeName;
    private String themePosition;
    private boolean isSelect;

    public ColorThemeBean() {
    }

    protected ColorThemeBean(Parcel in) {
        themeName = in.readString();
        themePosition = in.readString();
        isSelect = in.readByte() != 0;
    }

    public static final Creator<ColorThemeBean> CREATOR = new Creator<ColorThemeBean>() {
        @Override
        public ColorThemeBean createFromParcel(Parcel in) {
            return new ColorThemeBean(in);
        }

        @Override
        public ColorThemeBean[] newArray(int size) {
            return new ColorThemeBean[size];
        }
    };

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemePosition() {
        return themePosition;
    }

    public void setThemePosition(String themePosition) {
        this.themePosition = themePosition;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(themeName);
        dest.writeString(themePosition);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }

    @Override
    public String toString() {
        return "ColorThemeBean{" +
                "themeName='" + themeName + '\'' +
                ", themePosition='" + themePosition + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }
}
