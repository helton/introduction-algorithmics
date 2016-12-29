package me.helton.introductionalgorithmics;

import java.util.LinkedList;

/**
 * Created by helton on 28/12/16.
 */
public class StockTrader implements IStockTrader {
    private LinkedList<InvestmentQuery> stocks2trade = new LinkedList<>();

    @Override
    public void enqueueStockForTrading(InvestmentQuery query) {
        stocks2trade.add(query);
    }

    @Override
    public void handleTradings() {
        System.out.printf("[%d stocks]\n", stocks2trade.size());
        while (!stocks2trade.isEmpty()) {
            InvestmentQuery query = stocks2trade.getFirst();
            // As this is simulation of a real service that consumes queries as they arrive,
            // remember to remove the query from the list when processed:
            stocks2trade.removeFirst();

            // Simulate stock trade:
            //Thread.sleep(100);
        }
    }
}
