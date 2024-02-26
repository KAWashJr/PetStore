package api.test;

import api.utilities.DataProviders;

import java.io.IOException;
import java.util.Arrays;

public class Sample {
    public static void main(String[] args) throws IOException {
        DataProviders dataProviders = new DataProviders();
        String data[][] = dataProviders.getAllData();
        Arrays.stream(data).forEach(row -> System.out.println(Arrays.toString(row)));
    }
}
