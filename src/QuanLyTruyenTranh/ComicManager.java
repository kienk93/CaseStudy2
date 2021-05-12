package QuanLyTruyenTranh;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComicManager {

    public static final String URL1 = "http://truyenqq.com/the-loai/chuyen-sinh-91.html";
    public static final String LISTTRUYEN1 = "<h3 class=\"title-book\"><a href=\"(.*?)\" title=\"(.*?)\">(.*?)</a></h3>";
    public static final String CRAWL1 = LISTTRUYEN1;
    public static final String URL2 = "https://ln.hako.re/";
    public static final String CRAWL2 = "<div class=\"thumb_attr series-title\"><a href=\"(.*?)\" title=\"(.*?)\">(.*?)</a></div>";
    public static final String DATA = "src\\Data\\ComicData.txt";
    Scanner input = new Scanner(System.in);
    ArrayList<ComicBook> comicBookList = new ArrayList<>();

    public void createComic() {
        comicBookList.add(new ComicBookManga(1, "Jikan Teishi Yuusha", "Adventure", "2021", 19));
        comicBookList.add(new ComicBookManga(3, "Toàn Chức Pháp Sư", "Shounen", "2018", 690));
        comicBookList.add(new ComicBookManga(2, "Đảo Hải Tặc", "Comedy", "2015", 1012));
        comicBookList.add(new ComicBookLightNovel(4, "KonoSuba", "Comedy", "2016", "45,684 từ"));
        comicBookList.add(new ComicBookLightNovel(8, "Màu của biển khơi", "Drama", "2025", "44,733 từ"));
        comicBookList.add(new ComicBookLightNovel(6, "Re:Zero kara Hajimaru Isekai Seikatsu", "Drama", "2017", "401,011 từ"));
    }

    public void myMenu() {
        System.out.println("Chào mừng đến với Chương trình Quản Lý truyện ");
        System.out.println("********************************************* ");
        System.out.println("1. Nhập thông tin comic ");
        System.out.println("2. Hiện thị tất cả comic ");
        System.out.println("3. Sửa thông tin comic bằng id ");
        System.out.println("4. Xóa comic bằng id ");
        System.out.println("5. Sắp xếp comic theo năm sản xuất ");
        System.out.println("6. Hiển thị list theo manga ");
        System.out.println("7. Hiển thị list theo lightnovel ");
        System.out.println("8. Sắp xếp comic theo id ");
        System.out.println("9. Ghi danh sách manga ra file ");
        System.out.println("10. Đọc file ");
        System.out.println("11. Crawl manga chuyển sinh ");
        System.out.println("12. Crawl lightnovel ");
        System.out.println("0. Exit ");
    }

    public void addComic() {
        int key;
        System.out.println("Chọn nhập Manga");
        System.out.println("1. ComicBookManga");
        System.out.println("2. ComicBookLightNovel");
        key = input.nextInt();
        input.nextLine();
        if (key == 1) {
            comicBookList.add(addComicBookManga());
        } else if (key == 2) {
            comicBookList.add(addComicBookLightNovel());
        }

    }

    public ComicBook addComicBookManga() {
        System.out.println("Nhập id manga");
        int id = checkEqualsID();
        return getComicBookManga(id);

    }

    public ComicBook addComicBookMangaWithOutEqualsID() {
        System.out.println("Nhập lại id manga");
        int id = Integer.parseInt(input.nextLine());
        return getComicBookManga(id);

    }

    private ComicBook getComicBookManga(int id) {
        System.out.println("Nhập tên manga");
        String name = input.nextLine();
        System.out.println("Nhập thể loại manga");
        String genre = input.nextLine();
        System.out.println("Nhập năm sản xuất");
        String yearCreate = input.nextLine();
        System.out.println("Nhập số chapter");
        int chapter = Integer.parseInt(input.nextLine());
        return new ComicBookManga(id, name, genre, yearCreate, chapter);
    }

    public ComicBook addComicBookLightNovel() {
        System.out.println("Nhập id lightnovel");
        int id = checkEqualsID();
        return getComicBookLightNovel(id);

    }

    private ComicBook getComicBookLightNovel(int id) {
        System.out.println("Nhập tên lightnovel");
        String name = input.nextLine();
        System.out.println("Nhập thể loại lightnovel");
        String genre = input.nextLine();
        System.out.println("Nhập năm sản xuất lightnovel");
        String yearCreate = input.nextLine();
        System.out.println("Nhập số lượng từ của lightnovel");
        String length = input.nextLine();

        return new ComicBookLightNovel(id, name, genre, yearCreate, length);
    }

    public ComicBook addComicBookLightNovelWithOutEqualsID() {
        System.out.println("Nhập lại id lightnovel");
        int id = Integer.parseInt(input.nextLine());
        return getComicBookLightNovel(id);

    }

    public void displayList() {
        for (ComicBook comics : comicBookList) {
            System.out.println(comics);
        }
    }

    public int checkEqualsID() {
        int id;
        try {
            id = Integer.parseInt(input.nextLine());
            for (ComicBook book : comicBookList) {
                if (book.getId() == id) {
                    System.out.println("ID này đã có rồi!");
                    return checkEqualsID();
                }
            }
        } catch (Exception e) {
            return checkEqualsID();
        }
        return id;
    }

    public int checkID() {
        try {
            System.out.println("nhập ID muốn tìm");
            int key = Integer.parseInt(input.nextLine());
            int index;
            int left = 0;
            int right = comicBookList.size() - 1;
            do {
                int mid = (left + right) / 2;
                if (key == comicBookList.get(mid).getId()) {
                    index = mid;
                    return index;
                } else if (key < comicBookList.get(mid).getId()) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } while (left <= right);
            return -1;
        } catch (Exception e) {
            return checkID();
        }

    }

    public void replaceComicByID() {
        int check = checkID();
        if (check == -1) {
            System.out.println("không tìm thấy ID");
            replaceComicByID();
        } else {
            if (comicBookList.get(check) instanceof ComicBookManga) {
                comicBookList.set(check, addComicBookMangaWithOutEqualsID());
            } else if (comicBookList.get(check) instanceof ComicBookLightNovel) {
                comicBookList.set(check, addComicBookLightNovelWithOutEqualsID());
            }
        }

    }

    public void deteleComicByID() {
        int check = checkID();
        if (check == -1) {
            System.out.println("không tìm thấy ID!");
            deteleComicByID();
        } else {
            comicBookList.remove(check);
            System.out.println("List đã được cập nhật");
            System.out.println("=====================================================================");
            displayList();
        }
    }

    public void selectionSortByYearCreate() {
        for (int i = 0; i < comicBookList.size(); i++) {
            int min = i;
            for (int j = i + 1; j < comicBookList.size(); j++) {
                if (comicBookList.get(j).getYearCreate().compareTo(comicBookList.get(min).getYearCreate()) < 0) {
                    min = j;
                }
            }
            ComicBook temp = comicBookList.get(i);
            comicBookList.set(i, comicBookList.get(min));
            comicBookList.set(min, temp);

        }
        System.out.println("List đã được cập nhật");
        System.out.println("=====================================================================");
        displayList();
    }

    public void displayManga() {
        for (ComicBook mangas : comicBookList) {
            if (mangas instanceof ComicBookManga) {
                System.out.println(mangas);
            }
        }
    }

    public void displayLightNovel() {
        for (ComicBook lightnovels : comicBookList) {
            if (lightnovels instanceof ComicBookLightNovel) {
                System.out.println(lightnovels);
            }
        }
    }

    public void selectionSortByID() {
        for (int i = 0; i < comicBookList.size(); i++) {
            int min = i;
            for (int j = i + 1; j < comicBookList.size(); j++) {
                if (comicBookList.get(i).getId() > comicBookList.get(j).getId()) {
                    min = j;
                }
            }
            ComicBook temp = comicBookList.get(min);
            comicBookList.set(min, comicBookList.get(i));
            comicBookList.set(i, temp);
        }
        System.out.println("List đã được cập nhật");
        System.out.println("=====================================================================");
        displayList();
    }

    public void writeDataToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA));
            oos.writeObject(comicBookList);
            oos.close();
            System.out.println("Success!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ShowDataFromReadFile() {
        List<ComicBook> x = readData();
        for (ComicBook a : x) {
            System.out.println(a);
        }
    }

    public List<ComicBook> readData() {
        List<ComicBook> x = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA));
            x = (List<ComicBook>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return x;
    }

    public String crawlMangaChuyenSinh() throws IOException {
        System.out.println("List truyện manga chuyển sinh");
        URL url = new URL(URL1);
        Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
        scanner.useDelimiter("\\Z");
        String content = scanner.next();
        scanner.close();
        content = content.replaceAll("\\n+", "");
        Pattern pattern = Pattern.compile(CRAWL1);
        Matcher matcher = pattern.matcher(content);

        content.trim();
        showDataFromWeb(matcher, 20);
        return  content;
    }

    public void crawlLightNovel() throws IOException {
        System.out.println("List truyện lightnovel");
        URL url = new URL(URL2);
        Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
        scanner.useDelimiter("\\Z");
        String content2 = scanner.next();
        scanner.close();
        content2 = content2.replaceAll("\\n+", "");
        Pattern pattern = Pattern.compile(CRAWL2);
        Matcher matcher = pattern.matcher(content2);
        showDataFromWeb(matcher, 15);

    }


    public static void showDataFromWeb(Matcher matcher, int x) {
        int i = 0;
        while (matcher.find()) {
            if (i == x) {
                break;
            }
            System.out.println(matcher.group(3));
            i++;
        }
    }

    public void menuSystem() throws IOException {
        int choice;
        do {
            myMenu();
            System.out.println("Nhập lựa chọn của bạn");

            choice = input.nextInt();
            switch (choice) {
                case 1:
                    addComic();
                    break;
                case 2:
                    displayList();
                    break;
                case 3:
                    replaceComicByID();
                    break;
                case 4:
                    deteleComicByID();
                    break;
                case 5:
                    selectionSortByYearCreate();
                    break;
                case 6:
                    displayManga();
                    break;
                case 7:
                    displayLightNovel();
                    break;
                case 8:
                    selectionSortByID();
                    break;
                case 9:
                    writeDataToFile();
                    break;
                case 10:
                    ShowDataFromReadFile();
                    break;
                case 11:
                    crawlMangaChuyenSinh();
                    break;
                case 12:
                    crawlLightNovel();
                    break;
                case 13:

                    break;
                case 0:
                    System.exit(0);
            }
        }
        while (choice != 0);
    }
}


