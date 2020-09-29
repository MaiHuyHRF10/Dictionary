package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    public void addWord(Word newWord) {
        library.add(new Word(newWord.getWordTarget(), newWord.getWordExplain()));
    }


}
