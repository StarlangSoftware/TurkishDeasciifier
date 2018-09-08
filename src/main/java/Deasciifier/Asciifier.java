package Deasciifier;

import Corpus.Sentence;

public interface Asciifier {

    /**
     * The asciify method which takes a {@link Sentence} as an input and also returns a {@link Sentence} as the output.
     *
     * @param sentence {@link Sentence} type input.
     * @return Sentence result.
     */
    Sentence asciify(Sentence sentence);
}
