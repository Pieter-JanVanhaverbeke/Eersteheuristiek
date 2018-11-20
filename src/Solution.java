import java.util.ArrayList;
import java.util.List;

public class Solution {
    String oplossing;
    int permutationlengte;
    List<String> permutationlijst;

    public Solution(String oplossing, int permutationlengte) {
        this.oplossing = oplossing;
        this.permutationlengte = permutationlengte;
    }

    public String getOplossing() {
        return oplossing;
    }

    public void setOplossing(String oplossing) {
        this.oplossing = oplossing;
    }

    public int getPermutationlengte() {
        return permutationlengte;
    }

    public void setPermutationlengte(int permutationlengte) {
        this.permutationlengte = permutationlengte;
    }

    public List<String> getPermutationlijst() {
        return permutationlijst;
    }

    public void setPermutationlijst(List<String> permutationlijst) {
        this.permutationlijst = permutationlijst;
    }

    public boolean ControlleerAlles(){
        for(int i=0; i<permutationlijst.size();i++){
            if(!oplossing.contains(permutationlijst.get(i))){
                return false;
            }
        }
        return true;
    }


}
