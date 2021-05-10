package QuanLyTruyenTranh;

import java.io.Serializable;

public class ComicBook implements Serializable {
    int id;
    String name;
    String genre;
    String yearCreate;

    public ComicBook() {
    }

    public ComicBook(int id, String name, String genre, String yearCreate) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.yearCreate = yearCreate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYearCreate() {
        return yearCreate;
    }

    public void setYearCreate(String yearCreate) {
        this.yearCreate = yearCreate;
    }

    @Override
    public String toString() {
        return "ComicBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", yearCreate='" + yearCreate + '\'' +
                '}';
    }


}
