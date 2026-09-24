package org.julio.utils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public final class SizeUtils {
    private SizeUtils(){}


    private static final String UNIT_SIZE = "KMGTPE";
    public static String humanReadableByteCountBin(long bytes) {
        // if bytes is equal to Long.MIN_VALUE, then its value will be Long.MAX_VALUE
        // if not -> Math.abs(bytes)
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        // if absB is less than 1024 (byte threshold)
        if (absB < 1024) {
            // just concat "B" (bytes)
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator(UNIT_SIZE);
        // Se asigna i = 40
        // mientras i sea mayor igual a 0 y que absB sea un valor mayor a
        // 0xfffccccccccccccL >> i;
        // el value se divide entre 1024 y se va al siguiente indice de ci
        // se resta -10 al indice
        // cuando absB ya NO supera el umbral, el loop para — tenemos la unidad correcta
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        // devuelve el signo original de bytes
        value *= Long.signum(bytes);
        return String.format("%.2f %ciB", value / 1024.0, ci.current());
    }

    public static String humanReadableByteCountSI(long bytes) {
        if (-1000 < bytes && bytes < 1000) {
            return bytes + " B";
        }
        CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        while (bytes <= -999_950 || bytes >= 999_950) {
            bytes /= 1000;
            ci.next();
        }
        return String.format("%.2f %cB", bytes / 1000.0, ci.current());
    }
}
