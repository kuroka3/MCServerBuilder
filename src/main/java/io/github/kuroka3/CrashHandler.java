package io.github.kuroka3;

import java.nio.file.Path;
import java.util.Scanner;

public class CrashHandler {
    public static void apifailedcrash(String ver, Path svpath) {
        System.out.println(LangManager.blank);
        System.out.println(LangManager.api3failed);
        System.out.println(LangManager.nextcheck);
        System.out.println(LangManager.check1);
        System.out.println(LangManager.check2);
        System.out.println(LangManager.check3 + ver);
        System.out.println(LangManager.checkif3);
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) { // 무한 루프
            System.out.print("(Y/N) > ");
            input = scanner.nextLine();

            if ("Y".equalsIgnoreCase(input)) {
                System.out.println(LangManager.sver);
                break; // 루프 탈출
            } else if ("N".equalsIgnoreCase(input)) {
                System.out.println(LangManager.taskkill);
                System.exit(0);
            } else {
                System.out.println(LangManager.misping);
            }
        }
        System.out.print("> ");
        String sver = scanner.nextLine();
        System.out.println(LangManager.blank);
        Creater.dlbukkit(sver, svpath);
    }
}
