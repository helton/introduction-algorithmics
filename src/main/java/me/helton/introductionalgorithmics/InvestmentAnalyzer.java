package me.helton.introductionalgorithmics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Created by helton on 28/12/16.
 */
public class InvestmentAnalyzer {
    private IStockTrader stockTrader;
    private List<InvestmentQuery> queries = new ArrayList<>();
    private List<RatingCacheElement> stock2rating = new ArrayList<>();
    private Random random = new Random();

    public InvestmentAnalyzer(IStockTrader stockTrader) {
        this.stockTrader = stockTrader;
    }

    public void handleQuery(InvestmentQuery query) {
        queries.add(query);
    }

    public void analyzeQueries() {
        while (!queries.isEmpty()) {
            int rating;
            InvestmentQuery query = getFirstPriority(queries); // Get first-priority queries first

            Optional<RatingCacheElement> cacheElement = stock2rating
                    .stream()
                    .filter(x -> x.getStockId().equals(query.getStockId()))
                    .findFirst();

            if (cacheElement.isPresent()) {
                rating = cacheElement.get().rating;
            } else {
                rating = calculateRating(query.getStockId());
                stock2rating.add(
                        new RatingCacheElement(query.getStockId(), rating)
                );
            }

            if (rating > 80) { // Let's say that a rating of 80 triggers a stock trade
                stockTrader.enqueueStockForTrading(query);
            }
        }
    }

    private InvestmentQuery getFirstPriority(List<InvestmentQuery> queries) {
        InvestmentQuery minQuery = null;
        for (InvestmentQuery query : queries) {
            if (minQuery == null || minQuery.compareTo(query) < 0) {
                minQuery = query;
            }
        }
        queries.remove(minQuery);
        return minQuery;
    }

    private int calculateRating(String stockId) {
        return random.nextInt(100);
    }

    class RatingCacheElement {
        private String stockId;
        private int rating;

        public RatingCacheElement(String stockId, int rating) {
            this.stockId = stockId;
            this.rating = rating;
        }

        public String getStockId() {
            return stockId;
        }

        public int getRating() {
            return rating;
        }
    }
}
