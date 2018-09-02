package DAA.Graphical;

import java.util.Scanner;
import java.util.Stack;

/*
 * Created by Rebecca D'souza on 20/03/18
 * */

class Queen{

    int row;
    int column;
    int id;

    Queen(int id) {

        this.row = id;
        this.column = 1;
        this.id = id;

    }

    public String toString() {

        return "Queen "+id+" placed at ["+row+","+column+"]";

    }
}

public class NQueen{

    private static Stack<Queen> stack = new Stack<>();

    private static boolean noConflict(Queen queen){

        for(Queen x : stack){

            if(x.id<queen.id) {

                if (x.column == queen.column|| Math.abs(queen.row - x.row) == Math.abs(queen.column - x.column))
                    return false;

            }

        }

        return true;
    }
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int filled = 1;
        boolean repeat;

        Queen [] queen = new Queen[N+1];
        queen[1] = new Queen(1);
        stack.push(queen[1]);


        while(filled<N){

            repeat = true;

            queen[filled+1] = new Queen(filled + 1);
            stack.push(queen[filled+1]);

            while(repeat){

                if(noConflict(stack.peek())){

                    filled++;
                    repeat = false;

                }

                else if(stack.peek().column!=N){

                    stack.peek().column++;

                    if(noConflict(stack.peek())){
                        filled++;
                        repeat = false;
                    }
                }

                else if(stack.peek().column==N) {

                    stack.pop();
                    filled--;

                    if(stack.peek().column==N){
                        stack.pop();
                        filled--;
                    }

                    stack.peek().column++;
                    repeat = true;

                }

            }
        }

        System.out.println();
        for (int x = 1; x<N+1; x++)
            System.out.println(queen[x]);

        System.out.println();

        for(int i =N;i>0;i--){
            for(int j = 1;j<N+1;j++){
                if(j==queen[i].column)
                    System.out.print("[Q]");
                else System.out.print("[ ]");
            }
            System.out.println();

        }
    }
}

/*

8

Queen 1 placed at [1,1]
Queen 2 placed at [2,5]
Queen 3 placed at [3,8]
Queen 4 placed at [4,6]
Queen 5 placed at [5,3]
Queen 6 placed at [6,7]
Queen 7 placed at [7,2]
Queen 8 placed at [8,4]

[ ][ ][ ][Q][ ][ ][ ][ ]
[ ][Q][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][Q][ ]
[ ][ ][Q][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][Q][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][Q]
[ ][ ][ ][ ][Q][ ][ ][ ]
[Q][ ][ ][ ][ ][ ][ ][ ]

Process finished with exit code 0

 */

