import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;

/*

"Write an application that downloads only main table (marked as table report_table)
from: https://www.ote-cr.cz/en/statistics/electricity-imbalances-1 and store it as a HTML file."

*/
public class Task_B1 {

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.ote-cr.cz/en/statistics/electricity-imbalances-1").get();
        Elements tableElements = document.select("table[class=table report_table]");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title>Task_B1</title></head><body>");
        sb.append(tableElements.toString());
        sb.append("</body></html>");
        String html = sb.toString();

        PrintWriter pw = new PrintWriter("src\\main\\resources\\Task_B1.html");
        pw.println(html);
        pw.close();
    }
}
