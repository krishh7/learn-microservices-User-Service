package com.lcwd.user.service.external.service;

import com.lcwd.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //Get
    @GetMapping
    Rating getRating();

    //Post
    @PostMapping("/ratings")
    Rating createRating(@RequestBody Rating rating);

    //Put
    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

    //Delete
    @DeleteMapping("ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}
