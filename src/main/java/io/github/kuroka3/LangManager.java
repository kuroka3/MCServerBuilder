package io.github.kuroka3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class LangManager {

    public static String blank, EULA1, EULA2, EULAsuc, taskkill,
            misping, ipath, svname, sver, APIfailed, folderfailed,
            folderalr, folderask, foldery, api3failed, nextcheck,
            check1, check2, check3, checkif3;

    public static List<String> loadlangs() {
        JSONParser parser = new JSONParser();

        JSONArray list = null;
        try {
            // JSON 파일 읽기
            FileReader fileReader = new FileReader("src/main/resources/lang.json");
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
            fileReader.close();

            // "langs/list"에서 목록 추출
            JSONObject langsObject = (JSONObject) jsonObject.get("langs");
            list = (JSONArray) langsObject.get("list");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void loadtexts(String lang) {
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader("src/main/resources/lang.json");
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
            JSONObject uselang = (JSONObject) jsonObject.get(lang);

            blank = (String) uselang.get("blank");
            EULA1 = (String) uselang.get("EULA1");
            EULA2 = (String) uselang.get("EULA2");
            EULAsuc = (String) uselang.get("EULAsuc");
            taskkill = (String) uselang.get("taskkill");
            misping = (String) uselang.get("misping");
            ipath = (String) uselang.get("ipath");
            svname = (String) uselang.get("svname");
            sver = (String) uselang.get("sver");
            APIfailed = (String) uselang.get("APIfailed");
            folderfailed = (String) uselang.get("folderfailed");
            folderalr = (String) uselang.get("folderalr");
            folderask = (String) uselang.get("folderask");
            foldery = (String) uselang.get("foldery");
            api3failed = (String) uselang.get("api3failed");
            nextcheck = (String) uselang.get("nextcheck");
            check1 = (String) uselang.get("check1");
            check2 = (String) uselang.get("check2");
            check3 = (String) uselang.get("check3");
            checkif3 = (String) uselang.get("checkif3");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
