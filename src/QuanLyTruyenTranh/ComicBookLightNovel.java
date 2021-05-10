package QuanLyTruyenTranh;

public class ComicBookLightNovel extends ComicBook {
    String TextLength;


    public ComicBookLightNovel() {
    }

    public ComicBookLightNovel(int id, String name, String genre, String yearCreate, String textLength) {
        super(id, name, genre, yearCreate);
        TextLength = textLength;
    }

    @Override
    public String toString() {
        return "ComicBookLightNovel{" +
                "id= " + super.getId() +
                ", name= '" + super.getName() + '\'' +
                ", genre= '" + super.getGenre() + '\'' +
                ", yearCreate= '" + super.getYearCreate() +
                ", TextLength= '" + TextLength + '\'' +
                '}';
    }
}
