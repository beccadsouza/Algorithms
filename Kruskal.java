package DAA;
import java.util.Scanner;
class edge{
    public int a,b,wt;
    edge(int a, int b, int wt){
        this.a = a;
        this.b = b;
        this.wt = wt;
    }
}
public class Kruskal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int MSTedges = 1;
        int index = 0;
        int noNodes,noEdges,u,v,x,y,totalCost=0;
        //System.out.print("Enter the total no. nodes : ");
        noNodes = sc.nextInt();
        //System.out.print("Enter the total no. edges : ");
        noEdges = sc.nextInt();
        int[] parent = new int[noNodes+1];
        edge[] queue = new edge[noEdges];
        for(int i = 0;i<noEdges;i++)
            queue[i] = new edge(sc.nextInt(),sc.nextInt(),sc.nextInt());
        for(int i = 0;i<noEdges;i++)
            for(int j = 0;j<noEdges-1;j++)
                if(queue[j].wt>queue[j+1].wt){
                    edge temp;
                    temp = queue[j];
                    queue[j] = queue[j+1];
                    queue[j+1] = temp;
                }
        for(int i = 0;i<noEdges;i++){
            for(int j = 0;j<noEdges-1;j++){
                if(queue[j].wt==queue[j+1].wt){
                    int wt1 = queue[j].a + queue[j].wt + queue[j].b;
                    int wt2 = queue[j+1].a + queue[j+1].wt + queue[j+1].b;
                    if(wt2<wt1){
                        edge temp;
                        temp = queue[j];
                        queue[j] = queue[j+1];
                        queue[j+1] = temp;
                    }
                }
            }
        }
        while (MSTedges < noNodes) {
            edge curr = queue[index++];
            u = curr.a;
            v = curr.b;
            x = u;
            y = v;
            while (parent[x] != 0)
                x = parent[x];
            while (parent[y] != 0)
                y = parent[y];
            if (x != y) {
                MSTedges++;
                System.out.println("Edge found (" + u + "," + v + ") of weight " + curr.wt);
                totalCost += curr.wt;
                System.out.println("Parent[" + v + "] = " + u);
                parent[v] = u;
            }
        }
        System.out.println("The weight of the minimum spanning tree is " + totalCost);
    }
}
