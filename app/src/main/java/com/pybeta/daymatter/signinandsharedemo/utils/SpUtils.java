package com.pybeta.daymatter.signinandsharedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类
 * Created by luogj on 2018/2/2.
 */

public class SpUtils {

    private static SharedPreferences mSp;
    private static SpUtils spUtils;
    private static SharedPreferences.Editor mEdit;

    private static final String SP_UTILS = "sp_utils";
    private static final String SKIN_POSITION = "skin_position";
    private static final String LANGUAGE_TYPE = "language_type";
    private static final String LANGUAGE_NAME = "language_name";

    private SpUtils(Context context) {
        mSp = context.getSharedPreferences(SP_UTILS, Context.MODE_PRIVATE);
    }

    public static SpUtils getInstance(Context context) {
        if (spUtils == null) {
            spUtils = new SpUtils(context.getApplicationContext());
        }
        mEdit = mSp.edit();
        return spUtils;
    }

    /**
     * 保存肤色名称
     * @param skinName
     */
    public void saveSkinPosition(String skinName){
        mEdit.putString(SKIN_POSITION,skinName);
        mEdit.commit();
    }

    /**
     * 获取肤色名称
     * @return
     */
    public String getSkinPosition(){
        return mSp.getString(SKIN_POSITION,"");
    }

    /**
     * 保存语言种类
     * @param language_type
     */
    public void saveLanguageType(int language_type){
        mEdit.putInt(LANGUAGE_TYPE,language_type);
        mEdit.commit();
    }

    /**
     * 获取语言种类
     * @return
     */
    public int getLanguageType(){
        return mSp.getInt(LANGUAGE_TYPE,0);
    }

    public void saveLanguageName(String language_name){
        mEdit.putString(LANGUAGE_NAME,language_name);
        mEdit.commit();
    }

    public String getLanguageName(){
        return mSp.getString(LANGUAGE_NAME,"");
    }

}
