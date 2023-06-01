package orma.dict.service;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<String> readFromFile(String fullFileName);

    List<String> readRowsFromFile(String fullFileName, int startRowNumber, int rowsNumber);

    void writeToFile(String fullFileName, List<String> data) throws IOException;
}
