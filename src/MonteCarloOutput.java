public class MonteCarloOutput {
    public static void main(String[] args) {
        displayTable(100);
    }

    private static void displayTable(int maxTreeDensity) {
        for (int treeDensity = 5; treeDensity <= maxTreeDensity; treeDensity+=5) {
            double answer = calculateAverage(treeDensity);
            System.out.println(treeDensity + ", " + (100-(answer*100)));
        }
    }

    private static double calculateAverage(int treeDensity){
        double resultSum = 0;
        for (int i = 0; i < 1000; i++) {
            double result = runExperiment(treeDensity);
            resultSum+=result;
        }
        return resultSum/1000;
    }

    private static double runExperiment(int treeDensity) {
        Simulator sim = new Simulator(100,100);
        int treesLeft, startingTrees;

        sim.initializeTrees(treeDensity);
        startingTrees = sim.numTrees();

        sim.startFire(1);
        while (!sim.isFireOnScreen()){
            sim.propagateFire();
        }

        treesLeft = sim.numTrees();
        return (double)treesLeft/startingTrees;
    }


}
