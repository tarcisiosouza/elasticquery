package de.l3s.elasticquery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	public static boolean ASC = true;
    public static boolean DESC = false;

    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    public void testApp() throws IOException
    {
    	/*
    	String propFileName = "elections2004_2013.properties";
		UrlElasticQuery testQuery = new UrlElasticQuery ();
    	HashMap <String, Url> result = new HashMap <String,Url>();
    	HashMap <String, Integer> domains = new HashMap <String,Integer>();
    	
		InputStream inputStream = AppTest.class.getClassLoader().getResourceAsStream(propFileName);
		Properties config = new Properties ();
		
		if (inputStream != null) {
			config.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		
		String dateFrom = config.getProperty("dateFrom");
		String dateTo = config.getProperty("dateTo");
		String keywords = config.getProperty("keywords");
		
		result = testQuery.getDocuments(dateFrom, dateTo, keywords,5);
		domains = testQuery.getDomains();
		Map<String, Integer> sortedMapDesc = sortByComparator(domains, DESC);
		
		Set<String> set = result.keySet();
    	ArrayList<String> list = new ArrayList<String>();
    	list.addAll(set);
    	Collections.sort(list);
    	
    	System.out.println ("###########<Query Result>###########");
    	System.out.println ("Total urls: "+testQuery.getUrlCount());
    	System.out.println ("Total domains: "+testQuery.getDomainCount());
    	
	     for (Entry<String, Integer> entry : sortedMapDesc.entrySet())
	     {
           System.out.println("Domain: " + entry.getKey() + " freq: "+ entry.getValue());
	     }  
     

    	for(Entry<String, Url> s : result.entrySet())
    	{
    	    System.out.print("url: {"+s.getKey()+"} timestamp: {"+s.getValue().getTimestamp()+"} domain: {"+s.getValue().getDomain()+"}");
    	    System.out.println ();
    	}
    	*/
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
