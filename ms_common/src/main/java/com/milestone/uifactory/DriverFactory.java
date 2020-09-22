package com.milestone.uifactory;

import com.milestone.browserstack.BrowserStack;
import com.milestone.devicefactory.DeviceStore;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

public class DriverFactory {

    static HashMap<String, WebDriver> driverMap= new HashMap<>();


    public static WebDriver getDriver() {
        return DriverFactory.getDriver(new JSONObject(DeviceStore.getDevice().toString()));
    }

    public static WebDriver getDriver(JSONObject deviceInfo) {

        if (!driverMap.containsKey(MobileDevice.getDeviceId(deviceInfo))) {
            DriverFactory.createInstance(DeviceStore.getPlatform(), deviceInfo);
        }
        return driverMap.get(MobileDevice.getDeviceId(deviceInfo));
    }

    private static void setDriver(WebDriver driver) {
        driverMap.put(DeviceStore.getDeviceId(), driver);
    }

    private static void createInstance(String platform, JSONObject deviceInfo) {
        if (deviceInfo == null) {
            System.out.println("Getting a new device from store");
            try {
                deviceInfo = new JSONObject(DeviceStore.getDevice().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String serverURL;

        if (!deviceInfo.has("url"))
            serverURL = BrowserStack.getBrowserStackURL();
        else
            serverURL = deviceInfo.get("url").toString();

        URL url = null;
        try {
            url = new URL(serverURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        DesiredCapabilities caps = new DesiredCapabilities();

        Iterator<?> keys = deviceInfo.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            Object keyValue = deviceInfo.get(key);

            if ((key.equalsIgnoreCase("app") || key.equalsIgnoreCase("ipa"))
                    && (keyValue.toString().endsWith(".ipa") || keyValue.toString().endsWith(".app")))
                caps.setCapability(key, new File(deviceInfo.get(key).toString()).getAbsolutePath());
            else
                caps.setCapability(key, deviceInfo.get(key));
        }

        if (caps.getBrowserName() == null)
            caps.setBrowserName("");

        System.out.println("Requesting new session with capabilities as: \n" + caps.asMap());

        File fs  = new File ("../chromedriver");


        switch (platform.toLowerCase()) {
            case "ios":
                caps.setCapability("preventWDAAttachments", true);
                caps.setCapability("clearSystemFiles", true);
                setDriver(new AppiumDriver(url, caps));
                break;
            case "tvos":
                caps.setCapability("wdaStartupRetries", 2);
                caps.setCapability("wdaStartupRetryInterval", 5000);
                setDriver(new AppiumDriver(url, caps));
                break;
            case "android":
                try {
                    caps.setCapability("chromedriverExecutable", fs.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setDriver(new AndroidDriver(url, caps));
                break;
            case "browserstackios":
                setDriver(new RemoteWebDriver(url, caps));
                break;
            case "browserstackandroid":
                try {
                    caps.setCapability("chromedriverExecutable", fs.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                caps.setCapability("build", "Android-Test");
                caps.setCapability("name", "Automation");
                caps.setCapability("project", "BrowserStack");
                setDriver(new RemoteWebDriver(url, caps));
                break;
            case "dockerandroid":
                setDriver(new RemoteWebDriver(url, caps));
                break;
            default:
                try {
                    throw new Exception("Unknown platform: " + platform);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
