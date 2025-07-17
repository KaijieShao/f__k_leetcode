package CODE.Heap.M_Design_Twitter;

// Design and implement a mini version of Twitter with these features:
// - postTweet(userId, tweetId): A user posts a tweet.
// - getNewsFeed(userId): Get the 10 most recent tweets from the user and users they follow.
// - follow(followerId, followeeId): One user follows another.
// - unfollow(followerId, followeeId): One user unfollows another.

import java.util.*;
// Imports all utility classes (HashMap, ArrayList, HashSet, PriorityQueue, etc.)

public class Twitter {

    private int count;                               // Counter for tweets, used to timestamp and order tweets
    private Map<Integer, List<int[]>> tweetMap;      // Maps user IDs to their tweets (timestamp, tweetId)
    private Map<Integer, Set<Integer>> followMap;    // Maps user IDs to the set of users they follow

    public Twitter() {                               // Constructor: initializes all fields
        this.count = 0;
        this.tweetMap = new HashMap<>();
        this.followMap = new HashMap<>();
    }



    public void postTweet(int userId, int tweetId) {               
    // Posts a new tweet for 'userId'

        tweetMap.computeIfAbsent(userId, k -> new ArrayList<>())   
                .add(new int[]{count, tweetId});
        // If user has no 'tweet list', create it. Add new tweet with current count and tweetId

        if (tweetMap.get(userId).size() > 10) {
            tweetMap.get(userId).remove(0);
        }
        // Keep max 10 tweets per user. Remove oldest if more than 10

        count--;
        // Decrement counter so newer tweets have smaller counts (used for sorting)
    }



    public List<Integer> getNewsFeed(int userId) {
    // Returns the 10 most recent tweetIds in user's news feed.

        List<Integer> res = new ArrayList<>();
        // Result list for tweetIds.

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );
        // a[0] and b[0] are timestamps of two tweets -> Sort in ascending order based on timestamps (smaller count comes first)

        followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
        // Ensure user follows themself (so own tweets show up)

        if (followMap.get(userId).size() >= 10) {
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])
            );
            // If user follows 10+ people, use a max-heap to get top 10 most recent tweets

            for (int followeeId : followMap.get(userId)) {
                if (!tweetMap.containsKey(followeeId)) continue;
                // If tweetMap does not contain any tweets for followeeId, it skips processing that followee and checks next one

                List<int[]> tweets = tweetMap.get(followeeId);                           // Retrieves the list of tweets
                int index = tweets.size() - 1;                                           // Gets the index of the latest tweet
                int[] tweet = tweets.get(index);                                         // Gets the latest tweet itself
                maxHeap.offer(new int[]{-tweet[0], tweet[1], followeeId, index - 1});    // reverses the order (min -> max)
                if (maxHeap.size() > 10) {
                    maxHeap.poll();
                }
            }
            // For each followee, add their latest tweet to the heap. Keep heap size ≤ 10

            while (!maxHeap.isEmpty()) {
            // Loops until all elements are removed from maxHeap.

                int[] top = maxHeap.poll();
                // Removes and retrieves the top (most recent) tweet from maxHeap.

                minHeap.offer(new int[]{-top[0], top[1], top[2], top[3]});
                // Adds the tweet to minHeap.
                // -top[0] reverses the earlier negation, restoring the original timestamp value.
                // top[1], top[2], top[3] carry tweetId, followeeId, and next tweet index.
            }
        } else {
            for (int followeeId : followMap.get(userId)) {        // Loops through each followee of the user.
                if (!tweetMap.containsKey(followeeId)) continue;  // Skips followees with no tweets.
                List<int[]> tweets = tweetMap.get(followeeId);    
                int index = tweets.size() - 1;
                int[] tweet = tweets.get(index);                  // Gets their latest tweet.
                minHeap.offer(new int[]{tweet[0], tweet[1], followeeId, index - 1});
                // Inserts the latest tweet into minHeap with:
                // - tweet[0]: timestamp
                // - tweet[1]: tweetId
                // - followeeId: who posted
                // - index - 1: index of the next most recent tweet from this user (for future retrieval)
            }
        }


        while (!minHeap.isEmpty() && res.size() < 10) {
        // Repeatedly extract the most recent tweet until you have 10 or heap is empty.

            int[] top = minHeap.poll();
            // Remove and get the tweet with the smallest timestamp (most recent).

            res.add(top[1]);
            // Add its tweetId to the result list.

            int nextIndex = top[3];
            // Get index of that user's next most recent tweet.

            if (nextIndex >= 0) {
            // If there are more tweets from the same user:

                List<int[]> tweets = tweetMap.get(top[2]);
                // Get all tweets for that user.

                int[] nextTweet = tweets.get(nextIndex);
                // Get the next most recent tweet.

                minHeap.offer(new int[]{nextTweet[0], nextTweet[1], top[2], nextIndex - 1});
                // Adds the next most recent tweet from the same user to the minHeap.
                // - nextTweet[0]: timestamp
                // - nextTweet[1]: tweetId
                // - top[2]: userId (followeeId)
                // - nextIndex - 1: index for the next older tweet (to continue traversing this user's tweets if needed)
            }
        }

        return res;
        // Return the list of tweetIds.
    }



    public void follow(int followerId, int followeeId) {
    // User `followerId` follows `followeeId`

        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        // If no follow list, create one -> Add `followeeId`
    }



    public void unfollow(int followerId, int followeeId) {
    // User `followerId` unfollows `followeeId`

        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
        // Remove `followeeId` if `followerId` is following them.
    }
}

