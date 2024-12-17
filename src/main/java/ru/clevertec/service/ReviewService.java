package ru.clevertec.service;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;

import java.util.List;

public interface ReviewService extends CommonService<Review> {
    void addReview(Client client, Car car, String text, int rating);
    List<Review> searchReviews(String keyword);
}
