package CODE.Heap.M_Task_Scheduler;

// It asks for the minimum total time (CPU cycles) needed to finish all tasks, given:
// - Each identical task must be separated by at least n cycles (cooldown)
// Example: If tasks = ["A","A","A","B","c"], n = 3
// - You can do: A -> B -> C -> Idle -> A -> Idle -> Idle -> Idle -> A

import java.util.Arrays;

public class Solution {

    // Greedy
    // Greedy algorithm: Always picks the best option available at each step, without worrying about future choices. 
    //                   It makes a "locally optimal" choice hoping for the best global result.

    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];      // Array to count frequency of each task (A-Z)
        for (char task : tasks) {       // Count occurrences of each task
            count[task - 'A']++;        // Map 'A' to 0, 'B' to 1, ..., 'Z' to 25
        }

        Arrays.sort(count);             // Sorts the 'count' array in ascending order -> 'count[0]' is the smallest frequency
        int maxf = count[25];           // 'count[25]' is the largest (most frequent task)
        int idle = (maxf - 1) * n;      // Calculates the initial # of idle slots needed to separate the most frequent tasks
        // -1 -> Example: For AAA (3 times), you have two gaps between them: A _ A _ A

        for (int i = 24; i >= 0; i--) {
            idle -= Math.min(maxf - 1, count[i]);
        }
        // Math.min(maxf-1, count[i]) means: in each gap, put one of this task, but don't exceed how many you have.
        // Subtract that from the idle slot count, because these tasks "fill in" those idle spots instead of leaving them empty
        // This is greedy: always fill the next available idle slot with any available task immediately

        return Math.max(0, idle) + tasks.length;
        // If you still have idle slots left after filling with all possible tasks, you must actually spend those idle cycles
        // So the total cycles is all tasks (tasks.length) plus any remaining idle slots (if any)
    }
}

