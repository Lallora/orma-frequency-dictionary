package orma.dict;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import orma.dict.service.*;

public class App {

    public static void main(String[] args) {
        final String fullFileName = "./src/main/resources/Book.txt";
        final FileService fileService = new FileServiceImpl();

        List<String> listFileContent = fileService.readFromFile(fullFileName);
        Map<String, Integer> dict = new HashMap<>();
        String strFileContent = "";
        for (String str : listFileContent) {
            strFileContent = strFileContent + " " + str;
        }
        strFileContent = strFileContent
                .replaceAll("[^a-zA-Zа-яёА-ЯЁ ]", " ")
                .toLowerCase(Locale.ROOT)
                .trim();
        for (int i = 0; i < 20; i++) {
            strFileContent = strFileContent.replace("  ", " ");
        }

        String word;
        String workString = strFileContent;
        while (workString.length() > 0){
            int index = workString.indexOf(" ");
            if(index <= 0){
                word = workString;
                workString = "";
            } else {
                word = workString.substring(0, index);
                workString = workString.substring(index + 1);
            }
            addToMap(word, dict);
        }
        String gaps = "-----------------------------";
        System.out.println(gaps);
        System.out.println("\t  Частотный словарь");
        System.out.println(gaps);
        dict.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach((entry)->System.out.println(entry.getValue() + "\t\t" + entry.getKey()));
        System.out.println(gaps);
    }

    private static void addToMap(String word, Map<String, Integer> dict){
        if(dict.containsKey(word)){
            int value = dict.get(word);
            dict.remove(word);
            dict.put(word, value + 1);
        } else {
            dict.put(word, 1);
        }
    }

}
