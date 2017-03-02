package com.liupf.androidstudy.util;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by root on 16-5-9.
 */
public class Util {

    /**
     * 新建文件夹
     *
     * @param path
     */
    public static void makeDir(String path) {
        if (path == null || path.length() == 0)
            return;
        makeDir(new File(path));
    }

    /**
     * 新建文件夹
     *
     * @param file
     */
    public static void makeDir(File file) {
        if (file == null || file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static void deleteFile(String path) {
        if (!TextUtils.isEmpty(path))
            deleteFile(new File(path));
    }

    public static void delete(String path) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
                file = null;
            }
        }

    }

    public static void deleteFile(File file) {
        try {
            if (file != null && file.exists()) {
                File toFile = new File(file.getAbsolutePath() + ".tmp");
                file.renameTo(toFile);
                toFile.delete();
                toFile = null;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 判断sdcard是否可用
     *
     * @return
     */
    public static boolean isSDCardAvailable() {
        boolean sdcardAvailable = false;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sdcardAvailable = true;
        }
        return sdcardAvailable;
    }

    /**
     * 数组转列表
     */
    public static ArrayList<Byte> byteArrayToList(byte[] byteArray) {
        ArrayList<Byte> byteList = new ArrayList<Byte>();

        if (byteArray != null) {
            for (int i = 0; i < byteArray.length; i++) {
                byteList.add(byteArray[i]);
            }
        }
        return byteList;
    }

    /**
     * 数组转列表
     */
    public static byte[] byteListToArray(List<Byte> byteArray) {
        byte[] tempArray = new byte[byteArray.size()];

        if (byteArray != null) {
            for (int i = 0; i < byteArray.size(); i++) {
                tempArray[i] = byteArray.get(i);
            }
        }
        return tempArray;
    }

    /**
     * 加密
     *
     * @param src
     * @return
     */
    public static String encryptString(String src, String seed) {
        byte[] temp = src.getBytes();

        int length = temp.length;
        int count = length / 2 / 2 + (length / 2 % 2 == 0 ? 0 : 1);

        for (int i = 0; i < count; i++) {
            int index = 0;

            if (length % 2 == 0) {
                index = length / 2 + 2 * i;
            } else {
                index = length / 2 + 2 * i + 1;
            }

            byte tempByte = temp[i];
            temp[i] = temp[index];
            temp[index] = tempByte;
        }

        for (int i = 0; i < length / 2 - count; i++) {
            int index = length - 2 * i - 1;
            byte tempByte = temp[i + count];
            temp[i + count] = temp[index];
            temp[index] = tempByte;
        }

        byte[] byteSend = seed.getBytes();
        int timeLength = byteSend.length;

        ArrayList<Byte> result = byteArrayToList(temp);

        for (int i = 0; i < timeLength; i++) {
            if (i < length) {
                result.add(length - i, byteSend[i]);
            } else {
                result.add(0, byteSend[i]);
            }
        }

        byte[] tempResult = Util.byteListToArray(result);
        return new String(tempResult);
    }

    /**
     * 获取单个imei
     */
    public static String getSingleIMEI(Context context) {
        String imei = "";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(imei)) {
            if (imei.trim().length() < 15) {
                int count = 15 - imei.trim().length();
                imei = imei.trim();
                for (int i = 0; i < count; i++) {
                    imei += "0";
                }
            }
        }
        return imei;
    }

//    public static String getMacAddress() {
//        try {
//            Context sContext = App.getInstance().getApplicationContext();
//            WifiManager wifi = (WifiManager) sContext.getSystemService(Context.WIFI_SERVICE);
//            String macAddress = wifi.getConnectionInfo().getMacAddress();
//            macAddress = macAddress.replace(":", "");
//            return macAddress;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * gzip压缩
     */
    public static byte[] gzipCompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return new byte[0];
        }
        return gzipCompress(str.getBytes());
    }

    /**
     * gzip压缩
     */
    public static byte[] gzipCompress(byte[] source) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(source);
        gzip.close();
        return out.toByteArray();
    }

    /**
     * gzip解压
     */
    public static byte[] gzipDecompress(String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        return gzipDecompress(str.getBytes());
    }

    /**
     * gzip解压
     */
    public static byte[] gzipDecompress(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /**
     * gzip解压
     */
    public static byte[] gzipDecompress(InputStream is) {
        byte[] b = null;
        try {
            GZIPInputStream gzip = new GZIPInputStream(is);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

//    /**
//     * @param sizeB
//     * @return 格式化文件大小
//     */
//    public static final String formatFileSize(long sizeB) {
//        double SIZE_KB = 1024.00;
//        String STR_M = "M";
//
//        BigDecimal sizeTotal = null;
//        if (sizeB > -1) {
//            sizeTotal = new BigDecimal(sizeB / SIZE_KB / SIZE_KB);
//            return sizeTotal.intValue() + STR_M;
//        } else {
//            return sizeB + "";
//        }
//    }

    public static final String getDataSize(float size) {
        DecimalFormat formater = new DecimalFormat("####");
        float SIZE_KB = 1024.00f;

        if (size < SIZE_KB) {
            return size + "bytes";

        } else if (size < SIZE_KB * SIZE_KB) {
            float kbsize = size / SIZE_KB;
            return formater.format(kbsize) + "KB";

        } else if (size < SIZE_KB * SIZE_KB * SIZE_KB) {
            float mbsize = size / SIZE_KB / SIZE_KB;
            return formater.format(mbsize) + "MB";

        } else if (size < SIZE_KB * SIZE_KB * SIZE_KB * SIZE_KB) {
            float gbsize = size / SIZE_KB / SIZE_KB / SIZE_KB;
            return formater.format(gbsize) + "GB";

        } else {
            return "size: error";
        }
    }

//        /**
//         * 计算进度
//         *
//         * @param min
//         * @param total
//         * @return
//         */
//    public static int computeProgress(long min, long total) {
//        return (int) (total == 0 ? 0 : min * 100 / total);
//    }

    /**
     * 计算进度
     *
     * @param min
     * @param total
     * @return
     */
    public static float computeProgress(long min, long total) {
        if (total == 0) {
            return 0;
        } else {
            float douMin = (float) min;
            float douTotal = (float) total;
            DecimalFormat decimalFormat = new DecimalFormat("###.0");
            String p = decimalFormat.format(douMin * 100 / douTotal);
            return Float.valueOf(p);
        }
    }

    /**
     * 计算进度
     *
     * @param min
     * @param total
     * @return
     */
    public static int computeProgress(int min, int total) {
        return total == 0 ? 0 : min * 100 / total;
    }

    /**
     * 计算进度
     *
     * @param min
     * @param total
     * @return
     */
    public static int computeProgressInt(long min, long total) {
        return (int) (total == 0 ? 0 : min * 100 / total);
    }

    public static String getFileNameFromUrl(String url) {
        return getMD5(url);
    }

    public static String getMD5(String val) {
        return val != null ? getMD5(val.getBytes()) : "";
    }

    public static String getMD5(byte[] val) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = e.digest(val);
            StringBuffer hexValue = new StringBuffer();

            for (int i = 0; i < md5Bytes.length; ++i) {
                int temp = md5Bytes[i] & 255;
                if (temp < 16) {
                    hexValue.append("0");
                }

                hexValue.append(Integer.toHexString(temp));
            }

            return hexValue.toString();
        } catch (Exception var6) {
            var6.printStackTrace();
            return "";
        }
    }


//    public static boolean isWhiteItem(String name) {
//        ArrayList<String> whiteList = Util.arrayToList(App.getInstance()
//                .getResources().getStringArray(R.array.auto_white_list));
//        if (whiteList == null || whiteList.size() == 0)
//            return false;
//
//        return whiteList.contains(name);
//    }

    /**
     * 数组转列表
     */
    public static ArrayList<String> arrayToList(String[] strArray) {
        ArrayList<String> strList = new ArrayList<String>();

        if (strArray != null) {
            for (int i = 0; i < strArray.length; i++) {
                strList.add(strArray[i]);
            }
        }
        return strList;
    }


    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            if (!email.contains("@")) return flag;
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    /**
     * Attempts to cancel execution of this task
     *
     * @param task
     * @param mayInterruptIfRunning true if the thread executing this task should be interrupted;
     *                              otherwise, in-progress tasks are allowed to complete
     */
    public static void cancelTask(AsyncTask<?, ?, ?> task,
                                  boolean mayInterruptIfRunning) {
        if (task == null)
            return;
        if (task.getStatus() != AsyncTask.Status.FINISHED) {
            task.cancel(mayInterruptIfRunning);
        }
    }

//    public static List<BaseInfo> getLocalPkgData(Context context) {
//        List<BaseInfo> installedList = new ArrayList<>();
//        String apkDir = DownloadUtil.getInstance(context).getApkCacheDir();
//        File apkDirFile = new File(apkDir);
//        if (apkDirFile != null && apkDirFile.isDirectory()) {
//            File[] files = apkDirFile.listFiles(new FileFilter() {
//                @Override
//                public boolean accept(File file) {
//                    return file.isFile() && file.getName().endsWith(Const.DOWNLOAD_FILE_APK);
//                }
//            });
//            if (files != null) {
//                AppSnippet mAppSnippet;
//                AppInfo mAppinfo;
//                for (File file : files) {
//                    if (file != null && file.exists()) {
//                        mAppSnippet = getAppSnippet(context, Uri.parse(file.getAbsolutePath()));
//                        if (mAppSnippet == null) continue;
//                        if (mAppSnippet.getPackageName().equals(App.getInstance().getPackageName()) &&
//                                mAppSnippet.getVersionCode() <= Util.getAppVersionCode())
//                            continue;
//                        mAppinfo = new AppInfo().parse(mAppSnippet);
//                        if (!com.mycheering.game.util.PackageUtil.isInstalledApk(mAppinfo.getPackageName(), mAppinfo.getVersionCode())) {
//                            installedList.add(mAppinfo);
//                        }
//                    }
//                }
//            }
//        }
//        return installedList;
//    }

//    /**
//     * 正在下载
//     *
//     * @return
//     */
//    public static List<BaseInfo> getDownloadingData(Context context) {
//        List<BaseInfo> appList = new ArrayList<>();
//        List<DownloadTask> listData = DownloadTask.getAllDownloadTasks(context);
//        if (listData == null || listData.size() == 0) return appList;
//
//        for (int i = 0; i < listData.size(); i++) {
//            if (listData.get(i).state > 5) {
//                continue;
//            }
//            //去掉对自身应用的限制
////            if (App.getInstance().getPackageName().equals(listData.get(i).pkg)) {
////                continue;
////            }
//            AppInfo info = new AppInfo().convertDownloadTask(listData.get(i));
//            if (info != null &&
//                    (info.getDownDoiType() == DownloadTask.DIOTYPE_NORMAL ||
//                            info.getDownDoiType() == DownloadTask.DIOTYPE_WIFI_UPDATE_AND_INSTALL ||
//                            info.getDownDoiType() == DownloadTask.DIOTYPE_UPDATE ||
//                            info.getDownDoiType() == DownloadTask.DIOTYPE_WIFI_DOWNLOADED_AND_INSTALL_SELF)) {
//                info.setIsCancel(0);
//                appList.add(info);
//            }
//        }
//        return appList;
//    }

//    public static int getDownloadNum(Context context) {
//        return getDownloadingData(context).size() + getLocalPkgData(context).size();
//    }
//
//    public static int getUpgradeNum(Context context) {
//        return AppInfo.getAllUpdateAppListFromDb(true).size();
//    }
//
//
//    public static int getLocalAppNum(Context context) {
//        return getLocalAPPData(context).size();
//    }
//
//    public static List<BaseInfo> getLocalAPPData(Context context) {
//        PackageManager manager = context.getPackageManager();
//        List<PackageInfo> mPackageInfo = manager.getInstalledPackages(0);
//        List<BaseInfo> installAppInfo = new ArrayList<>();
//        AppInfo info;
//        AppSnippet appSnippet;
//        for (PackageInfo packageInfo : mPackageInfo) {
//            if (App.getInstance().getPackageName().equals(packageInfo.packageName) ||
//                    Util.isWhiteItem(packageInfo.packageName)) {
//                continue;
//            }
//
//            if (PackageUtil.isUserApp(context, packageInfo.packageName)) {//用户应用
//                try {
//                    appSnippet = new AppSnippet().parse(packageInfo, null, manager, "");
//                    info = new AppInfo().parse(appSnippet);
//                    installAppInfo.add(info);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return installAppInfo;
//    }
//
//    public static AppSnippet getAppSnippet(String pkg) {
//        Context context = App.getInstance().getApplicationContext();
//        AppSnippet appSnippet = null;
//        try {
//            PackageManager sPackageManager = context.getPackageManager();
//            PackageInfo sPackageInfo = sPackageManager.getPackageInfo(pkg, 0);
//            if (sPackageInfo != null) {
//                String path = sPackageInfo.applicationInfo.sourceDir;
//                appSnippet = new AppSnippet().parse(sPackageInfo, null, sPackageManager, path);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return appSnippet;
//    }
//
//    public static int getAppVersionCode(String pkg) {
//        Context context = App.getInstance().getApplicationContext();
//        int versionCode = 10000000;
//        try {
//            PackageManager sPackageManager = context.getPackageManager();
//            PackageInfo sPackageInfo = sPackageManager.getPackageInfo(pkg, 0);
//            if (sPackageInfo != null) {
//                versionCode = sPackageInfo.versionCode;
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return versionCode;
//    }
//
//    public static boolean isInstalledAppUpdate(String pkg, int versionCode) {
//        Context context = App.getInstance().getApplicationContext();
//        boolean flag = false;
//        try {
//            PackageManager sPackageManager = context.getPackageManager();
//            PackageInfo sPackageInfo = sPackageManager.getPackageInfo(pkg, 0);
//            if (sPackageInfo != null) {
//                flag = versionCode > sPackageInfo.versionCode;
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return flag;
//    }
//
//    public static AppSnippet getAppSnippet(Context context, Uri packageURI) {
//        AppSnippet sAppSnippet = new AppSnippet();
//        try {
//            String apkPath = packageURI.getPath();
//            PackageManager pm = context.getPackageManager();
//            PackageInfo packageInfo = pm.getPackageArchiveInfo(apkPath, 1);
//            Resources sResources = getUninstalledApkResources(context, apkPath);
//            sAppSnippet.parse(packageInfo, sResources, pm, apkPath);
//        } catch (Exception var11) {
//        }
//        return sAppSnippet;
//    }
//
//
//    public static Resources getUninstalledApkResources(Context context, String apkPath) {
//        if (TextUtils.isEmpty(apkPath)) {
//            return context.getResources();
//        } else {
//            File apkFile = new File(apkPath);
//            if (!apkFile.exists()) {
//                return context.getResources();
//            } else {
//                String PATH_AssetManager = "android.content.res.AssetManager";
//
//                try {
//                    Class[] var10000 = new Class[]{String.class};
//                    Object[] var12 = new Object[]{apkPath};
//                    Class assetMagCls = Class.forName(PATH_AssetManager);
//                    Object assetMag = assetMagCls.newInstance();
//                    Class[] e = new Class[]{String.class};
//                    Method assetMag_addAssetPathMtd = assetMagCls.getDeclaredMethod("addAssetPath", e);
//                    Object[] valueArgs = new Object[]{apkPath};
//                    assetMag_addAssetPathMtd.invoke(assetMag, valueArgs);
//                    Resources res = context.getResources();
//                    e = new Class[]{assetMag.getClass(), res.getDisplayMetrics().getClass(), res.getConfiguration().getClass()};
//                    Constructor resCt = Resources.class.getConstructor(e);
//                    valueArgs = new Object[]{assetMag, res.getDisplayMetrics(), res.getConfiguration()};
//                    res = (Resources) resCt.newInstance(valueArgs);
//                    return res;
//                } catch (Exception var11) {
//                    var11.printStackTrace();
//                    return context.getResources();
//                }
//            }
//        }
//    }

//    private static Bitmap bitmap;
//
//    public static Bitmap drawableToBitamp(Drawable drawable) {
//        int w = drawable.getIntrinsicWidth();
//        int h = drawable.getIntrinsicHeight();
//        Bitmap.Config config =
//                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
//                        : Bitmap.Config.RGB_565;
//        bitmap = Bitmap.createBitmap(w, h, config);
//
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, w, h);
//        drawable.draw(canvas);
//        return bitmap;
//    }
//
//
//    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
//        int w = bitmap.getWidth();
//        int h = bitmap.getHeight();
//        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//        final int color = 0xff424242;
//        final Paint paint = new Paint();
//        final Rect rect = new Rect(0, 0, w, h);
//        final RectF rectF = new RectF(rect);
//        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        paint.setColor(color);
//        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(bitmap, rect, rect, paint);
//
//        return output;
//    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

//    /**
//     * 设备名称
//     *
//     * @return
//     */
//    public static String getDeviceName() {
//        String deviceName = SPUtil.getInstant().getString(SPUtil.DeviceName, "");
//        if (TextUtils.isEmpty(deviceName)) {
//            deviceName = Build.MODEL;
//            if (isNumber(deviceName) || TextUtils.isEmpty(deviceName))
//                deviceName = ShellUtils.runCmd("hideapi_hook device");
//            if (!TextUtils.isEmpty(deviceName)) {
//                SPUtil.getInstant().save(SPUtil.DeviceName, deviceName);
//            }
//        }
//        return deviceName;
//    }
//
//    /**
//     * 软件版本
//     *
//     * @return
//     */
//    public static String getAppVersion() {
//        try {
//            PackageManager manager = App.getInstance().getPackageManager();
//            PackageInfo info = manager.getPackageInfo(App.getInstance()
//                    .getPackageName(), 0);
//            String sVersion = String.valueOf(info.versionName);
//            return sVersion;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    /**
//     * 软件版本号
//     *
//     * @return
//     */
//    public static int getAppVersionCode() {
//        try {
//            PackageManager manager = App.getInstance().getPackageManager();
//            PackageInfo info = manager.getPackageInfo(App.getInstance()
//                    .getPackageName(), 0);
//            return info.versionCode;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public static String getIMSI() {
//        String imsi = SPUtil.getInstant().getString(SPUtil.IMSI, "");
//        if (!TextUtils.isEmpty(imsi)) return imsi;
//        try {
//            Context sContext = App.getInstance().getApplicationContext();
//            TelephonyManager tm = (TelephonyManager) sContext
//                    .getSystemService(Context.TELEPHONY_SERVICE);
//            imsi = tm.getSubscriberId();
//            SPUtil.getInstant().save(SPUtil.IMSI, imsi);
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return imsi;
//    }
//
//    /**
//     * 获取自身签名信息
//     *
//     * @return
//     */
//    public static String getSelfSignature() {
//        App sApp = App.getInstance();
//        String strSignature = PackageUtil.getApkSignatureWithPackageName(
//                sApp.getApplicationContext(), sApp.getPackageName());
//        if (TextUtils.isEmpty(strSignature)) {
//            return "";
//        } else {
//            return strSignature;
//        }
//    }
//
//    /**
//     * 打开应用
//     *
//     * @param packageName
//     */
//    public static void openApp(String packageName) {
//        try {
//            Context sContext = App.getInstance().getApplicationContext();
//            if (PackageUtil.isInstalledApk(packageName)) {
//                PackageManager packageManager = sContext.getPackageManager();
//                Intent intent = packageManager.getLaunchIntentForPackage(packageName);
//                if (intent != null) {
//                    sContext.startActivity(intent);
//                }
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 打开软件详情页
//     *
//     * @param from
//     * @param appId
//     */
//    public static void openAppDetail(int from, long appId) {
//        Context sContext = App.getInstance().getApplicationContext();
//        AppDetailActivity.actionAppDetailActivity(
//                sContext,
//                "",                       //标题
//                appId,                    //软件id
//                PageId.PAGE_APP_DETAIL,   //详情页type
//                from,                     //来源id
//                0,                        //默认打开tab
//                new BaseInfo());          //appinfo元素
//    }
//
//    public static void openAppDetail(int from, long appId, int defaultTab) {
//        Context sContext = App.getInstance().getApplicationContext();
//        AppDetailActivity.actionAppDetailActivity(
//                sContext,
//                "",                       //标题
//                appId,                    //软件id
//                PageId.PAGE_APP_DETAIL,   //详情页type
//                from,                     //来源id
//                defaultTab,               //默认打开tab
//                new BaseInfo());          //appinfo元素
//    }
//
//    public static void openAppDetail(int from, BaseInfo baseInfo, int defaultTab) {
//        Context sContext = App.getInstance().getApplicationContext();
//        AppDetailActivity.actionAppDetailActivity(sContext, baseInfo.getTitle(),
//                baseInfo.getAppId(), PageId.PAGE_APP_DETAIL, (int) from, defaultTab, baseInfo);
//    }
//
//    /**
//     * 制造商
//     *
//     * @return
//     */
//    public static String getManufactruer() {
//        return Build.MANUFACTURER + "";
//    }
//
//    /**
//     * 厂商
//     *
//     * @return
//     */
//    public static String getCompany() {
//        if (isYunOs()) {
//            return "yunos";
//        } else if (isBaiduOs()) {
//            return "baidu";
//        } else {
//            return Build.BRAND;
//        }
//    }

//    /**
//     * 是否是Miui
//     */
//    public static boolean isMiui() {
//        String strCompany = getCompany();
//        if (strCompany != null) {
//            return "xiaomi".equals(strCompany.toLowerCase(Locale.getDefault()));
//        }
//        return false;
//    }

//    /**
//     * 是否是阿里os
//     */
//    public static boolean isYunOs() {
//        BufferedReader sReader = null;
//        try {
//            File sFile = new File("/system/build.prop");
//            sReader = new BufferedReader(new InputStreamReader(new FileInputStream(sFile)));
//            String strBuffer;
//
//            while ((strBuffer = sReader.readLine()) != null) {
//                if (strBuffer.startsWith("ro.sys.vendor") && strBuffer.toLowerCase(Locale.getDefault()).contains("yunos")) {
//                    try {
//                        if (sReader != null) {
//                            sReader.close();
//                            sReader = null;
//                        }
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//                    return true;
//                }
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        } finally {
//            if (sReader != null) {
//                try {
//                    sReader.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 是否是百度os
//     *
//     * @return
//     */
//    public static boolean isBaiduOs() {
//        try {
//            String manufactruer = getManufactruer();
//
//            if (!TextUtils.isEmpty(manufactruer) && manufactruer.toLowerCase(Locale.getDefault()).trim().equals("baidu")) {
//                return true;
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 是否是需要过滤的app
//     *
//     * @param context
//     * @param packageInfo
//     * @param systemSignature
//     * @return
//     */
//    public static boolean filterUpdateApp(Context context,
//                                          PackageInfo packageInfo, String systemSignature) {
//        try {
//            PackageManager sPackageManager = context.getPackageManager();
//            Intent sIntent = sPackageManager
//                    .getLaunchIntentForPackage(packageInfo.packageName);
//
//            if (sIntent == null) {
//                return true;
//            } else {
//                if (systemSignature == null) {
//                    if (isBrandApp(context, packageInfo.packageName)) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                } else {
//                    if (systemSignature.equals(PackageUtil
//                            .getApkSignatureWithPackageName(context,
//                                    packageInfo.applicationInfo.packageName))) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 是否是厂商app
//     *
//     * @param context
//     * @param pkg
//     * @return
//     */
//    private static boolean isBrandApp(Context context, String pkg) {
//        String[] filterPkg = context.getResources().getStringArray(
//                R.array.filter_package_app_update);
//        for (String string : filterPkg) {
//            if (string.equals(pkg)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static synchronized void deleteDownloadTask(Context context, String pkg) {
//        String selection = "pkg = ? ";
//        String[] selectionArgs = new String[]{pkg};
//        DownloadTask.delete(context, DownloadTask.CONTENT_URI, selection, selectionArgs);
//    }
//
//    public static ArrayList listToArrayList(List strArray) {
//        ArrayList strList = new ArrayList();
//
//        if (strArray != null) {
//            for (int i = 0; i < strArray.size(); i++) {
//                strList.add(strArray.get(i));
//            }
//        }
//        return strList;
//    }
//
//    public static void handAppUpdateCanPush(AppInfo appInfo) {
//        Context context = App.getInstance().getApplicationContext();
//        if (!SPUtil.getInstant().getString(SPUtil.UpdateVersionName, "")
//                .equals(appInfo.getVersionName())) {
//            SPUtil.getInstant().save(SPUtil.UpdatePushCount, (int) 0);
//            SPUtil.getInstant().save(SPUtil.LastUpdatePushTime, (long) 0);
//            SPUtil.getInstant().save(SPUtil.UpdateVersionName, appInfo.getVersionName());
//        }
//        //去掉3次push的限制
//        int pushCount = (Integer) SPUtil.getInstant().get(SPUtil.UpdatePushCount, (int) 0);
//        SPUtil.getInstant().save(SPUtil.UpdatePushCount, (int) pushCount++);
//        SPUtil.getInstant().save(SPUtil.LastUpdatePushTime, (long) System.currentTimeMillis());
//
//        try {
//            HashMap hashMap = new HashMap();
//            hashMap.put("appId", appInfo.getAppId());
//            hashMap.put("crc", appInfo.getCrc32());
//            hashMap.put("downloadUrl", appInfo.getApkUrl());
//            hashMap.put("id", appInfo.getsId());
//            hashMap.put("imgUrl", appInfo.getImageUrl());
//            hashMap.put("name", appInfo.getTitle());
//            hashMap.put("openType", appInfo.getOpenType());
//            hashMap.put("packageName", appInfo.getPackageName());
//            hashMap.put("size", appInfo.getSize());
//            hashMap.put("version", appInfo.getVersionCode());
//            hashMap.put("versionName", appInfo.getVersionName());
//            String jsonStr = JsonParseUtil.getTargetJsonString(hashMap);
//
//            PushBean pushBean = new PushBean();
//            pushBean.id = Const.SELFUPDATE_PUSH_ID;
//            pushBean.title = context.getString(R.string.self_update_remind);
//            pushBean.content = context.getString(R.string.selfupdate_push_message,
//                    Util.getAppVersion(), appInfo.getVersionName());
//            pushBean.clear = true;
//            pushBean.sound = false;
//            pushBean.type = PushEventService.PUSH_TYPE_SELFUPDATE;
//            pushBean.pushType = pushBean.showType = PushEventService.PUSH_TYPE_SELFUPDATE;
//            pushBean.data = jsonStr;
//            pushBean.offline = true;
//
//            int lastid = PushUtil.getLastPushID(context, PushEventService.PUSH_TYPE_SELFUPDATE);
//            NotificationUtil.getInstance(context).cancelNotification(lastid);
//            NotificationUtil.getInstance(context).showPushMessageNotification(pushBean);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void copyContent(String content) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
//            android.text.ClipboardManager clipboard =
//                    (android.text.ClipboardManager) App.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
//            clipboard.setText(content);
//        } else {
//            ClipboardManager cmb = (ClipboardManager) App.getInstance()
//                    .getSystemService(Context.CLIPBOARD_SERVICE);
//            cmb.setText(content.trim());
//        }
//    }
//
//    /**
//     * 获取已下载任务大小
//     */
//    public static long getCurrDownloadSize(DownloadTask downloadTask) {
//        Context context = App.getInstance().getApplicationContext();
//        String filePath = "";
//        if (downloadTask.state < DownloadTask.STATE_FINISH) {
//            filePath = com.mycheering.sdk.util.Util.getDownloadingFilePath(context, downloadTask.downloadUrl);
//        } else {
//            filePath = com.mycheering.sdk.util.Util.getDownloadedFilePath(context, downloadTask);
//        }
//        File sFile = new File(filePath);
//        if (sFile != null && sFile.exists()) {
//            return sFile.length();
//        }
//        return 0;
//    }
//
//    /**
//     * 获取下载进度
//     */
//    public static int getDownloadProgress(DownloadTask downloadTask) {
//        long currDownloadSize = getCurrDownloadSize(downloadTask);
//        long totalSize = downloadTask.taskSize;
//        return computeProgressInt(currDownloadSize, totalSize);
//    }
//
//
//    public static int getSDKVersionNumber() {
//        int sdkVersion;
//        try {
//            sdkVersion = Integer.valueOf(Build.VERSION.SDK);
//        } catch (NumberFormatException e) {
//            sdkVersion = 0;
//        }
//        return sdkVersion;
//    }
//
//    private static final int F = 10000;
//
//    public static final String formatLongNumber(long num) {
//        DecimalFormat formater = new DecimalFormat("#.#");
//        if (num < F) {
//            return num + "";
//        } else if (num < F * 10) {
//            float wNum = num / F;
//            return formater.format(wNum) + "万";
//        } else if (num < F * 10 * 10) {
//            float swNum = num / F;
//            return formater.format(swNum) + "万";
//        } else if (num < F * 10 * 10 * 10) {
//            float bwNum = num / 10 / 10 / F;
//            return formater.format(bwNum) + "百万";
//        } else {
//            return "error size";
//        }
//    }
//
//    /**
//     * 获取渠道号
//     *
//     * @return
//     */
//    public static String getChannel() {
//        String channelNo = SPUtil.getInstant().getString(SPUtil.SelfChannel, "");
//        if (!TextUtils.isEmpty(channelNo)) return channelNo;
//
//        InputStream is = null;
//        BufferedReader sReader = null;
//        try {
//            Context sContext = App.getInstance().getApplicationContext();
//            is = sContext.getAssets().open("c");
//            sReader = new BufferedReader(new InputStreamReader(is));
//
//            String temp = sReader.readLine();
//            channelNo = temp.split("_")[0];
//            SPUtil.getInstant().save(SPUtil.SelfChannel, channelNo);
//        } catch (Exception e) {
//        } finally {
//            try {
//                if (sReader != null) {
//                    sReader.close();
//                    sReader = null;
//                }
//                if (is != null) {
//                    is.close();
//                    sReader = null;
//                }
//            } catch (Exception e2) {
//            }
//        }
//        return channelNo;
//    }
//
//    public static int getSdkVersionCode() {
//        return Build.VERSION.SDK_INT;
//    }
//
//    public static String getSdkVersion() {
//        return "" + Build.VERSION.SDK_INT;
//    }
//
//    public static String getSdkVersionName() {
//        return Build.VERSION.RELEASE + "";
//    }
//
//    public static String getAndroidId() {
//        Context context = App.getInstance().getApplicationContext();
//        String androidId = SPUtil.getInstant().getString(SPUtil.AndroidId, "");
//        if (!TextUtils.isEmpty(androidId)) return androidId;
//        androidId = android.provider.Settings.Secure.getString(context.getContentResolver(),
//                android.provider.Settings.Secure.ANDROID_ID);
//        SPUtil.getInstant().save(SPUtil.AndroidId, androidId);
//        return androidId;
//    }
//
//    public static String getResolution() {
//        String resolution = SPUtil.getInstant().getString(SPUtil.ResolutionInfo, "");
//        if (!TextUtils.isEmpty(resolution)) return resolution;
//
//        Context context = App.getInstance().getApplicationContext();
//        DisplayMetrics sDisplayMetrics = context.getResources().getDisplayMetrics();
//        resolution = sDisplayMetrics.widthPixels + "*" + sDisplayMetrics.heightPixels;
//        SPUtil.getInstant().save(SPUtil.ResolutionInfo, resolution);
//        return resolution;
//    }
//
//    public static String getIMEI() {
//        Context sContext = App.getInstance().getApplicationContext();
//        String imei = SPUtil.getInstant().getString(SPUtil.IMEI, "");
//        if (TextUtils.isEmpty(imei)) {
//            ImeiHelper.getInstance().getIMEI();
//            return getSingleIMEI(sContext);
//        }
//        return imei;
//    }
//
//    /**
//     * 格式化时间
//     *
//     * @param times
//     * @param formatType
//     * @return
//     */
//    public static String formatDate(long times, String formatType) {
//        if (formatType == null) {
//            throw new NullPointerException("formatType is null!");
//        }
//        SimpleDateFormat df = new SimpleDateFormat(formatType);
//        return df.format(times);
//    }
//
//    /**
//     * 格式化时间
//     *
//     * @param times
//     * @param formatType
//     * @return
//     */
//    public static long formatDate(String times, String formatType) {
//        try {
//            if (formatType == null) {
//                throw new NullPointerException("formatType is null!");
//            }
//            SimpleDateFormat df = new SimpleDateFormat(formatType);
//            return df.parse(times).getTime();
//        } catch (IllegalArgumentException e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return 0;
//    }
}
