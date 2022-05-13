/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.hui.project.generate;

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
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * MysqlGeneratorTest
 * </p>
 *
 * @author hui
 */
public class GeneratorTest {

    int flag = 1; // 开关
    // 多个表生成
    String[] tableNames = new String[]{
            "sys_user",
            "sys_role"
    };
    // 生成项目路径（到src\\main\\java）
    String outputDir = getJavaPath();
    // 生成父包路径
    String outputParentPath = "com.hui.project";
    String controllerPath = "web.controller";
    String entityPath = "model.entity.sys";
    // 引用包路径import
    String packagePath = outputParentPath + ".model";
    String packageCommonPath = "com.hui.project.common";  // 公共包路径
    String packageBasePath = packageCommonPath + ".base";
    // 引用类路径import
    String packageEntityPath = packagePath + ".entity.sys";
    String packageDtoPath = packagePath + ".dto";
    String packageVoPath = packagePath + ".vo";
    String packagePagePath = packageCommonPath + ".page";
    String packageResultPath = packageBasePath;
    // 生成POJO开关
    boolean POJOFlag = true;
    boolean POJOCreateFlag = true;
    String POJOCreateDir = getRootPath() + "/src/main/java/com/hui/project/model"; // POJO文件路径

    @Test
    public void generateAllCode() {
        if (flag != 1) {
            return;
        }
        POJOCreateFlag = true;
        TemplateConfig templateConfig = new TemplateConfig().setXml(null);
        AutoGenerator mpg = getAutoGenerator(templateConfig);
        mpg.execute();
        System.err.println(" TableName【 " + StringUtils.join(tableNames, ",") + " 】" + "Generator Success !");
    }

    @Test
    public void generateNotController() {
        if (flag != 1) {
            return;
        }
        TemplateConfig templateConfig = new TemplateConfig().setXml(null).setController(null);
        AutoGenerator mpg = getAutoGenerator(templateConfig);
        mpg.execute();
        System.err.println(" TableName【 " + StringUtils.join(tableNames, ",") + " 】" + "Generator Success !");
    }

    @Test
    public void generatePOJO() {
        if (flag != 1) {
            return;
        }
        POJOCreateFlag = true;
        TemplateConfig templateConfig = new TemplateConfig().setXml(null)
                .setController(null)
                .setService(null)
                .setServiceImpl(null)
                .setMapper(null);
        AutoGenerator mpg = getAutoGenerator(templateConfig);
        mpg.execute();
        System.err.println(" TableName【 " + StringUtils.join(tableNames, ",") + " 】" + "Generator Success !");
    }

    @Test
    public void generateNotPOJO() {
        if (flag != 1) {
            return;
        }
        POJOCreateFlag = false;
        TemplateConfig templateConfig = new TemplateConfig().setXml(null)
                .setEntity(null).setEntityKt(null)
                .setService(null).setServiceImpl(null)
                .setMapper(null);
        AutoGenerator mpg = getAutoGenerator(templateConfig);
        mpg.execute();
        System.err.println(" TableName【 " + StringUtils.join(tableNames, ",") + " 】" + "Generator Success !");
    }

    /**
     * =================================================【生成代码方法】=================================================
     */

    /**
     * 获取JAVA目录
     *
     * @return
     */
    protected String getJavaPath() {
        return getRootPath() + "/src/main/java";
    }

    /**
     * 获取根目录
     *
     * @return
     */
    private String getRootPath() {
        String file = Objects.requireNonNull(GeneratorTest.class.getClassLoader().getResource("")).getFile();
        return new File(file).getParentFile().getParent();
    }

    /**
     * 获取Resource目录
     *
     * @return
     */
    protected String getResourcePath() {
        return getRootPath() + "/src/main/resources";
    }

    protected AutoGenerator getAutoGenerator(TemplateConfig templateConfig) {
        return new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig())
                // 数据源配置
                .setDataSource(getDataSourceConfig())
                // 策略配置
                .setStrategy(getStrategyConfig())
                // 包配置
                .setPackageInfo(getPackageConfig())
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                .setCfg(getInjectionConfig())
                .setTemplate(templateConfig);
    }

    protected GlobalConfig getGlobalConfig() {
        return new GlobalConfig()
                .setOutputDir(outputDir) //输出目录
                .setFileOverride(true) // 是否覆盖文件
                .setActiveRecord(false) // 开启 activeRecord 模式
                .setEnableCache(false) // XML 二级缓存
                .setBaseResultMap(true) // XML ResultMap
                .setBaseColumnList(true) // XML columList
                .setKotlin(false) //是否生成 kotlin 代码
                .setOpen(false)
                .setAuthor("hui") //作者
                .setSwagger2(false) // 开启 swagger2 模式
                //自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName("%s")
                .setControllerName("%sController")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                ;
    }

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
                .setUsername(dbUserName)
                .setPassword(dbPassword)
                .setUrl(dbUrl);
    }

    protected StrategyConfig getStrategyConfig() {
        List<TableFill> tableFillList = getTableFills();
        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig.setCapitalMode(false);// 全局大写命名
        strategyConfig.setTablePrefix("sys_");// 去除前缀
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        //strategyConfig.setInclude(new String[] { "user" }) // 需要生成的表
        //自定义实体父类
        strategyConfig.setSuperEntityClass(packageBasePath + ".BaseTimeIdModel");
        // 自定义实体，公共字段
        strategyConfig.setSuperEntityColumns("id", "create_time", "update_time");
        strategyConfig.setTableFillList(tableFillList);
        // 自定义 mapper 父类
        //strategyConfig.setSuperMapperClass(packageBaseName + ".BaseMapper")
        // 自定义 controller 父类
        strategyConfig.setSuperControllerClass(packageBasePath + ".BaseController");
        // 自定义 service 实现类父类
        strategyConfig.setSuperServiceImplClass(packageBasePath + ".BaseServiceImpl");
        // 自定义 service 接口父类
        strategyConfig.setSuperServiceClass(packageBasePath + ".BaseService");
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
        strategyConfig.setInclude(tableNames);
        return strategyConfig;
    }

    protected PackageConfig getPackageConfig() {
        return new PackageConfig()
                .setParent(outputParentPath) // 父路径包名
                .setController(controllerPath)// Controller路径
                .setEntity(entityPath) // Entity路径
                .setMapper("mapper") // Mapper路径
                .setService("service") // Service路径
                .setServiceImpl("service.impl"); // ServiceImpl路径
    }

    protected InjectionConfig getInjectionConfig() {
        // 自定义生成模板
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        if (POJOFlag && POJOCreateFlag) {
            focList.add(new FileOutConfig("/templates/entityCreateInput.java.vm") {
                // 自定义输出文件目录
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return POJOCreateDir + "/dto/" + underlineToPascal(tableInfo.getEntityName()) + "Input.java";
                }
            });
            focList.add(new FileOutConfig("/templates/entityUpdateDto.java.vm") {
                // 自定义输出文件目录
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return POJOCreateDir + "/dto/" + underlineToPascal(tableInfo.getEntityName()) + "Dto.java";
                }
            });
            focList.add(new FileOutConfig("/templates/entityPageDto.java.vm") {
                // 自定义输出文件目录
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return POJOCreateDir + "/dto/" + underlineToPascal(tableInfo.getEntityName()) + "PageDto.java";
                }
            });
//			focList.add(new FileOutConfig("/templates/entityOutput.java.vm") {
//				// 自定义输出文件目录
//				@Override
//				public String outputFile(TableInfo tableInfo) {
//					return POJOCreateDir + "/" + underlineToPascal(tableInfo.getEntityName()) + "Output.java";
//				}
//			});
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
                map.put("packagePath", packagePath);
                map.put("packageBasePath", packageBasePath);
                map.put("packageCommonPath", packageCommonPath);
                map.put("packageEntityPath", packageEntityPath);
                map.put("packageDtoPath", packageDtoPath);
                map.put("packageVoPath", packageVoPath);
                map.put("packagePagePath", packagePagePath);
                map.put("packageResultPath", packageResultPath);
                this.setMap(map);
            }
        }.setFileOutConfigList(focList);
    }

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

}
