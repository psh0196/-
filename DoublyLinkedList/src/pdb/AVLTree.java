package pdb;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AVLTree{
	public static int key ;
	public Node root;
	public AVLTree() {
		root = null;
		key=0;
	}
	
	public class Node{
		public int key, height;
		private Node left,right;
		public List data;
	    Node(int d,List datalist) { //Main���� datalist ��������
			this.key=d;
			left = null;
			right = null;
			this.height=1;
			List newdl = new ArrayList();//data=data.list�� �ּҸ� �������Ƿ� newd1 ����Ʈ�� ���� ���� �����ѵ� �����Ϳ� �����Ѵ�.
			for(int i=0;i<datalist.size();i++) {
			   newdl.add(datalist.get(i));
			}
			this.data=newdl;
		}
	}
  


	void addData(FieldsArray fa,List datalist) {//data�� insert ��Ű�� key���� ������Ų��
		this.root = insert(this.root,datalist,this.key);
//		System.out.print(key+" : ");
//		look(fa, this.root);
		key=key+1;
	}
	public Node delete(String ddata) {//������ ����� key�� ã�� ��带 �����ϴ� �Լ��� �Ѱ��ش�.
		Node dnode = search(this.root,ddata);
		//System.out.println(dnode.key);
		this.root = removeNode(this.root,root.data,dnode.key);//root�� node����
		return this.root;
	}
	
	private int height(Node n) {
		if(n==null) {
			return 0;
		}
		return n.height;
	}
	
	private Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;
		//Perform rotation
		x.right = y;
		y.left = T2;
		//Update heights
		y.height = Math.max(height(y.left),height(y.right))+1;
		x.height = Math.max(height(x.left),height(x.right))+1;
		//return new root
		return x;
	}
	
	private Node leftRotate(Node x) {
		Node y=x.right;
		Node T2 = y.left;
		
		y.left = x;
		x.right = T2;
		
		x.height = Math.max(height(x.left),height(x.right))+1;
		y.height = Math.max(height(y.left),height(y.right))+1;
		
		return y;
	}
	
	private int getBalance(Node n) {
		if(n==null) {
			return 0;
		}
		return height(n.left)-height(n.right);
		
	}
	
	public Node insert(Node node,List datalist,int key) {
		if(node == null) {			
			node = new Node(key,datalist);//��� ���� ����
			return node;
		}
		
		if(key<node.key) {//�ְ��� �ϴ� Ű���� ����� Ű������ ������
			node.left = insert(node.left,datalist,key);//����� ������ �������� �ٽ� insert
		}else if(key>node.key) {//�ְ��� �ϴ� Ű���� ����� Ű������ ũ��
			node.right = insert(node.right,datalist,key);//����� �������� �������� �ٽ� insert
		}else {
			return node;
		}
		
		node.height = 1+Math.max(height(node.left), height(node.right));//����� depth ���ϱ�
		
		int balance = getBalance(node);//balance�� ���ϱ�
		//LL case
		if(balance>1&&key<node.left.key) {
			System.out.println("LL rotation");
			return rightRotate(node);
		}
		//RR case
		if(balance<-1&&key>node.right.key) {
			System.out.println("RR rotation");
			return leftRotate(node);
		}
		//LR case
		if(balance>1&&key>node.left.key) {
			node.left = leftRotate(node.left);
			System.out.println("LR rotation");
			return rightRotate(node);
		}
		//RL case
		if(balance<-1&&key<node.right.key) {
			node.right = rightRotate(node.right);
			System.out.println("RL rotation");
			return leftRotate(node);
		}
		return node;
	}
	public Node minValueNode(Node node) {//��带 �������� ���� ���� Ű���� ��� ���ϱ�(���� ����)
		Node current = node;
		//loop down to find the leftmost leaf
		 while (current.left!=null)
			 current = current.left;
		 return current;
	}
	public Node removeNode(Node root,List data,int key) {//��� ����
		
		if(root==null) {
			return root;
		}
		if(key<root.key) {//key���� root�� key������ ������
			root.left=removeNode(root.left,data,key);//root�� ������ �������� �ٽ� remove
		}else if(key>root.key) {//key���� root�� key������ ũ��
			root.right = removeNode(root.right,data,key);//root�� �������� �������� �ٽ� remove
		}
		else {//root�� �����ϰ��� �ϴ� ����϶�
			if((root.left==null)||(root.right==null)) {//root�� �����̳� �������� null�̸�
				Node temp = null;//temp�� null����
				if(temp==root.left) {//root�� ������ null�̰ų� ������ null�϶�
					temp = root.right;//temp�� root�� ������ ��带 ����
				}else {//root�� �����ʸ� null�̸�
					temp = root.left;//temp�� root�� ������ ����
				}
				//No child case
				if(temp == null) {
					temp=root;
					root = null;
				}
				//One child case
				else {
					root = temp;
					
				}
				
			}else {
				/*
				 * Node with two children: Get the inorder
				 * successor(smallest in the right subtree)
				 */
				Node temp = minValueNode(root.right);
				
				//copy the inorder successor's data to this node
				root.key = temp.key;
				root.data = temp.data;
				//delete the inorder successor
				root.right = removeNode(root.right,data,temp.key);
				
			}
		}
		
		//If the tree had only one node then return
		if(root == null) {
			return root;
		}
		
		//update height of the current node
		root.height = Math.max(height(root.left), height(root.right))+1;//���� root�� depth=���ʰ� ������ depth�� �ִ밪+1
		
		//get the balance factor of this node
		int balance = getBalance(root);
		
		if(balance>1&&getBalance(root.left)>=0) {
			System.out.println("LL rotation");
			return rightRotate(root);
		}
		if(balance>1&&getBalance(root.left)<0) {
			root.left = leftRotate(root.left);
			System.out.println("LR rotation");
			return rightRotate(root);
		}
		if(balance<-1&&getBalance(root.right)<=0) {
			System.out.println("RR rotation");
			return leftRotate(root);
		}
		if(balance<-1&&getBalance(root.right)>0) {
			root.right = rightRotate(root.right);
			System.out.println("RL rotation");
			return leftRotate(root);
		}
		return root;
	}
	public void look(FieldsArray fa,Node node) {
		if(node!=null) {
			look(fa,node.left);
			int dsize = node.data.size();
			for(int i=0;i<dsize;i++) {
				System.out.print(fa.name[i]+":"+node.data.get(i)+" ");
			}
			System.out.println();
			look(fa,node.right);
		}	
	}
	public Node search(Node node,String adata) {
		if(node==null) {
			return null;
		}
		for(int i=0;i<node.data.size();i++) {
			if(adata.equals(node.data.get(i))) {
				return node;
			}
		}
		Node lNode = search(node.left,adata);
		Node rNode = search(node.right,adata);
		if(lNode !=null) 
			return lNode;
		if(rNode !=null)
			return rNode;
			
		
		return null;	
	}
	Scanner sc = new Scanner(System.in);
	public void modify(FieldsArray fa,Node node,String mdata) {
		if(this.root == null) {
			System.out.println("empty");
		}
		else {
		    Node mnode = search(node,mdata);
		    System.out.println("������ field�� index�� �����ϼ���.(0,1,...):");
		    for(int i=0;i<fa.fn;i++) {
		    	System.out.println(i+"."+fa.name[i]);
		    }
			int modif = sc.nextInt();
			sc.nextLine();
			System.out.println("�����͸� �Է��ϼ���.");
			String modid = sc.nextLine();
			mnode.data.set(modif,modid); //����Ʈ�� �ִ� ������ ��ȯ
			int dsize = mnode.data.size();
			for(int i=0;i<dsize;i++) {//������ ������ �����ֱ�
				System.out.println(fa.name[i]+":"+mnode.data.get(i));
		    }
		}
    }
}		


	


	
