package com.Trading_plaform.Trading_platform.services;

import com.Trading_plaform.Trading_platform.models.Coin;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CoinService {
    List<Coin> getCoinList(int page) throws Exception;

    String getMarketChart(String coinId,int days) throws Exception;

    String getCoinDetails(String coinId) throws Exception;

    Coin findById(String coinId) throws Exception;

    String searchCoin(String keyword) throws Exception;

    String getTop50CoinsByMarketCapRank() throws JsonProcessingException;

    String getTreadingCoins() throws JsonProcessingException;
}
