package QuanLyTruyenTranh;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        UserManage userManage = new UserManage();
        Scanner input = new Scanner(System.in);

        List<User> account = userManage.readAccFromFile();
        System.out.println("Acc hợp lệ");
        System.out.println(account);

        int check;
        do {
            System.out.println();
            System.out.println("Choice");
            System.out.println("1: Log in");
            System.out.println("2: Register");
            System.out.println("______________");
            check = input.nextInt();
            switch (check) {
                case 1:
                    account = userManage.readAccFromFile();
                    userManage.logIn(account);
                    break;
                case 2:
                    userManage.writeAccToFile();
                    break;
            }
        } while (check != 1 || check != 2);
    }
//        ComicManager comicManager = new ComicManager();
//        comicManager.createComic();
//        comicManager.menuSystem();
}