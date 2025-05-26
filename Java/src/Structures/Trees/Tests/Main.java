package Structures.Trees.Tests;

import Structures.Trees.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> BST = new BinarySearchTree<>();

        BST.insert(7);
        BST.insert(2);
        BST.insert(3);
        BST.insert(1);
        BST.insert(6);
        BST.insert(5);
        BST.insert(10);
        BST.insert(8);
        BST.insert(12);
        BST.insert(9);
        BST.insert(11);
        BST.insert(13);

        BST.recorrer();
    }
}
