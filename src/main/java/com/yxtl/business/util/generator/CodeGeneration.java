package com.yxtl.business.util.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * @author px
 * @date 2021/9/18 14:54
 */
@Slf4j
//根据数据库表快速生成 Entity、Mapper、Mapper XML、Service、Service Impl、Controller
public class CodeGeneration {
    public static void main (String[] args) {
        // Step1：代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // Step2：全局配置
        GlobalConfig gc = new GlobalConfig();
        // 填写代码生成的目录(需要修改)
        String projectPath = "D:\\Project\\IdeaProjects\\admin-version";
        // 拼接出代码最终输出的目录
        gc.setOutputDir(projectPath + "\\src\\main\\java");
        // 配置开发者信息（可选）（需要修改）
        gc.setAuthor("px");
        // 配置是否打开目录，false 为不打开（可选）
        gc.setOpen(false);
        // 实体属性 Swagger2 注解，添加 Swagger 依赖，开启 Swagger2 模式（可选）
        gc.setSwagger2(true);
        // 重新生成文件时是否覆盖，false 表示不覆盖（可选）
        gc.setFileOverride(false);
        gc.setActiveRecord(false);// 不需要ActiveRecord(实体类继承Model)特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        //gc.setBaseColumnList(true);// XML ColumnList
        // 配置主键生成策略，此处为 ASSIGN_ID（可选）
        gc.setIdType(IdType.AUTO);
        // 配置日期类型，此处为 ONLY_DATE（可选）
        gc.setDateType(DateType.ONLY_DATE);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // 默认service接口名IXXXService 自定义指定之后就不会用I开头了
        gc.setServiceName("%sService");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // Step3：数据源配置（需要修改）
        DataSourceConfig dsc = new DataSourceConfig();
        // 配置数据库 url 地址
        dsc.setUrl("jdbc:mysql://39.101.193.57:3306/yxtl_db_lora?useUnicode=yes&characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("testMyBatisPlus"); // 可以直接在 url 中指定数据库名
        // 配置数据库驱动
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        // 配置数据库连接用户名
        dsc.setUsername("root");
        // 配置数据库连接密码
        dsc.setPassword("Yxtl1234..");
        //需要自定义将表字段类型转换时可选填（比如将tinyint转Integer，将Data转LocalDateTime）
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                log.info("转换类型：{}",fieldType );
                //tinyint转换成Integer
                if (fieldType.toLowerCase().contains("tinyint")) {
                    return DbColumnType.INTEGER;
                }
                //将数据库中datetime转换成LocalDateTime
                if (fieldType.toLowerCase().contains("datetime")) {
                    return DbColumnType.LOCAL_DATE_TIME;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        // Step:4：包配置
        PackageConfig pc = new PackageConfig();
        // 配置父包名（需要修改）
        pc.setParent("com.yxtl.business");
        //==========================================
        // 配置 controller 包名
        pc.setController("controller.second");
        // 配置 entity 包名
        pc.setEntity("entity.second");
        // 配置 service 包名
        pc.setService("service.second");
        // 配置 impl 包名
        pc.setServiceImpl("service.second.impl");
        // 配置 mapper 包名
        pc.setMapper("mapper.second");
        // 配置 xml 包名
        pc.setXml("mapper.second.xml");
        mpg.setPackageInfo(pc);
        //==========================================

        // Step5：策略配置（数据库表配置）
        StrategyConfig strategy = new StrategyConfig();
        // 指定表名（可以同时操作多个表，使用 , 隔开）（需要修改）
        String str1 = "bank_transfer";
        String[] s = str1.split(",");
        strategy.setInclude(s);
        // 配置数据表与实体类名之间映射的策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 配置数据表的字段与实体类的属性名之间映射的策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 配置 lombok 模式
        strategy.setEntityLombokModel(true);
        // 配置驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        //额外配置：1.将tinyint(1)映射为Integer 2.将datatime映射为LocalDataTime


        // Step6：执行代码生成操作
        mpg.execute();
    }
}

