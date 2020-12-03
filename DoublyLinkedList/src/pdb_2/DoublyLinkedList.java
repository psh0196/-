package pdb_2;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class DoublyLinkedList {

	Scanner sc = new Scanner(System.in);
	private Node header;
	private int size;
	public DoublyLinkedList() {
		header = new Node(null);
		size=0;
	}
	
	
	private class Node{
		
		private List data;
		private Node previousNode;
		private Node nextNode;
		
		Node(List data){
			if(data == null)
				this.data = null;
			else {//data=data.list�� �ּҸ� �������Ƿ� temp��� ����Ʈ�� ���� ���� �����ѵ� �����Ϳ� �����Ѵ�.
				List temp = new ArrayList();
				int size = data.size();
				for(int i=0;i<size;i++) {
					temp.add(data.get(i));
				}
				this.data = temp;
			}
			this.previousNode = null;
			this.nextNode = null;
		}
	
	}
	
	void addData(int index,List datalist) {
		add(index,datalist);
	}
	
	//index��ġ���� ���� ����� �����͸� ��ȯ�Ѵ�.
	public Object get(int index) {
		return getNode(index).data;
	}
	
	//ù��° ��带 ��ȯ�Ѵ�.
	public Object getFirst() {
		return get(0);
	}
	
	private Node getNode(int index) {
		
		if(index<0 || index>size) {
			throw new IndexOutOfBoundsException("Index : "+index+", Size : " +size);
			
		}
		
		Node node = header;
		
		
		//index�� ����Ʈ size�� �߰� ���̸� ���������� Ž���Ѵ�.
		if(index<(size/2)) {
			
			for(int i=0; i<=index; i++) {
				node = node.nextNode;
			}
			
		}else {
			//index�� ����Ʈ size�� �߰����� �ڸ� �ڿ������� Ž���Ѵ�.
				
			for(int i= size; i> index; i--) {
				node = node.previousNode;
			}
		}
			
			
		return node;
			
	}
	
		
	//obj �����Ϳ� ���� ����� ��ġ�� ��ȯ�Ѵ�.
	public int getNodeIndex(Object obj) {
		
		if(size<=0) {
			return -1;
		}
		
		int index = 0;
		//ù��° ��带 �����´�.
		Node node = header.nextNode;
		List nodeDate = node.data;
		
		//ù��° ������ ���� �����͸� ���� ��带 Ž���Ѵ�.
		while(!nodeDate.contains(obj)) {
			
			
			node = node.nextNode;
			
			if(node==null) {
				return -1;
			}
			
			
			nodeDate = node.data;
			index++;
		}
		
		//��ġ�� ��ȯ�Ѵ�.
		return index;
	}
	
	//����Ʈ�� ù��°�� �����͸� �����Ѵ�.
	
	public void addFirst(List data) {
		
		//�����͸� ���� ���ο� ��� ����
		Node newNode = new Node(data);
		
		//���ο� ��尡 ���� ���� ù ��° ��带 ����Ų��.
		newNode.nextNode = header.nextNode;
		
		//����Ʈ�� ������� ������
		if(header.nextNode !=null) {
			
			//ù ��尡 �ڽ��� �� ���� ���ο� ��带 ����Ų��.
			header.nextNode.previousNode = newNode;
			
		}else {//����Ʈ�� ���������
			
			//����� ������ ��带 ���ο� ���� ����Ű���� �Ѵ�.
			header.previousNode = newNode;
		}
		
		//����� ù��° ���� ���ο� ��带 ����Ű���� �Ѵ�.
		header.nextNode = newNode;
		size++;
	
	}
	
	public void add(int index, List data) {
		
		//index�� 0�̸� addFirst() �Լ��� ȣ���Ѵ�.
		if(index ==0) {
			addFirst(data);
			return;
		}
		
		//������ index��ġ�� �� ��带 �����´�.
		Node previous = getNode(index-1);
		
		//������ index�� ��ġ�� ���� ��带 �����´�.
		Node next = previous.nextNode;
		
		//data�� ���ο� ��� ����
		Node newNode = new Node(data);
		
		//�ճ�尡 ���ο� ��带 �������� ����Ų��.
		previous.nextNode = newNode;
		
		//���ο� ��尡 �ճ�带 �������� ����Ų��.
		newNode.previousNode = previous;
		
		//���ο� ����� ���� ��忡 ������带 �����Ѵ�.
		newNode.nextNode = next;
		
		//������ġ�� ������ ��ġ�� �ƴϸ�
		if(newNode.nextNode != null) {
			
			//���� ��尡 ���ο� ��带 �ճ��� �����Ѵ�.
			next.previousNode = newNode;
			
		}else {//���� ��ġ�� ������ �̸�
			
			//����� ����Ű�� ������ ��尡 ���ο� ��尡 �ȴ�. 
			header.previousNode = newNode;
		}
	
		size++;
	}
	
	//������ ��带 ��ȯ�Ѵ�.
	public void addLast(List data) {
		add(size,data);
	}
	
	//data�� �������� �ִ´�.
	public void add(List data) {
		addLast(data);
	}
	
	public Object removeFirst() {
		
		//ù��° ��带 �����´�.
		Node firstNode = getNode(0);
		
		//����� ù ���� �ι�° ��带 ����Ų��.
		header.nextNode = firstNode.nextNode;
		
		//����Ʈ�� ������� ������
		if(header.nextNode != null) {
			
			//�ι�° ��尡 ����Ű�� �� ���� ����.
			firstNode.nextNode.previousNode = null;
			
		}else {//����Ʈ�� ��� �Ǹ�
			
			//����� ����Ű�� ������ ��尡 ����.
			header.previousNode = null;
			
		}
		
		size--;
		//ù��° ����� �����͸� ��ȯ
		return firstNode.data;
	}
	
	public Object remove(int index) {
		
		if(index<0 || index>=size) {
			throw new IndexOutOfBoundsException("Index : "+index+", Size:"+size);
		}else if(index==0) {
			return removeFirst(); //index�� 0�̸� ù��° �����͸� ����
		}
		
		
		//������ index��ġ�� ��带 �����´�.
		Node removeNode = getNode(index);
		//������ ����� �ճ�带 �����´�
		Node previous = removeNode.previousNode;
		//������ ����� �޳�带 �����´�.
		Node next = removeNode.nextNode;
		
		//�ճ�尡 ������带 �������� ����Ų��.
		previous.nextNode = next;
		
		//���ŵǴ� ��尡 ������ ��尡 �ƴϸ� 
		if(next!=null) {
			
			//���� ����� �޳�尡 �ճ��� ���� ��� �� ��带 ����Ų��.
			next.previousNode = previous;
			
		}else {//���� ��尡 ������ ����
			
			//����� ������ ����  �ճ�带 ����Ų��.
			header.previousNode = previous;
		}
		
		size--;
		
		//���ų���� �����͸� ��ȯ
		return removeNode.data;
		
	}
	
	//�����͸� �����Ѵ�.
	public boolean remove(Object data) {
		//data�� �ִ� index�� ��´�.
		int nodeIndex = getNodeIndex(data);
		
		//�����Ͱ� ������ false ��ȯ
		if(nodeIndex == -1) {
			return false;
		}else {//�����Ͱ� ������ ����
			remove(nodeIndex);
			return true;
		}
	}
	
	public Object removeLast() {
		return remove(size-1);
	}
	
	
	public int size() {
		return size;
	}
	
	
	public void look(FieldsArray fa) {
		if(size == 0) {
			System.out.println("Empty!");
			return;
		}
		Node node = header;
		List data;
	
		for(int j=0;j<size;j++) {
			node = node.nextNode;
			data = node.data;
			int dsize = data.size();
			for(int i=0;i<dsize;i++) {
				if(i==0) {
					System.out.println();
				}
				System.out.print(fa.name[i]+":"+data.get(i)+" ");
			}
		}
	}	
	
	public Node search(FieldsArray fa) {
		if(size==0) {
			System.out.println("Empty");
			return null;
		}
		Node node=header;
		List data;
		String searchd=sc.nextLine();
		 // for(int j=0;j<size;j++) {
			node=node.nextNode;
			int index=getNodeIndex(searchd);
			node = getNode(index);
			//List nodeDate = node.data;
			//Object obj=searchd;
			//if(nodeDate.contains(obj)) {
			data = node.data;
			int dsize = data.size();
			for(int i=0;i<dsize;i++) {
				System.out.println(fa.name[i]+":"+data.get(i));
			//}
		  //}
		}
		return node;
	}
	public void modify(FieldsArray fa) {
		if(size==0) {
			System.out.println("Empty");
			return;
		}
		Node node=header;
		List data;
		node=search(fa);
		System.out.println("������ field�� index�� �����ϼ���.(0,1,...):");
		int modif = sc.nextInt();
		sc.nextLine();
		System.out.println("�����͸� �Է��ϼ���.");
		String modid = sc.nextLine();
		node.data.set(modif,modid); //����Ʈ�� �ִ� ������ ��ȯ
		data = node.data;
		int dsize = data.size();
		for(int i=0;i<dsize;i++) {//������ ������ �����ֱ�
			System.out.println(fa.name[i]+":"+data.get(i));
		}
	}

		
}
	    

	

