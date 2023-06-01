package orma.dict;

import orma.dict.service.DictionaryService;
import orma.dict.service.FileService;
import orma.dict.service.StringService;
import orma.dict.service.impl.DictionaryServiceImpl;
import orma.dict.service.impl.FileServiceImpl;
import orma.dict.service.impl.StringServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.*;

public class App {

    public static void main(String[] args) {
        final String fullFileName = "./src/main/resources/Book.txt";
        final FileService fileService = new FileServiceImpl();
        final StringService stringService = new StringServiceImpl();
        final DictionaryService dictionaryService = new DictionaryServiceImpl();
        final int ROW_STEP = 50;

        Map<String, Integer> dict = new HashMap<>();
        int rowCounter = 0;
        boolean again = true;
        do {
            StringBuilder strFileContent = new StringBuilder();
            List<String> listRowsContent = fileService.readRowsFromFile(fullFileName, rowCounter, ROW_STEP);
            if (!listRowsContent.isEmpty()) {
                rowCounter = ++rowCounter + listRowsContent.size();
                for (String str : listRowsContent) {
                    strFileContent.append(" ").append(str);
                }
                strFileContent = new StringBuilder(stringService.prepareString(strFileContent.toString()));
                String word;
                String workString = strFileContent.toString();
                while (workString.length() > 0) {
                    int index = workString.indexOf(" ");
                    if (index <= 0) {
                        word = workString;
                        workString = "";
                    } else {
                        word = workString.substring(0, index);
                        workString = workString.substring(index + 1);
                    }
                    dictionaryService.addToMap(word, dict);

                }
            } else {
                again = false;
            }
        } while (again);

        String gaps = "----------------------------------------------";
        out.println(gaps);
        out.println("\t  Частотный словарь. Всего слов: " + dict.size());
        out.println(gaps);
        dict.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach((entry) -> out.println(entry.getValue() + "\t\t" + entry.getKey()));
        out.println(gaps);
    }

}
