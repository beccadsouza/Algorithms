package DAA;
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

                    System.out.println(queen + " being compared w " + x);

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

        System.out.println("Queen "+queen[1].id+" created.");

        while(filled<N){

            System.out.println("FILLED : "+filled);
            repeat = true;

            queen[filled+1] = new Queen(filled + 1);
            stack.push(queen[filled+1]);

            while(repeat){

                if(noConflict(stack.peek())){

                    System.out.println(stack.peek()+" w no conflict");
                    filled++;
                    repeat = false;

                }

                else if(stack.peek().column!=N){

                    stack.peek().column++;
                    System.out.println("curr column "+stack.peek().column);

                    if(noConflict(stack.peek())){
                        System.out.println(stack.peek()+" w conflict");
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

        for (Queen x : stack)
            System.out.println(x);

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