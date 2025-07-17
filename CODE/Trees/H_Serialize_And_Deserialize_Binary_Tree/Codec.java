package CODE.Trees.H_Serialize_And_Deserialize_Binary_Tree;

// Implement two functions for a binary tree:

// 1. serialize: Convert a binary tree into a string (so it can be stored/transmitted).
// 2. deserialize: Convert that string back into the original binary tree structure.

import java.util.List;
import java.util.ArrayList;

public class Codec {

    // Depth First Search
    
    // Encodes a tree to a single string -> preorder: node, left, right
    public String serialize(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfsSerialize(root, res);
        // Visits each node in preorder and adds N for nulls, appends these tokens to res

        return String.join(",", res);
        // Takes a list of tokens and concatenates them with commas into a single 'String' 
    }

    private void dfsSerialize(TreeNode node, List<String> res) {
    // node: the current TreeNode you are visiting in the tree (starts with the root).
    // res: the list that accumulates the serialized string tokens (String values).
        
        if (node == null) {
            res.add("N");
            return;
        }
        res.add(String.valueOf(node.val));
        // Adds the current node's value as a string to the result list.

        dfsSerialize(node.left, res);
        dfsSerialize(node.right, res);
        // Recursively visit the left and right child nodes.
        // For each node visited, its value (or "N" if null) is added to res 
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        // Splits the serialized string data into an array of node values/tokens using commas as separators

        int[] i = {0};
        // Creates a mutable integer array to keep track of the current index during recursion (no primitive int)

        return dfsDeserialize(vals, i);
        // Calls the recursive function to rebuild the binary tree from vals, starting at index 0
    }

    private TreeNode dfsDeserialize(String[] vals, int[] i) {
        if (vals[i[0]].equals("N")) {
            i[0]++;          // i[0]++ advances the index to the next token
            return null;     // return null; returns null for this tree node (no child here)
        }
        // Checks if the current token is "N" (representing a null node in the serialized data)

        TreeNode node = new TreeNode(Integer.parseInt(vals[i[0]]));
        // Creates a new TreeNode using the integer value at the current position (i[0]) in the vals array
        // Integer.parseInt(...) converts the string value to an integer for the node

        i[0]++;
        // Moves to the next token in vals (needed before processing children)

        node.left = dfsDeserialize(vals, i);
        // Recursively deserializes the left subtree from the next token

        node.right = dfsDeserialize(vals, i);

        return node;
    }
}


