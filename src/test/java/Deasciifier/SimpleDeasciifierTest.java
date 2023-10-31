package Deasciifier;

import Corpus.Sentence;
import Dictionary.Word;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;
import MorphologicalAnalysis.FsmParse;
import MorphologicalAnalysis.FsmParseList;
import Ngram.NGram;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
        SimpleDeasciifier simpleDeasciifier = new SimpleDeasciifier(fsm);
        assertEquals("hakkında", simpleDeasciifier.deasciify(new Sentence("hakkinda")).toString());
        assertEquals("küçük", simpleDeasciifier.deasciify(new Sentence("kucuk")).toString());
        assertEquals("karşılıklı", simpleDeasciifier.deasciify(new Sentence("karsilikli")).toString());
    }

    public void testDistinctWordList(){
        SimpleDeasciifier simpleDeasciifier = new SimpleDeasciifier(fsm);
        try {
            PrintWriter output1 = new PrintWriter("deasciified.txt");
            PrintWriter output2 = new PrintWriter("not-analyzed.txt");
            Scanner input = new Scanner(new File("distinct.txt"));
            while (input.hasNext()){
                String word = input.next();
                Sentence sentence = new Sentence(word);
                Sentence newSentence = simpleDeasciifier.deasciify(sentence);
                if (!sentence.toString().equals(newSentence.toString())){
                    output1.println(word + "\t" + newSentence.toString());
                } else {
                    output2.println(word);
                }
            }
            input.close();
            output1.close();
            output2.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}