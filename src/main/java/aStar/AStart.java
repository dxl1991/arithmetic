package aStar;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author dengxinlong
 *  @date 2023/6/12 9:52
 *  A星寻路算法
 */
public class AStart {
        public static final int[][] MAZE= {
                {0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0}
        };

        public static Grid aStarSearch(Grid start,Grid end) {
            ArrayList<Grid> openList=new ArrayList<Grid>();
            ArrayList<Grid> closeList=new ArrayList<Grid>();
            //把起点加入openList
            openList.add(start);
            //主循环，每一轮检查1个当前方格节点
            while(openList.size()>0) {
                //在OpenList中查找F值最小节点，将其作为当前方格节点
                Grid currentGrid=findMinGrid(openList);
                //将当前方格从openList中移除
                openList.remove(currentGrid);
                //当前方格节点进入closeList
                closeList.add(currentGrid);
                //找到所有临近节点
                List<Grid> neighbors=findNeighbors(currentGrid,openList,closeList);
                for (Grid grid : neighbors) {
                    if(!openList.contains(grid)) {
                        //邻近节点不在open List中，标记父节点，G，H，F，并放入openList
                        grid.initGrid(currentGrid, end);
                        openList.add(grid);
                    }
                }
                //如果终点在openList中，直接返回终点的格子
                for (Grid grid : openList) {
                    if((grid.x==end.x)&&(grid.y==end.y)) {
                        return grid;
                    }
                }
            }
            //openList用尽，仍找不到终点，说明终点不可达，返回空
            return null;
        }
        private static Grid findMinGrid(ArrayList<Grid> openList) {
            Grid tempGrid=openList.get(0);
            for (Grid grid : openList) {
                if(grid.f<tempGrid.f) {
                    tempGrid=grid;
                }
            }
            return tempGrid;
        }
        private static ArrayList<Grid> findNeighbors(Grid grid,List<Grid> openList,List<Grid> closeList){
            ArrayList<Grid> gridList=new ArrayList<Grid>();
            if(isValidGrid(grid.x,grid.y-1,openList,closeList)) {
                gridList.add(new Grid(grid.x,grid.y-1));
            }
            if(isValidGrid(grid.x,grid.y+1,openList,closeList)) {
                gridList.add(new Grid(grid.x,grid.y+1));
            }
            if(isValidGrid(grid.x-1,grid.y,openList,closeList)) {
                gridList.add(new Grid(grid.x-1,grid.y));
            }
            if(isValidGrid(grid.x+1,grid.y,openList,closeList)) {
                gridList.add(new Grid(grid.x+1,grid.y));
            }
            return gridList;
        }

        private static boolean isValidGrid(int x,int y,List<Grid> openList,List<Grid> closeList) {
            //是否超过边界
            if(x<0||x>=MAZE.length||y<0||y>=MAZE[0].length) {
                return false;
            }
            //是否有障碍物
            if(MAZE[x][y]==1) {
                return false;
            }
            //是否在openList
            if(containGrid(openList, x, y)) {
                return false;
            }
            //是否在closeList
            if(containGrid(closeList, x, y)) {
                return false;
            }
            return true;
        }

        private static boolean containGrid(List<Grid> grids,int x,int y) {
            for (Grid n : grids) {
                if((n.x==x)&&(n.y==y)) {
                    return true;
                }
            }
            return false;
        }
        static class Grid{
            public int x;
            public int y;
            public int f;
            public int g;
            public int h;
            public Grid parent;
            public Grid(int x,int y) {
                this.x=x;
                this.y=y;
            }
            public void initGrid(Grid parent,Grid end) {
                this.parent=parent;
                if(parent!=null) {
                    this.g=parent.g+1;
                }else {
                    this.g=1;
                }
                this.h=Math.abs(this.x-end.x)+Math.abs(this.y-end.y);
                this.f=this.g+this.h;
            }
        }
        public static void main(String[] args) {
            //设置起点和终点
            Grid startGrid=new Grid(2, 1);
            Grid endGrid=new Grid(2, 5);
            //搜索迷宫终点
            Grid resultGrid=aStarSearch(startGrid, endGrid);
            //回溯迷宫路径
            ArrayList<Grid> path=new ArrayList<Grid>();
            while(resultGrid!=null) {
                path.add(new Grid(resultGrid.x,resultGrid.y));
                resultGrid=resultGrid.parent;
            }
            //输出迷宫和路径
            for (int i = 0; i < MAZE.length; i++) {
                for (int j = 0; j < MAZE[0].length; j++) {
                    if(containGrid(path, i, j)) {
                        System.out.print("&,");
                    }else {
                        System.out.print(MAZE[i][j]+", ");
                    }
                }
                System.out.println();
            }
        }
}
