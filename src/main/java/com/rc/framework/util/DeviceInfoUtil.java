package com.rc.framework.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Description: 获取设备信息的工具类
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-01-09 19:50
 */
public class DeviceInfoUtil {

    /**
     * 手机设备序列号
     * 它会根据不同的手机设备返回IMEI，MEID或者ESN码，但在使用的过程中有以下问题：
     * 非手机设备：最开始搭载Android系统都手机设备，而现在也出现了非手机设备：如平板电脑、电子书、电视、音乐播放器等。
     * 这些设备没有通话的硬件功能，系统中也就没有TELEPHONY_SERVICE，自然也就无法通过上面的方法获得DEVICE_ID。
     * 权限问题：获取DEVICE_ID需要READ_PHONE_STATE权限，如果只是为了获取DEVICE_ID而没有用到其他的通话功能，申请这个权限一来大才小用，
     * 二来部分用户会怀疑软件的安全性。厂商定制系统中的Bug：少数手机设备上，由于该实现有漏洞，会返回垃圾，如:zeros或者asterisks
     *
     * @param context
     * @return
     */
    public static String phoneDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取sim卡系列号
     *
     * @param context
     * @return sim卡系列号
     */
    public static String simSerialNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimSerialNumber();
    }

    /**
     * 在设备首次启动时，系统会随机生成一个64位的数字，并把这个数字以16进制字符串的形式保存下来，当设备被wipe后该值会被重置。
     * ANDROID_ID可以作为设备标识，但需要注意：
     * 厂商定制系统的Bug：不同的设备可能会产生相同的ANDROID_ID：9774d56d682e549c。
     * 厂商定制系统的Bug：有些设备返回的值为null。设备差异：对于CDMA设备，ANDROID_ID和TelephonyManager.getDeviceId() 返回相同的值
     *
     * @param context
     * @return Settings.Secure.ANDROID_ID
     */
    public static String androidId(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Android系统2.3版本以上可以通过下面的方法得到Serial Number，且非手机设备也可以通过该接口获取。
     *
     * @param context
     * @return
     */
    public static String serialNumber(Context context) {
        return Build.SERIAL;
    }

}
