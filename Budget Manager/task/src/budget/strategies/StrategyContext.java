package budget.strategies;

import budget.Budget;

public class StrategyContext {
    private Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }
    public void sort(Budget budget){
        strategy.sort(budget);
    }
}
