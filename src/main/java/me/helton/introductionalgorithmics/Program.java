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
    private static IStockTrader stockTrader = new StockTrader();
    private static InvestmentAnalyzer analyzer = new InvestmentAnalyzer(stockTrader);
    private static Stopwatch stopwatch = Stopwatch.createUnstarted();

    public static void main(String[] args) {
        execute("Querying", () -> {
            for (int i = 0; i < 100000; i++)
                analyzer.handleQuery(createQuery());
        });

        execute("Analyzing queries", () -> analyzer.analyzeQueries());

        execute("Handling tradings", () -> stockTrader.handleTradings());
    }

    private static void execute(String message, Runnable func) {
        System.out.println(String.format("=> %s ...", message));
        stopwatch.reset();
        stopwatch.start();
        func.run();
        stopwatch.stop();
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.printf("Done in %d ms\n", elapsed);
    }

    private static InvestmentQuery createQuery() {
        return new InvestmentQuery("Stock" + random.nextInt(10000),
                new Date(),
                random.nextInt(5),
                UUID.randomUUID().toString());
    }
}
