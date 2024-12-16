package ru.clevertec.service.exception;

public enum ObjectType {
    CAR("Car"),
    CAR_SHOWROOM("Car Showroom"),
    CATEGORY("Category"),
    CLIENT("Client"),
    REVIEW("Review");

    private String title;

    ObjectType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Type " + title;
    }
}
