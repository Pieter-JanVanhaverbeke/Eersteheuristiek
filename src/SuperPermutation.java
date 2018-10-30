import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class SuperPermutation {
    private String superstring;
    int lengte;
    private Map<String, Integer> permutationlijst;
    private int aantalelements;



    public SuperPermutation(int lengte, int aantalelements){
        this.lengte = lengte;
        superstring = "";
        permutationlijst = new HashMap<>();
        this.aantalelements = aantalelements;
    }

    public String getSuperstring() {
        return superstring;
    }

    public void setSuperstring(String superstring) {
        this.superstring = superstring;
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte(int lengte) {
        this.lengte = lengte;
    }

    public int getAantalelements() {
        return aantalelements;
    }

    public void setAantalelements(int aantalelements) {
        this.aantalelements = aantalelements;
    }

    public Map<String, Integer> getPermutationlijst() {
        return permutationlijst;
    }

    public void setPermutationlijst(Map<String, Integer> permutationlijst) {
        this.permutationlijst = permutationlijst;
    }

    public void initialiseerstringRandom(){
        int randomNum;
        for(int i=0; i<lengte;i++){
            randomNum = ThreadLocalRandom.current().nextInt(1, aantalelements+1);
            superstring = superstring +  randomNum;
        }
    }


    public void initialiseerMap(List<String> lijst, int combinatielengte){
        for (String element : lijst) {
            permutationlijst.put(element,0);
        }
        System.out.println();
        for(int i=0; i<superstring.length()-combinatielengte+1;i++){
            String controlestring = superstring.substring(i,combinatielengte+i);
            if(!repeat(controlestring)){
                permutationlijst.merge(controlestring, 1, Integer::sum);
            }
        }
    }

    public boolean controleerOplossing(){
        for (int value : permutationlijst.values()) { //alle waardes moeten groter zijn dan 0
            if (value==0) return false;                 //voorwaarde niet voldaan
        }
        return true;
    }



    public boolean repeat(String string){
        Set<Character> controlleerset = new HashSet<>();
        controlleerset.add(string.charAt(0));
        for(int i=1; i<string.length();i++){
            if(controlleerset.contains(string.charAt(i))){
                return true;
            }
        }

        return false;
    }


    public void printmap(){
        for (String name: permutationlijst.keySet()){
            String key =name.toString();
            String value = permutationlijst.get(name).toString();
            System.out.println(key + " " + value);


        }

    }
}
