package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import static api.constants.Constants.USER_DATA_EXCEL;
import static api.constants.Constants.USER_DATA_FOLDER;
import static api.constants.Constants.USER_DIR;
import static api.constants.Constants.SHEET_1;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = USER_DIR + USER_DATA_FOLDER + USER_DATA_EXCEL;
        XLUtility xlUtility = new XLUtility(path);

        int rowNumber = xlUtility.getRowCount(SHEET_1);
        int columnNumber = xlUtility.getCellCount(SHEET_1, 1);
        String apiData[][] = new String[rowNumber][columnNumber];

        for (int i = 1; i <= rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                apiData[i - 1][j] = xlUtility.getCellData(SHEET_1, i, j);
            }
        }
        return apiData;
    }

    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        String path = USER_DIR + USER_DATA_FOLDER + USER_DATA_EXCEL;
        XLUtility xlUtility = new XLUtility(path);

        int rowNumber = xlUtility.getRowCount(SHEET_1);
        String apiData[] = new String[rowNumber];

        for (int i = 1; i <= rowNumber; i++) {
            apiData[i - 1] = xlUtility.getCellData(SHEET_1, i, 1);
        }
        return apiData;
    }
}
