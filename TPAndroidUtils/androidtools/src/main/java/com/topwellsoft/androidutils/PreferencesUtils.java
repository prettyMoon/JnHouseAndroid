package com.topwellsoft.androidutils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PreferencesUtils {

    /**
     * 存储复杂类型
     *
     * @param context
     * @param key
     * @param object
     */
    public static void putObject(Context context, String key, Object object) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                key, Activity.MODE_PRIVATE);
        // 将Product对象转换成byte数组，并将其进行base64编码
        String productBase64 = new String(Base64.encodeBase64(baos
                .toByteArray()));
        Editor editor = mySharedPreferences.edit();
        // 将编码后的字符串写到base64.xml文件中
        editor.putString(key, productBase64);
        editor.commit();
    }

    public static Object getObject(Context context, String key) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                key, Activity.MODE_PRIVATE);
        String productBase64 = mySharedPreferences.getString(key, "");
        // 对Base64格式的字符串进行解码
        byte[] base64Bytes = Base64.decodeBase64(productBase64.getBytes());
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        ObjectInputStream ois = null;
        Object object = null;
        try {
            ois = new ObjectInputStream(bais);
            object = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 存储图片
     */
    public static void putimage(Context context, String key, Drawable drawable) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                key, Activity.MODE_PRIVATE);
        Editor editor = mySharedPreferences.edit();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 将ImageView组件中的图像压缩成JPEG格式，并将压缩结果保存在ByteArrayOutputStream对象中
        ((BitmapDrawable) drawable).getBitmap().compress(CompressFormat.PNG,
                50, baos);
        String imageBase64 = new String(Base64.encodeBase64(baos.toByteArray()));
        // 保存由图像字节流转换成的Base64格式字符串
        editor.putString(key, imageBase64);
        editor.commit();
    }

    /**
     * 获取图片
     *
     * @param context
     * @param key
     * @return
     */
    public static Drawable getimage(Context context, String key) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                key, Activity.MODE_PRIVATE);
        String imageBase64 = mySharedPreferences.getString(key, "");
        byte[] base64Bytes = Base64.decodeBase64(imageBase64.getBytes());
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        // 在ImageView组件上显示图像
        return Drawable.createFromStream(bais, key);
    }

    public static void clearData(Context context, String key) {

        SharedPreferences preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.clear().commit();
    }

    public static void writeData(Context context, String key, String content) {

        // 实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences = context.getSharedPreferences(key, Activity.MODE_PRIVATE);
        // 实例化SharedPreferences.Editor对象（第二步）
        Editor editor = mySharedPreferences.edit();
        // 用putString的方法保存数据
        editor.putString(key, content);
        // 提交当前数据
        editor.commit();
    }

    public static String readData(Context context, String key) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Activity.MODE_PRIVATE);
        String flag = sharedPreferences.getString(key, "");

        return flag;
    }

}
