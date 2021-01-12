package com.first.start.framework.gen;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class GenConfig {

	public Map<String,String> conf ;

	/**
	 * 获取配置信息
	 */
	public GenConfig() {
		try {
			Configuration config =  new PropertiesConfiguration("generator.properties");
			conf = new HashMap<String, String>();
		    //全局配置
			conf.put("outputDir", config.getString("outputDir"));
			conf.put("fileOverride", config.getString("fileOverride"));
			conf.put("open",config.getString("open"));
			conf.put("enableCache", config.getString("enableCache"));
			conf.put("author", config.getString("author"));
			conf.put("kotlin", config.getString("kotlin"));
			conf.put("swagger2", config.getString("swagger2"));
			conf.put("activeRecord", config.getString("activeRecord"));
			conf.put("baseResultMap", config.getString("baseResultMap"));
			conf.put("dateType", config.getString("dateType"));
			conf.put("baseColumnList", config.getString("baseColumnList"));
			//数据源配置
			conf.put("url", config.getString("datasource.url"));
			conf.put("DriverName", config.getString("datasource.driver-class-name"));
			conf.put("Username", config.getString("datasource.username"));
			conf.put("Password", config.getString("datasource.password"));
			//包配置
			conf.put("Parent", config.getString("Parent"));
			conf.put("ModuleName", config.getString("ModuleName"));
			conf.put("Entity", config.getString("Entity"));
			conf.put("Mapper", config.getString("Mapper"));
			conf.put("Service", config.getString("Service"));
			conf.put("ServiceImpl", config.getString("ServiceImpl"));
			conf.put("Controller", config.getString("Controller"));
			//策略配置
			conf.put("Include", config.getString("Include1"));
			conf.put("EntityLombokModel", config.getString("EntityLombokModel"));
			conf.put("RestControllerStyle", config.getString("RestControllerStyle"));
			conf.put("ControllerMappingHyphenStyle", config.getString("ControllerMappingHyphenStyle"));
			
			
		} catch (ConfigurationException e) {
			throw new RuntimeException("获取配置文件失败，", e);
		}
	}

	
	
	
	public static void main(String[] args) {
		GenConfig ge = new GenConfig();
		System.out.println("数据源"+Boolean.parseBoolean(ge.conf.get("open")));
	}

}
