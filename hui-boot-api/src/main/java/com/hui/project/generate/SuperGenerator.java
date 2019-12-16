package com.hui.project.generate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 代码生成器父类
 * </p>
 *
 * @author hui
 */
public class SuperGenerator {

	static String[] huiTempate = {"0"};

	/**
	 * 获取TemplateConfig
	 *
	 * @return
	 */
	protected TemplateConfig getTemplateConfig() {
		TemplateConfig templateConfig = new TemplateConfig().setXml(null);
		if (ArrayUtils.contains(huiTempate, "et")) {
			templateConfig.setController(null)
					.setService(null)
					.setServiceImpl(null)
					.setMapper(null);
		}
		if (ArrayUtils.contains(huiTempate, "-c")) {
			templateConfig.setController(null);
		}
		return templateConfig;
	}

	/**
	 * 获取InjectionConfig
	 *
	 * @return
	 */
	protected InjectionConfig getInjectionConfig() {
		// 自定义生成模板
		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		if (ArrayUtils.contains(huiTempate, "0")
				|| ArrayUtils.contains(huiTempate, "dto")) {
			//focList.add(new FileOutConfig("/templates/entityPageInput.java.vm") {
			//	// 自定义输出文件目录
			//	@Override
			//	public String outputFile(TableInfo tableInfo) {
			//		return getRootPath() + "/../slyx-schedule-common/src/main/java/cn/duc/cloudservice/slyx/schedule/common/dto/dynamic/Describe"
			//		+ underlineToPascal(tableInfo.getName().replace("slyx_", "")) + "PageInput.java";
			//	}
			//});
		}
		return new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<>();
				String tableName = this.getConfig().getTableInfoList().get(0).getName();
				String tableComment = this.getConfig().getTableInfoList().get(0).getComment();
				String modulePath = tableName.replace("sys_", "");
				map.put("tableComment", tableComment.replace("表", ""));
				map.put("tableNameCamel", underlineToCamel(tableName));
				map.put("tableNamePascal", underlineToPascal(tableName));
				map.put("objPath", underlineToCamel(modulePath));
				map.put("obj", underlineToPascal(modulePath));
				this.setMap(map);
			}
		}.setFileOutConfigList(focList);
	}

	/**
	 * 下划线格式字符串转换为驼峰格式字符串
	 *
	 * @param param
	 * @return
	 */
	protected String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == '_') {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 下划线格式字符串转换为帕斯卡格式字符串
	 *
	 * @param param
	 * @return
	 */
	protected String underlineToPascal(String param) {
		String pascalString = underlineToCamel(param);
		return pascalString.substring(0, 1).toUpperCase() + pascalString.substring(1);
	}

	/**
	 * 获取PackageConfig
	 *
	 * @return
	 */
	protected PackageConfig getPackageConfig() {
		return new PackageConfig()
				.setParent("com.hui.project")
				.setController("web")
				.setEntity("model.entity.sys")
				.setMapper("mapper")
				.setService("service")
				.setServiceImpl("service.impl");
	}

	/**
	 * 获取StrategyConfig
	 *
	 * @param tableName
	 * @return
	 */
	protected StrategyConfig getStrategyConfig(String tableName) {
		List<TableFill> tableFillList = getTableFills();
		StrategyConfig strategyConfig = new StrategyConfig();

		strategyConfig.setCapitalMode(false);// 全局大写命名
		strategyConfig.setTablePrefix("sys_");// 去除前缀
		strategyConfig.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		//strategyConfig.setInclude(new String[] { "user" }) // 需要生成的表
		//自定义实体父类
		strategyConfig.setSuperEntityClass("com.hui.project.common.base.BaseModel");
		// 自定义实体，公共字段
		strategyConfig.setSuperEntityColumns("id", "create_time", "update_time");
		strategyConfig.setTableFillList(tableFillList);
		// 自定义 mapper 父类
		//strategyConfig.setSuperMapperClass("com.hui.project.common.base.BaseMapper")
		// 自定义 controller 父类
		strategyConfig.setSuperControllerClass("com.hui.project.web.CrudController");
		// 自定义 service 实现类父类
		strategyConfig.setSuperServiceImplClass("com.hui.project.common.base.BaseServiceImpl");
		// 自定义 service 接口父类
		strategyConfig.setSuperServiceClass("com.hui.project.common.base.BaseService");
		// 【实体】是否生成字段常量（默认 false）
		strategyConfig.setEntityColumnConstant(false);
		// 【实体】是否为构建者模型（默认 false）
		strategyConfig.setEntityBuilderModel(false);
		// 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
		strategyConfig.setEntityLombokModel(true);
		// Boolean类型字段是否移除is前缀处理
		strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);
		// 逻辑删除字段
		strategyConfig.setLogicDeleteFieldName("del_flag");
		strategyConfig.setRestControllerStyle(true);
		strategyConfig.setInclude(tableName);
		return strategyConfig;
	}

	/**
	 * 获取TableFill策略
	 *
	 * @return
	 */
	protected List<TableFill> getTableFills() {
		// 自定义需要填充的字段
		List<TableFill> tableFillList = new ArrayList<>();
		tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
		tableFillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
		tableFillList.add(new TableFill("create_by", FieldFill.INSERT));
		tableFillList.add(new TableFill("update_by", FieldFill.INSERT_UPDATE));
		return tableFillList;
	}

	/**
	 * 获取DataSourceConfig
	 *
	 * @return
	 */
	protected DataSourceConfig getDataSourceConfig() {
		return new DataSourceConfig()
				.setDbType(DbType.MYSQL)// 数据库类型
				.setTypeConvert(new MySqlTypeConvert() {
					@Override
					public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
						if ("bit".equals(fieldType.toLowerCase()) || "tinyint(1)".equals(fieldType.toLowerCase())) {
							return DbColumnType.BOOLEAN;
						}
						if ("tinyint".equals(fieldType.toLowerCase())) {
							return DbColumnType.BOOLEAN;
						}
						if ("date".equals(fieldType.toLowerCase())) {
							return DbColumnType.LOCAL_DATE;
						}
						if ("time".equals(fieldType.toLowerCase())) {
							return DbColumnType.LOCAL_TIME;
						}
						if ("datetime".equals(fieldType.toLowerCase())) {
							return DbColumnType.LOCAL_DATE_TIME;
						}
						if ("int(11) unsigned".equals(fieldType.toLowerCase())) {
							return DbColumnType.LONG;
						}
						return super.processTypeConvert(globalConfig, fieldType);
					}
				})
				.setDriverName("com.mysql.cj.jdbc.Driver")
				.setUsername("root")
				.setPassword("123456")
				.setUrl("jdbc:mysql://www.53.test:3306/w_simple?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT");
	}

	/**
	 * 获取GlobalConfig
	 *
	 * @return
	 */
	protected GlobalConfig getGlobalConfig() {
		return new GlobalConfig()
				.setOutputDir(getJavaPath())//输出目录
				.setFileOverride(true)// 是否覆盖文件
				.setActiveRecord(false)// 开启 activeRecord 模式
				.setEnableCache(false)// XML 二级缓存
				.setBaseResultMap(true)// XML ResultMap
				.setBaseColumnList(true)// XML columList
				.setKotlin(false) //是否生成 kotlin 代码
				.setOpen(false)
				.setAuthor("hui") //作者
				.setSwagger2(true) // 开启 swagger2 模式
				//自定义文件命名，注意 %s 会自动填充表实体属性！
				.setEntityName("%sEntity")
				.setControllerName("%sController")
				.setMapperName("%sMapper")
				.setXmlName("%sMapper")
				.setServiceName("%sService")
				.setServiceImplName("%sServiceImpl")
				;
	}

	/**
	 * 获取根目录
	 *
	 * @return
	 */
	private String getRootPath() {
		String file = Objects.requireNonNull(SuperGenerator.class.getClassLoader().getResource("")).getFile();
		return new File(file).getParentFile().getParent();
	}

	/**
	 * 获取JAVA目录
	 *
	 * @return
	 */
	protected String getJavaPath() {
		return getRootPath() + "/src/main/java";
	}

	/**
	 * 获取Resource目录
	 *
	 * @return
	 */
	protected String getResourcePath() {
		return getRootPath() + "/src/main/resources";
	}

	/**
	 * 获取AutoGenerator
	 *
	 * @param tableName
	 * @return
	 */
	protected AutoGenerator getAutoGenerator(String tableName, String... diyTempate) {
		huiTempate = diyTempate;
		return new AutoGenerator()
				// 全局配置
				.setGlobalConfig(getGlobalConfig())
				// 数据源配置
				.setDataSource(getDataSourceConfig())
				// 策略配置
				.setStrategy(getStrategyConfig(tableName))
				// 包配置
				.setPackageInfo(getPackageConfig())
				// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
				.setCfg(getInjectionConfig())
				.setTemplate(getTemplateConfig());
	}

}
