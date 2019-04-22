package foody.com;

public class movie {
    public String ten_menu;
    public int soluong;
    public int giamenu;
    public movie() {

    }
    public movie(String ten_menu, int soluong, int giamenu) {
        this.ten_menu = ten_menu;
        this.soluong  = soluong;
        this.giamenu = giamenu;
    }

    public String getTen_menu() {
        return ten_menu;
    }

    public void setTen_menu(String ten_menu) {
        this.ten_menu = ten_menu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiamenu() {
        return giamenu;
    }

    public void setGiamenu(int giamenu) {
        this.giamenu = giamenu;
    }
}
