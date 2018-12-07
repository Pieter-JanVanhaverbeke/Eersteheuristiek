import java.util.*;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Solution {
    private char [] solutionarray;
    private int lengte;
    private int aantalelements;
    private int combinationlistsize;
    private int score;

    public Solution(int lengte, int aantalelements, int combinationlistsize) {
        this.solutionarray = new char [aantalelements];
        this.lengte = lengte;
        this.aantalelements = aantalelements;
        this.combinationlistsize = combinationlistsize;
        this.score = -1;
    }

    public Solution(Solution solution){
        this.lengte = solution.lengte;
        this.aantalelements = solution.aantalelements;
        this.combinationlistsize = solution.getCombinationlistsize();
        solutionarray = solution.getSolutionarray().clone();
       // solutionarray = new char [aantalelements];
      //  System.arraycopy(solution.getSolutionarray(),0,solutionarray,0,lengte);

        this.score = solution.score;
    }

                //VERSCHILLENDE MOVES
    public void swap(int index1, int index2){       //TODO NIET ALLES IN MAP STEKEN
        char temp = solutionarray [index1];
        solutionarray[index1] = solutionarray [index2];
        solutionarray[index2] = temp;
    }


    public void insertpermutation(int index, String permutation){
        char [] elements = permutation.toCharArray();
        for(int i=0; i<lengte;i++){                 //INVOEGEN PERMUTATIE
            solutionarray[index+i] = elements[i];
        }
    }

    public void change(int index, char c){
        solutionarray[index] = c;
    }


    public String getstringpos(int index){              //TODO kan nog sneller?
        return new String(solutionarray , index, lengte);
    }


    //INTITIALISEREN
    public void initialiseerrandom(Random random){
        solutionarray = new char [aantalelements];
        for(int i=0; i<aantalelements;i++){
            char nummer = (char) (random.nextInt(lengte) + '1');
            solutionarray[i] = nummer;
        }
    }

    public void intitialiseerFeasible(){
        solutionarray[0] = '2';
        solutionarray[1] = '1';
        solutionarray[2] = '1';
        solutionarray[3] = '3';
        solutionarray[4] = '2';
        solutionarray[5] = '3';
        solutionarray[6] = '2';
        solutionarray[7] = '2';
        solutionarray[8] = '3';
    }





    //CHECKEN OF OPLOSSING FEASIBLE IS

    public boolean controlleerAlles(){
        Set<String> aanwezigestrings = new HashSet<>();
        for(int i=0; i<aantalelements-lengte+1;i++){
            String string = getstringpos(i);
            if(uniqueCharacters(string)){
                aanwezigestrings.add(string);               //adding to booleanset
            }
        }

        if(aanwezigestrings.size()==combinationlistsize){
            return true;
        }
        return false;
    }

    public String getNodigePermutation(List<String> combinationlist,Random random){              //TODO KAN EFFICIENTER
        Set<String> aanwezigestrings = new HashSet<>();
        for(int i=0; i<aantalelements-lengte+1;i++){
            String string = getstringpos(i);
            if(uniqueCharacters(string)){
                aanwezigestrings.add(string);               //adding to booleanset
            }
        }

        combinationlist.removeAll(aanwezigestrings);
        if(combinationlist.size()!=0){
            int index = random.nextInt(combinationlist.size());
            return combinationlist.get(index);
        }
        else return "";

    }


    //CHECKEN VAN GRAAD OPLOSSING

    public int getGraadVanOplossing(){
        Set<String> aanwezigestrings = new HashSet<String>();
        for(int i=0; i<aantalelements-lengte+1;i++){
            String string = getstringpos(i);
            if(uniqueCharacters(string)){
                aanwezigestrings.add(string);               //adding to booleanset

            }
        }

        return aanwezigestrings.size();
    }

    public int getGraadVanOplossing2(){
        int teller = 0;

        for(int i=0; i<lengte-1;i++){
            if(solutionarray[i]==solutionarray[i+1]){
                teller++;
            }
        }

        return teller;
    }


    private boolean uniqueCharacters(String str)
    {
        char[] chArray = str.toCharArray();

        // Using sorting
        // Arrays.sort() uses binarySort in the background
        // for non-primitives which is of O(nlogn) time complexity
        Arrays.sort(chArray);

        for (int i = 0; i < chArray.length - 1; i++) {
            // if the adjacent elements are not
            // equal, move to next element
            if (chArray[i] != chArray[i + 1])
                continue;

                // if at any time, 2 adjacent elements
                // become equal, return false
            else
                return false;
        }
        return true;
    }



    //GETTERS EN SETTERS

    public char[] getSolutionarray() {
        return solutionarray;
    }

    public void setSolutionarray(char[] solutionarray) {
        this.solutionarray = solutionarray;
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

    public int getCombinationlistsize() {
        return combinationlistsize;
    }

    public void setCombinationlistsize(int combinationlistsize) {
        this.combinationlistsize = combinationlistsize;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return  Arrays.toString(solutionarray);
    }
}
