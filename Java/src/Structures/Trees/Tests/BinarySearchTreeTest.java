package Structures.Trees.Tests;

import Structures.Trees.BinarySearchTree;
import Structures.Trees.BinarySearchTree.Node;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Insert elements
        bst.insert(20);
        bst.insert(10);
        bst.insert(30);
        bst.insert(5);
        bst.insert(15);
        bst.insert(25);
        bst.insert(35);
        bst.insert(22);
        bst.insert(27);

        System.out.println("InOrder Traversal (should be sorted):");
        bst.inOrderTraversal();

        System.out.println("PreOrder Traversal (root first):");
        bst.preOrderTraversal();

        System.out.println("PostOrder Traversal (root last):");
        bst.postOrderTraversal();

        // Test 1: Remove leaf node
        System.out.println("\nTest 1: Remove leaf node");
        System.out.println("Remove leaf node 5:");
        bst.remove(5);
        bst.inOrderTraversal();

        // Test 2: Remove node with one child
        System.out.println("\nTest 2: Remove node with one child");
        System.out.println("Remove node 25 (has one child 27):");
        bst.remove(25);
        bst.inOrderTraversal();

        // Test 3: Remove node with two children
        System.out.println("\nTest 3: Remove node with two children");
        System.out.println("Remove node 30 (has two children 27, 35):");
        bst.remove(30);
        bst.inOrderTraversal();

        // Test 4: Remove root node
        System.out.println("\nTest 4: remove root node");
        System.out.println("Remove root node 20:");
        bst.remove(20);
        bst.inOrderTraversal();

        // Test 5: Parent pointers
        System.out.println("\nTest 5: Parent pointers");
        Node<Integer> node15 = bst.fetch(15);
        if (node15 != null && node15.parent != null) {
            System.out.println("Parent of 15: " + node15.parent.data);
        } else {
            System.out.println("Parent pointer test failed for 15");
        }

        // Test 6: fetch
        System.out.println("\nTest 6: fetch");
        System.out.println("Fetch 27: " + (bst.fetch(27) != null ? "Found" : "Not Found"));
        System.out.println("Fetch 100: " + (bst.fetch(100) != null ? "Found" : "Not Found"));

        // Test 7: range search
        System.out.println("\nTest 7: range search");
        System.out.println("Range search 10 to 27:");
        for (Node<Integer> n : bst.rangeSearch(10, 27)) {
            System.out.print(n.data + " ");
        }
        System.out.println();

        //Test 7.1: next
        System.out.println("\nTest 7.1: next of all nodes");
        System.out.println("Next of 10: " + (bst.next(bst.fetch(10)) != null ? bst.next(bst.fetch(10)).data : "null"));
        System.out.println("Next of 15: " + (bst.next(bst.fetch(15)) != null ? bst.next(bst.fetch(15)).data : "null"));
        System.out.println("Next of 22: " + (bst.next(bst.fetch(22)) != null ? bst.next(bst.fetch(22)).data : "null"));
        System.out.println("Next of 27: " + (bst.next(bst.fetch(27)) != null ? bst.next(bst.fetch(27)).data : "null"));
        System.out.println("Next of 35: " + (bst.next(bst.fetch(35)) != null ? bst.next(bst.fetch(35)).data : "null"));
        System.out.println();

        //Test 7.2: prev
        System.out.println("\nTest 7.2: prevs of all nodes");
        System.out.println("Prev of 10: " + (bst.prev(bst.fetch(10)) != null ? bst.prev(bst.fetch(10)).data : "null"));
        System.out.println("Prev of 15: " + (bst.prev(bst.fetch(15)) != null ? bst.prev(bst.fetch(15)).data : "null"));
        System.out.println("Prev of 22: " + (bst.prev(bst.fetch(22)) != null ? bst.prev(bst.fetch(22)).data : "null"));
        System.out.println("Prev of 27: " + (bst.prev(bst.fetch(27)) != null ? bst.prev(bst.fetch(27)).data : "null"));
        System.out.println("Prev of 35: " + (bst.prev(bst.fetch(35)) != null ? bst.prev(bst.fetch(35)).data : "null"));
        System.out.println();

        bst = new BinarySearchTree<>();
        // Test 8: Remove root node with two children
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        System.out.println("\nTest 8: Remove root node (10) with two children");
        bst.remove(10);
        System.out.println("New root: " + (bst.getRoot() != null ? bst.getRoot().data : "null"));
        System.out.println("New root's parent: " + (bst.getRoot() != null && bst.getRoot().parent != null ? bst.getRoot().parent.data : "null"));
        bst.inOrderTraversal();

        // Test 9: Remove node with one child and check parent pointer
        bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(12);
        System.out.println("\nTest 9: Remove node (15) with one child (12)");
        bst.remove(15);
        Node<Integer> node12 = bst.fetch(12);
        System.out.println("Parent of 12: " + (node12 != null && node12.parent != null ? node12.parent.data : "null"));
        bst.inOrderTraversal();

        // Test 10: Remove leaf node and check parent pointer
        System.out.println("\nTest 10: Remove leaf node (5)");
        bst.remove(5);
        Node<Integer> node10 = bst.fetch(10);
        System.out.println("Left child of 10: " + (node10 != null && node10.left != null ? node10.left.data : "null"));
        bst.inOrderTraversal();

        // Test 11: Remove all nodes one by one and check root and parent
        System.out.println("\nTest 11: Remove all nodes one by one");
        bst.remove(12);
        bst.remove(10);
        System.out.println("Root after all removals: " + (bst.getRoot() != null ? bst.getRoot().data : "null"));

        // Test 12: Insert after removals and check parent pointer
        System.out.println("\nTest 12: Insert after removals");
        bst.insert(100);
        System.out.println("Root: " + (bst.getRoot() != null ? bst.getRoot().data : "null"));
        System.out.println("Root's parent: " + (bst.getRoot() != null && bst.getRoot().parent != null ? bst.getRoot().parent.data : "null"));

        // Test 13: Insert duplicate values
        bst = new BinarySearchTree<>();
        System.out.println("\nTest 13: Insert duplicate values");
        bst.insert(10);
        bst.insert(10); // Should not insert, should print warning
        bst.inOrderTraversal();

        // Test 14: Remove from empty tree
        System.out.println("\nTest 14: Remove from empty tree");
        BinarySearchTree<Integer> emptyBst = new BinarySearchTree<>();
        emptyBst.remove(42); // Should not throw exception
        emptyBst.inOrderTraversal();

        // Test 15: Remove non-existent value
        System.out.println("\nTest 15: Remove non-existent value");
        bst.remove(999); // Should not throw exception
        bst.inOrderTraversal();

        // Test 16: Deep left-skewed tree (degenerate case)
        System.out.println("\nTest 16: Deep left-skewed tree");
        BinarySearchTree<Integer> leftSkewed = new BinarySearchTree<>();
        for (int i = 10; i > 0; i--) leftSkewed.insert(i);
        leftSkewed.inOrderTraversal();
        // Remove all nodes from bottom up
        for (int i = 1; i <= 10; i++) leftSkewed.remove(i);
        leftSkewed.inOrderTraversal();
        System.out.println("Root after all removals: " + (leftSkewed.getRoot() != null ? leftSkewed.getRoot().data : "null"));

        // Test 17: Deep right-skewed tree (degenerate case)
        System.out.println("\nTest 17: Deep right-skewed tree");
        BinarySearchTree<Integer> rightSkewed = new BinarySearchTree<>();
        for (int i = 1; i <= 10; i++) rightSkewed.insert(i);
        rightSkewed.inOrderTraversal();
        // Remove all nodes from bottom up
        for (int i = 10; i >= 1; i--) rightSkewed.remove(i);
        rightSkewed.inOrderTraversal();
        System.out.println("Root after all removals: " + (rightSkewed.getRoot() != null ? rightSkewed.getRoot().data : "null"));
    }
}
