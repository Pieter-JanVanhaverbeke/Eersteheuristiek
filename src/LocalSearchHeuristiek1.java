import org.jgrapht.util.MathUtil;
import java.util.*;

public class LocalSearchHeuristiek1 {
    private char [] besteoplossing;              //TODO omzetten naar char
    private char [] oplossing;
    private char [] neighbouroplossing;
    private int aantalelements;
    private int lengte;
    private List<Character> minlijst;
    private List<String> combinationlist;
    private Set<String> aanwezigestrings;
    private Set<String> tabulijst;
   // private int[] frequenties;
    private int bestegraad;

    boolean validoplossing;


    public LocalSearchHeuristiek1() {
        this.oplossing = null;
        aantalelements=0;
        lengte=0;
    }

    public LocalSearchHeuristiek1(int aantalelements, int lengte, List<String> combinationlist) {
        this.oplossing = null;
        this.aantalelements=aantalelements;
        this.lengte = lengte;
        this.combinationlist = combinationlist;
        this.aanwezigestrings = new HashSet<String>();

     //   this.frequenties = new int[combinationlist.size()];
        bestegraad = 0;
        validoplossing = false;

    }

    public char[] getBesteoplossing() {
        return besteoplossing;
    }

    public void setBesteoplossing(char[] besteoplossing) {
        this.besteoplossing = besteoplossing;
    }

    public char[] getOplossing() {
        return oplossing;
    }

    public void setOplossing(char[] oplossing) {
        this.oplossing = oplossing;
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


    public void start() {
        boolean oplossinggevonden = false;
        //constructie oplossing
        Collections.sort(combinationlist);

        //eerst initiele opl
        maakinitieleopl();
        //    System.out.println(new String(oplossing,0,aantalelements));
        besteoplossing = oplossing.clone();
//123121321

        besteoplossing[0] = '1';
        besteoplossing[1] = '1';
        besteoplossing[2] = '2';
        besteoplossing[3] = '2';
        besteoplossing[4] = '2';
        besteoplossing[5] = '2';
        besteoplossing[6] = '3';
        besteoplossing[7] = '3';
        besteoplossing[8] = '3';
        oplossing = besteoplossing.clone();

    //   String bla = new String(besteoplossing,0,aantalelements);

        goedeswap(0,oplossing);
        System.out.println("valid: " + validoplossing );
        System.out.println("finale oplossing: " + new String(besteoplossing,0,aantalelements));


    }

       /* for(int i=0; i<combinationlist.size();i++){
            System.out.println(combinationlist.get(i));
        }




*/

       /*

       oplossing = besteoplossing.clone();
if(controlleerAlles()){
    System.out.println("true true");
}
      //  while(!oplossinggevonden) {
     //   overloopswappen();
     //       deepSearch();
           // oplossinggevonden = controlleerAlles();
       // }

        System.out.println("finale oplossing: " + new String(besteoplossing,0,aantalelements));
*/

    public void change(int index, char character){
        oplossing[index] = character;
    }






    public void swap(int index1, int index2){       //TODO NIET ALLES IN MAP STEKEN
        char temp = oplossing [index1];
        oplossing[index1] = oplossing [index2];
        oplossing[index2] = temp;
    }

    public void swap(int index1, int index2,char[] oplossing){       //TODO NIET ALLES IN MAP STEKEN
        char temp = oplossing [index1];
        oplossing[index1] = oplossing [index2];
        oplossing[index2] = temp;
    }


    public void goedeswap(int index,char [] oplossing){

        for(int i=index;i<aantalelements-1;i++){
            char [] opl = oplossing;
           // for(int j=index+1; j<aantalelements-1;j++){
                swap(index,i+1,opl);
            System.out.println(new String(oplossing,0,aantalelements));
            if(controlleerAlles()){
                besteoplossing = opl.clone();

            }

            goedeswap(index+1,opl);
         //   }

        }
    }


    public void recursieswap(int index){
        for(int i=index;i<aantalelements-1;i++){
            System.out.println(i);
            for(int j=index+1; j<aantalelements-1;j++){
                swap(i,j);
                //controleren
                if(controlleerAlles()){
                    besteoplossing = oplossing.clone();
                }
           //     System.out.println( new String(oplossing,0,aantalelements));

            }
            recursieswap(index+1);  //plaats opschuiven

        }

    }


    public void deepSearch(){
        int teller = 0;

        while(teller<aantalelements){
            oplossing = besteoplossing.clone();
            //changevalues [teller]
            System.out.println(teller);
              changevalue(teller);
              teller++;

            //for iedere change, swap alle elementen
        }

    }

    public void insertpermutation(){

    }




    public void changevalue(int index){
        char nummer = oplossing[index];

        for(int i=0;i<lengte;i++){
            char nieuwnummer = (char) (i + '1');
            if(nummer != nieuwnummer ){                 //TODO KAN NOG EFFICIENTER
                change(index, nieuwnummer);
                overloopswappen();                        //TODO NIET ALLES OVERLOPEN
            }
        }
        change(index,besteoplossing[index]);        //Reverten van changes
    }


    public void swapAlles(){
     /*   for(int i=0; i<aantalelements;i++){
            swapvolgende(i);
        }*/
     //overloopswappen();
    }





    public void overloopswappen(){
     //   System.out.println(new String(oplossing,0,aantalelements));
        for(int i=0 ;i<aantalelements;i++){
            for(int j=0; j<aantalelements;j++){
                if(i!=j){
                    swap(i,j);
                    if(!controlleerAlles()){
                        System.out.println(new String(oplossing,0,aantalelements));

                        swap(i,j);
                    }
                    else{

                        validoplossing = true;
                        besteoplossing = oplossing.clone();
                    }
                }
            }
        }
    }




    public void swapvolgende(int index){
        for(int i=index+1; i<aantalelements;i++){
            swap(index,i);                      //alles swappen rechts van character
            if(!controlleerAlles()){
             //   System.out.println("bla");
             swap(index,i);
            }

            //Beste oplossing zetten
            else{
                besteoplossing = oplossing.clone();
            }

            System.out.println(new String(oplossing,0,aantalelements));


        }
    }


    public void maakinitieleopl(){
        oplossing = new char[aantalelements];
        ArrayList<Character> lijst = new ArrayList<Character>();
        int factorial = (int) MathUtil.factorial(lengte-1);     //k-1 factorial
        for(int i=0; i<factorial;i++){
            for(int j=0; j<lengte;j++){
                char character = (char) (j +'1');                           //omzetten naar character
                lijst.add(character);
            }
        }
        minlijst = new ArrayList<>(lijst);
        while(lijst.size()<aantalelements){
        char random2 = (char)((Math.random() * lengte) + '1');
        lijst.add(random2);         //random overige elements
  }

        Collections.shuffle(lijst);

        for(int i=0; i<lijst.size();i++){
            oplossing[i] =  lijst.get(i);
        }
    }

    public boolean controlleerAlles(){
        aanwezigestrings.clear();
        for(int i=0; i<aantalelements-lengte+1;i++){
            String string = getstringpos(i);
            if(uniqueCharacters(string)){
                aanwezigestrings.add(string);               //adding to booleanset
            }
        }

        if(aanwezigestrings.size()==combinationlist.size()){
            System.out.println(aanwezigestrings.size());
            validoplossing = true;
            return true;
       /*     for (String s : aanwezigestrings) {
                System.out.println(s);
            }*/
        }
            return false;

    }

    public boolean controlleerAlles(char [] oplossing){
        aanwezigestrings.clear();
        for(int i=0; i<aantalelements-lengte+1;i++){
            String string = getstringpos(i);
            if(uniqueCharacters(string)){
                aanwezigestrings.add(string);               //adding to booleanset
            }
        }

        if(aanwezigestrings.size()==combinationlist.size()){
            System.out.println(aanwezigestrings.size());
            validoplossing = true;
            return true;
       /*     for (String s : aanwezigestrings) {
                System.out.println(s);
            }*/
        }
        return false;

    }










    public void randomswap(){
        int index1 = (int)(Math.random() * (aantalelements-lengte));      //random element tussen mogelijke indices
        int index2 = (int)(Math.random() * (aantalelements-lengte));

        Set<Integer> indexset = new HashSet<Integer>();
        addIndexToSet(indexset,index1);
        addIndexToSet(indexset,index2);

        swap(index1,index2);


    }

    public void insertrandom(){
        //verander char[index]

        int index = (char)(Math.random() * (aantalelements-lengte));
        Set<Integer> indexset = new HashSet<Integer>();
        addIndexToSet(indexset,index);


        oplossing[index] = (char)((Math.random() * lengte) +'1');      //naar random ander element



    }

    public void insert(int index, char element){
        //verander char[index]
        oplossing[index] = element;    //naar ander element
    }




    //Methode dat string van bepaalde positie returned
    public String getstringpos(int index){              //TODO kan nog sneller?
       return new String(oplossing , index, lengte);
    }



    public void maakrandomopl(){
        ArrayList<Character> lijst = new ArrayList<>(minlijst);
        Collections.shuffle(lijst);
        for(int i=0; i<minlijst.size();i++){
            oplossing[i]=lijst.get(i);                                   //TODO oplossing in RAM houden
        }
        for(int j=minlijst.size();j<oplossing.length;j++){
            char character = (char)((Math.random() * lengte)+'1');
            oplossing[j]= character ;
        }


    }


    public Set addIndexToSet(Set<Integer> set, int index){
        for(int i=index-lengte+1; i<=index;i++){
            if(i>=0){
                set.add(i);
            }
        }

        return set;
    }

    public void printset(Set<Integer> set){
        for (Integer s : set) {
            System.out.println(s);
        }
    }

    //O[Nlog(N)]
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



}




