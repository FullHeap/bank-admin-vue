package com.first.start.project.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGen {

	public static void main(String[] args) {
		// 1、代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 2、全局配置
		GlobalConfig gc = new GlobalConfig();
		// 获取当前工作目录，填写代码生成的目录(需要修改)
		String projectPath = System.getProperty("user.dir");
//        String projectPath = "E:\\myProject\\test\\test_mybatis_plus";
		// 拼接出代码最终输出的目录
		gc.setOutputDir(projectPath + "/src/main/java");
		// 配置开发者信息（可选）（需要修改）
		gc.setAuthor("lyz");
		// 配置是否打开目录，false 为不打开（可选）
		gc.setOpen(false);
		// 实体属性 Swagger2 注解，添加 Swagger 依赖，开启 Swagger2 模式（可选）
		// gc.setSwagger2(true);

		// 重新生成文件时是否覆盖，false 表示不覆盖（可选）
		gc.setFileOverride(true);

		// 配置主键生成策略，此处为 ASSIGN_ID（可选）
		gc.setIdType(IdType.ASSIGN_ID);

		// 配置日期类型，此处为 ONLY_DATE（可选）
		gc.setDateType(DateType.ONLY_DATE);

		// gc.setSwagger2(true); 实体属性 Swagger2 注解

		//配置文件名
//		gc.setBaseColumnList(true);
//		gc.setBaseResultMap(true);
//		gc.setControllerName("");
//		gc.setMapperName("");
//		gc.setXmlName("");
//		gc.setEntityName("");
		// 默认生成的 service 会有 I 前缀
		gc.setServiceName("%sService");
//		gc.setServiceImplName("%sServiceImpl");

		mpg.setGlobalConfig(gc);

		// 3、数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		 // 配置数据库 url 地址
		dsc.setUrl("jdbc:mysql://120.92.151.50:3306/dyjsj?useUnicode=true&useSSL=false&characterEncoding=utf8");
		// dsc.setSchemaName("dyjsj");// 可以直接在 url 中指定数据库名
		// 配置数据库驱动
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//		dsc.setDriverName("com.mysql.jdbc.Driver");
		// 配置数据库连接用户名
        dsc.setUsername("root");
        // 配置数据库连接密码
        dsc.setPassword("root");
		mpg.setDataSource(dsc);
		

		// 4、包配置
		PackageConfig pc = new PackageConfig();
		
        // 配置父包名（需要修改）
        pc.setParent("com.first.start.project.system");
        // 配置模块名（需要修改）
        pc.setModuleName("system");
        // 配置 entity 包名
        pc.setEntity("entity");
        // 配置 mapper 包名
        pc.setMapper("mapper");
        // 配置 service 包名
        pc.setService("service");
        pc.setServiceImpl("serviceImpl");
        // 配置 controller 包名
        pc.setController("controller");
        mpg.setPackageInfo(pc);
		
			
//		pc.setModuleName(scanner("模块名"));
//		pc.setParent("com.baomidou.ant");
//		mpg.setPackageInfo(pc);

		// 自定义配置
//		InjectionConfig cfg = new InjectionConfig() {
//			@Override
//			public void initMap() {
//				// to do nothing
//			}
//		};
//
//		// 如果模板引擎是 freemarker
//		String templatePath = "/templates/mapper.xml.ftl";
//		// 如果模板引擎是 velocity
//		// String templatePath = "/templates/mapper.xml.vm";
//
//		// 自定义输出配置
//		List<FileOutConfig> focList = new ArrayList<>();
//		// 自定义配置会被优先输出
//		focList.add(new FileOutConfig(templatePath) {
//			@Override
//			public String outputFile(TableInfo tableInfo) {
//				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//				return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
//						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//			}
//		});
//		/*
//		 * cfg.setFileCreate(new IFileCreate() {
//		 * 
//		 * @Override public boolean isCreate(ConfigBuilder configBuilder, FileType
//		 * fileType, String filePath) { // 判断自定义文件夹是否需要创建
//		 * checkDir("调用默认方法创建的目录，自定义目录用"); if (fileType == FileType.MAPPER) { // 已经生成
//		 * mapper 文件判断存在，不想重新生成返回 false return !new File(filePath).exists(); } //
//		 * 允许生成模板文件 return true; } });
//		 */
//		cfg.setFileOutConfigList(focList);
//		mpg.setCfg(cfg);
//
//		// 配置模板
//		TemplateConfig templateConfig = new TemplateConfig();
//
//		// 配置自定义输出模板
//		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//		// templateConfig.setEntity("templates/entity2.java");
//		// templateConfig.setService();
//		// templateConfig.setController();
//
//		templateConfig.setXml(null);
//		mpg.setTemplate(templateConfig);

		// 策略配置
//		StrategyConfig strategy = new StrategyConfig();
//		strategy.setNaming(NamingStrategy.underline_to_camel);
//		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
////        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
//		strategy.setEntityLombokModel(true);
//		strategy.setRestControllerStyle(true);
//		// 公共父类
////        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
//		// 写于父类中的公共字段
//		strategy.setSuperEntityColumns("id");
//		strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//		strategy.setControllerMappingHyphenStyle(true);
//		strategy.setTablePrefix(pc.getModuleName() + "_");
//		mpg.setStrategy(strategy);
//		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		
		
		// 策略配置（数据库表配置）
        StrategyConfig strategy = new StrategyConfig();
        // 指定表名（可以同时操作多个表，使用 , 隔开）（需要修改）
        strategy.setInclude("user");
        // 配置数据表与实体类名之间映射的策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 配置数据表的字段与实体类的属性名之间映射的策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 配置 lombok 模式
        strategy.setEntityLombokModel(true);
        // 配置 rest 风格的控制器（@RestController）
        strategy.setRestControllerStyle(true);
        // 配置驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 配置表前缀，生成实体时去除表前缀
        // 此处的表名为 test_mybatis_plus_user，模块名为 test_mybatis_plus，去除前缀后剩下为 user。
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
		
		
		

		mpg.execute();
	}

}
