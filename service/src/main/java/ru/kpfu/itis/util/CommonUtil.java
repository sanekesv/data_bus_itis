package ru.kpfu.itis.util;

import com.ibm.icu.text.Transliterator;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

public class CommonUtil {

    /**
     * Генерация логина по ФИО, требуется полное ФИО
     */
    public static String generateLogin(String name) {
        Transliterator transliterator = Transliterator.getInstance("Russian-Latin/BGN");
        String transliteration = transliterator.transliterate(name);
        transliteration = transliteration.replaceAll("ʹ", "");
        String[] splits = transliteration.split("\\s+");
        String surname = "";
        //Видел фамилию из двух частей, с именами и отчествами вроде не было. если что подправим
        for (int i = 0; i < splits.length - 2; i++) surname += splits[i];
        return splits[splits.length - 2].substring(0, 1) + splits[splits.length - 1].substring(0, 1) + surname;
    }


    public static long getYearMillis(@NotNull Integer year) {
        return new DateTime(year, 1, 1, 0, 0).getMillis();
    }

}
