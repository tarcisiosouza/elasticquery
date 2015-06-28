package de.l3s.elasticquery;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

public class UrlElasticQuery  
{
	private Url url;
	private HashMap <String, Url> document;
	private HashMap <String, Integer> domains;
	private String dateFrom;
	private String dateTo;
	private int capturesCount;
	private int domainCount;
	
	public UrlElasticQuery() throws IOException {
		
		document = new HashMap <String,Url>();
		ElasticServer.loadProperties();
	}

	public HashMap<String, Url> getDocuments (String dateF, String dateT, String keywords, int limit) {
	
		dateFrom = DateUtils.parseDate(dateF);
		dateTo = DateUtils.parseDate(dateT);
		domainCount = 0;
		domains = new HashMap <String,Integer>();
		document = new HashMap <String, Url>();
		
		SearchResponse response = ElasticServer.getClient().prepareSearch(ElasticServer.getIndex())
		        .setTypes(ElasticServer.getType())
		        .setSearchType(SearchType.SCAN)		    
		        .setScroll(new TimeValue(60000))
		        .setQuery(QueryBuilders.queryString(keywords))             // Query
		        .setPostFilter(FilterBuilders.rangeFilter("ts").from(dateFrom).to(dateTo))   // Filter
		        .setSize(100).execute().actionGet();
		
		while (limit > 0) {
		    for (SearchHit hit : response.getHits().getHits()) {
		    	
		    	if (limit == 0)
		    		break;
		    	Map<String,Object> source=hit.getSource();
		    	url = new Url ();
		    	url.setOrigUrl(source.get("orig").toString());
		    	url.setCompressedsize(source.get("compressedsize").toString());
		    	url.setFilename(source.get("filename").toString());
		    	url.setOffset(source.get("offset").toString());
		    	url.setRedirectUrl(source.get("redirectUrl").toString());
		    	url.setTimestamp(source.get("ts").toString());
		    	url.setDomain(source.get("orig").toString());
		    	capturesCount++;
		    	
		    	document.put(url.getOrigUrl(), url);
		    	
		    	if (domains.containsKey(url.getDomain()))
		    	{
		    		int value = domains.get(url.getDomain());
		    		value += 1;
		    		domains.put(url.getDomain(), value);
		    	}
		    	else
		    	{
		    		domains.put(url.getDomain(), 1);
		    		domainCount += 1;
		    	}
		    
		    	
		    	limit--;
		    }
		    response = ElasticServer.getClient().prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
		    //Break condition: No hits are returned
		    if (response.getHits().getHits().length == 0) {
		        break;
		    }
		}

		return document;
		
	}
	
	public HashMap<String, Integer> getDomains ()
	{
		return domains;
	}

	public int getUrlCount() {
		return document.size();
	}
	public int getDomainCount() {
		return domainCount;
	}
	
}
