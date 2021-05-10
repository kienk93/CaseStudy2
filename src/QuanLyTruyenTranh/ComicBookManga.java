package QuanLyTruyenTranh;

public class ComicBookManga extends ComicBook {
    int chapter;

    public ComicBookManga() {
    }

    public ComicBookManga(int id, String name, String genre, String yearCreate, int chapter) {
        super(id, name, genre, yearCreate);
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "ComicBookManga{" +
                "id= " + super.getId() +
                ", name= '" + super.getName() + '\'' +
                ", genre= '" + super.getGenre() + '\'' +
                ", yearCreate= '" + super.getYearCreate() +
                ", chapter= '" + chapter + '\'' +
                '}';
    }
}


