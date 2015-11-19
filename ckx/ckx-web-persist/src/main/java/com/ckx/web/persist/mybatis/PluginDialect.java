package com.ckx.web.persist.mybatis;

import java.sql.Connection;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import com.ckx.lang.Pager;

public interface PluginDialect {

  public enum Type {
    DB2, Derby, H2, HSQL, Informix, MySQL, Oracle, PostgreSQL, Sybase;

    public static Type loadValue(String value) {
      if (value == null) return null;
      for (Type type : Type.values()) {
        if (type.name().toUpperCase().equals(value.toUpperCase())) return type;
      }
      return null;
    }
  }

  /**
   * 执行SQL包装
   * 
   * @param mappedStatement
   * @param boundSql
   * @param connection
   * @param page
   * @return
   */
  public String prepareStatement(MappedStatement mappedStatement, BoundSql boundSql, Connection connection, Pager page);

}
