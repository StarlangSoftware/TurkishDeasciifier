package Deasciifier;

import Corpus.Sentence;
import Dictionary.Word;

public class SimpleAsciifier implements Asciifier{

    private String asciify(Word word){
        char[] modified = word.getName().toCharArray();
        for (int i = 0; i < modified.length; i++){
            switch (modified[i]){
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

    public Sentence asciify(Sentence sentence) {
        Word word, newWord;
        Sentence result = new Sentence();
        for (int i = 0; i < sentence.wordCount(); i++){
            word = sentence.getWord(i);
            newWord = new Word(asciify(word));
            result.addWord(newWord);
        }
        return result;
    }

}
