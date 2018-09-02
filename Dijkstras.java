package DAA;

import java.util.Scanner; //Scanner Function to take in the Input Values


public class Dijkstras {

    static Scanner scan; // scan is a Scanner Object

    public static void main(String[] args){

        int[] preD = new int[6];
        int min = 999, nextNode = 0; // min holds the minimum value, nextNode holds the value for the next node.
        scan = new Scanner(System.in);
        int[] distance = new int[6]; // the distance matrix
        int[][] matrix = new int[6][6]; // the actual matrix
        int[] visited = new int[6]; // the visited array

        System.out.println("Source node : ");
        int src = scan.nextInt();
        System.out.println("Enter the cost matrix");

        for(int i = 0; i < distance.length; i++){

            visited[i] = 0; //initialize visited array to zeros

            preD[i] = src;

            for(int j = 0; j < distance.length; j++){

                matrix[i][j] = scan.nextInt(); //fill the matrix

                if(matrix[i][j]==0){

                    matrix[i][j] = 999; // make the zeros as 999

                }

            }

        }
        distance = matrix[src]; //initialize the distance array
        visited[src] = 1; //set the source node as visited
        distance[src] = 0; //set the distance from source to source to zero which is the starting point

        for(int counter = 0; counter < 6; counter++){

            min = 999;

            for(int i = 0; i < 6; i++){

                if(min > distance[i] && visited[i]!=1){

                    min = distance[i];
                    nextNode = i;

                }

            }

            visited[nextNode] = 1;

            for(int i = 0; i < 6; i++){

                if(visited[i]!=1){

                    if(min+matrix[nextNode][i] < distance[i]){

                        distance[i] = min+matrix[nextNode][i];
                        preD[i] = nextNode;

                    }

                }

            }

        }

        for(int i = 0; i < 6; i++){

            System.out.print("|" + distance[i]);

        }
        System.out.println("|");

        int j;
        for(int i = 0; i < 6; i++){

            if(i!=src){

                System.out.print("Path = " + i);
                j = i;
                do{

                    j=preD[j];
                    System.out.print(" <- " + j);

                }while(j!=src);

            }

            System.out.println();

        }

    }

}