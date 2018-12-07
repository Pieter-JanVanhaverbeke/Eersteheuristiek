import org.jgrapht.util.MathUtil;

import java.util.*;

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

    private int teller;

    Random random = new Random(1);

    private List<String> combinations;

    public Hillclimbing(int lengte, int aantalElements) {
        bestescore = 0;
        huidigescore = 0;
        neighbourhoodscore = 0;

        this.aantalElements = aantalElements;
        this.lengte = lengte;
        this.bestegraad = (int) MathUtil.factorial(aantalElements-1);
        this.teller = 0;

        bestesolution = new Solution(lengte,aantalElements,bestegraad);
        huidigesolution = new Solution(lengte,aantalElements,bestegraad);
        neighbourhoodsolution = new Solution(lengte,aantalElements,bestegraad);

    }

    public void start(int aantalminuten){
        aantalminuten = aantalminuten*600;
        long end = System.currentTimeMillis() + aantalminuten;

       bestesolution.initialiseerrandom(random);      //TODO INTIALISEREN
 //       bestesolution.intitialiseerFeasible();
       neighbourhoodsolution = new Solution(bestesolution);
       huidigesolution = new Solution(bestesolution);

      //  huidigesolution.initialiseerrandom(random);
   //     huidigesolution.intitialiseerFeasible();

        System.out.println(bestesolution);

        while (System.currentTimeMillis() < end) {
            hillclimbing();
        }
        System.out.println("de beste oplossing is: " + Arrays.toString(bestesolution.getSolutionarray()));
        System.out.println("de graad is: " + bestesolution.getGraadVanOplossing());
      //  System.out.println("graad huidige sol: " + Arrays.toString(huidigesolution.getSolutionarray()));
    }

    public void hillclimbing(){
        teller++;
        neighbourhoodsolution = new Solution((huidigesolution));


        if(teller%10000==0){
            neighbourhoodsolution = searchNeighbourhoodpermutation();


       //     neighbourhoodscore = neighbourhoodsolution.getScore();
        }
        else if(teller%25==0){
            char c = (char) (random.nextInt(lengte) + '1');
            neighbourhoodsolution = searchNeighbourhoodinsert(c);

        }
        else {
            int getal = random.nextInt(aantalElements);
            neighbourhoodsolution = searchNeighbourhoodswap(getal);

        }

        //ALS BETERE SOLUTION IS, UPDATEN
        if(neighbourhoodsolution.getGraadVanOplossing()>=huidigesolution.getGraadVanOplossing()){
            huidigesolution = new Solution(neighbourhoodsolution);
            bestesolution = new Solution(neighbourhoodsolution);
            if(bestescore!=bestesolution.getGraadVanOplossing()){
                bestescore=bestesolution.getGraadVanOplossing();
                System.out.println(bestescore);
            }
        }



    }


    public Solution searchNeighbourhoodswap(int index){
        Solution besteNeighbourhoodSolution = null;


        int besteneighbourhoodscore = -1;
        int neighbourhoodscore = 0;
        for(int i=0; i<aantalElements;i++){

            //ENKEL NUTTIGE SWAPS UITVOEREN
            if(i!=index){

            neighbourhoodsolution = new Solution(huidigesolution);
            neighbourhoodsolution.swap(index,i);
            neighbourhoodscore = neighbourhoodsolution.getGraadVanOplossing();

            if(neighbourhoodsolution.getGraadVanOplossing()>besteneighbourhoodscore){
                besteneighbourhoodscore = neighbourhoodscore;
                besteNeighbourhoodSolution = new Solution(neighbourhoodsolution);
            }

            }
        }

        return besteNeighbourhoodSolution;
    }


    public Solution searchNeighbourhoodinsert(char c){
        Solution besteNeighbourhoodSolution = null;


        int besteneighbourhoodscore = -1;
        int neighbourhoodscore = 0;
        for(int i=0; i<aantalElements;i++){

            //ENKEL NUTTIGE SWAPS UITVOEREN
                neighbourhoodsolution = new Solution(huidigesolution);
                neighbourhoodsolution.change(i,c);
                neighbourhoodscore = neighbourhoodsolution.getGraadVanOplossing();

                if(neighbourhoodsolution.getGraadVanOplossing()>besteneighbourhoodscore){
                    besteneighbourhoodscore = neighbourhoodscore;
                    besteNeighbourhoodSolution = new Solution(neighbourhoodsolution);
                    //besteNeighbourhoodSolution.setScore(neighbourhoodscore);
                }

            }

        return besteNeighbourhoodSolution;
    }

    public Solution searchNeighbourhoodpermutation(){
        Solution besteNeighbourhoodSolution = null;


        int besteneighbourhoodscore = -1;
       // int neighbourhoodscore = 0;

        neighbourhoodsolution = new Solution(huidigesolution);
        List<String> com = new ArrayList<>(combinations);
        String permutation = huidigesolution.getNodigePermutation(com,random);

        for(int i=0; i<aantalElements-lengte;i++){

            //ENKEL NUTTIGE SWAPS UITVOEREN
            neighbourhoodsolution = new Solution(huidigesolution);

         /*   String string = "1234";         //TODO NIET HARDCODEREN
            string = getScrambled(string);
*/

            neighbourhoodsolution.insertpermutation(i,permutation);       //TODO PERMUTATIE TOEVOEGEN
            neighbourhoodscore = neighbourhoodsolution.getGraadVanOplossing();

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

    public List<String> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<String> combinations) {
        this.combinations = combinations;
    }

    public  String getScrambled(String s) {
            String[] scram = s.split("");
            List<String> letters = Arrays.asList(scram);
            Collections.shuffle(letters);
            StringBuilder sb = new StringBuilder(s.length());
            for (String c : letters) {
                sb.append(c);
            }
            return sb.toString();
        }




}
