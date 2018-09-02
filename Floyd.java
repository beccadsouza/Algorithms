package DAA;
import java.util.*;
/*
* Created by Rebecca D'souza on 28/03/18
* */
public class Floyd{
    private static final int max = 99999;
    public static void print(int graph[][]){
        for(int i = 1;i<graph.length;i++){
            for(int j = 1;j<graph.length;j++)
                if (graph[i][j] == max)
                    System.out.print("OO\t");
                else System.out.print(graph[i][j]+"\t");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int graph [][] = new int[n+1][n+1];
        for(int i = 1;i<n+1;i++)
            for(int j = 1;j<n+1;j++)
                if(i==j)
                    graph[i][j] = 0;
                else graph[i][j] = max;
        for(int i = 0;i<m;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int wt = sc.nextInt();
            graph[x][y] = wt;
        }
        System.out.println("\n\nD0 : \n");
        print(graph);
        for(int k = 1;k<n+1;k++) {
            for (int i = 1; i < n + 1; i++)
                for (int j = 1; j < n + 1; j++)
                    graph[i][j] = Integer.min(graph[i][j], graph[i][k] + graph[k][j]);
            System.out.println("\n\nD"+k+" : \n");
            print(graph);
        }
    }
}
/*

4 5
1 3 3
2 1 2
3 2 7
3 4 1
4 1 6


D0 :

0	OO	3	OO
2	0	OO	OO
OO	7	0	1
6	OO	OO	0


D1 :

0	OO	3	OO
2	0	5	OO
OO	7	0	1
6	OO	9	0


D2 :

0	OO	3	OO
2	0	5	OO
9	7	0	1
6	OO	9	0


D3 :

0	10	3	4
2	0	5	6
9	7	0	1
6	16	9	0


D4 :

0	10	3	4
2	0	5	6
7	7	0	1
6	16	9	0

Process finished with exit code 0

 */