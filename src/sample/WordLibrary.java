package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class WordLibrary {

    private ArrayList<Word> library = new ArrayList<Word>();


    public int getSize() {
        return library.size();
    }

    public Word getWordAt(int index) {
        return library.get(index);
    }

    public WordLibrary() {

    }

    public Word addWord(Word newWord) {
        library.add(new Word(newWord.getWordTarget(), newWord.getWordExplain()));
        Collections.sort(library);
        return newWord;
    }

    public void deleteWord(String selectWord) {
        int index = 0;
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getWordTarget().equals(selectWord)) {
                index = i;
                break;
            }
        }
        if (library.get(index).getWordTarget().equals(selectWord)) {
            library.remove(index);
        }
    }

    public void editWord (Word select) {
        int index = 0;
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getWordTarget().equals(select.getWordTarget())) {
                index = i;
                break;
            }
        }
        if (library.get(index).getWordTarget().equals(select.getWordTarget())) {
            library.get(index).setWordExplain(select.getWordExplain());
        }
    }
    public static void main(String[] args) {
        ArrayList<Word> test = new ArrayList<Word>();
        Word a = new Word("abcs", "cstvcs");
        Word b = new Word("aaaa", "dsdbc");
        test.add(a);
        test.add(b);
        Collections.sort(test);
        for (Word i : test) {
            System.out.println(i.getWordTarget() + " " + i.getWordExplain());
        }
    }
}
