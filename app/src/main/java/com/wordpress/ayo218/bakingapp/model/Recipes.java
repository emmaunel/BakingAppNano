package com.wordpress.ayo218.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Recipes implements Parcelable{
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("servings")
    private int servings;
    @JsonProperty("image")
    private String image;
    @JsonProperty("ingredients")
    private List<Ingredients> ingredients;
    @JsonProperty("steps")
    private List<Step> steps;


    public Recipes() {
        this.image = "";
        this.servings = 0;
        this.name = "";
        this.ingredients = new ArrayList<>();
        this.id = 0;
        this.steps = new ArrayList<>();
    }


    protected Recipes(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.servings = in.readInt();
        this.image = in.readString();
        this.ingredients = new ArrayList<>();
        in.readList(this.ingredients, Ingredients.class.getClassLoader());
        this.steps = new ArrayList<>();
        in.readList(this.steps, Step.class.getClassLoader());
    }

    public static final Parcelable.Creator<Recipes> CREATOR = new Parcelable.Creator<Recipes>() {
        @Override
        public Recipes createFromParcel(Parcel in) {
            return new Recipes(in);
        }

        @Override
        public Recipes[] newArray(int size) {
            return new Recipes[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeInt(this.servings);
        parcel.writeString(this.image);
        parcel.writeList(this.ingredients);
        parcel.writeList(this.steps);
    }


    @Override
    public String toString() {
        return "Recipes{" + "id=" + id
                + ", name='" + name + '\''
                + ", servings=" + servings
                + ", image='" + image + '\''
                + ", ingredients=" + ingredients
                + '}';
    }
}
