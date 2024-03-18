package Universidad;
import java.util.Scanner;

class Solution3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String numeros = sc.nextLine();
        int n, d;
        String partes[] = numeros.split(" ");
        n = Integer.parseInt(partes[0]);
        d = Integer.parseInt(partes[1]);
        boolean c = n<0 ^ d<0 ? true : false;
        n = n > 0 ? n : -n;
        d = d > 0 ? d : -d;
        String propio = c ? (n != d ? "-"+n + "/" + d : "-1/1") : (n != d ? n + "/" + d : "1/1");
        String mixto = c ? (n/d == 0 ? " -" + n%d+"/"+d: " -"+n/d+" "+ (n%d != 0 ? n%d+"/"+d : "")) : (n/d == 0 ? " " + n%d+"/"+d: " "+n/d+" "+ (n%d != 0 ? n%d+"/"+d : ""));
        System.out.println(propio + mixto);
        sc.close();
    }
}