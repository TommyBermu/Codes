package Structures.DisjointSet;

public class DisjointSet {
    private int[] parent;
    private int[] rank;

    public DisjointSet(int size) {
        this.parent = new int[size];
        this.rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int i) { // find con path comrpession
        if (parent[i] != i)
            parent[i] = find(parent[i]);
        return parent[i];
    }

    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot)
            return;

        if (rank[xRoot] < rank[yRoot])
            parent[xRoot] = parent[yRoot];
        else if (rank[xRoot] > rank[yRoot])
            parent[yRoot] = parent[xRoot];
        else {
            parent[yRoot] = parent[xRoot];
            rank[xRoot]++;
        }
    }
}
