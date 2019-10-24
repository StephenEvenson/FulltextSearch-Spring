package me.stephenj.administration.service;

import me.stephenj.administration.model.SearchItem;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class SearchService {
    private static final String SOLR_ADDR = "localhost:8983/solr/SmartPlant";
    private static final SolrClient solr = new HttpSolrClient.Builder(SOLR_ADDR).build();

    public List<SearchItem> getResult(String q) {
        SolrQuery query = new SolrQuery();
        if (q == null || q.length() < 1) {
            q = "*:*";
        }
        query.setQuery(q);
        query.setStart(0);
        query.setRows(20);
        query.setHighlight(true);
        query.setHighlightFragsize(100);
        query.setHighlightSnippets(6);
        query.add("hl.q", q);
        query.setHighlightSimplePre("em");
        query.setHighlightSimplePost("em");
        query.addHighlightField("content_txt");
//        query.set("df", "content_txt");
        query.set("wt", "json");

        QueryResponse response = null;
        try {
            response = solr.query(query);
        } catch (SolrServerException | IOException e) {
            e.getMessage();
        }


        SolrDocumentList documents;
        //获取结果包含关键字部分
        Map<String, Map<String, List<String>>> hightLights = null;
        if (response != null) {
            documents = response.getResults();
            hightLights = response.getHighlighting();
            //System.out.println(response.getHighlighting());
        } else {
            documents = null;
        }

        //查询返回的结果集合
        List<SearchItem> searchResult = null;
        SearchItem searchItem = null;
        //结果编号
        int index = 0;
        if (hightLights != null) {
            searchResult = new ArrayList<>();

            //遍历高亮map
            Iterator<Map.Entry<String, Map<String, List<String>>>> entries = hightLights.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Map<String, List<String>>> entry = entries.next();
                searchItem = new SearchItem();
//                searchItem.setId(index++);
                //如：/home/doc/2019年度国家社科基金重大项目招标选题研究方向.docx,  筛选最后一个为标题名
                String[] splits = entry.getKey().split("/");
                searchItem.setTitle(splits[splits.length - 1]);

                //第二层遍历
                Iterator<Map.Entry<String, List<String>>> item_entries = entry.getValue().entrySet().iterator();
                while (item_entries.hasNext()) {
                    Map.Entry<String, List<String>> item_entry = item_entries.next();
                    //放置文本内容
                    searchItem.setHighlight(item_entry.getValue());
                }
                if (searchItem.getHighlight()==null){
                    String[] ex = {"文中没有与搜索关键词匹配的内容"};
                    searchItem.setHighlight(Arrays.asList(ex));
                }


                searchResult.add(searchItem);

            }
            return searchResult;
        } else {
            return null;
        }
    }
}
