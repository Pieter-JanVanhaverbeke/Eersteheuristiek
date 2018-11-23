import org.jgrapht.util.MathUtil;

import java.util.*;

public class LocalSearchHeuristiek1 {
    private int [] besteoplossing;
    private int [] oplossing;
    private String oplossingstring;
    private int aantalelements;
    private int lengte;
    private List<Integer> minlijst;
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


    public int[] getOplossing() {
        return oplossing;
    }

    public void setOplossing(int[] oplossing) {
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
        System.out.println(Arrays.toString(oplossing));

        while(!oplossinggevonden){

            randomswap();
            System.out.println(Arrays.toString(oplossing));
            oplossinggevonden = checkFeasible();
            //oplossinggevonden = true;
        }


    }

    public void maakinitieleopl(){
        oplossing = new int[aantalelements];
        ArrayList<Integer> lijst = new ArrayList<Integer>();
        int factorial = (int) MathUtil.factorial(lengte-1);     //k-1 factorial
        for(int i=0; i<factorial;i++){
            for(int j=0; j<lengte;j++){
                lijst.add(j);
            }
        }
        minlijst = lijst;
        while(lijst.size()<aantalelements){
        int random2 = (int )(Math.random() * lengte);
        lijst.add(random2);         //random 3 elements
  }

        Collections.shuffle(lijst);

        for(int i=0; i<lijst.size();i++){
            oplossing[i] = lijst.get(i);
        }

        intioplossingmap();         //alle huidige combinaties mappen

        oplossingstring = Arrays.toString(oplossing);
    }

    public void swap(int index1, int index2){
        int temp = oplossing [index1];
        oplossing[index1] = oplossing [index2];
        oplossing[index2] = temp;
    }

    public void randomswap(){
        int index1 = (int)(Math.random() * aantalelements);
        int index2 = (int)(Math.random() * aantalelements);
        swap(index1,index2);
    }

    public void change(int index){
        //verander char[index]
        oplossing[index] = (char)(Math.random() * aantalelements);      //naar random ander element
    }

    public void change(int index, char element){
        //verander char[index]
        oplossing[index] = element;    //naar ander element
    }

    public int solutiongrade(){
        int grade = 0;
        for(int i=0; i<combinationlist.size();i++){
            if(oplossingstring.contains(combinationlist.get(0))){
                grade++;
            }
        }
        return grade;
    }

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
        String string = "";
        for(int i=0; i<lengte;i++){
            string = string + oplossing[index+i];
        }

        return string;
    }

    public boolean ControlleerAlles(List<String> lijst){
        for(int i=0; i<lijst.size();i++){
            if(!oplossingstring.contains(lijst.get(i))){
                return false;
            }
        }
        return true;
    }







}


