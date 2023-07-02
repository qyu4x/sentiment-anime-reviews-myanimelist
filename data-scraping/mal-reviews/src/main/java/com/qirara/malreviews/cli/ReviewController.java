package com.qirara.malreviews.cli;


import com.qirara.malreviews.service.FileGeneratorService;
import com.qirara.malreviews.service.ReviewService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/mal/v1/csv")
public class ReviewController {

    private ReviewService reviewService;

    private FileGeneratorService fileGeneratorService;

    public ReviewController(ReviewService reviewService, FileGeneratorService fileGeneratorService) {
        this.reviewService = reviewService;
        this.fileGeneratorService = fileGeneratorService;
    }

    @GetMapping("/reviews")
    public void animeReviews(@RequestParam("page") Integer page, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=anime_reviews-mal.csv");
        fileGeneratorService.writeReviewsToCsvWeb(reviewService.reviewsList(page), response.getWriter());
    }
}
