package com.pybeta.daymatter.signinandsharedemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 个性主题样式
 * Created by luogj on 2018/2/24.
 */
@Entity
public class PersonalityThemeBean implements Parcelable{
    @Unique
    @Property(nameInDb = "id")
    private String id;              // 主题id
    @Property(nameInDb = "themeName")
    private String themeName;       //主题名称
    @Property(nameInDb = "themeLogo")
    private String themeLogo;       // 图标
    @Property(nameInDb = "isSelect")
    private boolean isSelect ;      //是否选中

    public PersonalityThemeBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeLogo() {
        return themeLogo;
    }

    public void setThemeLogo(String themeLogo) {
        this.themeLogo = themeLogo;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    protected PersonalityThemeBean(Parcel in) {
        id = in.readString();
        themeName = in.readString();
        themeLogo = in.readString();
        isSelect = in.readByte() != 0;
    }

    public static final Creator<PersonalityThemeBean> CREATOR = new Creator<PersonalityThemeBean>() {
        @Override
        public PersonalityThemeBean createFromParcel(Parcel in) {
            return new PersonalityThemeBean(in);
        }

        @Override
        public PersonalityThemeBean[] newArray(int size) {
            return new PersonalityThemeBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(themeName);
        dest.writeString(themeLogo);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }

    @Override
    public String toString() {
        return "PersonalityThemeBean{" +
                "id='" + id + '\'' +
                ", themeName='" + themeName + '\'' +
                ", themeLogo='" + themeLogo + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }
}
