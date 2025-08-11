import java.util.*;
public class Blind03_Trees {
    public static void main(String[] args) {
        TreeNode node = buildTree(new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7});
        levelOrderCheck(node);
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

    private static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
            }
            level++;
        }
        return level;
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

    // Is balanced binary tree
    private static boolean isBalanced(TreeNode root) {
        return isBalanced_dfs(root)[0] == 1;
    }
    // Array: {0 if tree is balanced 1 otherwise, height of tree}
    private static int[] isBalanced_dfs(TreeNode node) {
       if (node == null) return new int[]{1, 0};
       int[] left = isBalanced_dfs(node.left);
       int[] right = isBalanced_dfs(node.right);
       boolean balanced = (left[0] == 1 && right[0] == 1 && Math.abs(left[1] - right[1]) <= 1);
       return new int[]{balanced ? 1 : 0, 1 + Math.max(left[1], right[1])};
    }

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(p);
        q2.offer(q);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode node1 = q1.poll(), node2 = q2.poll();
            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null) return false;
            if (node1.val != node2.val) return false;
            q1.offer(node1.left);
            q1.offer(node1.right);
            q2.offer(node2.left);
            q2.offer(node2.right);
        }
        return q1.isEmpty() && q2.isEmpty();
    }

    private static boolean isSubTree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubTree(root.left, subRoot) || isSubTree(root.right, subRoot);
    }

    // Lowest common ancestor of a binary search tree
    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while (cur != null) {
            if (p.val < cur.val && q.val < cur.val) cur = cur.left;
            else if (p.val > cur.val && q.val > cur.val) cur = cur.right;
            else return cur;
        }
        return null;
    }

    // Level order traversal of a binary tree
    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int cnt = q.size();
            for (int i = 0; i < cnt; i++) {
                TreeNode node = q.poll();
                if (node != null) {
                    level.add(node.val);
                    if (node.left != null) q.add(node.left);
                    if (node.right != null) q.add(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    private static List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                    if (i == cnt - 1) list.add(node.val);
                }
            }
        }
        return list;
    }

    static class TreeNodeData {
        TreeNode node;
        int ans = 0;
        public TreeNodeData(TreeNode node, int ans) {
            this.node = node;
            this.ans = ans;
        }
    }

    private static int goodNodes(TreeNode root) {
        Queue<TreeNodeData> queue = new LinkedList<>();
        int ans = 0;
        queue.offer(new TreeNodeData(root, Integer.MIN_VALUE));
        while (!queue.isEmpty()) {
            TreeNodeData data = queue.poll();
            TreeNode node = data.node;
            int maxVal = data.ans;
            if (data.node.val >= maxVal) ans++;
            if (node.left != null) {
                queue.offer(new TreeNodeData(node.left, Math.max(node.val, maxVal)));
            }
            if (node.right != null) {
                queue.offer(new TreeNodeData(node.right, Math.max(node.val, maxVal)));
            }
        }
        return ans;
    }

    private static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode root, long left, long right) {
        if (root == null) return true;
        if (!(left < root.val && root.val < right)) return false;
        return isValidBST(root.left, left, root.val) && isValidBST(root.right, root.val, right);
    }

    private static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            k--;
            if (k == 0) return node.val;
            node = node.right;
        }
        return -1;
    }

    private static void levelOrderCheck(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        int[] preidx = {0};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree_dfs(preorder, 0, inorder.length - 1, preidx, map);
    }
    private static TreeNode buildTree_dfs(int[] preorder, int l, int r, int[] preidx, Map<Integer, Integer> map) {
        if (l > r) return null;
        TreeNode root = new TreeNode(preorder[preidx[0]++]);
        int mid = map.get(root.val);
        root.left = buildTree_dfs(preorder, l, mid - 1, preidx, map);
        root.right = buildTree_dfs(preorder, mid + 1, r, preidx, map);
        return root;
    }

    private static int maxPathSum(TreeNode root) {
        int[] res = new int[root.val];
        return maxPathSumDFS(root, res);
    }

    private static int maxPathSumDFS(TreeNode root, int[] res) {
        if (root == null) return 0;
        // Get maximum path sum without the split
        int leftMax = Math.max(maxPathSumDFS(root.left, res), 0);
        int rightMax = Math.max(maxPathSumDFS(root.right, res), 0);
        // Max path with split
        res[0] = Math.max(res[0], root.val + leftMax + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }
}
