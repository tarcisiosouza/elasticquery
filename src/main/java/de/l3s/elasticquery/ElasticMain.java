package de.l3s.elasticquery;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ElasticMain {

	public static boolean ASC = true;
    public static boolean DESC = false;

	public static void main(String[] args) throws IOException 
	{
		
		String propFileName = args[0];
		String outputFileName = args[1];
		OutputStream output = new FileOutputStream (outputFileName);
		
		HashMap <String,Url> result = new HashMap <String,Url>();
		HashMap <String, Integer> domains = new HashMap <String,Integer>();
		PropertyUtil property = new PropertyUtil (propFileName); 

		String dateFrom = property.getStringProperty("dateFrom");
		String dateTo = property.getStringProperty("dateTo");
		String keywords = property.getStringProperty("keywords");
		int limit = Integer.parseInt(property.getStringProperty("limit"));
		
		System.out.println ("\nQuery terms:"+keywords);
		System.out.println ("DateFrom: "+dateFrom+" DateTo: "+dateTo+"\n");
		UrlElasticQuery query = new UrlElasticQuery ();
		result = query.getDocuments(dateFrom, dateTo, keywords,limit);
		domains = query.getDomains();
			
		Set<String> set = result.keySet();
	    ArrayList<String> list = new ArrayList<String>();
	    list.addAll(set);
	    Map<String, Integer> sortedMapDesc = sortByComparator(domains, DESC);
	    
	    System.out.println ("###########<Query Result>###########");
	    System.out.println ("Total urls: "+query.getUrlCount());
	    System.out.println ("Total domains: "+query.getDomainCount());
	    	
	    for (Entry<String, Integer> entry : sortedMapDesc.entrySet())
	 	{
	       System.out.println("Domain: " + entry.getKey() + " freq: "+ entry.getValue());
	 	}  
	      
	    for(Entry<String, Url> s : result.entrySet())
	    {
//	    	System.out.print(s.getKey()+" "+s.getValue().getTimestamp()+" "+s.getValue().getFilename()+" "+s.getValue().getOffset()+" "+s.getValue().getRedirectUrl());
	    	String str = s.getKey()+" "+s.getValue().getTimestamp()+" "+s.getValue().getFilename()+" "+s.getValue().getOffset()+" "+s.getValue().getRedirectUrl()+"\n";
	    	byte[] b = str.getBytes();
	    	output.write(b);
	    	
	    }

    }

	 private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
	 {

	            List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

	            // Sorting the list based on values
	            Collections.sort(list, new Comparator<Entry<String, Integer>>()
	            {
	                public int compare(Entry<String, Integer> o1,
	                        Entry<String, Integer> o2)
	                {
	                    if (order)
	                    {
	                        return o1.getValue().compareTo(o2.getValue());
	                    }
	                    else
	                    {
	                        return o2.getValue().compareTo(o1.getValue());

	                    }
	                }
	            });

	            // Maintaining insertion order with the help of LinkedList
	            Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	            for (Entry<String, Integer> entry : list)
	            {
	                sortedMap.put(entry.getKey(), entry.getValue());
	            }

	            return sortedMap;
	}
	
	}
