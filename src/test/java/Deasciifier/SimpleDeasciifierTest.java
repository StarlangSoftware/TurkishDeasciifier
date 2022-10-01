package Deasciifier;

import Corpus.Sentence;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;
import Ngram.NGram;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleDeasciifierTest {

    FsmMorphologicalAnalyzer fsm;
    NGram<String> nGram;

    @Before
    public void setUp(){
        fsm = new FsmMorphologicalAnalyzer();
    }

    @Test
    public void testDeasciify2() {
        SimpleDeasciifier nGramDeasciifier = new SimpleDeasciifier(fsm);
        assertEquals("hakkında", nGramDeasciifier.deasciify(new Sentence("hakkinda")).toString());
        assertEquals("küçük", nGramDeasciifier.deasciify(new Sentence("kucuk")).toString());
        assertEquals("karşılıklı", nGramDeasciifier.deasciify(new Sentence("karsilikli")).toString());
    }

}