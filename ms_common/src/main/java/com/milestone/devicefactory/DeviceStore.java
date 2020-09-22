package com.milestone.devicefactory;


import com.milestone.appfactory.AppStore;
import com.milestone.common.json.JsonFileParser;
import com.milestone.uifactory.MobileDevice;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DeviceStore {

    private static volatile List<Object> androidDeviceList = new ArrayList<>();
    private static volatile List<Object> iOSDeviceList = new ArrayList<>();
    private static volatile List<Object> browserStackIOSDeviceList = new ArrayList<>();
    private static volatile List<Object> browserStackAndroidDeviceList = new ArrayList<>();
    private static volatile List<Object> dockerkAndroidDeviceList = new ArrayList<>();
    private static volatile List<Object> tvOSDeviceList = new ArrayList<>();



    private static ThreadLocal<Object> lockedDevice = new ThreadLocal<>();
    private static ThreadLocal<String> lockedPlatform = new ThreadLocal<>();
    private static ThreadLocal<String> deviceID = new ThreadLocal<>();

    static {
        String pDeviceStore = System.getProperty("deviceStore");
        JSONObject devices = null;
        if (pDeviceStore == null)
            devices = new JsonFileParser().extractJsonFromFile("../devices.json");
        else
            devices = new JsonFileParser().extractJsonFromFile(pDeviceStore);

        JSONArray androidDevices = (JSONArray) devices.get("android");
        JSONArray iOSDevices = (JSONArray) devices.get("iOS");
        JSONArray browserStackIOSDevices = (JSONArray) devices.get("browserStackIOS");
        JSONArray browserStackAndroidDevices = (JSONArray) devices.get("browserStackAndroid");
        JSONArray dockerAndroidDevices = (JSONArray) devices.get("dockerDevice");
        JSONArray tvOSDevices = (JSONArray) devices.get("tvOS");


        for (Object androidDevice : androidDevices) {
            androidDeviceList.add(androidDevice);
        }

        for (Object iOSDevice : iOSDevices) {
            iOSDeviceList.add(iOSDevice);
        }

        for (Object browserStackIOSDevice : browserStackIOSDevices) {
            browserStackIOSDeviceList.add(browserStackIOSDevice);
        }

        for (Object browserStackAndroidDevice : browserStackAndroidDevices) {
            browserStackAndroidDeviceList.add(browserStackAndroidDevice);
        }

        for (Object dockerAndroidDevice : dockerAndroidDevices) {
            dockerkAndroidDeviceList.add(dockerAndroidDevice);
        }

        for (Object tvOSDevice : tvOSDevices) {
            tvOSDeviceList.add(tvOSDevice);
        }
    }


    public static JSONObject getDevice() {
        return getDevice(AppStore.getAppName());
    }
    public static String getDeviceId() {
        if (deviceID.get() == null)
            deviceID.set(MobileDevice.getDeviceId());

        return deviceID.get();
    }

    public static synchronized JSONObject getDevice(String appName) {

        JSONObject deviceToReturn;

        if (lockedDevice.get() != null)
            deviceToReturn = (JSONObject) lockedDevice.get();
        else {
            switch (lockedPlatform.get().toLowerCase()) {
                case "ios":
                    deviceToReturn = (JSONObject) iOSDeviceList.get(0);
                    break;
                case "tvos":
                    deviceToReturn = (JSONObject) tvOSDeviceList.get(0);
                    break;
                case "android":
                    deviceToReturn = (JSONObject) androidDeviceList.get(0);
                    break;
                case "browserstackios":
                    deviceToReturn = (JSONObject) browserStackIOSDeviceList.get(0);
                    break;
                case "browserstackandroid":
                    deviceToReturn = (JSONObject) browserStackAndroidDeviceList.get(0);
                    break;
                case "dockerandroid":
                    deviceToReturn = (JSONObject) dockerkAndroidDeviceList.get(0);
                    break;
                default:
                    throw new IllegalArgumentException("No Such platform");
            }
        }
        lockDevice(deviceToReturn);


        if (appName != null) {
            JSONObject app = AppStore.getApp(appName);
            for (String k : app.keySet())
                deviceToReturn.put(k, app.getString(k));
        }
        return deviceToReturn;
    }

    private static void lockDevice(Object deviceInfo) {
            lockedDevice.set(deviceInfo);
    }

    public static String getPlatform() {
        return lockedPlatform.get();
    }

    public static void setPlatform(String platform) {
        lockedPlatform.set(platform);
    }

    public static int getAndroidDeviceCount() {
        return androidDeviceList.size();
    }

    public static int getIOSDeviceCount() {
        return iOSDeviceList.size();
    }

    public static int gettvOSDeviceCount() {
        return tvOSDeviceList.size();
    }
}
