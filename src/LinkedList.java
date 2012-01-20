import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
class Node
{
	int value;
	Node next;
}

class LinkedList
{
	static Node start=new Node();
	public static Node InsertNode(Node current,int number)
	{
		Node node = new Node();
		node.value=number;
		if (start==null){
			node.next=null;
			start=node;
			current=start;
			return current;
		}
		node.next=null;
		current.next=node;
		current=node;
		return current;
	}

	public static void DisplayList(Node node)
	{
		while(node!=null){
			System.out.println(node.value);
			node=node.next;
		}
	}
	public static void DeleteNode(int nodenumber)
	{
		Node node = start;
		Node previous=null;
		while(nodenumber>1)
		{
			previous=node;
			node=node.next;
			nodenumber--;
		}
		previous.next=node.next;
		node=null;
		
	}
	public static void InsertmiddleNode(int value, int position)
	{
		Node nodel=start;
		while(position>2){
			nodel=nodel.next;
			position--;
		}
		Node newnode=new Node();
		newnode.value=value;
		newnode.next=nodel.next;
		nodel.next=newnode;
	}
	public static void main(String[] args) throws IOException
	{	
		start=null;
		int choice;
		Node current=new Node();
		do{
			System.out.println("Enter choice:");
			String getchoice;
			int num;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			getchoice=br.readLine();
			choice=Integer.parseInt(getchoice);
			if (choice==1){
				System.out.println("Enter value");
				num=Integer.parseInt(br.readLine());
				current=InsertNode(current,num);
			}
			else if (choice==2){
				DisplayList(start);
			}
			else if (choice==3){
				DeleteNode(3);
			}
			else if (choice==4)
			{
				InsertmiddleNode(5,3);
			}
		}while(choice!=5);
		
			
		
	}
}
