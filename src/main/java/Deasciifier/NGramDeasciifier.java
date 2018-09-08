package Deasciifier;

import Corpus.Sentence;
import Dictionary.Word;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;
import MorphologicalAnalysis.FsmParseList;
import Ngram.NGram;

import java.util.ArrayList;

public class NGramDeasciifier extends SimpleDeasciifier {
    private NGram nGram;

    /**
     * A constructor of {@link NGramDeasciifier} class which takes an {@link FsmMorphologicalAnalyzer} and an {@link NGram}
     * as inputs. It first calls it super class {@link SimpleDeasciifier} with given {@link FsmMorphologicalAnalyzer} input
     * then initializes nGram variable with given {@link NGram} input.
     *
     * @param fsm   {@link FsmMorphologicalAnalyzer} type input.
     * @param nGram {@link NGram} type input.
     */
    public NGramDeasciifier(FsmMorphologicalAnalyzer fsm, NGram nGram) {
        super(fsm);
        this.nGram = nGram;
    }

    /**
     * The deasciify method takes a {@link Sentence} as an input. First it creates a String {@link ArrayList} as candidates,
     * and a {@link Sentence} result. Then, loops i times where i ranges from 0 to words size of given sentence. It gets the
     * current word and generates a candidateList with this current word then, it loops through the candidateList. First it
     * calls morphologicalAnalysis method with current candidate and gets the first item as root word. If it is the first root,
     * it gets its N-gram probability, if there are also other roots, it gets probability of these roots and finds out the
     * best candidate, best root and the best probability. At the nd, it adds the bestCandidate to the bestCandidate {@link ArrayList}.
     *
     * @param sentence {@link Sentence} type input.
     * @return Sentence result as output.
     */
    public Sentence deasciify(Sentence sentence) {
        Word word, bestRoot;
        Word previousRoot = null, root;
        String bestCandidate;
        double probability, bestProbability;
        ArrayList<String> candidates;
        Sentence result = new Sentence();
        for (int i = 0; i < sentence.wordCount(); i++) {
            word = sentence.getWord(i);
            candidates = candidateList(word);
            bestCandidate = null;
            bestRoot = null;
            bestProbability = 0;
            for (String candidate : candidates) {
                FsmParseList fsmParseList = fsm.morphologicalAnalysis(candidate);
                root = fsmParseList.getFsmParse(0).getWord();
                if (previousRoot != null) {
                    probability = nGram.getProbability(previousRoot, root);
                } else {
                    probability = nGram.getProbability(root);
                }
                if (probability > bestProbability) {
                    bestCandidate = candidate;
                    bestRoot = root;
                    bestProbability = probability;
                }
            }
            previousRoot = bestRoot;
            result.addWord(new Word(bestCandidate));
        }
        return result;
    }
}
