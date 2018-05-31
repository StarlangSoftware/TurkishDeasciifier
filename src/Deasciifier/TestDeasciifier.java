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
        Sentence s = new Sentence("İri turpu ne yaptın .");
        Sentence s1 = asciify(s);
        System.out.println(s1);
        Sentence s2 = deasciify(s1, fsm);
        System.out.println(s2);
    }
}
