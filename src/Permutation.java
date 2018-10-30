import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {
    //private String str;
    private int lengte;
    private List<String> permutationlijst;
    private Set<String> permutationset;

    public Permutation(int lengte){
        String str = "";

        this.lengte = lengte;
        this.permutationlijst = new ArrayList<String>();

        str = "";
        for(int i=0; i<lengte;i++){
            str = str + (i+1);
        }

        permutationlijst = permute(str, 0, lengte-1);
        Set<String> permutationset = permuteset(str,0,lengte-1);


  //      for(int i=0; i<permutationlijst.size();i++){
      //      System.out.println(permutationlijst.get(i));
  //      }
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte(int lengte) {
        this.lengte = lengte;
    }

    public List<String> getPermutationlijst() {
        return permutationlijst;
    }

    public void setPermutationlijst(List<String> permutationlijst) {
        this.permutationlijst = permutationlijst;
    }

    public Set<String> getPermutationset() {
        return permutationset;
    }

    public void setPermutationset(Set<String> permutationset) {
        this.permutationset = permutationset;
    }

    private List<String> permute(String str, int l, int r) {
        if (l == r)
            permutationlijst.add(str);
            //System.out.println(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }

        return permutationlijst;

    }


    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public  Set<String> permuteset(String str, int l, int r) {
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

}