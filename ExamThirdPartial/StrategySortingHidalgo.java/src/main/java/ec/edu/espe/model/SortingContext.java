package ec.edu.espe.model;

public class SortingContext {
    private SortingStrategy strategy;

    public void setSortStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public int[] sort(int[] input) {
        if (strategy == null) {
            throw new IllegalStateException("No se ha elegido una estrategia de ordenamiento");
        }
        return strategy.sort(input);
    }
   
    public String getCurrentStrategyName() {
        if(strategy == null) return "Ninguna";
        return strategy.getClass().getSimpleName();
    }
}