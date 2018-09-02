package DAA;
import java.util.*;
class box{
    public String dir;
    public int value;
    box(){
        dir = "b";
        value = 0;
    }
}
public class LCS {
    private static ArrayList<Integer> longestCommonSubsequence(int[] a, int[] b) {
        //making da grid
        box[][] grid = new box[a.length+1][b.length+1];
        for(int i = 0;i<a.length+1;i++){
            for(int j = 0;j<b.length+1;j++){
                grid[i][j] = new box();
                if(j==0 || i==0)
                    grid[i][j].value = 0;
                else if(a[i-1]==b[j-1]){
                    grid[i][j].value = grid[i-1][j-1].value +1;
                    grid[i][j].dir = "d";
                }
                else {
                    if(grid[i-1][j].value>grid[i][j-1].value){
                        grid[i][j].value = grid[i-1][j].value;
                        grid[i][j].dir = "^";
                    }
                    else{
                        grid[i][j].value = grid[i][j-1].value;
                        grid[i][j].dir = "<";
                    }
                }
            }
        }
        System.out.print("\t\t");
        for(int x : b)
            System.out.printf("%d\t",x);
        System.out.println();
        for(int i = 0;i<a.length+1;i++){
            if(i==0)
                System.out.print("\t");
            else System.out.printf("%d\t",a[i-1]);
            for(int j = 0;j<b.length+1;j++){
                System.out.printf("%d %s\t",grid[i][j].value,grid[i][j].dir);
            }
            System.out.println();
        }
        //answer
        String temp;
        ArrayList<Integer> al = new ArrayList<>();
        int x = b.length;
        int y = a.length;
        while(x!=0 && y!=0){
            temp = grid[y][x].dir;
            if(temp.equalsIgnoreCase("d")){
                al.add(a[y-1]);
                x -= 1;
                y -= 1;
            }
            else if(temp.equalsIgnoreCase("<"))
                x -= 1;
            else y -= 1;
        }
        return al;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for(int a_i = 0; a_i < n; a_i++)
            a[a_i] = in.nextInt();
        int[] b = new int[m];
        for(int b_i = 0; b_i < m; b_i++)
            b[b_i] = in.nextInt();
        ArrayList<Integer> al = longestCommonSubsequence(a, b);
        Collections.reverse(al);
        for(int x : al)
            System.out.print(x+" ");
        in.close();
    }
}