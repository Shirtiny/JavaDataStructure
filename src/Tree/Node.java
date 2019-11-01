package Tree;

public class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;
    private Node pre=null;
    //1. 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    //2. 如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
    private int leftType;
    private int rightType;
    private int index=0;

    public void print() {
        System.out.println(this);
    }

    //中左右
    public void preOrder() {
        this.print();
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    //前序查找
    public Node preOrder(int id) {
        if (this.id == id) {
            return this;
        }
        Node res = null;
        if (this.left != null) {
            res = this.left.preOrder(id);
            //左子树找到，便返回
            if (res!=null){
                return res;
            }
        }
        //说明左子树没找到，继续查右子树，返回res，不管res有没有结果
        if (this.right != null) {
            res = this.right.preOrder(id);
            return res;
        }
        return null;
    }


    //左中右
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        this.print();
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //中序查找
    public Node infixOrder(int id) {
        Node res=null;
        if (this.left != null) {
            res = this.left.infixOrder(id);
            if (res!=null){
                return res;
            }
        }
        if (this.id==id){
            return this;
        }
        if (this.right != null) {
           res =  this.right.infixOrder(id);
           return res;
        }
        return null;
    }

    //中序线索化
    public void infixOrder(Node node){
        if (node==null){
            return;
        }

        //线索化左子
        infixOrder(node.getLeft());

        //处理当前节点
        if (node.getLeft()==null){
            this.setLeft(pre);
            this.setLeftType(1);
        }
        if (node.getRight()==null){
            pre.setRight(node);
            pre.setLeftType(1);
        }
        //前驱节点赋值
        pre=node;

        //线索化右子
        infixOrder(node.getRight());
    }

    //左右中
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }
        this.print();
    }

    //后序查找
    public Node postOrder(int id) {
        Node res=null;
        if (this.left != null) {
            res=this.left.postOrder(id);
            if (res!=null){
                return res;
            }
        }

        if (this.right != null) {
            res= this.right.postOrder(id);
            if (res!=null){
                return res;
            }
        }

        if (this.id==id){
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "Node{" +
//                "id=" + id +
//                ", name=" + name+
//                '}';
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }




    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
