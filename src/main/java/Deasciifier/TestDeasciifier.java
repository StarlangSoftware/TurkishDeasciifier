package Deasciifier;

import Corpus.Sentence;
import Dictionary.TurkishWordComparator;
import Dictionary.TxtDictionary;
import Dictionary.Word;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;

public class TestDeasciifier {

    private static Sentence asciify(Sentence sentence){
        Asciifier asciifier = new SimpleAsciifier();
        return asciifier.asciify(sentence);
    }

    private static Sentence deasciify(Sentence sentence, FsmMorphologicalAnalyzer fsm){
        Deasciifier deasciifier = new SimpleDeasciifier(fsm);
        return deasciifier.deasciify(sentence);
    }

    private static void createMisspellings(String dictionaryFileName, String misspellingsFileName){
        SimpleAsciifier asciifier = new SimpleAsciifier();
        TxtDictionary dictionary = new TxtDictionary(dictionaryFileName, new TurkishWordComparator(), misspellingsFileName);
        for (int i = 0; i < dictionary.size(); i++){
            Word word = dictionary.getWord(i);
            String asciified = asciifier.asciify(word);
            if (dictionary.getWord(asciified) == null && dictionary.getCorrectForm(asciified) == null){
                System.out.println(asciified + "\t" + word.getName());
            }
        }
    }

    public static void main(String[] args){
        //createMisspellings("gittigidiyor_dictionary.txt", "gittigidiyor_misspellings.txt");
        FsmMorphologicalAnalyzer fsm;
        fsm = new FsmMorphologicalAnalyzer();
        Sentence s = new Sentence("zorlandik");
        Sentence s2 = deasciify(s, fsm);
        System.out.println(s2);
    }
}
