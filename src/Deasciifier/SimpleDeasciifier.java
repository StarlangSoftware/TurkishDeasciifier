package Deasciifier;

import Corpus.Sentence;
import Dictionary.Word;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;
import MorphologicalAnalysis.FsmParse;
import MorphologicalAnalysis.FsmParseList;

import java.util.ArrayList;
import java.util.Random;

public class SimpleDeasciifier implements Deasciifier{
    protected FsmMorphologicalAnalyzer fsm;

    private void generateCandidateList(ArrayList<String> candidates, String word, int index){
        String s = "iougcsIOUGCS";
        if (index < word.length()){
            if (s.indexOf(word.charAt(index)) != -1){
                int size = candidates.size();
                for (int i = 0; i < size; i++){
                    char[] modified = candidates.get(i).toCharArray();
                    switch (word.charAt(index)){
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
            generateCandidateList(candidates, word, index + 1);
        }
    }

    protected ArrayList<String> candidateList(Word word){
        ArrayList<String> candidates;
        candidates = new ArrayList<String>();
        candidates.add(word.getName());
        generateCandidateList(candidates, word.getName(), 0);
        for (int i = 0; i < candidates.size(); i++){
            FsmParseList fsmParseList = fsm.morphologicalAnalysis(candidates.get(i));
            if (fsmParseList.size() == 0){
                candidates.remove(i);
                i--;
            }
        }
        return candidates;
    }

    public SimpleDeasciifier(FsmMorphologicalAnalyzer fsm){
        this.fsm = fsm;
    }

    public Sentence deasciify(Sentence sentence) {
        Word word, newWord;
        int randomCandidate;
        Random random = new Random();
        ArrayList<String> candidates;
        Sentence result = new Sentence();
        for (int i = 0; i < sentence.wordCount(); i++){
            word = sentence.getWord(i);
            candidates = candidateList(word);
            if (candidates.size() > 0){
                randomCandidate = random.nextInt(candidates.size());
                newWord = new Word(candidates.get(randomCandidate));
            } else {
                newWord = word;
            }
            result.addWord(newWord);
        }
        return result;
    }
}
