package Deasciifier;

import Corpus.Sentence;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;
import Ngram.LaplaceSmoothing;
import Ngram.NGram;
import Ngram.NoSmoothing;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NGramDeasciifierTest {

    FsmMorphologicalAnalyzer fsm;
    NGram<String> nGram;

    @Before
    public void setUp(){
        fsm = new FsmMorphologicalAnalyzer();
        nGram = new NGram<String>("ngram.txt");
        nGram.calculateNGramProbabilities(new NoSmoothing<>());
    }

    @Test
    public void testDeasciify2() {
        NGramDeasciifier nGramDeasciifier = new NGramDeasciifier(fsm, nGram, false);
        assertEquals("noter hakkında", nGramDeasciifier.deasciify(new Sentence("noter hakkinda")).toString());
        assertEquals("sandık medrese", nGramDeasciifier.deasciify(new Sentence("sandik medrese")).toString());
        assertEquals("kuran'ı karşılıklı", nGramDeasciifier.deasciify(new Sentence("kuran'ı karsilikli")).toString());
    }

    @Test
    public void testDeasciify3() {
        nGram.calculateNGramProbabilities(new LaplaceSmoothing<>());
        NGramDeasciifier nGramDeasciifier = new NGramDeasciifier(fsm, nGram, true);
        assertEquals("dün akşam yeni aldığımız çam ağacını süsledik",
                nGramDeasciifier.deasciify(new Sentence("dün aksam yenı aldıgımız cam ağacını susledık")).toString());
        assertEquals("ünlü sanatçı tartışmalı konu hakkında demeç vermekten kaçındı",
                nGramDeasciifier.deasciify(new Sentence("unlu sanatçı tartismali konu hakkinda demec vermekten kacindi")).toString());
        assertEquals("köylü de durumdan oldukça şikayetçiydi",
                nGramDeasciifier.deasciify(new Sentence("koylu de durumdan oldukca şikayetciydi")).toString());
    }
}