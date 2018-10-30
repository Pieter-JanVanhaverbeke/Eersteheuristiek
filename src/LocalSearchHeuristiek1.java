import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LocalSearchHeuristiek1 {
    private char [] oplossing;
    private String oplossingstring;

    public LocalSearchHeuristiek1() {
        this.oplossing = null;
        oplossingstring = null;
    }

    public LocalSearchHeuristiek1(char[] oplossing) {
        this.oplossing = oplossing;
    }

    public char[] getOplossing() {
        return oplossing;
    }

    public void setOplossing(char[] oplossing) {
        this.oplossing = oplossing;
    }

    public void setOplossing(String oplossingstring) {
        this.oplossing = oplossingstring.toCharArray();
    }

    public String getOplossingstring() {
        return oplossingstring;
    }

    public void setOplossingstring(String oplossingstring) {
        this.oplossingstring = oplossingstring;
    }

    public void start1(){
        int lengte = oplossingstring.length();
        int random1 = (int )(Math.random() * lengte);
        int random2 = (int )(Math.random() * lengte);
        wissel(random1,random2);
        for(int i=0; i<oplossing.length;i++){
            System.out.print(oplossing[i]);
        }
    }

    public void ControlleerOpl(){

    }

    public void wissel(int index1, int index2){
        char temp = oplossing [index1];
        oplossing[index1] = oplossing [index2];
        oplossing[index2] = temp;
    }

    public void change(int index){
        //verander char[index]
    }



    public void zetDeOplossing(){
        oplossing = oplossingstring.toCharArray();
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



