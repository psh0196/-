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
		            fa.datalist.clear();//datalist 안에 있는 데이터값 비우기
	        	}
	        	System.out.println("---입력완료---");
	        	System.out.println("depth="+avl.root.height);
	        	System.out.println();
			}
			 if(value==2) {//Add()
		        	System.out.println("추가할 데이터 갯수를 입력하세요.");
		    		int arn=sc.nextInt();
		    		sc.nextLine();
		    		for(int j=0;j<arn;j++) {
		    			fa.contentInput();
		    			avl.addData(fa,fa.outputData());
		    			fa.datalist.clear();
		    		}
		       	    System.out.println("---입력완료---");
		       	    avl.look(fa,avl.root);
		       	    System.out.println("depth="+avl.root.height);
		       	    System.out.println();
		        }
			 if(value==3) { //Delete()
	        	System.out.println("삭제하고 싶은 데이터값을 입력하세요.");
	        	String ddata=sc.nextLine();
	        	avl.delete(ddata);
	        	System.out.println("---삭제결과---");
	        	avl.look(fa, avl.root);
	        	System.out.println("depth="+avl.root.height);
	        	System.out.println();
	        }
			 
			if(value==4) {//Look()
				avl.look(fa,avl.root);
				System.out.println();
			}
			if(value==5) {//Search()
				System.out.println("검색하고 싶은 데이터값을 입력하세요.");
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
				System.out.println("수정하고 싶은 데이터값을 입력하세요.");
			    String mdata = sc.nextLine();
			    avl.modify(fa,avl.root,mdata);
			    System.out.println("---수정결과---");
	        	avl.look(fa, avl.root);
				System.out.println();
			}
		}while(value!=0);
		sc.close();
	}
}
