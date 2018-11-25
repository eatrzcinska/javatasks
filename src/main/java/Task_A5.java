import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task_A5 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\main\\resources\\hurdat2-nepac-1949-2016-041317.txt");
        Scanner scanner = new Scanner(file, "UTF-8");

        do {
            String headerLine = scanner.nextLine();
            String year = headerLine.substring(4, 8);
            String name = headerLine.substring(18, 28).trim();

            if (Integer.parseInt(year) > 2015 && name.endsWith("A")) {
                String rowsNumber = headerLine.substring(33, 36).trim();
                int number = Integer.parseInt(rowsNumber);
                int maximumSpeed = 0;

                for (int i = 1; i <= number; i++) {
                    String dataLine = scanner.nextLine();
                    int speed = Integer.parseInt(dataLine.substring(38, 41).trim());

                    if (speed > maximumSpeed) {
                        maximumSpeed = speed;
                    }
                }
                System.out.println(name + ": " + maximumSpeed);
            }

        } while (scanner.hasNextLine());
    }
}

