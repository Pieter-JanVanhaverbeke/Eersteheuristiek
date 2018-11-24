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

    private HashMap<Integer,String> oudeopl;

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


        System.out.println("intit: " + String.valueOf(oplossing));
        while(!oplossinggevonden){
            maakrandomopl();
        //    System.out.println(String.valueOf(oplossing));
            for(int i=0; i<100;i++){
                randomswap();

                oplossinggevonden = controlleerAlles();
            }
        }

        System.out.println("finale oplossing: " + String.valueOf(oplossing));


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

    public void swap(int index1, int index2){


        frequentiemap.merge(getstringpos(index1),-1,Integer::sum);
        frequentiemap.merge(getstringpos(index2),-1,Integer::sum);

        char temp = oplossing [index1];
        oplossing[index1] = oplossing [index2];
        oplossing[index2] = temp;

        updateOplossing(index1);
        updateOplossing(index2);




    }

    public void randomswap(){
        int index1 = (int)(Math.random() * aantalelements);
        int index2 = (int)(Math.random() * aantalelements);


      //  oudeopl.put(1,getstringpos(index1));
      //  oudeopl.put(2,getstringpos(index2));        //zetten oude opl

        //updaten frequenties
        frequentiemap.merge(getstringpos(index1),-1,Integer::sum);
        frequentiemap.merge(getstringpos(index2),-1,Integer::sum);

        swap(index1,index2);


        updateOplossing(index1);
        updateOplossing(index2);

    }

    public void insertrandom(int index){
        //verander char[index]
        oplossing[index] = (char)(Math.random() * aantalelements);      //naar random ander element
    }

    public void insert(int index, char element){
        //verander char[index]
        oplossing[index] = element;    //naar ander element
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

    public boolean checkFeasible(){                 //TODO NIET ALLES MAP CONTROLEREN NA INTITIELE
        for(int i=0; i<combinationlist.size();i++){
            if (frequentiemap.get(combinationlist.get(i))==0){
                return false;
            }
        }
        return true;
    }

    public void intioplossingmap(){
        for(int i=0;i<aantalelements-lengte+1;i++){
            String comb = getstringpos(i);
            oplossingmap.put(i,comb);
            frequentiemap.merge(comb,1,  Integer::sum); //TODO snellere manier?
        }
    }

    //Methode dat string van bepaalde positie returned
    public String getstringpos(int index){
    /*    String string = "";
        for(int i=0; i<lengte;i++){
            string = string + oplossing[index+i];
        }
        return string;*/
/*
        int upperindex = index+lengte-1;
        if(index+lengte>=aantalelements){
            upperindex=aantalelements-1;
        }
        */

          int uppercount = lengte; //TODO kan nog sneller?
        if(index+lengte>=aantalelements){
     uppercount = aantalelements-index;
    }

       return new String(oplossing , index, uppercount);
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
        ArrayList<Character> lijst = new ArrayList<Character>(minlijst);
        Collections.shuffle(lijst);
        for(int i=0; i<minlijst.size();i++){
            oplossing[i]=lijst.get(i);                                   //TODO oplossing in RAM houden
        }
        for(int j=minlijst.size();j<oplossing.length;j++){
            char character = (char)((Math.random() * lengte)+'1');
            oplossing[j]= character ;
        }
    }


    public void updateOplossing(int index){  //oldcomb is oude value, die -1 in freq moet krijgen
        int uppercount = lengte;
        if(index+lengte>=aantalelements){
            uppercount = aantalelements-index;
        }

     //   System.out.println("index: " + index);
        String comb = new String(oplossing ,  index, uppercount);

        //UPDATEN OPLOSSING
     //   frequentiemap.merge(oldcomb,-1,Integer::sum);
        oplossingmap.put(index,comb);
        frequentiemap.merge(comb,1,Integer::sum);


    }

   /* public void updatefrequencyMap(int index, String comb, String oldcomb){


        frequentiemap.merge(oldcomb,-1,Integer::sum);
        oplossingmap.put(index,comb);
        frequentiemap.merge(comb,1,Integer::sum);
    }
*/


}


