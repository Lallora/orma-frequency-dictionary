package orma.dict.service.impl;

import orma.dict.service.StringService;

import java.util.Locale;

public class StringServiceImpl implements StringService {

    @Override
    public String prepareString(String str) {
        String res = str
                .replaceAll("[^a-zA-Zа-яёА-ЯЁ ]", " ")
                .toLowerCase(Locale.ROOT)
                .trim();
        for (int i = 0; i < 50; i++) {
            res = res.replace("  ", " ");
        }
        return res;
    }
}
