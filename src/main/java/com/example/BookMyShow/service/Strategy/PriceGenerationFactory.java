package com.example.BookMyShow.service.Strategy;

public class PriceGenerationFactory {

    public static PriceGenerationStrategy getPriceGenerationStrategy(PriceStrategyType strategyType) {
        return switch (strategyType){
            case SIMPLE -> new SimplePriceGenerationStrategy();
        };
    }


}
