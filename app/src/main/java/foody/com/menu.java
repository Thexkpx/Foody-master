package foody.com;

public class menu {
    public String Menu_name;
    public String image_url;
    public int id;
    public int price;

    public menu() {
    }

    public menu(String menu_name, String image_url, int id, int price) {
        Menu_name = menu_name;
        this.image_url = image_url;
        this.id = id;
        this.price = price;
    }

    public String getMenu_name() {
        return Menu_name;
    }

    public void setMenu_name(String menu_name) {
        Menu_name = menu_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
