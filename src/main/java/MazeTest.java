import java.util.Stack;

/**
 * @Author dengxinlong
 * @Date 2019/10/25
 * 使用栈解决迷宫问题
 */
public class MazeTest {
    private class Node{
        int x;
        int y;
        Node(int x,int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node){
                return ((Node) obj).x == x && ((Node) obj).y == y;
            }
            return false;
        }

        @Override
        public String toString() {
            return "["+x+","+y+"]";
        }
    }
    public Stack getPath(int[][] arrary,int startX,int startY,int endX,int endY){
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(startX,startY));
        while (stack.size() > 0){
            Node next;
            Node node = stack.peek();
            if(node.x == endX && node.y == endY){
              return stack;
            }
            int x = node.x + 1;
            int y = node.y;
            if(x < arrary.length && arrary[x][y] == 0){
                next = new Node(x,y);
                stack.push(next);
                arrary[x][y] = 2;
                continue;
            }
            x = node.x - 1;
            y = node.y;
            if(x > 0 && arrary[x][y] == 0){
                next = new Node(x,y);
                stack.push(next);
                arrary[x][y] = 2;
                continue;
            }
            x = node.x;
            y = node.y + 1;
            if(y < arrary[0].length && arrary[x][y] == 0){
                next = new Node(x,y);
                stack.push(next);
                arrary[x][y] = 2;
                continue;
            }
            x = node.x;
            y = node.y - 1;
            if(y > 0 && arrary[x][y] == 0){
                next = new Node(x,y);
                stack.push(next);
                arrary[x][y] = 2;
                continue;
            }
            //没有找到，出栈
            stack.pop();
        }
        return null;
    }

    public static void main(String[] args){
        int[][] maze = new int[][]{
                {1,1,1,1,1,1,1,1,1,1},
                {1,0,0,1,0,0,0,1,0,1},
                {1,0,0,1,0,0,0,1,0,1},
                {1,0,0,0,0,1,1,0,0,1},
                {1,0,1,1,1,0,0,0,0,1},
                {1,0,0,0,1,0,0,0,0,1},
                {1,0,1,0,0,0,1,0,0,1},
                {1,0,1,1,1,0,1,1,0,1},
                {1,1,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1}
        };
        MazeTest mazeTest = new MazeTest();
        Stack<Node> stack = mazeTest.getPath(maze,1,1,8,8);
        if(stack == null){
            System.out.println("没有找到路径");
        }else{
            while (stack.size() > 0){
                System.out.println(stack.pop());
            }
        }
    }
}
