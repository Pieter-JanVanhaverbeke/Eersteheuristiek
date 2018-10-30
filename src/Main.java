import java.util.*;

public class Main {
    public static void main(String[] args) {
        //List<String> superpermutation = new ArrayList<>();
       // String [] superpermutation3 = new String [9];
        int permutationlengte = 6;
        int superstringlengte = 30;


       // Map<String, Integer> permutationmap = new HashMap<>();


        Permutation permutation = new Permutation(permutationlengte);       //genereer permutatie

        Set<String> permutationset =  permutation.getPermutationset();

    //    SuperPermutation superpermutation = new SuperPermutation(superstringlengte,permutationlengte);  //maak superstring


   //     superpermutation.initialiseerstringRandom();    //superstring random waarde

      //  superpermutation.setSuperstring("123412314231243121342132413214331");
 //       System.out.println(superpermutation.getSuperstring());


 //       superpermutation.initialiseerMap(permutation.getPermutationlijst(),permutationlengte);  //intitialiseer counter
 //       System.out.println(superpermutation.controleerOplossing()); //controleer oplossing eerste keer


        System.out.println("oplossing heuristiek 1: ");

        Graafheuristiek1 opl1 = new Graafheuristiek1(permutation.getPermutationlijst());
     //   opl1.start();
        String opl = opl1.start();
        boolean check = controlleerAlles_B(opl,permutationset,permutationlengte);
   //     LocalSearchHeuristiek1 opl2 = new LocalSearchHeuristiek1();
   //     opl2.setOplossingstring(opl);


 //       boolean check = opl2.ControlleerAlles(permutation.getPermutationlijst());
       // boolean check = controlleerAlles_B(opl)
    //    System.out.println(check);


 //       opl2.setOplossingstring(opl);
 //       opl2.zetDeOplossing();
//        opl2.start1();








    }

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

    public static boolean controlleerAlles_B(String opl,Set<String> set, int combsize){
        int teller = 0;
        for (int i=0; i<opl.length()-combsize;i++){
            String checkstring = opl.substring(teller,teller+combsize);
            set.remove(checkstring);
        }
        if (set.isEmpty()) return true;
        else return false;
    }

}
