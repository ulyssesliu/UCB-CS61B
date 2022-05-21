package hw2;

import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size;
    private int openBlockNum;
    private int HEAD_SOURCE_INDEX;
    private final boolean[] openBlockList;
    private final WeightedQuickUnionUF unionUF;
    public Percolation(int N) {// create N-by-N grid, with all sites initially blocked
        // the size
        this.size = N;
        // the open block list
        this.openBlockList = new boolean[N*N+1];
        for(int i = 0; i < N*N; i++){
            openBlockList[i] = false;
        }
        openBlockList[N*N] = true; // the head source is set to be open
        openBlockNum = 0;
        // the union find set
        unionUF = new WeightedQuickUnionUF(N*N+1); // 0-N^2-1 is for blocks, N^2 is for head source
        // the head source index
        this.HEAD_SOURCE_INDEX = N*N;
    }
    public void open(int row, int col){ // open the site (row, col) if it is not open already
        if(!openBlockList[cordMap(row, col)]){
            // update the open block list and number
            openBlockList[cordMap(row, col)] = true;
            openBlockNum++;
            connectWithOpenNeighbour(row, col);
        }
    }
    public boolean isOpen(int row, int col){ // is the site (row, col) open?
        return openBlockList[cordMap(row, col)];
    }
    public boolean isFull(int row, int col){ // is the site (row, col) full?
        return unionUF.connected(cordMap(row,col),HEAD_SOURCE_INDEX);
    }
    public int numberOfOpenSites(){ // number of open sites
        return openBlockNum;
    }
    public boolean percolates(){ // TODO: does the system percolate?  It should be constant time
        boolean ans = false;
        for(int col = 0; col < this.size; col++){
            ans = ans || isFull(this.size-1,col);
        }
        return ans;
    }
    private int cordMap(int row, int col){
        return this.size * row + col;
    }
    private void connectWithOpenNeighbour(int row, int col){
        // TODO: judge whether the block is on the boundary
    }
    public static void main(String[] args)   // use for unit testing (not required)


}
