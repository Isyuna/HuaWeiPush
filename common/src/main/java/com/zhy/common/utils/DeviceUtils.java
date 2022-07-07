package com.zhy.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

/**
 * author : zhangyun.
 * date  : 2022/6/10  09:36.
 * description :设备信息获取工具类
 **/
public class DeviceUtils {

    //保存文件的路径
    private static final String CACHE_IMAGE_DIR = "/laozhang/cache/devices";
    //保存的文件 采用隐藏文件的形式进行保存
    private static final String DEVICES_FILE_NAME = ".DEVICES";
    public static final String SP_DEVICES_ID = "SP_DEVICES_ID";

    @SuppressLint("MissingPermission")
    public static String getSimSerialNumber(Context context) {
        String simSerialNumber = "";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            //SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
            SubscriptionManager subscriptionManager = SubscriptionManager.from(context);
            SubscriptionInfo activeSubscriptionInfo = null;
            if ("TAS-AL00".equals(getSystemModel()) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                TelecomManager tm2 = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
                List<PhoneAccountHandle> phoneAccounts = tm2.getCallCapablePhoneAccounts();
                PhoneAccountHandle phoneAccountHandl = phoneAccounts.get(0);
                if (phoneAccountHandl != null) {
                    simSerialNumber = phoneAccounts.get(0).getId();
                }
                return simSerialNumber;
            }
            List<SubscriptionInfo> activeSubscriptionInfos = subscriptionManager.getActiveSubscriptionInfoList();
            if (activeSubscriptionInfos != null && activeSubscriptionInfos.size() > 0) {
                activeSubscriptionInfo = activeSubscriptionInfos.get(0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    simSerialNumber = String.valueOf(activeSubscriptionInfo.getSubscriptionId());
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                    if (compareTextSame(OPPO)) {
                    simSerialNumber = String.valueOf(activeSubscriptionInfo.getSubscriptionId());
//                    } else {
//                        simSerialNumber = activeSubscriptionInfo.getIccId();
//                    }
                } else {
                    simSerialNumber = activeSubscriptionInfo.getIccId();
                }
            }
        } else {
            simSerialNumber = tm.getSimSerialNumber();
        }
        return simSerialNumber;
    }

    /**
     * 通过反射调取@hide的方法
     *
     * @param predictedMethodName 方法名
     * @param id                  参数
     * @return 返回方法调用的结果
     * @throws MethodNotFoundException 方法没有找到
     */
    private static String getReflexMethodWithId(Context context, String predictedMethodName, String id) throws MethodNotFoundException {
        String result = null;
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Class<?> telephonyClass = Class.forName(telephony.getClass().getName());
            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getSimID = telephonyClass.getMethod(predictedMethodName, parameter);
            Class<?>[] parameterTypes = getSimID.getParameterTypes();
            Object[] obParameter = new Object[parameterTypes.length];
            if (parameterTypes[0].getSimpleName().equals("int")) {
                obParameter[0] = Integer.valueOf(id);
            } else if (parameterTypes[0].getSimpleName().equals("long")) {
                obParameter[0] = Long.valueOf(id);
            } else {
                obParameter[0] = id;
            }
            Object ob_phone = getSimID.invoke(telephony, obParameter);
            if (ob_phone != null) {
                result = ob_phone.toString();
            }
        } catch (Exception e) {
            throw new MethodNotFoundException(predictedMethodName);
        }
        return result;
    }

    /**
     * 反射未找到方法
     */
    private static class MethodNotFoundException extends Exception {

        public static final long serialVersionUID = -3241033488141442594L;

        MethodNotFoundException(String info) {
            super(info);
        }
    }

    @SuppressLint("MissingPermission")
    public static String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = null;
        String readDeviceID = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            readDeviceID = readDeviceID(context);
            imei = getData(context);
            if (!StringUtils.isEmpty(imei)) {
                //app 缓存的和SD卡中保存的不相同 以app 保存的为准, 同时更新SD卡中保存的 唯一标识符
                if (StringUtils.isEmpty(readDeviceID) && !imei.equals(readDeviceID)) {
                    // 取有效地 app缓存 进行更新操作
                    if (StringUtils.isEmpty(readDeviceID) && !StringUtils.isEmpty(imei)) {
                        readDeviceID = imei;
                        saveDeviceID(readDeviceID, context);
                    }
                }
            } else {
                // app 没有缓存 (这种情况只会发生在第一次启动的时候)
                if (StringUtils.isEmpty(readDeviceID)) {
                    //保存设备id
                    readDeviceID = getDeviceId(context);
                }
                imei = readDeviceID;
                saveData(context, readDeviceID);
            }
        } else {
            if (tm != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    imei = tm.getImei();
                } else {
                    imei = tm.getDeviceId();
                }
            }
        }
        return imei;
    }

    public static void saveData(Context context, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SP_DEVICES_ID, value);
        editor.commit();
    }

    public static String getData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String deviceId = sharedPreferences.getString(SP_DEVICES_ID, "");
        return deviceId;
    }

    /**
     * 保存 内容到 SD卡中,  这里保存的就是 设备唯一标识符
     *
     * @param str
     * @param context
     */
    public static void saveDeviceID(String str, Context context) {
        File file = getDevicesDir(context);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(str);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取设备唯一标识符
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        //读取保存的在sd卡中的唯一标识符
        String deviceId = readDeviceID(context);
        //判断是否已经生成过,
        if (deviceId != null && !"".equals(deviceId)) {
            return deviceId;
        }
        deviceId = getDelete_Uuid(getUUID());
        if (deviceId != null && !"".equals(deviceId)) {
            saveDeviceID(deviceId, context);
        }
        return deviceId;
    }

    @SuppressLint("MissingPermission")
    public static String getUUID() {

        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                serial = android.os.Build.getSerial();
            } else {
                serial = Build.SERIAL;
            }
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * 获得一个去掉"-"符号的UUID
     *
     * @return
     */
    public static String getDelete_Uuid(String uuid) {
        return uuid.replace("-", "");
    }

    /**
     * 读取固定的文件中的内容,这里就是读取sd卡中保存的设备唯一标识符
     *
     * @param context
     * @return
     */
    public static String readDeviceID(Context context) {
        File file = getDevicesDir(context);
        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            Reader in = new BufferedReader(isr);
            int i;
            while ((i = in.read()) > -1) {
                buffer.append((char) i);
            }
            in.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 统一处理设备唯一标识 保存的文件的地址
     *
     * @param context
     * @return
     */
    private static File getDevicesDir(Context context) {
        File mCropFile = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cropdir = new File(Environment.getExternalStorageDirectory(), CACHE_IMAGE_DIR);
            if (!cropdir.exists()) {
                cropdir.mkdirs();
            }
            mCropFile = new File(cropdir, DEVICES_FILE_NAME);
        } else {
            File cropdir = new File(context.getFilesDir(), CACHE_IMAGE_DIR);
            if (!cropdir.exists()) {
                cropdir.mkdirs();
            }
            mCropFile = new File(cropdir, DEVICES_FILE_NAME);
        }
        return mCropFile;
    }

    public static int getVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            return info.versionCode;
        }
        return 0;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }


}
