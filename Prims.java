package DAA;
import java.util.Scanner;
/*
* Created by Rebecca D'souza on 20/03/18
* */
public class Prims {
    public static void main(String args[]) {
        int totalCost = 0,min = 100,par=0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int edges[][] = new int[n + 1][n + 1];
        boolean found = false;
        boolean visited[] = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++)
            for (int j = 1; j < n + 1; j++)
                edges[i][j] = 100;
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int wt = sc.nextInt();
            if (u != v) {
                edges[u][v] = Math.min(edges[u][v], wt);
                edges[v][u] = Math.min(edges[u][v], wt);
            }
        }
        int src = sc.nextInt();
        visited[src] = true;
        for (int i = 1; i <= n; i++) {
            if (visited[i])
                for (int k = 1; k <= n; k++)
                    if (edges[i][k] < min && !visited[k]) {
                        min = edges[i][k];
                        par = i;
                        src = k;
                        found = true;
                    }
            if (i == n && found) {
                System.out.printf("Edge having weight %d joining %d and %d has been selected\n\n", min, par, src);
                totalCost += min;
                found = false;
                visited[src] = true;
            }
            if (i == n)
                for (int j = 1; j < n + 1; j++)
                    if (!visited[j]) {
                        i = 0;
                        min = Integer.MAX_VALUE;
                    }
        }
        System.out.println("The weight of the minimum spanning tree is " + totalCost);
    }
}
