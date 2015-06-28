package de.l3s.elasticquery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

final class ElasticServer {
	
	private static TransportClient client;
	private static Settings settings;
	private static String index;
	private static String type;
	private static String cluster;
	private static String hostname;
	private static String user;
	private static String password;
	private static int port;
	private static int port2;
	private static Properties config;
	
	static void loadProperties () throws IOException
	{
		String propFileName = "config.properties";
		
		InputStream inputStream = ElasticServer.class.getClassLoader().getResourceAsStream(propFileName);
		config = new Properties ();
		
		if (inputStream != null) {
			config.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		
		index = config.getProperty("index");
		type = config.getProperty("type");
		cluster = config.getProperty("cluster");
		hostname = config.getProperty("hostname");
		port = Integer.parseInt(config.getProperty("port"));
		user = config.getProperty("user");
		password = config.getProperty("password");
		port2 = port + 1;
		settings = ImmutableSettings.settingsBuilder()
			.put("shield.user", "souza:pri2006")
			
		    .put("cluster.name", cluster).build();
		client = new TransportClient(settings)
		    .addTransportAddress(new InetSocketTransportAddress(hostname, port))
		.addTransportAddress(new InetSocketTransportAddress(hostname, port2));
	}

	public static TransportClient getClient() {
		return client;
	}

	public static void setClient(TransportClient client) {
		ElasticServer.client = client;
	}

	public static String getIndex() {
		return index;
	}

	public static void setIndex(String index) {
		ElasticServer.index = index;
	}

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		ElasticServer.type = type;
	}
	
	public static void closeConection ()
	{
		client.close();
		
	}
}
