package com.demo.cloudnative.moviereview;

//Enum class to be used for various search parameters for API
public enum SearchEnum {
    MOVIE_TITLE("t"),
    API_KEY("apiKey"),
    SOURCE_ROTTEN_TOMATO("Rotten Tomatoes"),
    SOURCE_INTERNET_MOVIE("Internet Movie Database");
    private final String searchType;
    SearchEnum(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchType() {
        return this.searchType;
    }
}
