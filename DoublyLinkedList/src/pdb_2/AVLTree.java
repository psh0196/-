package pdb_2;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class AVLTree{
	public static int key ;
	public Node root;
	public AVLTree() {
		root = null;
		key=0;
	}
	
	public class Node{
		private int key, height;
		private Node left,right;
		private List data;
	    Node(int d,List datalist) { 
			this.key=d;
			this.height=1;
			this.data=datalist;
	    }
	  
		public void inputData(List data) {
			if(data == null)
				this.data = null;
			else {//data=data.list는 주소를 가져오므로 temp라는 리스트를 만들어서 새로 저장한뒤 데이터에 저장한다.
				List temp = new ArrayList();
				int size = data.size();
				for(int i=0;i<size;i++) {
					temp.add(data.get(i));
				}
				this.data = temp;
			 }
			this.left = null;
			this.right = null;
	       }
		}



	void addData(List datalist) {
		insert(this.root,datalist,key);
		key=key+1;
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
			System.out.println("ss");
			node = new Node(key,datalist); 
			return node;
		}
		
		if(key<node.key) {
			node.left = insert(node.left,datalist,key);	
		}else if(key>node.key) {
			node.right = insert(node.right,datalist,key);
		}else {
			return node;
		}
		
		node.height = 1+Math.max(height(node.left), height(node.right));
		
		int balance = getBalance(node);
		//LL case
		if(balance>1&&key<node.left.key) {
			return rightRotate(node);
		}
		//RR case
		if(balance<-1&&key>node.right.key) {
			return leftRotate(node);
		}
		//LR case
		if(balance>1&&key>node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		//RL case
		if(balance<-1&&key<node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}
//	public void inOrder(Node node) {
//		if(node!=null) {
//			inOrder(node.left);
//			System.out.println(node.data+" ");
//			inOrder(node.right);
//		}
//	}
	public Node minValueNode(Node node) {
		Node current = node;
		//loop down to find the leftmost leaf
		 while (current.left!=null)
			 current = current.left;
		 return current;
	}
	public Node delete(Node root,int key) {
		
		if(root==null) {
			return root;
		}
		if(key<root.key) {
			root.left=delete(root.left,key);
		}else if(key>root.key) {
			root.right = delete(root.right,key);
		}
		else {
			if((root.left==null)||(root.right==null)) {
				Node temp = null;
				if(temp==root.right) {
					temp = root.right;
				}else {
					temp = root.left;
				}
				//No child case
				if(temp == null) {
					temp=root;
					root=null;
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
				
				//delete the inorder successor
				root.right = delete(root.right,temp.key);
			}
		}
		
		//If the tree had only one node then return
		if(root == null) {
			return root;
		}
		
		//update height of the current node
		root.height = Math.max(height(root.left), height(root.right))+1;
		
		//get the balance factor of this node
		int balance = getBalance(root);
		
		if(balance>1&&getBalance(root.left)>=0) {
			return rightRotate(root);
		}
		if(balance>1&&getBalance(root.left)<0) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}
		if(balance<-1&&getBalance(root.right)<=0) {
			return leftRotate(root);
		}
		if(balance<-1&&getBalance(root.right)>0) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
		return root;
	}
	public void look(FieldsArray fa,Node node) {
		if(node!=null) {
			look(fa,node.left);
			int dsize = node.data.size();
			System.out.println();
			for(int i=0;i<dsize;i++) {
				System.out.print(fa.name[i]+":"+node.data.get(i)+" ");
			}
			look(fa,node.right);
		}	
	}
	
		
		
}	

	


	
