package DAA;
import java.util.*;
/*
*Created by Rebecca D'souza on 14/05/18
* */
class Node{
    String value;
    int count;
    Node left,right;
    Node(String value, int count) { this.value = value; this.count = count; }
    @Override
    public String toString() { return value+"("+count+")"; }
}
class freqComparator implements Comparator{
    public int compare(Object o1, Object o2){
        Node u = (Node)o1;
        Node v = (Node)o2;
        return Integer.compare(u.count,v.count);
    }
}
public class HMain {
    private static ArrayList<Node> queue = new ArrayList<>();
    private static HashMap<Character,String> codes = new HashMap<>();
    private static void printNsort(){
        queue.sort(new freqComparator());
        System.out.println(queue);
    }
    private static void encoding(Node root,String code){
        if(root.left==null && root.right==null){
            codes.put(root.value.charAt(0),code);
            return;
        }
        code += "0";
        encoding(root.left,code);
        code = code.substring(0,code.length()-1);
        code += "1";
        encoding(root.right,code);
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message to be encoded : ");
        String encoded = "",message = sc.nextLine();
        ArrayList<Character> characters = new ArrayList<>();
        for(char x : message.toCharArray())
            characters.add(x);
        for(char x : new HashSet<>(characters))
            queue.add(new Node(String.valueOf(x),Collections.frequency(characters,x)));
        printNsort();
        while(queue.size()>1){
            Node u = queue.get(0);
            Node v = queue.get(1);
            Node node = new Node(u.value+v.value,u.count+v.count);
            node.left = u;
            node.right = v;
            queue.remove(u);
            queue.remove(v);
            queue.add(node);
            printNsort();
        }
        encoding(queue.get(0),"");
        System.out.println(codes);
        for(char x : message.toCharArray())
            encoded += codes.get(x);
        System.out.println("\nEncoded message is : "+encoded);
    }
}