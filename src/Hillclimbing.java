import org.jgrapht.util.MathUtil;

import java.util.List;

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

    public Hillclimbing(int aantalElements,int lengte) {
        bestesolution = null;
        huidigesolution = null;
        neighbourhoodsolution = null;

        bestescore = 0;
        huidigescore = 0;
        neighbourhoodscore = 0;

        this.aantalElements = aantalElements;
        this.lengte = lengte;
        this.bestegraad = (int) MathUtil.factorial(aantalElements-1);

    }

    public void start(){
        
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
