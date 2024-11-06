package resources;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCard {

    private List<String> ingredients;

    public OrderCard(List<String> ingredients) {
        this.ingredients = ingredients;
    }


}

