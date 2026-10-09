package com.nclh.dataProviders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class JsonReader {

    public static String readUserData(String fileName, String object, String key){
        byte[] bytes = null;
        File file = new File(System.getProperty("user.dir")+ "/testData/"+fileName+".json");
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonNode.get(object).get(key));
        return jsonNode.get(object).get(key).toString();
    }

    public static String readUserData(String object, String key){
        byte[] bytes = null;
        File file = new File(System.getProperty("user.dir")+ "/testData/userData.json");
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("bytes :: " + bytes.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonNode.get(object).get(key));
        String value = jsonNode.get(object).get(key).toString();
        return value;
    }

    /**
     * Read data from json data file
     *
     * @param object json object
     * @param key key
     * @return
     */
    public static String readData(String object, String key) {
        String keyValue = null;
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("./testData/userData.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray dataList = (JSONArray) obj;
            for (Object data : dataList) {
                keyValue = getData((JSONObject) data, object, key);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return keyValue;
    }


    /**
     * Get data
     *
     * @param dataJson object
     * @param key      key
     * @param value    value
     * @return return data
     */
    public static String getData(JSONObject dataJson, String key, String value) {

        JSONObject dataObject = (JSONObject) dataJson.get(key);
        String valuePair = (String) dataObject.get(value);
        System.out.println(valuePair);
        return valuePair;
    }
}
