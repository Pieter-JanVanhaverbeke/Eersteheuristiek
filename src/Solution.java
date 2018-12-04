import java.util.*;

public class Solution {
    private char [] solutionarray;
    private int lengte;
    private int aantalelements;
    private int combinationlistsize;

    public Solution(int lengte, int aantalelements, int combinationlistsize) {
        this.solutionarray = new char [lengte];
        this.lengte = lengte;
        this.aantalelements = aantalelements;
        this.combinationlistsize = combinationlistsize;
    }

                //VERSCHILLENDE MOVES
    public void swap(char[] oplossing,int index1, int index2){       //TODO NIET ALLES IN MAP STEKEN
        char temp = oplossing [index1];
        oplossing[index1] = oplossing [index2];
        oplossing[index2] = temp;
    }


    public void insertpermutation(int index,String permutation,char []oplossing){
        char [] elements = permutation.toCharArray();
        for(int i=0; i<lengte;i++){                 //INVOEGEN PERMUTATIE
            oplossing[index+i] = elements[i];
        }
    }


    public String getstringpos(int index, char [] oplossing){              //TODO kan nog sneller?
        return new String(oplossing , index, lengte);
    }





    //CHECKEN OF OPLOSSING FEASIBLE IS

    public boolean controlleerAlles(char [] oplossing){
        Set<String> aanwezigestrings = new HashSet<>();
        for(int i=0; i<aantalelements-lengte+1;i++){
            String string = getstringpos(i,oplossing);
            if(uniqueCharacters(string)){
                aanwezigestrings.add(string);               //adding to booleanset
            }
        }

        if(aanwezigestrings.size()==combinationlistsize){
            return true;
        }
        return false;
    }


    //CHECKEN VAN GRAAD OPLOSSING

    public int getGraadVanOplossing(char [] oplossing){
        Set<String> aanwezigestrings = new HashSet<String>();
        for(int i=0; i<lengte-aantalelements+1;i++){
            String string = getstringpos(i,oplossing);
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
}
