package com.milestone.common.json;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class JsonFileParser {
    public JSONObject extractJsonFromFile(String filePath) {
        JSONObject jsonObject = null;

        filePath = filePath.replace("%20", " ");
        String jsonContents;
        try {
            jsonContents = FileUtils.readFileToString(new File(filePath));
            jsonObject = new JSONObject(jsonContents);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}
