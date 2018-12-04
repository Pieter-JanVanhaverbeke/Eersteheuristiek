import org.jgrapht.util.MathUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Hillclimbing {
    private Solution bestesolution;
    private Solution huidigesolution;
    private Solution neighbourhoodsolution;

    private int bestescore;
    private int huidigescore;
    private int neighbourhoodscore;

    private int aantalElements;
    private int lengte;
    private int bestegraad;

    Random random = new Random(1);

    public Hillclimbing(int aantalElements,int lengte) {
        bestescore = 0;
        huidigescore = 0;
        neighbourhoodscore = 0;

        this.aantalElements = aantalElements;
        this.lengte = lengte;
        this.bestegraad = (int) MathUtil.factorial(aantalElements-1);

        bestesolution = new Solution(aantalElements,lengte,bestegraad);
        huidigesolution = new Solution(bestesolution);
        neighbourhoodsolution = new Solution(bestesolution);
    }

    public void start(int aantalminuten){
        aantalminuten = aantalminuten*6000;
        long end = System.currentTimeMillis() + aantalminuten;

        bestesolution.initialiseerrandom(random);      //TODO INTIALISEREN

        System.out.println(bestesolution);

        while (System.currentTimeMillis() < end) {
            hillclimbing();
        }
        System.out.println("de beste oplossing is: " + Arrays.toString(bestesolution.getSolutionarray()));
    }

    public void hillclimbing(){
        neighbourhoodsolution = new Solution((huidigesolution));
        int getal = random.nextInt(lengte);
        neighbourhoodsolution = searchNeighbourhood(getal);

        if(neighbourhoodsolution.getGraadVanOplossing()>=huidigesolution.getGraadVanOplossing()){
            huidigesolution = new Solution(neighbourhoodsolution);
            bestesolution = new Solution(neighbourhoodsolution);
            System.out.println(bestesolution.toString());
        }
    }


    public Solution searchNeighbourhood(int index){
        Solution besteNeighbourhoodSolution = null;


        int besteneighbourhoodscore = -1;
        int neighbourhoodscore = 0;
        for(int i=0; i<aantalElements;i++){
            neighbourhoodsolution = new Solution(huidigesolution);
            neighbourhoodsolution.swap(index,i);
            neighbourhoodscore = getNeighbourhoodscore();

            if(neighbourhoodsolution.getGraadVanOplossing()>besteneighbourhoodscore){
                besteneighbourhoodscore = neighbourhoodscore;
                besteNeighbourhoodSolution = new Solution(neighbourhoodsolution);
            }
        }

        return besteNeighbourhoodSolution;
    }



    public Solution getBestesolution() {
        return bestesolution;
    }

    public void setBestesolution(Solution bestesolution) {
        this.bestesolution = bestesolution;
    }

    public Solution getHuidigesolution() {
        return huidigesolution;
    }

    public void setHuidigesolution(Solution huidigesolution) {
        this.huidigesolution = huidigesolution;
    }

    public Solution getNeighbourhoodsolution() {
        return neighbourhoodsolution;
    }

    public void setNeighbourhoodsolution(Solution neighbourhoodsolution) {
        this.neighbourhoodsolution = neighbourhoodsolution;
    }

    public int getBestescore() {
        return bestescore;
    }

    public void setBestescore(int bestescore) {
        this.bestescore = bestescore;
    }

    public int getHuidigescore() {
        return huidigescore;
    }

    public void setHuidigescore(int huidigescore) {
        this.huidigescore = huidigescore;
    }

    public int getNeighbourhoodscore() {
        return neighbourhoodscore;
    }

    public void setNeighbourhoodscore(int neighbourhoodscore) {
        this.neighbourhoodscore = neighbourhoodscore;
    }

    public int getAantalElements() {
        return aantalElements;
    }

    public void setAantalElements(int aantalElements) {
        this.aantalElements = aantalElements;
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte(int lengte) {
        this.lengte = lengte;
    }

    public int getBestegraad() {
        return bestegraad;
    }

    public void setBestegraad(int bestegraad) {
        this.bestegraad = bestegraad;
    }
}
