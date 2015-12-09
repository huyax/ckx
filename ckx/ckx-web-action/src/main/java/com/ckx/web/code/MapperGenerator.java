package com.ckx.web.code;

import java.io.*;

/**
 * Created by Administrator on 2015/12/9.
 */
public class MapperGenerator {

    public static void main(String[] args) {
        //createMapperClass("Picture");
        createMapperXml("Picture");
    }

    public static void createMapperClass(String name) {
        StringBuilder classPath = new StringBuilder();

        String classContent = readFile(classPath.toString());
        if(classContent.contains("paginate")){
            throw new RuntimeException("原mapper java文件已经存在分页方法！");
        }

        classPath.append(System.getProperty("user.dir"))
                .append("\\ckx-web-persist")
                .append("\\src\\main\\java\\com\\ckx\\web\\persist\\mapper\\")
                .append(name).append("Mapper.java");
        StringBuilder paginate = new StringBuilder();
        paginate.append("List<").append(name).append(">").append(" paginate(Map<String, Object> paramsMap);");
        String paginateCount = "int paginateCount(Map<String, Object> paramsMap);";

        String importString = "package com.ckx.web.persist.mapper;\nimport java.util.List;\nimport java.util.Map;";
        classContent = classContent.replace("package com.ckx.web.persist.mapper;",importString);
        classContent = classContent.substring(0,classContent.lastIndexOf("}")) + "\n\t" + paginate + "\n\n\t" + paginateCount + "\n}";

        //System.out.println(classContent);

        writeFile(classPath.toString(),classContent);


    }

    public static void createMapperXml(String name) {

        StringBuilder xmlPath = new StringBuilder();
        xmlPath.append(System.getProperty("user.dir"))
                .append("\\ckx-web-persist")
                .append("\\src\\main\\resources\\com\\ckx\\web\\persist\\mapper\\xml\\")
                .append(name).append("MapMapper.xml");
        String xmlContent = readFile(xmlPath.toString());
        if(xmlContent.contains("id=\"paginate\"")){
            throw new RuntimeException("原mapper xml文件中已经生成分页方法！");
        }

        StringBuilder paginate = new StringBuilder();

        paginate.append("\t<select id=\"paginate\" resultMap=\"BaseResultMap\" parameterType=\"map\">");
        paginate.append("\n\t\tselect 'false' as QUERYID,");
        paginate.append("\n\t\t<include refid=\"Base_Column_List\" />");
        paginate.append("\n\t\t from ").append(name.toLowerCase());
        paginate.append("\n\t\t<where>");
        paginate.append("\n\t\t\t<if test=\"id != null\">");
        paginate.append("\n\t\t\t\t and id = #{id,jdbcType=INTEGER}");
        paginate.append("\n\t\t\t</if>");
        paginate.append("\n\t\t</where>");
        paginate.append("\n\t\torder by id desc");
        paginate.append("\n\t\tlimit #{begRow}, #{pageSize}");
        paginate.append("\n\t</select>\n");

        StringBuilder paginateCount = new StringBuilder();
        paginateCount.append("\t<select id=\"paginateCount\" resultType=\"java.lang.Integer\" parameterType=\"map\">");
        paginateCount.append("\n\t\tselect count(0) ");
        paginateCount.append("\n\t\tfrom ").append(name.toLowerCase());
        paginateCount.append("\n\t\t<where>");
        paginateCount.append("\n\t\t\t<if test=\"id != null\">");
        paginateCount.append("\n\t\t\t\t and id = #{id,jdbcType=INTEGER}");
        paginateCount.append("\n\t\t\t</if>");
        paginateCount.append("\n\t\t</where>");
        paginateCount.append("\n\t</select>\n");

        String addContent = paginate.toString() + paginateCount.toString() + "</mapper>";
        xmlContent = xmlContent.replace("</mapper>",addContent);

        writeFile(xmlPath.toString(),xmlContent);

    }

    public static String readFile(String filename) {
        String read;
        FileReader fileread;
        String readStr = "";
        try {
            fileread = new FileReader(filename);
            BufferedReader bufread = new BufferedReader(fileread);
            try {
                while ((read = bufread.readLine()) != null) {
                    readStr = readStr + read + "\r\n";
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("文件内容是:" + "\r\n" + readStr);
        return readStr;
    }

    /**
     * 写文件.
     *
     */
    public static void writeFile(String filename,String content) {
        String filein = content;
        RandomAccessFile mm = null;
        try {
            mm = new RandomAccessFile(filename, "rw");
            mm.writeBytes(filein);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (mm != null) {
                try {
                    mm.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

}
