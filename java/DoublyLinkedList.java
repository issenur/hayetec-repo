public class DoublyLinkedList{
	Node front;
	Node temp;
	Node rear;


	public class  Node {
		Node left;
		String name;
		Node right;

		private Node(Node left, String name, Node right){
			this.left = left;
			this.name = name;
			this.right = right;
		}
	}
		
	public void insert(String s){
    	if (front == null){
			front = new Node(null,s,null);
			front.left = front;
			front.right = front;
			rear = front; 
		}else if(!(front==null)){
			rear.left = new Node(null,s,null);
			rear.left.right = rear;
			rear = rear.left;
			rear.left = front;
			front.right = rear; 
		} 	
	}

	public void iterator(){
		//to show the circular nature of the list, I will
		//have the iterator do 3 rounds of iteration and 
		//print the string value associated with each 
		//Node.	
		temp = front;	
		int i = 0;
	
    	while(i < 3){
			
			System.out.println("Value " + temp.name);
			temp = temp.left;	
			if(temp == front){
		   		 i = i + 1;	
			}
			
		}	
	}
}
class DoublyLinkedListDriver{
	public static void main(String[] args){
		 DoublyLinkedList dll = new DoublyLinkedList();
		 dll.insert("A");
		 dll.insert("B");
		 dll.insert("C");
		 dll.insert("D");

		 dll.iterator();		
	}
}













