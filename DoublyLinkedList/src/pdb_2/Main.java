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
	        System.out.print("0~6������ ���ڸ� �Է��ϼ��� : ");
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
		            fa.datalist.clear();//datalist �ȿ� �ִ� �����Ͱ� ����
	        	}
	        	System.out.println("�Է¿Ϸ�");
	        }
	        if(value==2) {//Add()
	        	System.out.println("�߰��� ������ ������ �Է��ϼ���.");
	    		int arn=sc.nextInt();
	    		sc.nextLine();
	    		int size = dll.size();
	    		for(int j=0;j<arn;j++) {
	    			fa.contentInput();
	    			dll.addData(size+j,fa.outputData());
	    			fa.datalist.clear();
	    		}
	       	    System.out.println("�Է¿Ϸ�");
	       	   
	        }
	        if(value==3) {
	        	 //Delete()
	        	System.out.println("�����ϰ� ���� �����Ͱ��� �Է��ϼ���.");
	        	String data=sc.nextLine();
	        	dll.remove(data);
	        	 
	        }
	        if(value==4) {//Search()
	        	System.out.println("�˻� �� �����Ͱ��� �Է��ϼ���.");
	        	dll.search(fa);
	        }
	        if(value==5) {//Look()
	        	System.out.println("5");
	        	dll.look(fa);
	        }
	        if(value==6) {//Modify()
	            System.out.println("������ �����Ͱ��� �Է��ϼ���.");
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
		            fa.datalist.clear();//datalist �ȿ� �ִ� �����Ͱ� ����
	        	}
	        	System.out.println("�Է¿Ϸ�");
			}
			/*if(value==2) {
				System.out.println("������ �����͸� �Է��ϼ���.");
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
