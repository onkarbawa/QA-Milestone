package com.milestone.browserstack;

import com.milestone.common.json.JsonFileParser;
import org.json.JSONObject;


public class BrowserStack {

    private static String SERVER;
    private static String USERNAME;
    private static String ACCESS_KEY;

    static {
        String pbrowserStackCredStore = System.getProperty("browserStackCredStore");
        JSONObject creds = null;
        if (pbrowserStackCredStore == null)
            creds = new JsonFileParser().extractJsonFromFile("../browserstack.json");
        else
            creds = new JsonFileParser().extractJsonFromFile(pbrowserStackCredStore);

        JSONObject credentials = (JSONObject) creds.get("credentials");
        SERVER = (String) credentials.get("server");
        USERNAME = (String) credentials.get("user");
        ACCESS_KEY = (String) credentials.get("key");
    }

    public static String getBrowserStackURL() {
        return "https://" + USERNAME + ":" + ACCESS_KEY + "@" + SERVER + "/wd/hub";
    }

}
