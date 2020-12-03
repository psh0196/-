package pdb_2;
import java.util.List;
import java.util.Scanner;

import pdb.AVLTree.Node;

import java.util.ArrayList;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*DoublyLinkedList dll = new DoublyLinkedList();
		FieldsArray fa = new FieldsArray();
		
		int value;
		
		do {
			System.out.println();
	        System.out.println("1.Enter");
	        System.out.println("2.Add");
	        System.out.println("3.Delete");
	        System.out.println("4.Search");
	        System.out.println("5.Look");
	        System.out.println("6.Modify");
	        System.out.println("0.exit");
	        System.out.print("0~6까지의 숫자를 입력하세요 : ");
	        value = sc.nextInt();
	        sc.nextLine();
	        if(value==1){
	        	//Enter
	        	System.out.print("recordnum?:");
	        	int rn=sc.nextInt();
	        	fa.FieldsArray();
	        	for(int j=0;j<rn;j++) {
		            fa.contentInput();
		            dll.addData(j,fa.outputData());
		            fa.datalist.clear();//datalist 안에 있는 데이터값 비우기
	        	}
	        	System.out.println("입력완료");
	        }
	        if(value==2) {//Add()
	        	System.out.println("추가할 데이터 갯수를 입력하세요.");
	    		int arn=sc.nextInt();
	    		sc.nextLine();
	    		int size = dll.size();
	    		for(int j=0;j<arn;j++) {
	    			fa.contentInput();
	    			dll.addData(size+j,fa.outputData());
	    			fa.datalist.clear();
	    		}
	       	    System.out.println("입력완료");
	       	   
	        }
	        if(value==3) {
	        	 //Delete()
	        	System.out.println("삭제하고 싶은 데이터값을 입력하세요.");
	        	String data=sc.nextLine();
	        	dll.remove(data);
	        	 
	        }
	        if(value==4) {//Search()
	        	System.out.println("검색 할 데이터값을 입력하세요.");
	        	dll.search(fa);
	        }
	        if(value==5) {//Look()
	        	System.out.println("5");
	        	dll.look(fa);
	        }
	        if(value==6) {//Modify()
	            System.out.println("수정할 데이터값을 입력하세요.");
	            dll.modify(fa);
	        }
	       
		
		}while(value!=0);*/
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
			System.out.println("1.insert 2.delete 3.look ");
			value = sc.nextInt();
			sc.nextLine();
			
			if(value==1) {
				System.out.print("recordnum?:");
	        	int rn=sc.nextInt();
	        	fa.FieldsArray();
	        	for(int j=0;j<rn;j++) {
		            fa.contentInput();
		            avl.addData(fa.outputData());
		            fa.datalist.clear();//datalist 안에 있는 데이터값 비우기
	        	}
	        	System.out.println("입력완료");
			}
			/*if(value==2) {
				System.out.println("삭제할 데이터를 입력하세요.");
				data = sc.nextInt();
				sc.nextLine();
				root = tree.delete(root, data);
				
			}*/
			if(value==3) {
				avl.look(fa,avl.root);
			}
		}while(value!=0);
		sc.close();
	}
}
