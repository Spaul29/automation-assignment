package com.utility;

import com.constants.Env;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONUtility {

    public static String fetchUrlFromJson(Env env) {

        Gson gson = new Gson();
        File jsonFile = new File(System.getProperty("user.dir") + "/config/config.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Config file not found !!!");
        }

        Config config = gson.fromJson(fileReader, Config.class);
        Environment environment = config.getEnvironments().get(env.name().toUpperCase());
        return environment.getUrl();

    }

    public static <T> T readJson(String filePath, Class<T> type) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), type);
    }


}
