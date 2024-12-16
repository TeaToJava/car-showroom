package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.ReviewCreateDto;
import ru.clevertec.dto.ReviewDto;
import ru.clevertec.dto.ReviewUpdateDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;
import ru.clevertec.mapper.CarMapper;
import ru.clevertec.mapper.ClientMapper;
import ru.clevertec.mapper.ReviewMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.repository.ReviewRepository;
import ru.clevertec.service.ReviewService;
import ru.clevertec.service.exception.ObjectNotFoundException;
import ru.clevertec.service.exception.ObjectType;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final CarMapper carMapper;
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    @Override
    public List<ReviewDto> searchReviews(String keyword) {
        return reviewMapper.map(reviewRepository.findReviewsByKeyWord(keyword));
    }

    @Override
    public void addReview(ReviewCreateDto reviewDto) {
        Long clientId = reviewDto.getClientId();
        Client client = clientRepository.findById(reviewDto.getClientId())
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CLIENT, clientId.toString()));
        Long carId = reviewDto.getCarId();
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.CAR, carId.toString()));
        Review review = Review.builder()
                .client(client)
                .car(car)
                .rating(reviewDto.getRating())
                .text(reviewDto.getText()).build();
        reviewRepository.save(review);
    }

    @Override
    public List<ReviewDto> getAll() {
        return reviewMapper.map(reviewRepository.findAll());
    }

    @Override
    public ReviewDto getById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ObjectType.REVIEW, id.toString()));
        return reviewMapper.toDto(review);
    }

    @Override
    public void save(ReviewDto reviewDto) {
        reviewRepository.save(reviewMapper.toReview(reviewDto));
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void update(Long id, ReviewUpdateDto reviewDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectType.REVIEW, id.toString()));
        review.setText(reviewDto.text());
        reviewRepository.save(review);
    }
}
