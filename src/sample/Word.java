package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;

import static java.lang.Character.isLetter;

public class Word implements Comparable {
    private String wordTarget;
    private String wordExplain;

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    public Word() {
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public String getWordExplain() {
        return wordExplain;
    }


    @Override
    public int compareTo(Object o) {
        Word other = (Word) o;
        return wordTarget.compareTo(other.getWordTarget());
    }

    public static boolean check(String s) {
        if (!isLetter(s.charAt(0))) return false;
        return true;
    }
}

