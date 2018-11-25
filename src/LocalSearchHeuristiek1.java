import org.jgrapht.util.MathUtil;

import java.util.*;

public class LocalSearchHeuristiek1 {
    private char [] besteoplossing;              //TODO omzetten naar char
    private char [] oplossing;
    private String oplossingstring;
    private int aantalelements;
    private int lengte;
    private List<Character> minlijst;
    private List<String> combinationlist;
    private Map<Integer,String> oplossingmap;
    private Map<String,Integer> frequentiemap;


    public LocalSearchHeuristiek1() {
        this.oplossing = null;
        oplossingstring = null;
        aantalelements=0;
        lengte=0;
        oplossingmap = new HashMap<>();
        frequentiemap = new HashMap<>();
    }

    public LocalSearchHeuristiek1(int aantalelements, int lengte, List<String> combinationlist) {
        this.oplossing = null;
        this.oplossingstring = null;
        this.aantalelements=aantalelements;
        this.lengte = lengte;
        this.combinationlist = combinationlist;
        oplossingmap = new HashMap<>();
        frequentiemap = new HashMap<>();
        for(int i=0; i<combinationlist.size();i++){
            frequentiemap.put(combinationlist.get(i),0);
        }

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

    public String getOplossingstring() {
        return oplossingstring;
    }

    public void setOplossingstring(String oplossingstring) {
        this.oplossingstring = oplossingstring;
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

    public Map<Integer, String> getOplossingmap() {
        return oplossingmap;
    }

    public void setOplossingmap(Map<Integer, String> oplossingmap) {
        this.oplossingmap = oplossingmap;
    }

    public void start1(){
        boolean oplossinggevonden = false;
        //constructie oplossing

        //eerst initiele opl
        maakinitieleopl();

        while(!oplossinggevonden){
            maakrandomopl();
            for(int i=0; i<5;i++){
               randomswap();
                //oplossinggevonden = checkFeasible();
                oplossinggevonden = controlleerAlles();
           //     System.out.println(String.valueOf(oplossing));
           }
        }


            System.out.println("finale oplossing: " + String.valueOf(oplossing));
            printfrequentiemap();

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

        intioplossingmap();         //alle huidige combinaties mappen



    }

    public void swap(int index1, int index2){       //TODO NIET ALLES IN MAP STEKEN
     //   Set nieuweposities = new HashSet();


        char temp = oplossing [index1];
        oplossing[index1] = oplossing [index2];
        oplossing[index2] = temp;


    }

    public void randomswap(){
        int index1 = (int)(Math.random() * (aantalelements-lengte));      //random element tussen mogelijke indices
        int index2 = (int)(Math.random() * (aantalelements-lengte));

        Set<Integer> indexset = new HashSet<Integer>();
        addIndexToSet(indexset,index1);
        addIndexToSet(indexset,index2);

        verlaagFrequenties(indexset);

        swap(index1,index2);

        verhoogFrequenties(indexset);

    }

    public void insertrandom(int index){
        //verander char[index]
        oplossing[index] = (char)(Math.random() * aantalelements);      //naar random ander element
    }

    public void insert(int index, char element){
        //verander char[index]
        oplossing[index] = element;    //naar ander element
    }





    public boolean checkFeasible(){
        for(int i=0; i<combinationlist.size();i++){
            if (frequentiemap.get(combinationlist.get(i))==0){
                return false;
            }

        }
        return true;
    }

    public void intioplossingmap(){

        oplossingmap.clear();
        frequentiemap.clear();

        for(int i=0; i<combinationlist.size();i++){
            frequentiemap.put(combinationlist.get(i),0);
        }

        for(int i=0;i<aantalelements-lengte+1;i++){
            String comb = getstringpos(i);
            if(uniqueCharacters(comb)) {
                oplossingmap.put(i, comb);
                frequentiemap.merge(comb, 1, Integer::sum); //TODO snellere manier?
            }
        }
    }


    //Methode dat string van bepaalde positie returned
    public String getstringpos(int index){              //TODO kan nog sneller?
       return new String(oplossing , index, lengte);
    }


    public boolean controlleerAlles(){
        oplossingstring = String.valueOf(oplossing);
        for(int i=0; i<combinationlist.size();i++){
            if(!oplossingstring.contains(combinationlist.get(i))){
                return false;
            }
        }

        return true;
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

        intioplossingmap();

    }


    public void updateOplossing(int index){  //oldcomb is oude value, die -1 in freq moet krijgen
      String comb = getstringpos(index);

      if(uniqueCharacters(comb)){           //enkel als combinationstring is steken in map

        //UPDATEN OPLOSSING
        oplossingmap.put(index,comb);
        frequentiemap.merge(comb,1,Integer::sum);
      }
    }

    public void printfrequentiemap(){
        for(int i=0; i<combinationlist.size();i++){
            System.out.println(combinationlist.get(i) + ": " + frequentiemap.get(combinationlist.get(i)));
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

    public void verlaagFrequenties(Set<Integer> set){
        for (Integer s : set) {
            String comb = getstringpos(s);
            if(uniqueCharacters(comb)) {
                frequentiemap.merge(comb, -1, Integer::sum);
            }
        }
    }

    public void verhoogFrequenties(Set<Integer> set){
        for (Integer s : set) {
            String comb = getstringpos(s);
            if(uniqueCharacters(comb)) {
                oplossingmap.put(s,comb);
                frequentiemap.merge(comb, 1, Integer::sum);
            }
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


  /*  public int solutiongrade(){
        int grade = 0;
        for(int i=0; i<combinationlist.size();i++){
            if(oplossingstring.contains(combinationlist.get(0))){
                grade++;
            }
        }
        return grade;
    }
*/

    /* public void updatefrequencyMap(int index, String comb, String oldcomb){


        frequentiemap.merge(oldcomb,-1,Integer::sum);
        oplossingmap.put(index,comb);
        frequentiemap.merge(comb,1,Integer::sum);
    }
*/
