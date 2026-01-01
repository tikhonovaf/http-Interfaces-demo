package ru.httpinterfacesdemo.model;


import ru.httpinterfacesdemo.model.Pet;
import ru.httpinterfacesdemo.model.Store;

public class SummaryResponse {
    private Pet pet;
    private Store store;

    // Пустой конструктор для Jackson
    public SummaryResponse() {}

    // Тот самый конструктор, который ищет ваш сервис
    public SummaryResponse(Pet pet, Store store) {
        this.pet = pet;
        this.store = store;
    }

    // Геттеры и сеттеры...
    public Pet getPet() { return pet; }
    public void setPet(Pet pet) { this.pet = pet; }
    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }
}