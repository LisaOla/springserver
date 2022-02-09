package com.example.springserver;

// Uppgift: Lägg till mer information om filmen. Kanske en regissör, vilket år den kom ut, och i vilket land.

public class Jobs {
    private int id;
    private String title;
    private int basesalary;


    public int getId() {return id;  }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBasesalary() {
        return basesalary;
    }

    public void setBasesalary(int basesalary) {
        this.basesalary = basesalary;
    }



    @Override
    public String toString() {
        return "Jobs{" +
                "id=" + id +
                ", title=" + title +
                ", basesalary='" + basesalary + '\'' +
                '}';
    }
}