package Deasciifier;

import Corpus.Sentence;
import Dictionary.Word;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;
import MorphologicalAnalysis.FsmParseList;
import Ngram.NGram;

import java.util.ArrayList;

public class NGramDeasciifier extends SimpleDeasciifier{
    private NGram nGram;

    public NGramDeasciifier(FsmMorphologicalAnalyzer fsm, NGram nGram){
        super(fsm);
        this.nGram = nGram;
    }

    public Sentence deasciify(Sentence sentence) {
        Word word, bestRoot;
        Word previousRoot = null, root;
        String bestCandidate;
        double probability, bestProbability;
        ArrayList<String> candidates;
        Sentence result = new Sentence();
        for (int i = 0; i < sentence.wordCount(); i++){
            word = sentence.getWord(i);
            candidates = candidateList(word);
            bestCandidate = null;
            bestRoot = null;
            bestProbability = 0;
            for (String candidate : candidates){
                FsmParseList fsmParseList = fsm.morphologicalAnalysis(candidate);
                root = fsmParseList.getFsmParse(0).getWord();
                if (previousRoot != null){
                    probability = nGram.getProbability(previousRoot, root);
                } else {
                    probability = nGram.getProbability(root);
                }
                if (probability > bestProbability){
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
