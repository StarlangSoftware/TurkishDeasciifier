package Deasciifier;

import Corpus.Sentence;
import Dictionary.Word;

import static org.junit.Assert.*;

public class SimpleAsciifierTest {
    SimpleAsciifier simpleAsciifier;

    @org.junit.Before
    public void setUp() {
        simpleAsciifier = new SimpleAsciifier();
    }

    @org.junit.Test
    public void testWordAsciify() {
        assertEquals("cogusiCOGUSI", simpleAsciifier.asciify(new Word("çöğüşıÇÖĞÜŞİ")));
        assertEquals("sogus", simpleAsciifier.asciify(new Word("söğüş")));
        assertEquals("uckagitcilik", simpleAsciifier.asciify(new Word("üçkağıtçılık")));
        assertEquals("akiskanlistiricilik", simpleAsciifier.asciify(new Word("akışkanlıştırıcılık")));
        assertEquals("citcitcilik", simpleAsciifier.asciify(new Word("çıtçıtçılık")));
        assertEquals("duskirikligi", simpleAsciifier.asciify(new Word("düşkırıklığı")));
        assertEquals("yuzgorumlugu", simpleAsciifier.asciify(new Word("yüzgörümlüğü")));
    }

    @org.junit.Test
    public void testSentenceAsciify() {
        assertEquals(new Sentence("cogus iii COGUSI").toString(), simpleAsciifier.asciify(new Sentence("çöğüş ııı ÇÖĞÜŞİ")).toString());
        assertEquals(new Sentence("uckagitcilik akiskanlistiricilik").toString(), simpleAsciifier.asciify(new Sentence("üçkağıtçılık akışkanlıştırıcılık")).toString());
        assertEquals(new Sentence("citcitcilik duskirikligi yuzgorumlugu").toString(), simpleAsciifier.asciify(new Sentence("çıtçıtçılık düşkırıklığı yüzgörümlüğü")).toString());
    }
}