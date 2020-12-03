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
	    Node(int d,List datalist) { //Main에서 datalist 가져오기
			this.key=d;
			left = null;
			right = null;
			this.height=1;
			List newdl = new ArrayList();//data=data.list는 주소를 가져오므로 newd1 리스트를 만들어서 새로 저장한뒤 데이터에 저장한다.
			for(int i=0;i<datalist.size();i++) {
			   newdl.add(datalist.get(i));
			}
			this.data=newdl;
		}
	}
  


	void addData(FieldsArray fa,List datalist) {//data를 insert 시키고 key값을 증가시킨다
		this.root = insert(this.root,datalist,this.key);
//		System.out.print(key+" : ");
//		look(fa, this.root);
		key=key+1;
	}
	public Node delete(String ddata) {//삭제할 노드의 key를 찾아 노드를 삭제하는 함수에 넘겨준다.
		Node dnode = search(this.root,ddata);
		//System.out.println(dnode.key);
		this.root = removeNode(this.root,root.data,dnode.key);//root에 node저장
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
			node = new Node(key,datalist);//노드 새로 생성
			return node;
		}
		
		if(key<node.key) {//넣고자 하는 키값이 노드의 키값보다 작으면
			node.left = insert(node.left,datalist,key);//노드의 왼쪽을 기준으로 다시 insert
		}else if(key>node.key) {//넣고자 하는 키값이 노드의 키값보다 크면
			node.right = insert(node.right,datalist,key);//노드의 오른쪽을 기준으로 다시 insert
		}else {
			return node;
		}
		
		node.height = 1+Math.max(height(node.left), height(node.right));//노드의 depth 구하기
		
		int balance = getBalance(node);//balance값 구하기
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
	public Node minValueNode(Node node) {//노드를 기준으로 가장 작은 키값의 노드 구하기(가장 왼쪽)
		Node current = node;
		//loop down to find the leftmost leaf
		 while (current.left!=null)
			 current = current.left;
		 return current;
	}
	public Node removeNode(Node root,List data,int key) {//노드 삭제
		
		if(root==null) {
			return root;
		}
		if(key<root.key) {//key값이 root의 key값보다 작으면
			root.left=removeNode(root.left,data,key);//root의 왼쪽을 기준으로 다시 remove
		}else if(key>root.key) {//key값이 root의 key값보다 크면
			root.right = removeNode(root.right,data,key);//root의 오른쪽을 기준으로 다시 remove
		}
		else {//root가 삭제하고자 하는 노드일때
			if((root.left==null)||(root.right==null)) {//root의 왼쪽이나 오른쪽이 null이면
				Node temp = null;//temp에 null대입
				if(temp==root.left) {//root의 왼쪽이 null이거나 양쪽이 null일때
					temp = root.right;//temp에 root의 오른쪽 노드를 대입
				}else {//root의 오른쪽만 null이면
					temp = root.left;//temp에 root의 왼쪽을 대입
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
		root.height = Math.max(height(root.left), height(root.right))+1;//현재 root의 depth=왼쪽과 오른쪽 depth의 최대값+1
		
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
		    System.out.println("수정할 field의 index를 선택하세요.(0,1,...):");
		    for(int i=0;i<fa.fn;i++) {
		    	System.out.println(i+"."+fa.name[i]);
		    }
			int modif = sc.nextInt();
			sc.nextLine();
			System.out.println("데이터를 입력하세요.");
			String modid = sc.nextLine();
			mnode.data.set(modif,modid); //리스트에 있는 데이터 교환
			int dsize = mnode.data.size();
			for(int i=0;i<dsize;i++) {//수정된 데이터 보여주기
				System.out.println(fa.name[i]+":"+mnode.data.get(i));
		    }
		}
    }
}		


	


	
