package it.burningboots.appennino.server;

import it.burningboots.appennino.server.persistence.GenericDao;
import it.burningboots.appennino.shared.OrmException;
import it.burningboots.appennino.shared.entity.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.input.BOMInputStream;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyConfigReader {

	public static final String PROPERTY_VERSION = "version";
	public static final String PROPERTY_CLOSED = "closed";
	public static final String PROPERTY_TOTAL_MAX = "total_max";
	public static final String PROPERTY_HUT_MAX = "hut_max";
	public static final String PROPERTY_HUT_PRICE = "hut_price";
	public static final String PROPERTY_HUT_PRICE_LOW = "hut_price_low";
	public static final String PROPERTY_TENT_MAX = "tent_max";
	public static final String PROPERTY_TENT_PRICE = "tent_price";
	public static final String PROPERTY_TENT_PRICE_LOW = "tent_price_low";
	
	private static Logger LOG = LoggerFactory.getLogger(PropertyConfigReader.class);

	public static String readPropertyConfig(Session ses, String propertyName) throws IOException, OrmException {
		Config config = GenericDao.findById(ses, Config.class, propertyName);
		if (config == null) {
			config = new Config();
			config.setId(propertyName);
			String val = readProperty(propertyName);
			config.setVal(val);
			GenericDao.saveGeneric(ses, config);
		}
		return config.getVal();
	}
	
	private static String readProperty(String propertyName) throws IOException {
		URL confUrl = new PropertyConfigReader().getClass().getResource(ServerConstants.PROPERTY_FILE);
		if (confUrl == null) LOG.error("Could not find "+confUrl);
		LOG.debug(ServerConstants.PROPERTY_FILE + " exists (path "+confUrl.getPath()+")");
		Properties props = new Properties();
		String value = "";
		File f = new File(confUrl.getPath());
		props.load(new BOMInputStream(new FileInputStream(f)));
		value = props.getProperty(propertyName);
		return value;
	}
}
