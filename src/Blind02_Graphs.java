import java.util.*;
public class Blind02_Graphs {
    public static void main(String[] args) {
        int[][] grid = {
                {}
        };
        System.out.println(maxAreaOfIsland(grid));
    }

    private static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    numIslands_bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void numIslands_bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = '0';
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] d: dirs) {
                int ni = curr[0] + d[0], nj = curr[1] + d[1];
                if (ni >= 0 && nj >= 0 && ni < grid.length && nj < grid[0].length &&
                    grid[ni][nj] == '1') {
                    queue.offer(new int[]{ni, nj});
                    grid[ni][nj] = '0';
                }
            }
        }
    }


    private static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, maxAreaOfIsland_dfs(grid, i, j));
                }

            }
        }
        return maxArea;
    }

    private static int maxAreaOfIsland_dfs(int[][] grid, int i, int j) {
        Stack<int[]> stack = new Stack<>();
        grid[i][j] = 0;
        int count = 1;
        stack.push(new int[]{i, j});
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            for (int[] d: dirs) {
                int ni = curr[0] + d[0], nj = curr[1] + d[1];
                if (ni >= 0 && nj >= 0 && ni < grid.length && nj < grid[0].length
                 && grid[ni][nj] == 1) {
                    count++;
                    grid[ni][nj] = 0;
                    stack.push(new int[]{ni, nj});
                }
            }
        }
        return count;
    }

}
