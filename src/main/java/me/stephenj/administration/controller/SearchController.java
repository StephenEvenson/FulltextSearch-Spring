package me.stephenj.administration.controller;

import me.stephenj.administration.model.SearchItem;
import me.stephenj.administration.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private static final String SOLR_ADDR = "localhost:8983/solr/SmartPlant";
    private static final SolrClient solr = new HttpSolrClient.Builder(SOLR_ADDR).build();

    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<SearchItem> search(@RequestParam String q) {
        List<SearchItem> searchItems = searchService.getResult(q);
        for (int i = 0; i < searchItems.size(); i++) {
            String title = searchItems.get(i).getTitle();
            List<String> highLights = searchItems.get(i).getHighlight();
            try {
                for (int j = 0; j < highLights.size(); j++) {
                    String highLight = highLights.get(j);
                    System.out.println(highLight);
                }
            } catch (NullPointerException e){
                System.out.println("文中没有与搜索关键词匹配的内容");
            }
        }
        return searchItems;
    }
}
