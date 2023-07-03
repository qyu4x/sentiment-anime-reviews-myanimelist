# Sentiment Analysis of Anime Reviews from MyAnimeList
<img src="./banner.jpg" alt="Banner" style="width: 100%;">

This project focuses on conducting sentiment analysis on anime reviews sourced from the popular website MyAnimeList. Initially, the objective was to analyze fan responses to VTubers in Indonesia using data from Twitter. However, due to the limitations imposed by Twitter, including scraping restrictions, acquiring the desired data became challenging.

<img src="https://i.ibb.co/ykcqC83/limitmark.jpg" alt="Thanks elon" style="width: 30%;">


To overcome this obstacle, I developed custom scraping tools using Spring Boot. These tools successfully gathered a dataset of 17,500 anime reviews from the MyAnimeList website. Consequently, the project's scope shifted to analyzing user sentiments and opinions expressed in these reviews.

## Project Structure

 - **data_scraping**: Contains the Spring Boot application for scraping anime reviews from MyAnimeList.
 - **preprocessing**: Includes scripts for cleaning and preparing the collected data for sentiment analysis.
 - **modeling**: Contains the code for training a sentiment analysis model using machine learning techniques.
 - **api**: Provides an API endpoint for performing sentiment analysis on new anime reviews.
 - **visualization**: Contains the results of data visualisation.

## Example Request
To utilize the sentiment analysis functionality, send a request with the anime review text to the provided API endpoint. An example request would look like:

<img src="https://i.ibb.co/DphC1Rx/Screenshot-1183.png" alt="Sample Request" style="width: 70%;">

