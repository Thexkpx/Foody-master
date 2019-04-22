package foody.com;

public class menu {
    private String Menu_name;
    private String Category;
    private String Description;
    private String Gia;
    private int Thumbnail;

    public menu() {
    }

    public menu(String menu_name, String category, String description, String gia, int thumbnail) {
        Menu_name = menu_name;
        Gia = gia;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
    }

    public String getMenu_name() {
        return Menu_name;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setMenu_name(String menu_name) {
        Menu_name = menu_name;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }
}
