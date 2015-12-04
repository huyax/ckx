package com.ckx.web.code;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * <p>
 * 文件名称: GeneratorCode.java
 * </p>
 * <p>
 * 文件描述: 用于生产基础CRUD代码 生产Action及Service类以及html、js 持久层代码请于persist模块运行mybatis 插件生产
 * </p>
 * <p>
 * 内容摘要:
 * </p>
 * <p>
 * 其他说明:
 * </p>
 * <p>
 * 完成日期：2015年11月12日
 * </p>
 * <p>
 * 修改记录0：无
 * </p>
 *
 * @author 胡亚雄
 * @version 1.0
 */
public class GeneratorForEclipse {
    private static final String PROJECT_NAME = "ckx";

    private static final String SERVICE = "core";

    private static final String PATH = "\\src\\main\\java\\com\\ckx\\web\\code\\";

    private static final String PATH_HTML = "\\src\\main\\webapp\\pager\\biz-logic\\";

    private static final String PATH_JAVASCRIPT = "\\src\\main\\webapp\\assets\\biz-logic\\js\\";

    public static void main(String[] args) {
       /* Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Catelogs");
        //map.put("name", "Movie");
        //map.put("name", "Location");
        map.put("project", PROJECT_NAME);
        create(map);*/
        System.out.print("heh");

    }

    private static void create(Map<String, Object> map) {
        // action
        createAction(map);

        // service
        createService(map);

        // serviceImpl
        createServiceImpl(map);

        // html
        createHtml(map);

        // script
        createJavaScript(map);
    }

    public static void createAction(Map<String, Object> data) {
        String root = System.getProperty("user.dir");
        String name = (String) data.get("name");
        data.put("nameLow", name.toLowerCase());
        data.put("module", name.toLowerCase());
        Configuration cfg = new Configuration();
        cfg.setEncoding(Locale.getDefault(), "UTF-8");
        TemplateLoader templateLoader = null;
        try {
            // 使用FileTemplateLoader
            templateLoader = new FileTemplateLoader(new File(
                    System.getProperty("user.dir")));

            cfg.setTemplateLoader(templateLoader);
            Template template = cfg.getTemplate(PATH + "action.ftl", "UTF-8");
            template.setEncoding("UTF-8");
            String dir = root + PATH.replace("code", "action")
                    + name.toLowerCase();
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            String target = dirFile + File.separator + name + "Action.java";
            File targetFile = new File(target);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(targetFile), "UTF-8"));
            // 处理模版并开始输出文件
            template.process(data, out);
            out.flush();
            out.close();
            System.out.println("<- Action 生成结束  ->  路径：" + target);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createService(Map<String, Object> data) {
        String root = System.getProperty("user.dir").replace("-action",
                "-" + SERVICE);
        String name = (String) data.get("name");
        data.put("nameLow", name.toLowerCase());
        data.put("module", name.toLowerCase());
        Configuration cfg = new Configuration();
        cfg.setEncoding(Locale.getDefault(), "UTF-8");
        TemplateLoader templateLoader = null;
        try {
            // 使用FileTemplateLoader
            templateLoader = new FileTemplateLoader(new File(
                    System.getProperty("user.dir")));

            cfg.setTemplateLoader(templateLoader);
            Template template = cfg.getTemplate(PATH + "service.ftl", "UTF-8");
            template.setEncoding("UTF-8");
            String dir = root + PATH.replace("web\\code", "web\\core")
                    + name.toLowerCase();
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            String target = dirFile + File.separator + name + "Service.java";
            File targetFile = new File(target);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(targetFile), "UTF-8"));
            // 处理模版并开始输出文件
            template.process(data, out);
            out.flush();
            out.close();
            System.out.println("<- Service 生成结束  ->  路径：" + target);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createServiceImpl(Map<String, Object> data) {
        String root = System.getProperty("user.dir").replace("-action",
                "-" + SERVICE);
        String name = (String) data.get("name");
        data.put("nameLow", name.toLowerCase());
        data.put("module", name.toLowerCase());
        Configuration cfg = new Configuration();
        cfg.setEncoding(Locale.getDefault(), "UTF-8");
        TemplateLoader templateLoader = null;
        try {
            // 使用FileTemplateLoader
            templateLoader = new FileTemplateLoader(new File(
                    System.getProperty("user.dir")));

            cfg.setTemplateLoader(templateLoader);
            Template template = cfg.getTemplate(PATH + "serviceImpl.ftl",
                    "UTF-8");
            template.setEncoding("UTF-8");
            String dir = root + PATH.replace("web\\code", "web\\core")
                    + name.toLowerCase() + File.separator + "impl";
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            String target = dirFile + File.separator + name
                    + "ServiceImpl.java";
            File targetFile = new File(target);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(targetFile), "UTF-8"));
            // 处理模版并开始输出文件
            template.process(data, out);
            out.flush();
            out.close();
            System.out.println("<- ServiceImpl 生成结束  ->  路径：" + target);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createHtml(Map<String, Object> data) {
        String root = System.getProperty("user.dir");
        String name = (String) data.get("name");
        List<String> columns = new ArrayList<String>();
        try {
            Field[] fields = Class.forName("com.ckx.web.persist.entity." + name).getDeclaredFields();
            for (Field field : fields) {
                if ("id".equals(field.getName())) {
                    continue;
                }
                columns.add(field.getName());
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        data.put("columns", columns);

        data.put("nameLow", name.toLowerCase());
        data.put("module", name.toLowerCase());
        Configuration cfg = new Configuration();
        cfg.setEncoding(Locale.getDefault(), "UTF-8");
        TemplateLoader templateLoader = null;
        try {
            // 使用FileTemplateLoader
            templateLoader = new FileTemplateLoader(new File(
                    System.getProperty("user.dir")));

            cfg.setTemplateLoader(templateLoader);
            Template template = cfg
                    .getTemplate(PATH + "html.ftl", "UTF-8");
            template.setEncoding("UTF-8");
            String dir = root + PATH_HTML + name.toLowerCase();
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            String target = dirFile + File.separator + "index.html";
            File targetFile = new File(target);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(targetFile), "UTF-8"));
            // 处理模版并开始输出文件
            template.process(data, out);
            out.flush();
            out.close();
            System.out.println("<- HTML 生成结束  ->  路径：" + target);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createJavaScript(Map<String, Object> data) {
        String root = System.getProperty("user.dir");
        String name = (String) data.get("name");

        data.put("nameLow", name.toLowerCase());
        data.put("module", name.toLowerCase());
        Configuration cfg = new Configuration();
        cfg.setEncoding(Locale.getDefault(), "UTF-8");
        TemplateLoader templateLoader = null;
        try {
            // 使用FileTemplateLoader
            templateLoader = new FileTemplateLoader(new File(
                    System.getProperty("user.dir")));

            cfg.setTemplateLoader(templateLoader);
            Template template = cfg.getTemplate(PATH + "javascript.ftl",
                    "UTF-8");
            template.setEncoding("UTF-8");
            String dir = root + PATH_JAVASCRIPT + name.toLowerCase();
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            String target = dirFile + File.separator + "index.js";
            File targetFile = new File(target);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(targetFile), "UTF-8"));
            // 处理模版并开始输出文件
            template.process(data, out);
            out.flush();
            out.close();
            System.out.println("<- JAVASCRIPT 生成结束  ->  路径：" + target);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
