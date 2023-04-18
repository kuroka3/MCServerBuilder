package io.github.kuroka3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Creater {
    public static void dlbukkit(String ver, Path svpath) {
        String version = ver;
        try {
            int crc = 0;
            for(int i=1; i<4; i++) {
                // API 엔드포인트 URL 설정
                URL url = new URL("https://papermc.io/api/v2/projects/paper/versions/" + version);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");


                // 응답 받아오기
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();

                    // JSON 파싱
                    JSONParser parser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) parser.parse(content.toString());
                    JSONArray buildsArray = (JSONArray) jsonObject.get("builds");

                    // builds 리스트의 마지막 항목 가져오기
                    Long lastBuild = (Long) buildsArray.get(buildsArray.size() - 1);
                    String bcode = lastBuild.toString();
                    URL durl = new URL("https://api.papermc.io/v2/projects/paper/versions/" + ver + "/builds/" + bcode + "/downloads/paper-" + ver + "-" + bcode + ".jar");
                    String dpath = svpath + "/server.jar";
                    download(durl, dpath);
                    break;
                } else {
                    System.out.println(LangManager.APIfailed + responseCode);
                    crc++;
                }
            }
            if(crc>=3) {
                CrashHandler.apifailedcrash(ver, svpath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void crtstart(Path svpath) {
        String batFileName = svpath + "/start.bat"; // 생성할 bat 파일명
        String batFileContent = "@echo off" + System.lineSeparator()
                + "java -jar server.jar" + System.lineSeparator()
                + "pause";

        try {
            // BufferedWriter를 사용하여 bat 파일 생성
            BufferedWriter writer = new BufferedWriter(new FileWriter(batFileName));
            writer.write(batFileContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void crteula(Path svpath) {
        String batFileName = svpath + "/eula.txt"; // 생성할 txt 파일명
        String batFileContent = "eula=true";

        try {
            // BufferedWriter를 사용하여 bat 파일 생성
            BufferedWriter writer = new BufferedWriter(new FileWriter(batFileName));
            writer.write(batFileContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void crtfolder(Path svpath) {

        if (!Files.exists(svpath)) { // 폴더가 존재하지 않을 경우에만 생성
            try {
                Files.createDirectories(svpath); // 폴더 생성 시도
            } catch (IOException e) {
                System.out.println(LangManager.folderfailed);
                e.printStackTrace();
            }
        } else {
            System.out.println(LangManager.folderalr);
            System.out.println(LangManager.folderask + svpath);
            String input;

            while (true) { // 무한 루프
                System.out.print("(Y/N) > ");
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextLine();

                if ("Y".equalsIgnoreCase(input)) {
                    System.out.println(LangManager.foldery);
                    break; // 루프 탈출
                } else if ("N".equalsIgnoreCase(input)) {
                    System.out.println(LangManager.taskkill);
                    System.exit(0);
                } else {
                    System.out.println(LangManager.misping);
                }
            }
        }
    }

    private static void download(URL url, String path) {
        try {

            // URLConnection 객체 생성
            URLConnection connection = url.openConnection();

            // 입력 스트림 생성
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());

            // 파일 출력 스트림 생성
            FileOutputStream outputStream = new FileOutputStream(path);

            // 파일 다운로드
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // 스트림 닫기
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
