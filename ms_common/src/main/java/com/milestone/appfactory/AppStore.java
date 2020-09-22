package com.milestone.appfactory;

import com.milestone.common.json.JsonFileParser;
import com.milestone.devicefactory.DeviceStore;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AppStore {

    private static volatile List<JSONObject> androidAppList = new ArrayList<>();
    private static volatile List<JSONObject> iOSAppList = new ArrayList<>();
    private static volatile List<JSONObject> browserStackIOSAppList = new ArrayList<>();
    private static volatile List<JSONObject> browserStackAndroidAppList = new ArrayList<>();
    private static volatile List<JSONObject> dockerAndroidAppList = new ArrayList<>();
    private static volatile List<JSONObject> tvOSAppList = new ArrayList<>();

    private static ThreadLocal<String> appInUse = new ThreadLocal<>();

    static {
        String pAppStore = System.getProperty("appStore");
        JSONObject apps = null;
        if (pAppStore == null)
            apps = new JsonFileParser().extractJsonFromFile("../app_store.json");
        else
            apps = new JsonFileParser().extractJsonFromFile(pAppStore);

        JSONArray androidApps = (JSONArray) apps.get("android");
        JSONArray iOSApps = (JSONArray) apps.get("iOS");
        JSONArray browserStackIOSApps = (JSONArray) apps.get("browserStackIOS");
        JSONArray browserStackAndroidApps = (JSONArray) apps.get("browserStackAndroid");
        JSONArray dockerAndroidApps = (JSONArray) apps.get("dockerAndroid");
        JSONArray tvOSdApps = (JSONArray) apps.get("tvOS");


        for (Object androidApp : androidApps) {
            androidAppList.add((JSONObject) androidApp);
        }

        for (Object iOSApp : iOSApps) {
            iOSAppList.add((JSONObject) iOSApp);
        }

        for (Object browserStackIOSApp : browserStackIOSApps) {
            browserStackIOSAppList.add((JSONObject) browserStackIOSApp);
        }

        for (Object browserStackAndroidApp : browserStackAndroidApps) {
            browserStackAndroidAppList.add((JSONObject) browserStackAndroidApp);
        }

        for (Object dockerAndroidApp : dockerAndroidApps) {
            dockerAndroidAppList.add((JSONObject) dockerAndroidApp);
        }

        for (Object tvOSApp : tvOSdApps) {
            tvOSAppList.add((JSONObject) tvOSApp);
        }
    }

    public static String getAppName() {
        return appInUse.get();
    }

    public static synchronized void setAppName(String appName) {
        appInUse.set(appName);

    }

    public static synchronized JSONObject getApp() {
        return getApp(getAppName());
    }

    public static synchronized JSONObject getApp(String appName) {
        List<JSONObject> appList = new ArrayList<>();

        switch (DeviceStore.getPlatform().toLowerCase()) {
            case "ios":
                appList.addAll(iOSAppList);
                break;
            case "tvos":
                appList.addAll(tvOSAppList);
                break;
            case "android":
                appList.addAll(androidAppList);
                break;
            case "browserstackios":
                appList.addAll(browserStackIOSAppList);
                break;
            case "browserstackandroid":
                appList.addAll(browserStackAndroidAppList);
                break;
            case "dockerandroid":
                appList.addAll(dockerAndroidAppList);
                break;
            default:
                throw new IllegalArgumentException("No Such platform");
        }
        JSONObject app = null;
        for (JSONObject appInList : appList) {
            if (appInList.getString("name").equalsIgnoreCase(appName)) {
                app = new JSONObject(appInList.toString());
                app.remove("name");
                return app;
            }
        }

        try {
            throw new Exception("No application found with given name: " + appName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

