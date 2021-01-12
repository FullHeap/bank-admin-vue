package com.first.start.framework.gen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGen {

	public static void main(String[] args) {
		// 读取配置文件信息
		GenConfig cfg = new GenConfig();
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
		gc.setAuthor(cfg.conf.get("author"));
		// 配置是否打开目录，false 为不打开（可选）
		gc.setOpen(Boolean.parseBoolean(cfg.conf.get("open")));
		// 实体属性 Swagger2 注解，添加 Swagger 依赖，开启 Swagger2 模式（可选）
		// gc.setSwagger2(true);

		// 重新生成文件时是否覆盖，false 表示不覆盖（可选）
		gc.setFileOverride(Boolean.parseBoolean(cfg.conf.get("fileOverride")));

		// 配置主键生成策略，此处为 ASSIGN_ID（可选）
		gc.setIdType(IdType.ASSIGN_ID);

		// 配置日期类型，此处为 ONLY_DATE（可选）
		gc.setDateType(DateType.ONLY_DATE);

		// gc.setSwagger2(true); 实体属性 Swagger2 注解

		// 配置文件名
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
		dsc.setUrl(cfg.conf.get("url"));
		// dsc.setSchemaName("dyjsj");// 可以直接在 url 中指定数据库名
		// 配置数据库驱动
		dsc.setDriverName(cfg.conf.get("DriverName"));
//		dsc.setDriverName("com.mysql.jdbc.Driver");
		// 配置数据库连接用户名
		dsc.setUsername(cfg.conf.get("Username"));
		// 配置数据库连接密码
		dsc.setPassword(cfg.conf.get("Password"));
		mpg.setDataSource(dsc);

		// 4、包配置
		PackageConfig pc = new PackageConfig();

		// 配置父包名（需要修改）
		pc.setParent(cfg.conf.get("Parent"));
		// 配置模块名（需要修改）
		pc.setModuleName(cfg.conf.get("ModuleName"));
		// 配置 entity 包名
		pc.setEntity(cfg.conf.get("Entity"));
		// 配置 mapper 包名
		pc.setMapper(cfg.conf.get("Mapper"));
		// 配置 service 包名
		pc.setService(cfg.conf.get("Service"));
		pc.setServiceImpl(cfg.conf.get("ServiceImpl"));
		// 配置 controller 包名
		pc.setController(cfg.conf.get("Controller"));
		mpg.setPackageInfo(pc);

		// 指定自定义模板路径, 位置：/resources/templates/entity2.java.ftl(或者是.vm)
		// 注意不要带上.ftl(或者是.vm), 会根据使用的模板引擎自动识别
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setEntity("templates/entity.java")
			.setController("templates/controller.java")
			.setMapper("templates/mapper.java")
			.setService("templates/service.java")
			.setServiceImpl("templates/serviceImpl.java")
			.setXml("templates/mapper.xml");

		// 配置自定义模板
		mpg.setTemplate(templateConfig);

		// 策略配置（数据库表配置）
		StrategyConfig strategy = new StrategyConfig();
		// 指定表名（可以同时操作多个表，使用 , 隔开）（需要修改）
		strategy.setInclude("user");
		// 配置数据表与实体类名之间映射的策略
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// 配置数据表的字段与实体类的属性名之间映射的策略
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// 配置 lombok 模式
		strategy.setEntityLombokModel(Boolean.parseBoolean(cfg.conf.get("EntityLombokModel")));
		// 配置 rest 风格的控制器（@RestController）
		strategy.setRestControllerStyle(Boolean.parseBoolean(cfg.conf.get("RestControllerStyle")));
		// 配置驼峰转连字符
		strategy.setControllerMappingHyphenStyle(Boolean.parseBoolean(cfg.conf.get("ControllerMappingHyphenStyle")));
		// 配置表前缀，生成实体时去除表前缀
		// 此处的表名为 test_mybatis_plus_user，模块名为 test_mybatis_plus，去除前缀后剩下为 user。
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);

		mpg.execute();
	}

}
