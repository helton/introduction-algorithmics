package me.helton.introductionalgorithmics;

import com.google.common.base.Stopwatch;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by helton on 28/12/16.
 */
public class Program {
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        IStockTrader stockTrader = new StockTrader();
        InvestmentAnalyzer analyzer = new InvestmentAnalyzer(stockTrader);
        Stopwatch stopwatch = Stopwatch.createUnstarted();

        //Simulate queries
        System.out.println("Querying...");
        stopwatch.start();
        for (int i = 0; i < 100000; i++)
            analyzer.handleQuery(createQuery());
        stopwatch.stop();
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.printf("Done in %d ms\n", elapsed);

        //Analyze queries
        System.out.println("Analyzing queries...");
        stopwatch.reset();
        stopwatch.start();
        analyzer.analyzeQueries();
        stopwatch.stop();
        elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.printf("Done in %d ms\n", elapsed);

        //Simulate handling of stock tradings
        System.out.println("Handling tradings...");
        stopwatch.reset();
        stopwatch.start();
        stockTrader.handleTradings();
        stopwatch.stop();
        elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.printf("Done in %d ms\n", elapsed);
    }

    private static InvestmentQuery createQuery() {
        return new InvestmentQuery("Stock" + random.nextInt(10000),
                new Date(),
                random.nextInt(5),
                UUID.randomUUID().toString());
    }
}
