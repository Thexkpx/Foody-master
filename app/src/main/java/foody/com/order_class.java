package foody.com;

public class order_class {
    private String Menu_name_order;
    private String Category_order;
    private String Description_order;
    private String Gia_order;
    private int Thumbnail_order;

    public order_class(String menu_name_order, String category_order, String description_order, String gia_order, int thumbnail_order) {
        Menu_name_order = menu_name_order;
        Category_order = category_order;
        Description_order = description_order;
        Gia_order = gia_order;
        Thumbnail_order = thumbnail_order;
    }

    public order_class() {
    }

    public String getMenu_name_order() {
        return Menu_name_order;
    }

    public void setMenu_name_order(String menu_name_order) {
        Menu_name_order = menu_name_order;
    }

    public String getCategory_order() {
        return Category_order;
    }

    public void setCategory_order(String category_order) {
        Category_order = category_order;
    }

    public String getDescription_order() {
        return Description_order;
    }

    public void setDescription(String description_order) {
        Description_order = description_order;
    }

    public String getGia_order() {
        return Gia_order;
    }

    public void setGia_order(String gia_order) {
        Gia_order = gia_order;
    }

    public int getThumbnail_order() {
        return Thumbnail_order;
    }

    public void setThumbnail_order(int thumbnail_order) {
        Thumbnail_order = thumbnail_order;
    }
}
