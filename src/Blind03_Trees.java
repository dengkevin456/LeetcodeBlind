import java.util.*;
public class Blind03_Trees {
    public static void main(String[] args) {

    }

    public TreeNode invertTree(TreeNode root) {
       if (root == null) {
           return null;
       }
       Queue<TreeNode> queue = new LinkedList<>();
       queue.add(root);
       while (!queue.isEmpty()) {
           TreeNode node = queue.poll();
           TreeNode temp = node.left;
           node.left = node.right;
           node.right = temp;
           if (node.left != null) {
               queue.offer(node.left);
           }
           if (node.right != null) {
               queue.offer(node.right);
           }
       }
       return root;
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    private static int diameterOfBinaryTree(TreeNode root) {
        int[] a = new int[1];
        diameterOfBinaryTree_dfs(root, a);
        return a[0];
    }

    private static int diameterOfBinaryTree_dfs(TreeNode root, int[] res) {
        if (root == null) return 0;
        int left = diameterOfBinaryTree_dfs(root.left, res);
        int right = diameterOfBinaryTree_dfs(root.right, res);
        res[0] = Math.max(res[0], left + right);
        return 1 + Math.max(left, right);
    }
}
