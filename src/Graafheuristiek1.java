
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.jgrapht.Graph;
import org.jgrapht.alg.matching.GreedyWeightedMatching;
import org.jgrapht.graph.*;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;

import org.apache.commons.lang3.StringUtils;


import java.lang.reflect.Array;
import java.util.*;


public class Graafheuristiek1 {
        private List<String> stringcombinaties;

        public Graafheuristiek1(List<String> stringcombinaties){
            this.stringcombinaties = stringcombinaties;
        }

    public Graafheuristiek1(){
        this.stringcombinaties = new ArrayList<String>();
    }



    public String start() {


            ArrayList<String> stringlijst = new ArrayList<>();

            for(int i=0; i<stringcombinaties.size();i++){
                stringlijst.add(stringcombinaties.get(i));          //alle nodes toevoegen
            }

            DefaultDirectedWeightedGraph<String, DefaultEdge> graaf = maakgraaf(stringlijst);


            Set<String> set = graaf.vertexSet();
            int hoogste = 0;
            String hoogstevertice = "";
            DefaultEdge grootsteedge = null;


            krimpgraaf(graaf);

            for (String s : graaf.vertexSet()) {
                System.out.println(s);                  //printen oplossing
                System.out.println("lengte: " + s.length());
                return s;
            }

        return null;
        }

        public static void maakNieuweEdge(String vertexIN, String vertexOUT) {

        }


        public static DefaultEdge ZoekGrootsteEdge(DefaultDirectedWeightedGraph<String, DefaultEdge> graaf, Set<String> vertexset) {
            //hoogste pad zoeken

            int maxgewicht = 0;
            DefaultEdge edge = null;

            for (String s : vertexset) {

                Set<DefaultEdge> setedges = graaf.incomingEdgesOf(s);

                for (DefaultEdge pad : setedges) {
                    int gewicht = (int) graaf.getEdgeWeight(pad); //gewicht opvragen van edge

                    if (gewicht >= maxgewicht) {
                        maxgewicht = gewicht;
                        edge = pad;
                    }
                }
            }

            return edge;

        }

        public static void krimpgraaf(DefaultDirectedWeightedGraph<String, DefaultEdge> graaf) {

            while (graaf.vertexSet().size() != 1) {
            //    System.out.println(graaf.vertexSet().size());
                Set<String> set = graaf.vertexSet();
                int hoogste = 0;
                String hoogstevertice = "";
                DefaultEdge grootsteedge = null;


                grootsteedge = ZoekGrootsteEdge(graaf, set);


                hoogste = (int) graaf.getEdgeWeight(grootsteedge);
                hoogstevertice = graaf.getEdgeTarget(grootsteedge);


                //        ArrayList<String> matchlijst = new ArrayList<>();
                //        ArrayList<Integer> gewichten = new ArrayList<>();

                String vertex1 = hoogstevertice;
                String vertex2 = (String) graaf.getEdgeSource(grootsteedge);                                    //get source van edge
                graaf.removeEdge(vertex1, vertex2);                                                              //remove andere pad

                //        matchlijst.add(vertex1 + "->" + vertex2);                                      //opslaan
                //       gewichten.add(hoogste);                                                         //opslaan


                Set<DefaultEdge> uitgaanedges = graaf.outgoingEdgesOf(vertex1);
                Set<DefaultEdge> inkomenedges = graaf.incomingEdgesOf(vertex2);


                //  String nieuw = vertex2 + vertex1;
                String nieuw = overlapnode(vertex2, vertex1, hoogste);
                graaf.addVertex(nieuw);           //2->1 dus node 21


                for (DefaultEdge pad : uitgaanedges) {
                    if (!pad.equals(grootsteedge)) {
                        String target = graaf.getEdgeTarget(pad);
                        int gewicht = (int) graaf.getEdgeWeight(pad);
                        DefaultEdge toevoeg = graaf.addEdge(nieuw, target);
                        graaf.setEdgeWeight(toevoeg, gewicht);
                    }
                }


                for (DefaultEdge pad : inkomenedges) {
                    if (!pad.equals(grootsteedge)) {
                        String source = graaf.getEdgeSource(pad);
                        int gewicht = (int) graaf.getEdgeWeight(pad);
                        DefaultEdge toevoeg = graaf.addEdge(source, nieuw);
                        graaf.setEdgeWeight(toevoeg, gewicht);
                    }
                }

                graaf.removeVertex(vertex1);
                graaf.removeVertex(vertex2);

            }
        }



        public static int match(String a, String b) {
            int lengte = 0;


            for (int i = 0; i < b.length(); i++) {
                String partstring = a.substring(i);     //alle eindes van a
                //    System.out.println(partstring);

                if (b.startsWith(partstring)) {
                    lengte = partstring.length();
                }
            }
            return lengte;
        }

        public static String overlapnode(String vertexB, String vertexA, int gewicht) {
            int lengte = vertexB.length();
            vertexB = vertexB + vertexA.substring(gewicht);

            return vertexB;
        }


        public static DefaultDirectedWeightedGraph maakgraaf(ArrayList<String> strings) {
            DefaultDirectedWeightedGraph<String, DefaultEdge> graph = new DefaultDirectedWeightedGraph<String, DefaultEdge>(DefaultEdge.class);

            for (int i = 0; i < strings.size(); i++) {     //alle vertices adden
                graph.addVertex(strings.get(i));
            }

            for (int i = 0; i < strings.size(); i++) {
                for (int j = 0; j < strings.size(); j++) {
                    if (i != j) {
                        String a = strings.get(i);
                        String b = strings.get(j);
                        DefaultEdge edge1 = graph.addEdge(a, b);
                        graph.setEdgeWeight(edge1, match(a, b));


                    }
                }
            }

            return graph;
        }

        public static int zoekLangsteHamiltonPad(DefaultDirectedGraph<String, DefaultEdge> graaf, ArrayList<String> stringlijst) {
            int gewicht;
            for (int i = 0; i < stringlijst.size(); i++) {

            }


            return 0;
        }

    }





