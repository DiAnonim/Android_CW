package kz.example.lesson_3.Lesson7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kz.example.lesson_3.Lesson7.model.Ingredient;
import kz.example.lesson_3.R;

public class IngredientAdapter extends BaseAdapter {
    private Context context;
    private List<Ingredient> ingredientList;

    public IngredientAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @Override
    public int getCount() {
        return ingredientList.size();
    }

    @Override
    public Object getItem(int i) {
        return ingredientList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.ingredient_item, viewGroup, false);
        }
        Ingredient currentIng = (Ingredient) getItem(i);
        TextView name = view.findViewById(R.id.ingredient_name);
        TextView quantity = view.findViewById(R.id.quantity);
        TextView unit = view.findViewById(R.id.unit);
        ImageView imageView = view.findViewById(R.id.ingredient_image);

        name.setText(currentIng.getName());
        quantity.setText(String.valueOf(currentIng.getQuantity()));
        unit.setText(currentIng.getUnit());
        imageView.setImageResource(currentIng.getImageResourceId());

        return view;
    }
}
