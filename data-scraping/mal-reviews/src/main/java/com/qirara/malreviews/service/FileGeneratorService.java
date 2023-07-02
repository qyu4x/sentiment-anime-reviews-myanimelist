package com.qirara.malreviews.service;

import com.qirara.malreviews.entity.Review;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

@Service
public class FileGeneratorService {

    public void writeReviewsToCsvWeb(List<Review> reviews, Writer writer) throws IOException {
        String[] headers = {"Username", "Anime Title", "Comment", "Label", "Created At"};

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers));
        reviews.stream().forEach(review -> {
            try  {
                csvPrinter.printRecord(review.getUsername(), review.getAnimeTitle(), review.getComment(), review.getTags(), review.getReviewedAt());
            } catch (IOException exception) {
                throw new RuntimeException(exception.getMessage());
            }
        });

        csvPrinter.close();
    }

    public void writeReviewsToCsvCLI(List<Review> reviews, String filename) throws IOException {
        String downloadDirectory  = System.getProperty("user.home").concat("/Downloads/");
        String filePath = downloadDirectory.concat(filename).concat(".csv");

        Path path = Path.of(filePath);
        FileWriter fileWriter = new FileWriter(path.toString());

        String[] headers = {"Username", "Anime Title", "Comment", "Label", "Created At"};

        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(headers));
        reviews.stream().forEach(review -> {
            try  {
                csvPrinter.printRecord(review.getUsername(), review.getAnimeTitle(), review.getComment(), review.getTags(), review.getReviewedAt());
            } catch (IOException exception) {
                throw new RuntimeException(exception.getMessage());
            }
        });

        csvPrinter.close();
        fileWriter.close();
    }

}
