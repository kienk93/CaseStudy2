package QuanLyTruyenTranh;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class UserManage extends ComicManager implements Serializable {
    public static final String USER_DATA = "src\\Data\\UserData.txt";
    public static final String PASSWORDS_REGEX = "Passwords must be 1-6 characters";
    public static final String USERNAME_REGEX = "Username must be 1-6 characters";
    List<User> account = new ArrayList<>();

    Scanner input = new Scanner(System.in);


    public void logIn(List<User> account) throws IOException {
        System.out.println("log in");
        System.out.println("username: ");
        String accountName = input.nextLine();
        for (int i = 0; i < account.size(); i++) {
            if (accountName.equals(account.get(i).getUsername())) {
                System.out.println("password: ");
                String password = input.nextLine();
                if (password.equals(account.get(i).getPassword())) {
                    createComic();
                    menuSystem();
                    ArrayList<ComicBook> comicBookUserList = new ArrayList<>();
                } else {
                    System.out.println("Wrong password!");
                }
            }
        }
    }

    public void writeAccToFile() throws IOException {
        account = readAccFromFile();
        account.add(inputAccount());
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA));
        oos.writeObject(account);
        oos.close();
    }

    public List<User> readAccFromFile() {
        List<User> y = new ArrayList<>();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(USER_DATA));
            y = (List<User>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception ignored) {
        }
        return y;
    }

    public User inputAccount() {
        List<User> acc = readAccFromFile();
        System.out.println("New Account");
        String username = inputUserName(acc);
        String password = inputPassword();
        return new User(username, password);
    }

    public String inputPassword() {
        System.out.println("Input password: ");
        String password = input.next();
        input.nextLine();
        if (password.matches("^(.*){1,6}$")) {
            return password;
        } else {
            System.out.println(PASSWORDS_REGEX);
            return inputPassword();
        }
    }

    public String inputUserName(List<User> acc) {
        System.out.println("Input new username: ");
        String username = input.next();
        if (username.matches("^(.*){1,6}$")) {
            for (User user : acc) {
                if (user.getUsername().equals(username)) {
                    System.out.println("This username already exists");
                    return inputUserName(acc);
                }
            }
        } else {
            System.out.println(USERNAME_REGEX);
            return inputUserName(acc);
        }
        return username;
    }
//    public void addContent() throws IOException {
//        comicBookUserList.add(comicManager.crawlMangaChuyenSinh());
//    }
}
