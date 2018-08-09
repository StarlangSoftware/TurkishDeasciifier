package Deasciifier;

import Corpus.Sentence;
import Dictionary.TurkishWordComparator;
import Dictionary.TxtDictionary;
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

    public static void main(String[] args){
        FsmMorphologicalAnalyzer fsm;
        fsm = new FsmMorphologicalAnalyzer();
        Sentence s = new Sentence("COCUK");
        Sentence s2 = deasciify(s, fsm);
        System.out.println(s2);
    }
}
