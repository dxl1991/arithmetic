/**
 * @Author dengxinlong
 * @Date 2019/10/29
 * 二叉查找树：左孩子比自己小，右孩子比自己大
 * 平衡二叉树(AVL)：首先是一棵二叉查找树，但是它满足一点重要的特性：每一个结点的左子树和右子树的高度差最多为1
 */
public class BST {
    private Node root;

    public BST(int[] array){
        if(array != null){
            for(int val : array){
                insert(val);
            }
        }
    }
    //递归插入
    public Node insert(Node node,int val){
        if(node == null){
            node = new Node(val);
        }else if(val < node.val){
            node.lChild = insert(node.lChild,val);
            node.lChild.parent = node;
        }else if(val > node.val){
            node.rChild = insert(node.rChild,val);
            node.rChild.parent = node;
        }
        return node;
    }
    //插入
    public Node insert(int val){
        if(root == null){
            root = new Node(val);
            return root;
        }
        Node p = root;
        while (true){
            if(val < p.val){
                if(p.lChild == null){
                    p.lChild = new Node(val);
                    p.lChild.parent = p;
                    return p.lChild;
                }else{
                    p = p.lChild;
                }
            }else if(val > p.val){
                if(p.rChild == null){
                    p.rChild = new Node(val);
                    p.rChild.parent = p;
                    return p.rChild;
                }else{
                    p = p.rChild;
                }
            }else{
                return p;
            }
        }
    }
    //查找
    public Node query(int val){
        Node node = root;
        while (node != null){
            if(node.val == val){
                return node;
            }else if(node.val < val){
                node = node.rChild;
            }else{
                node = node.lChild;
            }
        }
        return null;
    }
    //递归查找
    public Node query(Node node,int val){
        if(node == null){
            return null;
        }
        if(node.val == val){
            return node;
        }else if(node.val < val){
            return query(node.rChild,val);
        }else{
            return query(node.lChild,val);
        }
    }
    //删除
    public Node delete(int val){
        Node node = query(val);
        if(node == null){
            return null;
        }
        //该节点没有子节点
        if(node.lChild == null && node.rChild == null){
            if(node.parent != null){
                if(node.parent.lChild == node){
                    node.parent.lChild = null;
                }else{
                    node.parent.rChild = null;
                }
            }else{
                root = null;
            }
            return node;
        }
        //该节点只有右孩子
        if(node.lChild == null && node.rChild != null){
            if(node.parent != null){
                if(node.parent.lChild == node){
                    node.parent.lChild = node.rChild;
                }else{
                    node.parent.rChild = node.rChild;
                }
            }else{
                root = node.rChild;
            }
            node.rChild.parent = node.parent;
            return node;
        }
        //该节点只有左孩子
        if(node.lChild != null && node.rChild == null){
            if(node.parent != null){
                if(node.parent.lChild == node){
                    node.parent.lChild = node.lChild;
                }else{
                    node.parent.rChild = node.lChild;
                }
            }else{
                root = node.lChild;
            }
            node.lChild.parent = node.parent;
            return node;
        }
        //该节点有两个孩子：把右边最小的节点（右孩子的最左边的节点）替换自己
        Node temp = node.rChild;
        while (temp.lChild != null){
            temp = temp.lChild;
        }
        delete(temp.val);
        if(node.parent != null){
            if(node.parent.lChild == node){
                node.parent.lChild = temp;
            }else{
                node.parent.rChild = temp;
            }
        }else{
            root = temp;
        }
        temp.parent = node.parent;
        temp.lChild = node.lChild;
        temp.rChild = node.rChild;
        return node;
    }

    //前序遍历
    public void pre_order(Node root){
        if(root != null){
            System.out.print(root.val+",");
            pre_order(root.lChild);
            pre_order(root.rChild);
        }
    }
    //中序遍历
    public void in_order(Node root){
        if(root != null){
            in_order(root.lChild);
            System.out.print(root.val+",");
            in_order(root.rChild);
        }
    }
    //后序遍历
    public void post_order(Node root){
        if(root != null){
            post_order(root.lChild);
            post_order(root.rChild);
            System.out.print(root.val+",");
        }
    }

    private class Node{
        int val;
        Node lChild;
        Node rChild;
        Node parent;
        Node(int val){
            this.val = val;
        }
    }
    public static void main(String[] args){
        BST bst = new BST(new int[]{3,4,6,1,9,0,5,8,7,2});
        bst.delete(3);
        bst.delete(7);
        bst.in_order(bst.root);
    }
}
