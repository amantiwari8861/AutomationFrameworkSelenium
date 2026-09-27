package com.training.util;

import com.training.model.LoginData;
import tools.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonUtil {

    private JsonUtil() {
    }

    public static Object[][] readJson(String path) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            LoginData[] loginData =
                    mapper.readValue(
                            new File(path),
                            LoginData[].class
                    );

            Object[][] data =
                    new Object[loginData.length][4];

            for (int i = 0; i < loginData.length; i++) {

                data[i][0] = loginData[i].username();
                data[i][1] = loginData[i].password();
                data[i][2] = loginData[i].expectedUrl();
                data[i][3] = loginData[i].loginExpected();
            }

            return data;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read JSON file: " + path,
                    e
            );
        }
    }
}