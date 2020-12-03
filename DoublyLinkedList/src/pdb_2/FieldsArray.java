package pdb_2;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class FieldsArray {
	public int fn,rn;
	public List datalist = new ArrayList();
	public String name[] = new String[10];
	Scanner sc = new Scanner(System.in);
	void FieldsArray(){
		System.out.print("Fieldsnum?:");
		fn = sc.nextInt();
		sc.nextLine();
           
		for(int i=0;i<fn;i++) {
		  //name[i] = new String();
		  System.out.print("Fieldname:");
		  String fn = sc.nextLine();
		  //sc.skip("[\\r\\n]+");
		  name[i]=fn;
   	   }
     }
	 
	void contentInput() {
		String d;
		for(int i=0;i<fn;i++) {
		   System.out.print(name[i]+":");
		   d = sc.nextLine();
		   //sc.skip("[\\r\\n]+");
		   datalist.add(d);
		}
	 }
	
	
		
	
		//sc.close();
    
	List outputData() {//datalist를 반환하는 함수
		return this.datalist;
	}
	
	
}
