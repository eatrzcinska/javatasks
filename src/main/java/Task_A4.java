import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.Date;
import java.util.Iterator;

public class Task_A4 {

    public static void main(String[] args) throws IOException {

        int rowIndexStart = 4;
        int rowIndexEnd = 34;
        int columnDate = 0;
        int columnNetAlizeService = 2;
        int columnSellMarginalPrice = 5;
        double sumNetAlizeService = 0;
        double sumSellMarginalPrice = 0;
        double averageNetAlizeService;
        double averageSellMarginalPrice;

        FileInputStream file = new FileInputStream(new File("src\\main\\resources\\Desequilibre_Charges_2017-10-15_2017-11-14.xls"));

        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("North");
        Date startDate = sheet.getRow(rowIndexStart).getCell(columnDate).getDateCellValue();
        Date endDate = sheet.getRow(rowIndexEnd).getCell(columnDate).getDateCellValue();
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t\t");
                        break;
                    case NUMERIC:
                        if (cell.getColumnIndex() == columnDate) {
                            Date date = row.getCell(columnDate).getDateCellValue();
                            System.out.format("%td.%tm.%ty", date, date, date);
                        } else {
                            System.out.format("%20.1f", cell.getNumericCellValue());
                        }
                        break;
                    case STRING:
                        System.out.format("%s%n", cell.getStringCellValue() + "\t\t");
                        break;
                }
            }
            System.out.println("");
        }

        for (int i = rowIndexStart; i <= rowIndexEnd; i++) {
            Double dayValue = sheet.getRow(i).getCell(columnNetAlizeService).getNumericCellValue();
            sumNetAlizeService += dayValue;
        }
        averageNetAlizeService = sumNetAlizeService / (rowIndexEnd - rowIndexStart + 1);

        for (int i = rowIndexStart; i <= rowIndexEnd; i++) {
            Double dayValue = sheet.getRow(i).getCell(columnSellMarginalPrice).getNumericCellValue();
            sumSellMarginalPrice += dayValue;
        }
        averageSellMarginalPrice = sumSellMarginalPrice / (rowIndexEnd - rowIndexStart + 1);

        file.close();


        System.out.format("Net imbalances through the ALIZES service: `%td.%tm.%ty - %td.%tm.%ty` " +
                        "SUM: `%10.1f` AVG: `%10.1f`\n", startDate, startDate, startDate, endDate, endDate, endDate,
                        sumNetAlizeService, averageNetAlizeService);
        System.out.format("Imbalances cashed out through sells at marginal price (kWh 25°C): " +
                        "`%td.%tm.%ty - %td.%tm.%ty`: SUM: `%10.1f` AVG: `%10.1f`", startDate, startDate, startDate,
                        endDate, endDate, endDate, sumSellMarginalPrice, averageSellMarginalPrice);
    }
}
