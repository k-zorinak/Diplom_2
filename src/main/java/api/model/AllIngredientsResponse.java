package api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AllIngredientsResponse {

    @JsonProperty("data")
    private List<DataItemIngredients> data;

    @JsonProperty("success")
    private boolean success;

    public List<DataItemIngredients> getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }
}