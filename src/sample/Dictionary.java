package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    private WordLibrary[] arrayLibrary = new WordLibrary[26];
    private int len = 0;

    public WordLibrary getLibraryAt(int index) {
        return arrayLibrary[index];
    }

    public Dictionary() {

    }

    public void print() {
        for (int i = 0; i < 4; i++) {
            int size = arrayLibrary[i].getSize();
            for (int j = 0; j < size; j++) {
                System.out.println(this.getWord(i, j).getWordTarget());
            }
        }
    }

    public Word getWord(int i, int j) {
        return arrayLibrary[i].getWordAt(j);
    }

    public void insertFromFile() {
        try {
            File text = new File("C:\\Users\\Bui Loan\\IdeaProjects\\Dictionary\\src\\sample\\wordA.txt");
            Scanner scanner = new Scanner(text);
            WordLibrary test = new WordLibrary();
            while (scanner.hasNextLine()) {
                String result = "";

                String s = scanner.nextLine();
                while (!(s.equals(""))) {
                    result = result + s + "\n";
                    s = scanner.nextLine();
                }
                Word tempW = new Word();
                tempW.setWordExplain(result);
                String Target = "";
                int i = 1;
                while ((result.charAt(i) != ' ') || (result.charAt(i) == ' ' && result.charAt(i + 1) != '/')) {
                    Target += result.charAt(i);
                    i++;
                }
                tempW.setWordTarget(Target);
                if (test.getSize() == 0) {
                    test.addWord(tempW);
                } else {
                    if (test.getWordAt(0).getWordTarget().charAt(0) == (tempW.getWordTarget().charAt(0))) {
                        test.addWord(tempW);
                        if (!scanner.hasNextLine()) {
                            arrayLibrary[test.getWordAt(0).getWordTarget().charAt(0) - 97] = test;
                        }
                    } else {
                        arrayLibrary[test.getWordAt(0).getWordTarget().charAt(0) - 97] = test;
                        test = new WordLibrary();
                        test.addWord(tempW);
                        if (!scanner.hasNextLine()) {
                            arrayLibrary[test.getWordAt(0).getWordTarget().charAt(0) - 97] = test;
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * public void delete(String searchingWord) {
     * int size = aStart.getSize();
     * int index = 0;
     * for (int i = 0; i < size; i++) {
     * if (searchingWord.equals(aStart.getWordAt(i).getWordTarget())) {
     * index = i;
     * break;
     * }
     * }
     * words.remove(index);
     * len --;
     * }
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * public void editWordsFromCommandLine() {
     * int size = words.size();
     * Scanner sc = new Scanner(System.in);
     * System.out.print("Edit: ");
     * String searchingTarget = sc.nextLine();
     * String searchingExplain = sc.nextLine();
     * <p>
     * for (int i = 0; i < size; i++) {
     * Word temp = this.getWordAt(i);
     * if (searchingExplain.equals(temp.getWordExplain())) {
     * temp.setWordTarget(searchingTarget);
     * break;
     * } else if (searchingTarget.equals(temp.getWordTarget())) {
     * temp.setWordExplain(searchingExplain);
     * break;
     * }
     * }
     * }
     */

    public String dictionaryLookup(String searchingWordTarget) {
        int index = 0;
        int pos = searchingWordTarget.charAt(0) - 97;
        int size = arrayLibrary[pos].getSize();
        for (int i = 0; i < size; i++) {
            if (searchingWordTarget.equals(this.getWord(pos, i).getWordTarget())) {
                index = i;
                break;
            }
        }
        if (this.getWord(pos, index).getWordTarget().equals(searchingWordTarget)) {
            return this.getWord(pos, index).getWordExplain();
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        Dictionary huy = new Dictionary();
        huy.insertFromFile();
    }
}

