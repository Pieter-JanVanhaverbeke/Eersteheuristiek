import org.jgrapht.util.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LocalSearchHeuristiek1 {
    private int [] besteoplossing;
    private int [] oplossing;
    private String oplossingstring;
    private int aantalelements;
    private int lengte;
    private List<Integer> minlijst;
    private List<String> combinationlist;

    public LocalSearchHeuristiek1() {
        this.oplossing = null;
        oplossingstring = null;
        aantalelements=0;
        lengte=0;
    }

    public LocalSearchHeuristiek1(int aantalelements, int lengte) {
        this.oplossing = null;
        this.oplossingstring = null;
        this.aantalelements=aantalelements;
        this.lengte = lengte;
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

    public void start1(){
        boolean oplossinggevonden = false;
        //constructie oplossing

        //eerst initiele opl
        maakinitieleopl();
        System.out.println(Arrays.toString(oplossing));

        while(!oplossinggevonden){

            randomswap();
            System.out.println(Arrays.toString(oplossing));
            oplossinggevonden = true;
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



    public boolean ControlleerAlles(List<String> lijst){
        for(int i=0; i<lijst.size();i++){
            if(!oplossingstring.contains(lijst.get(i))){
                return false;
            }
        }
        return true;
    }





}


