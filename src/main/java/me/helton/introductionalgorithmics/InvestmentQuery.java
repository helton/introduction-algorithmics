package me.helton.introductionalgorithmics;

import java.util.Date;

/**
 * Created by helton on 28/12/16.
 */
public class InvestmentQuery implements Comparable<InvestmentQuery> {
    private String stockId;
    private Date queryTime;
    private int priority;
    private String investor;

    public InvestmentQuery(String stockId, Date queryTime, int priority, String investor) {
        this.stockId = stockId;
        this.queryTime = queryTime;
        this.priority = priority;
        this.investor = investor;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    @Override
    public int compareTo(InvestmentQuery query) {
        return priority - query.getPriority();
    }
}
