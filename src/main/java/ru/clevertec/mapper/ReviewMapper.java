package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.dto.ReviewDto;
import ru.clevertec.entity.Review;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto toDto(Review review);

    Review toReview(ReviewDto reviewDto);

    List<ReviewDto> map(List<Review> review);
}
