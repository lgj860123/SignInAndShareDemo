package com.pybeta.daymatter.signinandsharedemo.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;
import skin.support.content.res.SkinCompatResources;
import skin.support.utils.SkinFileUtils;

/**
 * 支持自定义控件换肤的工具类
 * Created by luogj on 2018/2/5.
 */

public class SkinUtils {

    private static File dex;

    public static Class customViewChangeSkin(Context context) {
        String skinName = SpUtils.getInstance(context).getSkinPosition();
        if (!"default".equals(skinName)) {
            try {
                String skinDir = SkinFileUtils.getSkinDir(context);
                String skinPath = skinDir + File.separator + skinName + ".apk";
                File dex_in = new File( skinPath);

                String optimizedDirectory = context.getCacheDir() + File.separator;
                ClassLoader classLoader = new DexClassLoader(dex_in.getAbsolutePath(), optimizedDirectory, null, context.getClassLoader());
                Log.i( "CustomViewSkinUtils", dex_in.getAbsolutePath()+"###customViewChangeSkin: " + skinName );
                Class clazz = classLoader.loadClass( "com.fanao.custom.CustomGetResourcesUtils" );
                return clazz;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void copySkinApk(Context context, String skinName) {

        try {

            String skinDir = SkinFileUtils.getSkinDir(context);
            String skinPath = skinDir + File.separator + skinName + ".apk";
            dex = new File(skinPath);

            InputStream input = context.getAssets().open("skins/" + skinName + ".skin");
            dex.createNewFile();
            FileOutputStream fo = new FileOutputStream(dex);
            int a = 0;
            byte bf[] = new byte[1024];
            while ((a = input.read( bf )) != -1) {
                fo.write( bf, 0, a );
                fo.flush();
            }
            fo.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void deleteSkinApk(){
        if(dex!=null &&dex.exists()){
            dex.delete();
        }
    }



    public static void copyNetSkinApk(Context context, String skinName,String path) {
        try {
            String skinDir = SkinFileUtils.getSkinDir(context);
            String skinPath = skinDir + File.separator + skinName + ".apk";
            dex = new File( skinPath);
            dex.createNewFile();
            FileInputStream inputStream = new FileInputStream(path);//读入原文件
            FileOutputStream fo = new FileOutputStream( dex );
            int a = 0;
            byte bf[] = new byte[1024];
            while ((a = inputStream.read( bf )) != -1) {
                fo.write( bf, 0, a );
                fo.flush();
            }
            fo.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int getCustomViewSkinColor(Context context, String name, Class clazz, Resources resources, int normalDayColor) {
        String skinName = SpUtils.getInstance( context ).getSkinPosition();
        if ((clazz != null && resources != null) && !"default".equals(skinName)) {
            try {
                Method method = clazz.getMethod(name, Resources.class);
                int SkinColor = (int) method.invoke(null, resources);
                return SkinColor;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return normalDayColor;
    }

    public static float getCustomViewSkinSize(Context context, String name, Class clazz, Resources resources, float normalSize) {
        String skinName = SpUtils.getInstance(context).getSkinPosition();
        if ((clazz != null && resources != null) && !"default".equals(skinName)) {
            try {
                Method method = clazz.getMethod(name, Resources.class);
                float SkinSize = (float) method.invoke(null, resources);
                return SkinSize;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return normalSize;
    }

    public static Resources addOtherResourcesToMain(Context context) {
        String skinName = SpUtils.getInstance(context ).getSkinPosition();
        if (!"default".equals(skinName)) {
//            File dex_in = new File( Environment.getExternalStorageDirectory().toString() + File.separator + skinName + ".apk" );
            String skinDir = SkinFileUtils.getSkinDir(context);
            String skinPath = skinDir + File.separator + skinName + ".apk";
            File dex_in = new File( skinPath);

            AssetManager assetManager = null;
            try {
                assetManager = AssetManager.class.newInstance();
                //反射调用addAssetPath这个方法 就可以
                Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
                addAssetPath.invoke(assetManager, dex_in.getAbsolutePath());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //把themeapk里的资源 通过addAssetPath 这个方法增加到本apk自己的path里面以后 就可以重新构建出resource对象了
            return new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        }
        return null;
    }

    /**
     * 获取支持换肤的 string
     * @param resId string resource id
     */
    public static String getString(View view, int resId) {
        String originString = view.getContext().getResources().getString(resId);

        if (view.isInEditMode() || SkinCompatResources.getInstance().isDefaultSkin()) {
            return originString;
        }

        String resName = view.getContext().getResources().getResourceEntryName(resId);

        int targetResId = SkinCompatResources
                .getInstance()
                .getSkinResources()
                .getIdentifier(resName, "string", SkinCompatResources.getInstance().getSkinPkgName());

        if (targetResId != 0) {
            return SkinCompatResources
                    .getInstance()
                    .getSkinResources()
                    .getString(targetResId);

        } else {
            return originString;
        }
    }
}
