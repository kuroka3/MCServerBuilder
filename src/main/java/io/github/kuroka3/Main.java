package io.github.kuroka3;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> Langlist = LangManager.loadlangs();
        System.out.println("Language: ");
        for (Object item : Langlist) {
            System.out.println("  " + item);
        }
        System.out.print("> ");
        String lang = scanner.nextLine();
        LangManager.loadtexts(lang);
        System.out.println(LangManager.blank);


        System.out.println(LangManager.EULA1);
        System.out.println(LangManager.EULA2);
        String input;

        while (true) { // 무한 루프
            System.out.print("(Y/N) > ");
            input = scanner.nextLine();

            if ("Y".equalsIgnoreCase(input)) {
                LocalDateTime curdt = LocalDateTime.now();
                String curtm = curdt.getYear()+"-"+curdt.getMonthValue()+"-"+curdt.getDayOfMonth()+LangManager.blank+curdt.getHour()+":"+curdt.getMinute()+":"+curdt.getSecond();
                System.out.println(LangManager.EULAsuc + curtm);
                break; // 루프 탈출
            } else if ("N".equalsIgnoreCase(input)) {
                System.out.println(LangManager.taskkill);
                System.exit(0);
            } else {
                System.out.println(LangManager.misping);
            }
        }
        System.out.println(LangManager.blank);
        System.out.println(LangManager.ipath);
        System.out.print("> ");
        String isvpath = scanner.nextLine();
        System.out.println(LangManager.blank);
        System.out.println(LangManager.svname);
        System.out.print("> ");
        String isvname = scanner.nextLine();
        System.out.println(LangManager.blank);
        Path svpath = Path.of(isvpath + "/" + isvname);
        Creater.crtfolder(svpath);
        System.out.println(LangManager.sver);
        System.out.print("> ");
        String sver = scanner.nextLine();
        System.out.println(LangManager.blank);
        Creater.dlbukkit(sver, svpath);
        Creater.crtstart(svpath);
        Creater.crteula(svpath);
    }
}