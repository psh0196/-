package pdb;
import java.util.List;
import java.util.Scanner;

import pdb.AVLTree.Node;

import java.util.ArrayList;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int value;
		int data;
		FieldsArray fa = new FieldsArray();
		AVLTree avl = new AVLTree();
		/*root = tree.insert(root, 10);
		root = tree.insert(root, 20);
		root = tree.insert(root, 30);
		root = tree.insert(root, 40);
		root = tree.insert(root, 50);
		root = tree.insert(root, 25);
		root = tree.delete(root, 20);*/
		do {
			System.out.println("1.insert");
			System.out.println("2.add");
			System.out.println("3.delete");
			System.out.println("4.look");
			System.out.println("5.search");
			System.out.println("6.modify");
			System.out.println("0.exit");
			value = sc.nextInt();
			sc.nextLine();
			
			if(value==1) {
				System.out.print("recordnum?:");
	        	int rn=sc.nextInt();
	        	fa.FieldsArray();
	        	for(int j=0;j<rn;j++) {
		            fa.contentInput();
		            avl.addData(fa,fa.outputData());
		            fa.datalist.clear();//datalist �ȿ� �ִ� �����Ͱ� ����
	        	}
	        	System.out.println("---�Է¿Ϸ�---");
	        	System.out.println("depth="+avl.root.height);
	        	System.out.println();
			}
			 if(value==2) {//Add()
		        	System.out.println("�߰��� ������ ������ �Է��ϼ���.");
		    		int arn=sc.nextInt();
		    		sc.nextLine();
		    		for(int j=0;j<arn;j++) {
		    			fa.contentInput();
		    			avl.addData(fa,fa.outputData());
		    			fa.datalist.clear();
		    		}
		       	    System.out.println("---�Է¿Ϸ�---");
		       	    avl.look(fa,avl.root);
		       	    System.out.println("depth="+avl.root.height);
		       	    System.out.println();
		        }
			 if(value==3) { //Delete()
	        	System.out.println("�����ϰ� ���� �����Ͱ��� �Է��ϼ���.");
	        	String ddata=sc.nextLine();
	        	avl.delete(ddata);
	        	System.out.println("---�������---");
	        	avl.look(fa, avl.root);
	        	System.out.println("depth="+avl.root.height);
	        	System.out.println();
	        }
			 
			if(value==4) {//Look()
				avl.look(fa,avl.root);
				System.out.println();
			}
			if(value==5) {//Search()
				System.out.println("�˻��ϰ� ���� �����Ͱ��� �Է��ϼ���.");
				String adata = sc.nextLine();
				List sdata = new ArrayList();
				Node snode = avl.search(avl.root,adata);
				if(snode==null) {
					System.out.println("no data");
				}
				else {
					sdata = snode.data;
					for(int i=0;i<sdata.size();i++) {
						System.out.println(fa.name[i]+":"+sdata.get(i));
					}
					System.out.println();
			    }
				
			}
			if(value==6) {//Modify
				System.out.println("�����ϰ� ���� �����Ͱ��� �Է��ϼ���.");
			    String mdata = sc.nextLine();
			    avl.modify(fa,avl.root,mdata);
			    System.out.println("---�������---");
	        	avl.look(fa, avl.root);
				System.out.println();
			}
		}while(value!=0);
		sc.close();
	}
}
