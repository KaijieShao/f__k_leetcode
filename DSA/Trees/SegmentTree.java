package DSA.Trees;


public class SegmentTree {

    // Scenario:
    // Prefix Sum: build in O(n), query in O(1) -> Update an element must rebuild all subsequent sums -> O(n)
    // Segment Tree: allows both Range Queries and Updates in O(log n) time -> only update relevant nodes

    int sum;                                                      // Sum of [L, R]
    SegmentTree left;                                             // Pointer to left child (similar to binary tree)
    SegmentTree right;                                            
    int L;                                                        // Left boundary
    int R; 

    public SegmentTree(int total, int L , int R) {
        this.sum = total;
        this.left = null;                                         // Initialize left child to null
        this.right = null;                                        
        this.L = L;                                               // Set left boundary
        this.R = R;                                              
    }


    // O(n) 
    public static SegmentTree build(int[] nums, int L, int R) {
        if (L == R) {
            return new SegmentTree(nums[L], L, R);                // Base case: Set both boundaries at nums[L] 
        }

        int M = (L + R) / 2;                                      // Step 1: Building the segment tree
        SegmentTree root = new SegmentTree(0, L, R);        // i.e., [0, 5]
        root.left = SegmentTree.build(nums, L, M);                //       [0, 2]
        root.right =  SegmentTree.build(nums, M + 1, R);          //       [3, 5]
        // Now, every SegmentTree node will have a range [L, R]

        root.sum = root.left.sum + root.right.sum;                // Step 2: Filling up 'sum' from leaves to root
        return root;                         
    }


    // O(log n)
    public void update(int index, int val) {                      // Update the sum at 'index' to 'val'
        if (this.L == this.R) {
            this.sum = val;
            return;                                               // No further recursion is needed
        }

        int M = (this.L + this.R) / 2;                            // In each recursive call, M is recalculated
        if (index > M) {                                          // Either 'if' or 'else' runs (never both)
            this.right.update(index, val);
        } else {                                                  
            this.left.update(index, val);
        }

        this.sum = this.left.sum + this.right.sum;                // Update current node's sum after a child change
    }


    // O(log n)
    public int rangeQuery(int L, int R) {        
        if (L == this.L && R == this.R) {                         // Return the 'sum' already stored at this node
            return this.sum;
        }

        int M = (this.L + this.R) / 2;
        if (L > M) {
            return this.right.rangeQuery(L, R);                   // If found, return the 'sum' up the call stack
        } else if (R <= M) {
            return this.left.rangeQuery(L, R);
        } else {                                                  // Query spans both sides -> sum both child
            return (this.left.rangeQuery(L, M) + 
                    this.right.rangeQuery(M + 1, R));
        }
    }
}    

