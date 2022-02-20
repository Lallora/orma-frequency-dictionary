package orma.dict.service.impl;


import orma.dict.service.FileService;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileServiceImpl implements FileService {


    @Override
    public List<String> readFromFile(String fullFileName) {
        List<String> list = new ArrayList<>();
        try (FileReader fr = new FileReader(fullFileName)) {
            Scanner scan = new Scanner(fr);
            int i = 1;
            while (scan.hasNextLine()) {
                list.add(scan.nextLine());
                i++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<String> readRowsFromFile(String fullFileName, int startRowNumber, int rowsNumber) {
        List<String> list = new ArrayList<>();
        try (FileReader fr = new FileReader(fullFileName)) {
            Scanner scan = new Scanner(fr);
            int i = 1;
            while (scan.hasNextLine()) {
                if (i >= startRowNumber) {
                    list.add(scan.nextLine());
                } else {
                    scan.nextLine();
                    if (i > startRowNumber + rowsNumber) {
                        break;
                    }
                }
                i++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }


    @Override
    public void writeToFile(String fullFileName, List<String> data) throws IOException {
        try (FileWriter fw = new FileWriter(fullFileName)) {
            for (String str : data) {
                str = str + "\n";
                fw.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
