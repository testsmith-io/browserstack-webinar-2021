package com.browserstack.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class CapabilityHelper {
    public static final String PATH_TO_TEST_CAPS_JSON = "src/test/resources/capabilities/browserstack.json";
    private static final long TIMESTAMP = new Date().getTime();

    public static DesiredCapabilities generateCapabilities(int env_cap_id, String methodName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject testCapsConfig = (JSONObject) parser.parse(new FileReader(PATH_TO_TEST_CAPS_JSON));
        String url = (String) testCapsConfig.get("application_endpoint");

        JSONObject profilesJson = (JSONObject) testCapsConfig.get("capabilities");

        Map<String, String> commonCapabilities = (Map<String, String>) profilesJson.get("common_caps");
        commonCapabilities.put("name", methodName);
        commonCapabilities.put("build", commonCapabilities.get("build") + " - " + TIMESTAMP);
        Map<String, String> envCapabilities = (Map<String, String>) ((org.json.simple.JSONArray) profilesJson.get("env_caps")).get(env_cap_id);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.merge(new DesiredCapabilities(commonCapabilities));
        caps.merge(new DesiredCapabilities(envCapabilities));

        caps.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
        caps.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

        return caps;
    }
}
