package kz.example.lesson_3.Lesson7.model;

import java.util.List;

public class Recipe {
    private String title;
    private List<Ingredient> ingredientList;

    public Recipe(String title, List<Ingredient> ingredientList) {
        this.title = title;
        this.ingredientList = ingredientList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void addIngredient(Ingredient ingredient){
        ingredientList.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredientList.remove(ingredient);
    }

    public List<Ingredient> getIngredientList(){
        return ingredientList;
    }
}
