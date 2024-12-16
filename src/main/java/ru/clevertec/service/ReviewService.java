package ru.clevertec.service;

import ru.clevertec.dto.ReviewCreateDto;
import ru.clevertec.dto.ReviewDto;
import ru.clevertec.dto.ReviewUpdateDto;

import java.util.List;

public interface ReviewService extends CommonService<ReviewDto> {
    List<ReviewDto> searchReviews(String keyword);

    void addReview(ReviewCreateDto reviewDto);

    void update(Long id, ReviewUpdateDto reviewDto);
}
