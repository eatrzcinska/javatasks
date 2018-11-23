import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class Task_A1 {
    public static void main(String[] args) throws IOException {

        File file = new File("src\\main\\resources\\task_A1.html");
        Document document = Jsoup.parse(file, "ISO-8859-1");

        Elements tableElements = document.select("table");
        Elements rowElements = tableElements.select("tr");

        for (int i = 0; i < rowElements.size(); i++) {

            Element row = rowElements.get(i);
            Elements date = row.select("th[class='titulo ini']");

            System.out.format("%10s", date.text().replaceAll("10102017T0000", "10-10-2017")
                    .replaceAll("2017/10/10", "10-10-2017"));

            Elements columns = row.select("th[class='titulo']");

            for (int j = 0; j < columns.size(); j++) {
                System.out.format("%25s", columns.get(j).text().replaceAll("\\(\\d\\)", "")
                        .replaceAll("\\+", "Y").replaceAll("%", " ")
                        .replaceAll("\\,56", ".56").replaceAll("\\,", "")
                        .replaceAll("n\\.a", "n a").toUpperCase().trim());
            }
            System.out.println();
        }
    }
}
