package Deasciifier;

import Corpus.Corpus;
import Corpus.Sentence;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;
import Ngram.NGram;
import Ngram.NoSmoothing;
import org.junit.Test;

import static org.junit.Assert.*;

public class NGramDeasciifierTest {

    @Test
    public void testDeasciify() {
        FsmMorphologicalAnalyzer fsm = new FsmMorphologicalAnalyzer();
        NGram<String> nGram = new NGram<String>("ngram.txt");
        nGram.calculateNGramProbabilities(new NoSmoothing<>());
        NGramDeasciifier nGramDeasciifier = new NGramDeasciifier(fsm, nGram);
        SimpleAsciifier simpleAsciifier = new SimpleAsciifier();
        Corpus corpus = new Corpus("corpus.txt");
        for (int i = 0; i < corpus.sentenceCount(); i++){
            Sentence sentence = corpus.getSentence(i);
            for (int j = 1; j < sentence.wordCount(); j++){
                if (fsm.morphologicalAnalysis(sentence.getWord(j).getName()).size() > 0){
                    String asciified = simpleAsciifier.asciify(sentence.getWord(j));
                    if (!asciified.equals(sentence.getWord(j).getName())){
                        Sentence deasciified = nGramDeasciifier.deasciify(new Sentence(sentence.getWord(j - 1).getName() + " " + sentence.getWord(j).getName()));
                        assertEquals(sentence.getWord(j).getName(), deasciified.getWord(1).getName());
                    }
                }
            }
        }
    }
}