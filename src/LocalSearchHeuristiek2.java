import org.jgrapht.util.MathUtil;

import java.util.*;

public class LocalSearchHeuristiek2 {
    private char [] besteoplossing;
    private int aantalelements;
    private int lengte;
    private List<String> combinationlist;
    Random random;

    public LocalSearchHeuristiek2() {
        besteoplossing=null;
        aantalelements=0;
        lengte=0;
        random = new Random(1);
    }

    public LocalSearchHeuristiek2(int aantalelements, int lengte, List<String> combinationlist) {
        besteoplossing= new char[aantalelements];
        this.aantalelements=aantalelements;
        this.lengte = lengte;
        this.combinationlist = combinationlist;
        random = new Random(1);
    }

    public void start() {
        boolean oplossinggevonden = false;
        //constructie oplossing
        Collections.sort(combinationlist);

        //eerst initiele opl
        besteoplossing = maakinitieleopl();


//123121321
 //       123412314231243121342132413214321

     /*   besteoplossing[0] = '1';
        besteoplossing[1] = '2';
        besteoplossing[2] = '1';
        besteoplossing[3] = '2';
        besteoplossing[4] = '3';
        besteoplossing[5] = '2';
        besteoplossing[6] = '3';
        besteoplossing[7] = '2';
        besteoplossing[8] = '3';
*/


       // goedeswap(0,besteoplossing);
        insertpermutation(0,besteoplossing);

        System.out.println("finale oplossing: " + new String(besteoplossing,0,aantalelements));


    }


    public boolean swap(int index1, int index2,char[] oplossing){       //TODO NIET ALLES IN MAP STEKEN
        if(oplossing[index1]!=oplossing[index2]){
            char temp = oplossing [index1];
            oplossing[index1] = oplossing [index2];
            oplossing[index2] = temp;
            return true;
        }

        return true;
    }

    public void goedeswap(int index,char [] oplossing){

        for(int i=index;i<aantalelements-1;i++){
            char [] neighbouropl = oplossing.clone();

            boolean nuttigeswap = swap(index,i+1,neighbouropl);

            System.out.println(new String(neighbouropl,0,aantalelements));

            if(controlleerAlles(neighbouropl)){
                besteoplossing = neighbouropl.clone();
            }

            if(nuttigeswap){
                goedeswap(index+1,neighbouropl);            //recursie bij nuttige swap
            }

        }
    }

    public void insertpermutation(int index, char []oplossing){
        //TODO NIET MEER RANDOM
        index = random.nextInt(aantalelements-lengte-1);
        System.out.println(index);


        String permutation = getNodigePermutation(oplossing);
        System.out.println(permutation);
        char [] elements = permutation.toCharArray();
        for(int i=index; i<index+lengte;i++){                 //INVOEGEN PERMUTATIE
            oplossing[i] = elements[i];
        }


    }


    public boolean controlleerAlles(char [] oplossing){
        Set<String> aanwezigestrings = new HashSet<String>();
        for(int i=0; i<aantalelements-lengte+1;i++){
            String string = getstringpos(i,oplossing);
            if(uniqueCharacters(string)){
                aanwezigestrings.add(string);               //adding to booleanset
            }
        }

        if(aanwezigestrings.size()==combinationlist.size()){
            return true;
        }
        return false;

    }

    public String getNodigePermutation(char [] oplossing){
        Set<String> aanwezigestrings = new HashSet<String>();
        ArrayList<String> combinaties = new ArrayList<>(combinationlist);
        for(int i=0; i<aantalelements-lengte+1;i++){
            String string = getstringpos(i,oplossing);
            if(uniqueCharacters(string)){
                aanwezigestrings.add(string);               //adding to booleanset
            }
        }

        combinaties.removeAll(aanwezigestrings);

        if(combinaties.size()==0){
            return combinationlist.get(0);
        }

        return combinaties.get(0);
    }


    //Methode dat string van bepaalde positie returned
    public String getstringpos(int index, char [] oplossing){              //TODO kan nog sneller?
        return new String(oplossing , index, lengte);
    }

    public int getGraadVanOplossing(char [] oplossing){
        Set<String> aanwezigestrings = new HashSet<String>();
        for(int i=0; i<aantalelements-lengte+1;i++){
            String string = getstringpos(i,oplossing);
            if(uniqueCharacters(string)){
                aanwezigestrings.add(string);               //adding to booleanset
            }
        }

        return aanwezigestrings.size();

    }

    public int getGraadVanOplossing2(char[] oplossing){
        int teller = 0;

        for(int i=0; i<aantalelements-1;i++){
            if(oplossing[i]==oplossing[i+1]){
                teller++;
            }
        }

        return teller;
    }

    boolean uniqueCharacters(String str)
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

    public char[] maakinitieleopl(){
       char[] oplossing = new char[aantalelements];
        ArrayList<Character> lijst = new ArrayList<Character>();
        int factorial = (int) MathUtil.factorial(lengte-1);     //k-1 factorial
        for(int i=0; i<factorial;i++){
            for(int j=0; j<lengte;j++){
                char character = (char) (j +'1');                           //omzetten naar character
                lijst.add(character);
            }
        }

        while(lijst.size()<aantalelements){
            char random2 = (char)((Math.random() * lengte) + '1');
            lijst.add(random2);         //random overige elements
        }

        Collections.shuffle(lijst);

        for(int i=0; i<lijst.size();i++){
            oplossing[i] =  lijst.get(i);
        }

        return oplossing;
    }


    public char[] getBesteoplossing() {
        return besteoplossing;
    }

    public void setBesteoplossing(char[] besteoplossing) {
        this.besteoplossing = besteoplossing;
    }

    public int getAantalelements() {
        return aantalelements;
    }

    public void setAantalelements(int aantalelements) {
        this.aantalelements = aantalelements;
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte(int lengte) {
        this.lengte = lengte;
    }

    public List<String> getCombinationlist() {
        return combinationlist;
    }

    public void setCombinationlist(List<String> combinationlist) {
        this.combinationlist = combinationlist;
    }
}
