package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    //private static WordLibrary[] arrayLibrary;
    private WordLibrary[] arrayLibrary = new WordLibrary[26];
    private int len = 0;

    public WordLibrary getLibraryAt(int index) {
        return arrayLibrary[index];
    }

    public Dictionary() {

    }

    public void print() {
        for (int i = 0; i < 1; i++) {
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
        /*try {
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

         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dictionary2";// your db name
            String user = "root"; // your db username
            String password = ""; // your db password
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connect success!");
            }

            var sql = "select * from tbl_edict";
            var statement = conn.prepareStatement(sql);
            var resultSet = statement.executeQuery();
            showResult(resultSet);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void showResult(ResultSet resultSet) throws SQLException {
        WordLibrary test = new WordLibrary();
        while (resultSet.next()) {
            int idx = resultSet.getInt("idx");
            String word = resultSet.getString("word");
            String detail = resultSet.getString("detail");

            if (word.charAt(0) >= 'a' && word.charAt(0) <= 'z') {
                Word tempW = new Word();
                tempW.setWordExplain(detail);
                tempW.setWordTarget(word);

                if (test.getSize() == 0) {
                    test.addWord(tempW);
                } else {
                    if (test.getWordAt(0).getWordTarget().charAt(0) != (tempW.getWordTarget().charAt(0))) {
                        arrayLibrary[test.getWordAt(0).getWordTarget().charAt(0) - 97] = test;
                        test = new WordLibrary();
                    }
                    test.addWord(tempW);
                    if (resultSet.isLast()) {
                        arrayLibrary[test.getWordAt(0).getWordTarget().charAt(0) - 97] = test;
                    }
                }

            }
        }
    }

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

         /*

        int pos = searchingWordTarget.charAt(0) - 97;
        int index = (begin + end) / 2;
        if (begin > end) return null;
        if (searchingWordTarget.equals(this.getWord(pos, index).getWordTarget())) {
            return this.getWord(pos, index).getWordExplain();
        } else {
            if (searchingWordTarget.compareTo(this.getWord(pos, index).getWordTarget()) < 0) {
                return dictionaryLookup(searchingWordTarget, begin, index - 1);
            } else {
                return dictionaryLookup(searchingWordTarget, index + 1, end);
            }
        }

         */
    }

    public ArrayList<Word> dictionarySearcher(String select) {
        ArrayList<Word> temp = new ArrayList<>();
        int size = arrayLibrary[select.charAt(0) - 97].getSize();
        for (int i = 0; i < size; i++) {
            String s = arrayLibrary[select.charAt(0) - 97].getWordAt(i).getWordTarget();
            if (select.length() <= s.length() && select.equals(s.substring(0, select.length()))) {
                temp.add(arrayLibrary[select.charAt(0) - 97].getWordAt(i));
            }
        }

        return temp;
    }

}

