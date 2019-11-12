package lab1;
import java.util.Scanner;

public class Fibo {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if (n < 1 || n > 45){
            System.exit(-1);
        }
        int[] tab = new int[n];
        tab[0] = 0;
        tab[1] = 1;
        for (int i = 2; i < n; i++){
            tab[i] = tab[i-1] + tab[i-2];
            System.out.print(tab[i] + " ");
        }

    }
}