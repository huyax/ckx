package com.ckx.lang.mybatis;

import java.sql.Connection;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

public class PluginDialectOracle implements PluginDialect {

  @Override
  public String prepareStatement(MappedStatement mappedStatement, BoundSql boundSql, Connection connection, Pager page) {
    return null;
  }

}
