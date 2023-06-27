package week3.단어_변환;

import java.util.*;

public class 김유정_단어_변환 {
    public static void main(String[] args) {
        System.out.println("1번 테스트 정답: 4, 결과: " + new 단어_변환_Solution().solution("hit",	"cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println("2번 테스트 정답: 0, 결과: " + new 단어_변환_Solution().solution("hit",	"cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }
}

class 단어_변환_Solution {
    public int solution(String begin, String target, String[] words) {
        List<String> convertibleWordList = getConvertibleWordList(begin, words);
        System.out.println(convertibleWordList.toString());
        Queue<ConvertWord> queue = new LinkedList<>();
        queue.add(new ConvertWord(begin, 0));
        while (!queue.isEmpty()) {
            ConvertWord convertWord = queue.poll();
            System.out.println(convertWord.toString());
            for (String nextWord : getConvertibleWordList(convertWord.getCurrWord(), words)) {
                if (nextWord.equals(target)) {
                    return convertWord.getConvertCnt() + 1;
                }
                queue.add(new ConvertWord(nextWord, convertWord.getConvertCnt() + 1));
            }
            System.out.println("업데이트된 큐: " + queue.toString());
            if (convertWord.getConvertCnt() > words.length) {
                return 0;
            }
        }
        
        return 0;
    }
    
    public List<String> getConvertibleWordList(String word, String[] words) {
        List<String> convertibleWordList = new ArrayList<>();
        for (int idx = 0; idx < words.length; idx++) {
            String compareWord = words[idx];
            if (word.equals(compareWord)) {
                continue;
            }
            int diffCnt = 0;
            for (int j = 0; j < word.length(); j++) {
                if (word.charAt(j) != compareWord.charAt(j)) {
                    diffCnt++;
                }
            }
            if (diffCnt == 1) {
                convertibleWordList.add(compareWord);
            }
        }
        return convertibleWordList;
    }
}

class ConvertWord {
    String currWord;
    int convertCnt;
    
    ConvertWord(String currWord, int convertCnt) {
        this.currWord = currWord;
        this.convertCnt = convertCnt;
    }
    
    String getCurrWord() {
        return currWord;
    }
    int getConvertCnt() {
        return convertCnt;
    }
    @Override
    public String toString() {
        return "Convert{" + currWord + ", " + convertCnt + "}";
    }
}
