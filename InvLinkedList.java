package package1;

# (Testing git -  commit verstion)

public class InvLinkedList { 

 Node head;


 static class Node { 

     int data; 
     Node next; 


     Node(int d) 
     { 
         data = d; 
         next = null; 
     } 
 } 


 public static InvLinkedList insert(InvLinkedList list, int data) 
 { 
     Node new_node = new Node(data); 
     new_node.next = null; 

     if (list.head == null) { 
         list.head = new_node; 
     } 
     else { 
    	 
         Node last = list.head; 
         while (last.next != null) { 
             last = last.next; 
         } 

         last.next = new_node; 
     } 

     return list; 
 } 
 
 public static InvLinkedList remove(int data) {
	 Node temp = this.head;
	 while(temp.next != data) {
		 temp = temp.next;
	 }
	 temp.next = temp.next.next;
	 return list;
 }
 
 public static void printList(InvLinkedList list) 
 { 
     Node currNode = list.head; 

     System.out.print("Inventory: "); 

     while (currNode != null) {         
         
    	 System.out.print(currNode.data + " "); 

         currNode = currNode.next; 
     }
 }
}
