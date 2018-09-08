package Deasciifier;

import Corpus.Sentence;

public interface Deasciifier {

    /**
     * The deasciify method which takes a {@link Sentence} as an input and also returns a {@link Sentence} as the output.
     *
     * @param sentence {@link Sentence} type input.
     * @return Sentence result.
     */
    Sentence deasciify(Sentence sentence);
}
