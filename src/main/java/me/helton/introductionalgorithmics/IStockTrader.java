package me.helton.introductionalgorithmics;

/**
 * Created by helton on 28/12/16.
 */
public interface IStockTrader {
    void enqueueStockForTrading(InvestmentQuery query);
    void handleTradings();
}
