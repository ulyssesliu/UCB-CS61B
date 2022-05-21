package hw2;

//import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size;
    private int openBlockNum;
    private final int HEAD_SOURCE_INDEX;
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
        if(!isOpen(row, col)){
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
    private void checkAndConnect(int row1, int col1, int row2, int col2){
        if(isOpen(row2, col2)){
            unionUF.union(cordMap(row1, col1),cordMap(row2, col2));
        }
    }
    private void connectWithOpenNeighbour(int row, int col){
        if(row == 0){ // upper boundary
            if(col == 0){ // upper left corner
                unionUF.union(cordMap(row, col), HEAD_SOURCE_INDEX);
                checkAndConnect(row, col, row, col+1);
                checkAndConnect(row, col, row+1, col);
            } else if (col == this.size-1){ // upper right corner
                unionUF.union(cordMap(row, col), HEAD_SOURCE_INDEX);
                checkAndConnect(row, col, row, col-1);
                checkAndConnect(row, col, row+1, col);
            } else{ // in middle in row
                unionUF.union(cordMap(row, col), HEAD_SOURCE_INDEX);
                checkAndConnect(row, col, row, col-1);
                checkAndConnect(row, col, row+1, col);
                checkAndConnect(row, col, row, col+1);
            }
        } else if(row == this.size-1){ // lower boundary
            if(col == 0){ // lower left corner
                checkAndConnect(row, col, row, col+1);
                checkAndConnect(row, col, row-1,col);
            } else if (col == this.size-1){ // lower right corner
                checkAndConnect(row, col, row, col-1);
                checkAndConnect(row, col, row-1,col);
            } else{ // in middle in row
                checkAndConnect(row, col, row, col-1);
                checkAndConnect(row, col, row-1, col);
                checkAndConnect(row, col, row, col+1);
            }
        } else if(col == 0){ // in middle of left column
            checkAndConnect(row, col, row-1,col);
            checkAndConnect(row, col, row+1,col);
            checkAndConnect(row, col, row,col+1);

        } else if(col == this.size-1){ // in middle of right column
            checkAndConnect(row, col, row-1,col);
            checkAndConnect(row, col, row+1,col);
            checkAndConnect(row, col, row,col-1);
        } else{ // when the block is not on the boundary
            checkAndConnect(row, col, row-1,col);
            checkAndConnect(row, col, row+1,col);
            checkAndConnect(row, col, row,col-1);
            checkAndConnect(row, col, row, col+1);
        }
    }
    public static void main(String[] args) { // use for unit testing (not required)

    }


}
