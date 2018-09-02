package DAA.Huffman;
import java.util.*;
/*
* Created by Rebecca D'souza on 13/05/18
* */
class Node {
    String value;
    int count;
    Node left,right,next,prev;
    Node(String value, int count) {
        this.value = value;
        this.count = count;
    }
}
public class Huffman {
    private static Node node;
    private static HashMap<Character,String> hm = new HashMap<>();
    private static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.value + " -> ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    private static void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.value + " -> ");
            inOrder(root.right);
        }
    }
    private static void encoding(Node root,String code){
        if(root.left==null && root.right==null){
            hm.put(root.value.charAt(0),code);
            return;
        }
        code += "0";
        encoding(root.left,code);
        code = code.substring(0,code.length()-1);
        code += "1";
        encoding(root.right,code);
    }
    private static void printLinkedList(Node root){
        Node current = root;
        while (current.next != null) {
            System.out.print(current.value+"("+current.count+")"+" -> ");
            current = current.next;
        }
        System.out.println(current.value+"("+current.count+")");
    }
    private static void TreeMaker(Node root, Node end) {
        printLinkedList(root);
        node = new Node(end.prev.value + end.value  , end.prev.count + end.count);
        System.out.println("\nCombining "+end.prev.value+" and "+end.value);
        node.left = end.prev;
        node.right = end;
        end = end.prev.prev;
        end.next = null;
        Node temp = end;
        boolean placed = false;
        while(temp!=null){
            if(temp.count < node.count)
                temp = temp.prev;
            else{
                node.prev = temp;
                if(temp.next!=null) {
                    node.next = temp.next;
                    temp.next.prev = node;
                }
                else {
                    node.next = null;
                    end = node;
                }
                temp.next = node;
                placed = true;
                break;
            }
        }
        if(!placed){
            root.prev = node;
            node.next = root;
            node.prev = null;
            root = node;
        }
        if (root.next == end) {
            printLinkedList(root);
            node = new Node(root.value+end.value, root.count + end.count);
            System.out.println("\nCombining "+root.value+" and "+end.value+"\n"+node.value+"("+node.count+")");
            node.left = root;
            node.right = end;
            node.next = null;
            node.prev = null;
        } else TreeMaker(root, end);
    }
    public static void main(String[] args) {
        String message = "MALAYALAM";
        ArrayList<Character> al = new ArrayList<>();
        HashSet<Character> hs = new HashSet<>();
        for(char x : message.toCharArray()){
            hs.add(x);
            al.add(x);
        }
        Node array[] = new Node[hs.size()];
        int count = 0;
        for(char x : hs)
            array[count++] = new Node(String.valueOf(x),Collections.frequency(al,x));
        for(int i = 0;i<array.length-1;i++)
            for(int j = 0;j<array.length-i-1;j++)
                if(array[j].count<array[j+1].count){
                    Node temp;
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
        System.out.println("\nFREQUENCIES :");
        Node root = array[0];
        Node current = root;
        for(int i = 1;i<array.length;i++){
            System.out.println(current.value+" - "+current.count);
            current.next = array[i];
            array[i].prev = current;
            current = array[i];
        }
        System.out.println(current.value+" - "+current.count+"\n");
        Node end = current;
        TreeMaker(root, end);
        System.out.println("\nINORDER : ");
        inOrder(node);
        System.out.println("\n\nPREORDER : ");
        preOrder(node);
        System.out.println("\n\nHUFFMAN ENCODING :");
        encoding(node,"");
        System.out.println(hm);
    }
}