package Deasciifier;

import Corpus.Sentence;
import Dictionary.Word;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;
import MorphologicalAnalysis.FsmParseList;

import java.util.ArrayList;
import java.util.Random;

public class SimpleDeasciifier implements Deasciifier {
    protected FsmMorphologicalAnalyzer fsm;

    /**
     * The generateCandidateList method takes an {@link ArrayList} candidates, a {@link String}, and an integer index as inputs.
     * First, it creates a {@link String} which consists of corresponding Latin versions of special Turkish characters. If given index
     * is less than the length of given word and if the item of word's at given index is one of the chars of {@link String}, it loops
     * given candidates {@link ArrayList}'s size times and substitutes Latin characters with their corresponding Turkish versions
     * and put them to newly created char {@link java.lang.reflect.Array} modified. At the end, it adds each modified item to the candidates
     * {@link ArrayList} as a {@link String} and recursively calls generateCandidateList with next index.
     *
     * @param candidates {@link ArrayList} type input.
     * @param word       {@link String} input.
     * @param index      {@link Integer} input.
     */
    private void generateCandidateList(ArrayList<String> candidates, String word, int index) {
        String s = "ıiougcsİIOUGCS";
        if (index < word.length()) {
            if (s.indexOf(word.charAt(index)) != -1) {
                int size = candidates.size();
                for (int i = 0; i < size; i++) {
                    char[] modified = candidates.get(i).toCharArray();
                    switch (word.charAt(index)) {
                        case 'ı':
                            modified[index] = 'i';
                            break;
                        case 'i':
                            modified[index] = 'ı';
                            break;
                        case 'o':
                            modified[index] = 'ö';
                            break;
                        case 'u':
                            modified[index] = 'ü';
                            break;
                        case 'g':
                            modified[index] = 'ğ';
                            break;
                        case 'c':
                            modified[index] = 'ç';
                            break;
                        case 's':
                            modified[index] = 'ş';
                            break;
                        case 'I':
                            modified[index] = 'İ';
                            break;
                        case 'İ':
                            modified[index] = 'I';
                            break;
                        case 'O':
                            modified[index] = 'Ö';
                            break;
                        case 'U':
                            modified[index] = 'Ü';
                            break;
                        case 'G':
                            modified[index] = 'Ğ';
                            break;
                        case 'C':
                            modified[index] = 'Ç';
                            break;
                        case 'S':
                            modified[index] = 'Ş';
                            break;
                    }
                    candidates.add(new String(modified));
                }
            }
            if (candidates.size() < 10000){
                generateCandidateList(candidates, word, index + 1);
            }
        }
    }

    /**
     * The candidateList method takes a {@link Word} as an input and creates new candidates {@link ArrayList}. First it
     * adds given word to this {@link ArrayList} and calls generateCandidateList method with candidates, given word and
     * index 0. Then, loops i times where i ranges from 0 to size of candidates {@link ArrayList} and calls morphologicalAnalysis
     * method with ith item of candidates {@link ArrayList}. If it does not return any analysis for given item, it removes
     * the item from candidates {@link ArrayList}.
     *
     * @param word {@link Word} type input.
     * @return ArrayList candidates.
     */
    public ArrayList<String> candidateList(Word word) {
        ArrayList<String> candidates;
        candidates = new ArrayList<>();
        candidates.add(word.getName());
        generateCandidateList(candidates, word.getName(), 0);
        for (int i = 0; i < candidates.size(); i++) {
            FsmParseList fsmParseList = fsm.morphologicalAnalysis(candidates.get(i));
            if (fsmParseList.size() == 0) {
                candidates.remove(i);
                i--;
            }
        }
        return candidates;
    }

    /**
     * A constructor of {@link SimpleDeasciifier} class which takes a {@link FsmMorphologicalAnalyzer} as an input and
     * initializes fsm variable with given {@link FsmMorphologicalAnalyzer} input.
     *
     * @param fsm {@link FsmMorphologicalAnalyzer} type input.
     */
    public SimpleDeasciifier(FsmMorphologicalAnalyzer fsm) {
        this.fsm = fsm;
    }

    /**
     * The deasciify method takes a {@link Sentence} as an input and loops i times where i ranges from 0 to number of
     * words in the given {@link Sentence}. First it gets ith word from given {@link Sentence} and calls candidateList with
     * ith word and assigns the returned {@link ArrayList} to the newly created candidates {@link ArrayList}. And if the size of
     * candidates {@link ArrayList} is greater than 0, it generates a random number and gets the item of candidates {@link ArrayList}
     * at the index of random number and assigns it as a newWord. If the size of candidates {@link ArrayList} is 0, it then
     * directly assigns ith word as the newWord. At the end, it adds newWord to the result {@link Sentence}.
     *
     * @param sentence {@link Sentence} type input.
     * @return result {@link Sentence}.
     */
    public Sentence deasciify(Sentence sentence) {
        Word word, newWord;
        int randomCandidate;
        Random random = new Random();
        ArrayList<String> candidates;
        Sentence result = new Sentence();
        for (int i = 0; i < sentence.wordCount(); i++) {
            word = sentence.getWord(i);
            FsmParseList fsmParseList = fsm.morphologicalAnalysis(word.getName());
            if (fsmParseList.size() == 0){
                candidates = candidateList(word);
                if (!candidates.isEmpty()) {
                    randomCandidate = random.nextInt(candidates.size());
                    newWord = new Word(candidates.get(randomCandidate));
                } else {
                    newWord = word;
                }
            } else {
                newWord = word;
            }
            result.addWord(newWord);
        }
        return result;
    }
}
