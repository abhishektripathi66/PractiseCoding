// problem: https://leetcode.com/problems/word-ladder/

/*
Description:
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 */

// Note:
// Return 0 if there is no such transformation sequence.    

// example 1:
// Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
// Output: 5
// Explanation: As one possible transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", the length is 5.

// dry run: 
// beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
// Step 1: "hit" -> "hot"
// Step 2: "hot" -> "dot"
// Step 3: "dot" -> "dog"
// Step 4: "dog" -> "cog"
// Total steps: 5

package codingquestions.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class WordStep {
    String word;
    int steps;

    WordStep(String word, int steps) {
        this.word = word;
        this.steps = steps;
    }
}

public class WordLadder_I {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);

        // The end word must be present in the word list.
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<WordStep> queue = new LinkedList<>();
        queue.offer(new WordStep(beginWord, 1));

        // Mark the begin word as visited.
        wordSet.remove(beginWord);

        while (!queue.isEmpty()) {
            WordStep current = queue.poll();

            String currentWord = current.word;
            int currentSteps = current.steps;

            if (currentWord.equals(endWord)) {
                return currentSteps;
            }

            char[] characters = currentWord.toCharArray();

            for (int index = 0; index < characters.length; index++) {

                char originalCharacter = characters[index];

                for (char character = 'a'; character <= 'z'; character++) {

                    characters[index] = character;

                    String transformedWord = new String(characters);

                    if (wordSet.contains(transformedWord)) {
                        wordSet.remove(transformedWord);
                        queue.offer(new WordStep(
                                transformedWord,
                                currentSteps + 1));
                    }
                }

                // Restore the original character.
                characters[index] = originalCharacter;
            }
        }

        return 0;
    }
}
