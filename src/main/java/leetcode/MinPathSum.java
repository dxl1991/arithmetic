package leetcode;

/**
 * @author dengxinlong
 * @date 2021/12/15 18:05
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{3,8,6,0,5,9,9,6,3,4,0,5,7,3,9,3},{0,9,2,5,5,4,9,1,4,6,9,5,6,7,3,2},{8,2,2,3,3,3,1,6,9,1,1,6,6,2,1,9},{1,3,6,9,9,5,0,3,4,9,1,0,9,6,2,7},{8,6,2,2,1,3,0,0,7,2,7,5,4,8,4,8},{4,1,9,5,8,9,9,2,0,2,5,1,8,7,0,9},{6,2,1,7,8,1,8,5,5,7,0,2,5,7,2,1},{8,1,7,6,2,8,1,2,2,6,4,0,5,4,1,3},{9,2,1,7,6,1,4,3,8,6,5,5,3,9,7,3},{0,6,0,2,4,3,7,6,1,3,8,6,9,0,0,8},{4,3,7,2,4,3,6,4,0,3,9,5,3,6,9,3},{2,1,8,8,4,5,6,5,8,7,3,7,7,5,8,3},{0,7,6,6,1,2,0,3,5,0,8,0,8,7,4,3},{0,4,3,4,9,0,1,9,7,7,8,6,4,6,9,5},{6,5,1,9,9,2,2,7,4,2,7,2,2,3,7,2},{7,1,9,6,1,2,7,0,9,6,6,4,4,5,1,0},{3,4,9,2,8,3,1,2,6,9,7,0,2,4,2,0},{5,1,8,8,4,6,8,5,2,4,1,6,2,2,9,7}};
        System.out.println(new MinPathSum().minPathSum2(grid));
    }

    public int minPathSum2(int[][] grid) {
        int[][] minPath = new int[grid.length][grid[0].length];
        for(int i=0;i<minPath.length;i++){
            minPath[i][0] = grid[i][0];
            if(i > 0){
                minPath[i][0] += minPath[i-1][0];
            }
        }
        for(int j=0;j<minPath[0].length;j++){
            minPath[0][j] = grid[0][j];
            if(j > 0){
                minPath[0][j] += minPath[0][j - 1];
            }
        }
        for(int i=1;i<minPath.length;i++){
            for(int j=1;j<minPath[0].length;j++){
                minPath[i][j] = Math.min(minPath[i-1][j],minPath[i][j-1]) + grid[i][j];
            }
        }
        return minPath[minPath.length - 1][minPath[0].length - 1];
    }

    public int minPathSum(int[][] grid) {
        dfs(0,0,0,grid);
        return min;
    }
    int min = Integer.MAX_VALUE;
    public void dfs(int value,int i,int j,int[][] grid){
        value += grid[i][j];
        if(value >= min){
            return;
        }
        if(i == grid.length-1 && j == grid[0].length-1){
            min = Math.min(min,value);
            return;
        }
        if(i+1 < grid.length ){
            dfs(value,i+1,j,grid);
        }
        if(j+1 < grid[0].length){
            dfs(value,i,j+1,grid);
        }
    }
}
