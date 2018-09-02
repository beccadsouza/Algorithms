package DAA;
import java.util.*;
/*
 * Created by Rebecca D'souza on 22/03/18
 * */
class cell{
    int index=0;
    int val;
    @Override
    public String toString() {
        return val+" "+index;
    }
}
public class Main {
    private static final int MAX = 10000;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int a[] = new int[n+1];
        for(int i = 1;i<n+1;i++)
            a[i] = sc.nextInt();
        Arrays.sort(a);
        cell[][] grid = new cell[n+1][k+1];
        for(int i = 0;i <n+1;i++){
            for(int j = 0;j<k+1;j++){
                grid[i][j] = new cell();
                grid[i][j].val = MAX;
            }
        }
        grid[0][0].val = 0;
        for(int j = 1;j<k+1;j++){
            for(int i = 1;i<n+1;i++){
                int min = MAX;
                int index = 0;
                for(int p = i;p>0;p--){
                    int temp = grid[p-1][j-1].val + (int)Math.pow(a[i]-a[p],2);
                    if(temp < min){
                        min = temp;
                        index = p;
                    }
                    else if(temp==min){
                        index = p;
                    }
                }
                grid[i][j].val = min;
                grid[i][j].index = index;
            }
        }
        for(int i = 0;i <n+1;i++){
            for(int j = 0;j<k+1;j++){
                System.out.print(grid[i][j].val+" ");
            }
            System.out.println();
        }
        for(int i = 0;i <n+1;i++){
            for(int j = 0;j<k+1;j++)
                System.out.print(grid[i][j]+"\t\t");
            System.out.println();
        }
        int y = k;
        int x = n;
        ArrayList<Integer> al = new ArrayList<>();
        while(x!=0){
            al.add(grid[x][y].index);
            x = grid[x][y].index - 1;
            y--;
        }
        Collections.sort(al);
        System.out.println(al);
        System.out.println("The cluster's are : ");
        for(int i = 1;i<n+1;i++){
            if(al.contains(i)){
                System.out.print(" | ");
            }
            System.out.print(a[i]+" ");
        }
        System.out.println("\nMinimum Cost : "+grid[n][k].val);
    }
}