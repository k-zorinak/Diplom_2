package actions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenerateOrderData {
    OrderAction orderAction = new OrderAction();

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    private List<String> ingredients;

    public void idIngredients() {
        ingredients = orderAction.getAllIngredients();
    }
}
