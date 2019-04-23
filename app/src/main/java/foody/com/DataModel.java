package foody.com;
public class DataModel {

    private String name, price, imgURL;

    public String getImgURL(){
        return imgURL;
    }

    public void setImgURL(String imgURL){
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
