package Deasciifier;

import Corpus.Sentence;
import Dictionary.Word;

public class SimpleAsciifier implements Asciifier {


    /**
     * The asciify method takes a {@link Word} as an input and converts it to a char {@link java.lang.reflect.Array}. Then,
     * loops i times where i ranges from 0 to length of the char {@link java.lang.reflect.Array} and substitutes Turkish
     * characters with their corresponding Latin versions and returns it as a new {@link String}.
     *
     * @param word {@link Word} type input to asciify.
     * @return String output which is asciified.
     */
    public String asciify(Word word) {
        char[] modified = word.getName().toCharArray();
        for (int i = 0; i < modified.length; i++) {
            switch (modified[i]) {
                case 'ç':
                    modified[i] = 'c';
                    break;
                case 'ö':
                    modified[i] = 'o';
                    break;
                case 'ğ':
                    modified[i] = 'g';
                    break;
                case 'ü':
                    modified[i] = 'u';
                    break;
                case 'ş':
                    modified[i] = 's';
                    break;
                case 'ı':
                    modified[i] = 'i';
                    break;
                case 'Ç':
                    modified[i] = 'C';
                    break;
                case 'Ö':
                    modified[i] = 'O';
                    break;
                case 'Ğ':
                    modified[i] = 'G';
                    break;
                case 'Ü':
                    modified[i] = 'U';
                    break;
                case 'Ş':
                    modified[i] = 'S';
                    break;
                case 'İ':
                    modified[i] = 'I';
                    break;
            }
        }
        return new String(modified);
    }

    /**
     * Another asciify method which takes a {@link Sentence} as an input. It loops i times where i ranges form 0 to number of
     * words in the given sentence. First it gets each word and calls asciify with current word and creates {@link Word}
     * with returned String. At the and, adds each newly created ascified words to the result {@link Sentence}.
     *
     * @param sentence {@link Sentence} type input.
     * @return Sentence output which is asciified.
     */
    public Sentence asciify(Sentence sentence) {
        Word word, newWord;
        Sentence result = new Sentence();
        for (int i = 0; i < sentence.wordCount(); i++) {
            word = sentence.getWord(i);
            newWord = new Word(asciify(word));
            result.addWord(newWord);
        }
        return result;
    }

}
