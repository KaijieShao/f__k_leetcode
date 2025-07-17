package CODE.Greedy.M_Hand_of_Straights;

// Given an array of card values (hand) and an integer (groupSize):
// - Can you split all cards into groups of size 'groupSize'
// - Each group must form a consecutive sequence (e.g., [2,3,4,5])
// - Return true if possible, false otherwise.

import java.util.HashMap;
import java.util.Map;

public class Solution {

    // Hash Map

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        // If cards can't be divided evenly into groups, return false immediately.

        Map<Integer, Integer> count = new HashMap<>();
        // Key:   each unique card value in the hand array
        // Value: the number of times (occurrences) that card appears in hand

        for (int num : hand) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        // For each card, increment its count in the map
        
        for (int num : hand) {
        // Go through every card in the hand

            int start = num;
            while (count.getOrDefault(start - 1, 0) > 0) start--;
            // Build a group from the leftmost available card in a sequence 
            // i.e., If your hand has cards [3, 4, 5, 6] and you're at num = 5
            //       Check if 4 exists (count.getOrDefault(4, 0) > 0) → yes, so move to start = 4
            //       Check if 3 exists (count.getOrDefault(3, 0) > 0) → yes, so move to start = 3
            //       Check if 2 exists (count.getOrDefault(2, 0) > 0) → no, stop
            //       Result: 'start' will be set to 3, the leftmost card in this consecutive run.

            while (start <= num) {
            // You want to check every possible starting value from the smallest available (start) up to the current card (num) 
            // Suppose hand = [1,2,3,4,5,6], groupSize = 3 -> Valid groups: [1,2,3] and [4,5,6]
            // - If you only try to form groups starting at num = 6, you miss [1,2,3] and can't use all cards.
            // - Check all 'start' from smallest up to 'num', you guarantee all possible groups formed and all cards considered

                while (count.getOrDefault(start, 0) > 0) {
                // 'count' is a Map storing how many cards you have for each value.
                // .getOrDefault(start, 0) tries to get the value at key 'start'; if 'start' is not in the map, it returns 0.
                // - So, count.getOrDefault(start, 0) gives the number of cards left with value start.
                // - The loop continues while you have at least one card with value start.

                    // Now, we can try to form a group of size 'groupSize' starting at 'start'.

                    for (int i = start; i < start + groupSize; i++) {
                        if (count.getOrDefault(i, 0) == 0) return false;
                        // Verifies if you have at least one card of value i left to use in the current group.
                        // If there are no cards of value i left, you cannot form a valid group starting at start.

                        count.put(i, count.get(i) - 1);
                        // If you do, uses one card of value i by decrementing its count
                    }
                }
                start++;
                // Moves to the next card value to try forming new groups starting from there.
            }
        }
        return true;
    }
}



