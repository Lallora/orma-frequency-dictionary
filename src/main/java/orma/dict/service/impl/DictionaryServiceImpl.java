package orma.dict.service.impl;

import orma.dict.service.DictionaryService;

import java.util.Map;

public class DictionaryServiceImpl implements DictionaryService {

    @Override
    public void addToMap(String word, Map<String, Integer> dict) {
        if (dict.containsKey(word)) {
            int value = dict.get(word);
            dict.remove(word);
            dict.put(word, value + 1);
        } else {
            dict.put(word, 1);
        }
    }
}
