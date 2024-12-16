package ru.clevertec.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.ReviewCreateDto;
import ru.clevertec.dto.ReviewDto;
import ru.clevertec.dto.ReviewUpdateDto;
import ru.clevertec.service.ReviewService;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createReviewForCar(@RequestBody @Valid ReviewCreateDto reviewDto) {
        reviewService.addReview(reviewDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto getReview(@PathVariable("id") @Valid @NotBlank Long id) {
        return reviewService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateReview(@PathVariable("id") @Valid @NotBlank Long id,
                             @RequestBody @Valid ReviewUpdateDto reviewDto) {
        reviewService.update(id, reviewDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable("id") @Valid @NotBlank Long id) {
        reviewService.deleteById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDto> getReviewsByKeyword(@RequestParam("keyword") @Valid @Max(20) String keyword) {
        return reviewService.searchReviews(keyword);
    }

}
