package com.qirara.malreviews.service;

import com.qirara.malreviews.entity.Review;
import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Value("${mal.reviews.url}")
    private String reviewsURL;


    private final static Logger log = LoggerFactory.getLogger(ReviewService.class);

    public List<Review> reviewsList(Integer page) throws IOException {
        List<Review> reviews = new ArrayList<>();

        for (int currentPage = 0; currentPage <= page; currentPage++) {
            String link = reviewsURL.concat(String.valueOf(currentPage));
            log.info("fetch page => {}", currentPage);

            Document document = Jsoup.connect(link).get();
            Elements reviewElements = document.getElementsByClass("review-element js-review-element");

            reviewElements.stream().forEach(reviewElement -> {
                Review review = new Review();
                review.setUsername(reviewElement.getElementsByClass("username").select("a").text());
                review.setAnimeTitle(reviewElement.getElementsByClass("title ga-click").select("a").text());
                review.setComment(reviewElement.getElementsByClass("text").text().replace("<br>", ""));
                review.setTags(reviewElement.getElementsByClass("tags").text());
                review.setReviewedAt(reviewElement.getElementsByClass("update_at").text());

                log.info("username : {}", review.getUsername());
                log.info("title : {}", review.getAnimeTitle());
                log.info("comment : {}", review.getComment());
                log.info("tags : {}", review.getTags());
                log.info("reviewedAt : {}", review.getReviewedAt());

                reviews.add(review);
            });
        }

        return reviews;
    }
}
