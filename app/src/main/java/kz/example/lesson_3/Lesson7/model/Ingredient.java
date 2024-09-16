package kz.example.lesson_3.Lesson7.model;

public class Ingredient {
    private int quantity;
    private String name;
    private String unit;
    private int imageResourceId;

    public Ingredient(int quantity, String name, String unit, int imageResourceId){
        this.quantity = quantity;
        this.name = name;
        this.unit = unit;
        this.imageResourceId = imageResourceId;
    }

    public Ingredient(int quantity, String name, String unit){
        this.quantity = quantity;
        this.name = name;
        this.unit = unit;
        this.imageResourceId = 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

}
