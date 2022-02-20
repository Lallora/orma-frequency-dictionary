package orma.dict.service;

import java.util.Map;

public interface DictionaryService {
    void addToMap(String word, Map<String, Integer> dict);
}
