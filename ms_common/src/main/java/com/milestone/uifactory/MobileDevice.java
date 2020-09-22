package com.milestone.uifactory;

import com.milestone.devicefactory.DeviceStore;
import org.json.JSONObject;

public class MobileDevice {

    public MobileDevice() {
    }

    public static String getDeviceId() {
        return getDeviceId(DeviceStore.getDevice());
    }

    public static String getDeviceId(JSONObject device) {
        if (device.getString("platformName").equalsIgnoreCase("ios"))
            return device.getString("deviceName");
        else
            return device.getString("deviceName");
    }
}
