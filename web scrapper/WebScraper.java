import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
        String url = "http://quotes.toscrape.com";
        String filePath = "scraped_quotes.txt"; // Will be saved in the current folder

        try {
            // Connect to the website
            Document doc = Jsoup.connect(url).get();

            // Select all quote blocks
            Elements quotes = doc.select(".quote");

            // Prepare writer
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            // Loop through each quote and write to file
            for (Element quote : quotes) {
                String text = quote.select(".text").text();
                String author = quote.select(".author").text();

                writer.write("Quote: " + text);
                writer.newLine();
                writer.write("Author: " + author);
                writer.newLine();
                writer.write("----------------------");
                writer.newLine();
            }

            writer.close();
            System.out.println("Quotes successfully saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
