
package com.example.rssfeedtadbir.model.data;


import java.util.List;

public class JSearchResult {


    private List<JNews> search = null;

    private String totalResults;

    private String response;

    public List<JNews> getSearch() {
        return search;
    }

    public void setSearch(List<JNews> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
