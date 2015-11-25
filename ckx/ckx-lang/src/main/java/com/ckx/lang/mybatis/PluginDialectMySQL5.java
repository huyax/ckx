package com.ckx.lang.mybatis;

import java.util.List;

public class PluginDialectMySQL5 extends BasePluginDialect {
  private static final PluginDialectMySQL5 instance = new PluginDialectMySQL5();

  public static PluginDialectMySQL5 getInstance() {
    return instance;
  }

  private PluginDialectMySQL5() {
  }

  @Override
  protected String generateAvgSql(Pager page, String sql) {
    StringBuffer sbSQL = new StringBuffer();
    List<String> sumFields = page.getSumFields();
    sbSQL.append("select 1");
    for (String field : sumFields) {
      sbSQL.append(", AVG(t_out.").append(field).append(") AS ").append(field);
    }
    sbSQL.append(" FROM (").append(sql).append(") t_out");
    return sbSQL.toString();
  }

  protected String generateCountSql(Pager page, String sql) {
    StringBuffer sbSQL = new StringBuffer();
    sbSQL.append("select count(*) FROM (").append(sql).append(") t_out");
    return sbSQL.toString();
  }

  protected String generateListSql(Pager page, String sql) {
    StringBuffer sbSQL = new StringBuffer();
    String sort = page.getSort();
    String order = page.getOrder();
    sbSQL.append("select t_out.* FROM (").append(sql).append(") t_out ");
    if (sort != null) {
      sbSQL.append(" order by t_out.").append(sort).append(" ").append(order != null ? order : "asc");
    }
    if (page.getPageSize() != null) {
      sbSQL.append(" limit ").append(page.getBegRow()).append(", ").append(page.getPageSize());
    }
    return sbSQL.toString();
  }

  @Override
  protected String generateSumSql(Pager page, String sql) {
    StringBuffer sbSQL = new StringBuffer();
    List<String> sumFields = page.getSumFields();
    sbSQL.append("select 1");
    for (String field : sumFields) {
      sbSQL.append(", SUM(t_out.").append(field).append(") AS ").append(field);
    }
    sbSQL.append(" FROM (").append(sql).append(") t_out");
    return sbSQL.toString();
  }

}
