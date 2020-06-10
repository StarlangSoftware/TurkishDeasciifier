package Deasciifier;

import Corpus.Sentence;
import Dictionary.TxtWord;
import Dictionary.Word;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleDeasciifierTest {

    @Test
    public void testDeasciify() {
        FsmMorphologicalAnalyzer fsm = new FsmMorphologicalAnalyzer();
        SimpleDeasciifier simpleDeasciifier = new SimpleDeasciifier(fsm);
        SimpleAsciifier simpleAsciifier = new SimpleAsciifier();
        for (int i = 0; i < fsm.getDictionary().size(); i++){
            TxtWord word = (TxtWord) fsm.getDictionary().getWord(i);
            int count = 0;
            for (int j = 0; j < word.getName().length(); j++){
                switch (word.getName().charAt(j)){
                    case 'ç':
                    case 'ö':
                    case 'ğ':
                    case 'ü':
                    case 'ş':
                    case 'ı':
                        count++;
                }
            }
            if (count > 0 && !word.getName().endsWith("fulü") && (word.isNominal() || word.isAdjective() || word.isAdverb() || word.isVerb())){
                String asciified = simpleAsciifier.asciify(word);
                if (simpleDeasciifier.candidateList(new Word(asciified)).size() == 1){
                    String deasciified = simpleDeasciifier.deasciify(new Sentence(asciified)).toString();
                    assertEquals(word.getName(), deasciified);
                }
            }
        }
    }
}