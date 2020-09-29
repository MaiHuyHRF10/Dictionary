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

    /** public void insertFromFile() {
        try {
            File text = new File("C:\\Users\\DELL\\IdeaProjects\\Demo_Dictionary\\src\\sample\\wordA.txt");
            Scanner scanner = new Scanner(text);

            while (scanner.hasNextLine()) {
                String result = "";
                String s = scanner.nextLine();
                while (!(s.equals(""))) {
                    result = result + s + "\n";
                    s = scanner.nextLine();
                    System.out.println(result);
                }

                Word tempW = new Word();
                tempW.setWordExplain(result);
                String Target = "";
                int i = 1;
                while (result.charAt(i) != ' ') {
                    Target += result.charAt(i);
                    i++;
                }
                tempW.setWordTarget(Target);
                library.add(tempW);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } */


}
