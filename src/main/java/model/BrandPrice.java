package model;

public class BrandPrice {

    public int price;
    public String brand;
    public int total;

    public BrandPrice(int price, String brand, int total) {
        this.price = price;
        this.brand = brand;
        this.total = total;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    

}
