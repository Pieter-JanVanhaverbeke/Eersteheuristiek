import org.jgrapht.util.MathUtil;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        //List<String> superpermutation = new ArrayList<>();
        // String [] superpermutation3 = new String [9];
        int permutationlengte = 3;
        int superstringlengte = 9;
        Solution solution;
        Permutation permutation = new Permutation(permutationlengte);       //genereer permutatie


        Set<String> permutationset = permutation.getPermutationset();
      //  System.out.println("combinatielijstsize: " + permutation.getPermutationlijst().size());
       // int factorial = (int) MathUtil.factorial(4-1);
      //  System.out.println("factorial: " + factorial);

        System.out.println("oplossing heuristiek 1: ");

        Graafheuristiek1 opl1 = new Graafheuristiek1(permutation.getPermutationlijst());
        String opl = opl1.start();

        solution = new Solution(opl, permutationlengte);
        solution.setPermutationlijst(permutation.getPermutationlijst());
        System.out.println(solution.ControlleerAlles());                    //uitprinten feasible solution

        System.out.println();
        System.out.println();

        //LOCAL SEARCH HEURISTIEK
        System.out.println("oplossing localsearch1 heuristiek: ");
        LocalSearchHeuristiek2 opl2 = new LocalSearchHeuristiek2(superstringlengte,permutationlengte,permutation.getPermutationlijst());
        opl2.setCombinationlist(permutation.getPermutationlijst());
        opl2.start2();

    }
}

//        boolean check = controlleerAlles_B(opl,permutationset,permutationlengte);
//        LocalSearchHeuristiek1 opl2 = new LocalSearchHeuristiek1();
//        opl2.setOplossingstring(opl);


  //      boolean check = opl2.ControlleerAlles(permutation.getPermutationlijst());
       // boolean check = controlleerAlles_B(opl)
  //      System.out.println(check);


 //       opl2.setOplossingstring(opl);
 //       opl2.zetDeOplossing();
//        opl2.start1();





   /* private static Set<String> permuteset(String str, int l, int r) {
        Set<String> permuteset = new HashSet<String>();
        if (l == r)
            permuteset.add(str);
            //System.out.println(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permuteset(str, l + 1, r);
                str = swap(str, l, i);
            }
        }

        return permuteset;

    }

    public static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
*/


