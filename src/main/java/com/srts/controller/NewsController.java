package com.srts.controller;

import com.srts.entity.News;
import com.srts.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    // Fetch all news articles
    @GetMapping("/all")
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return ResponseEntity.ok(newsList);
    }

    // Add a news article
    @PostMapping("/add")
    public ResponseEntity<?> addNews(@RequestBody News news) {
        newsRepository.save(news);
        return ResponseEntity.ok("News article added successfully.");
    }

    // Delete a news article by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id) {
        if (!newsRepository.existsById(id)) {
            return ResponseEntity.status(404).body("News article not found.");
        }
        newsRepository.deleteById(id);
        return ResponseEntity.ok("News article deleted successfully.");
    }
}
