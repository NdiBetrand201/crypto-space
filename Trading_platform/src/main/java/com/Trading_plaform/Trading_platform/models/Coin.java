package com.Trading_plaform.Trading_platform.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coin{

    @JsonProperty("id")
    @Id
    private String id;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String image;

    @JsonProperty("current_price")
    private double currentPrice;

    @JsonProperty("market_cap")
    private long marketCap;

    @JsonProperty("market_cap_rank")
    private int marketCapRank;

    @JsonProperty("fully_diluted_valuation")
    private long fullyDilutedValuation;

    @JsonProperty("total_volume")
    private long totalVolume;

    @JsonProperty("high_24h")
    private double high24h;

    @JsonProperty("low_24h")
    private double low24h;

    @JsonProperty("price_change_24h")
    private double priceChange24h;

    @JsonProperty("price_change_percentage_24h")
    private double priceChangePercentage24h;

    @JsonProperty("market_cap_change_24h")
    private double marketCapChange24h;

    @JsonProperty("market_cap_change_percentage_24h")
    private double marketCapChangePercentage24h;

    @JsonProperty("circulating_supply")
    private long circulatingSupply;

    @JsonProperty("total_supply")
    private long totalSupply;

    @JsonProperty("max_supply")
    private long maxSupply;

    @JsonProperty("ath")
    private double ath;

    @JsonProperty("ath_change_percentage")
    private double athChangePercentage;

    @JsonProperty("ath_date")
    private String athDate;

    @JsonProperty("atl")
    private double atl;

    @JsonProperty("atl_change_percentage")
    private double atlChangePercentage;

    @JsonProperty("atl_date")
    private String atlDate;

    @JsonProperty("roi")
    private Double roi;

    @JsonProperty("last_updated")
    private String lastUpdated;

    // Getters and Setters
}