package com.ckx.web.persist.mybatis;

import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;

import com.ckx.lang.Pager;

@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }))
public class PageInterceptor implements Interceptor {
  private final static Log log = LogFactory.getLog(PageInterceptor.class);

  private PluginDialect.Type getDialectType(MappedStatement mappedStatement) {
    try {
      Configuration configuration = mappedStatement.getConfiguration();
      PluginDialect.Type dialectType = null;
      Object databaseId = null;

      if (dialectType == null) {
        databaseId = configuration.getDatabaseId();
        if (databaseId != null) dialectType = PluginDialect.Type.loadValue((String) databaseId);
      }
      if (dialectType == null) {
        databaseId = configuration.getVariables().get("dialect");
        if (databaseId != null) dialectType = PluginDialect.Type.loadValue((String) databaseId);
      }
      if (dialectType == null) {
        dialectType = PluginDialect.Type.MySQL;
      }

      return dialectType;
    } catch (Exception e) {
      log.error("mybatis 配置错误-->未配置dialect", e);
    }
    return null;
  }

  public Object intercept(Invocation invocation) throws Throwable {
    if (invocation.getTarget() instanceof RoutingStatementHandler) {
      RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
      StatementHandler delegate = (StatementHandler) ReflectHelper.getValueByFieldName(handler, "delegate");
      BoundSql boundSql = delegate.getBoundSql();
      Object obj = boundSql.getParameterObject();
      if (obj instanceof Pager) {
        Pager page = (Pager) obj;
        if (!page.isSimpleQuery()) {
          PluginDialect dialect = null;
          MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate,
              "mappedStatement");
          Connection connection = (Connection) invocation.getArgs()[0];
          switch (getDialectType(mappedStatement)) {
          case MySQL:
            dialect = PluginDialectMySQL5.getInstance();
            break;
          default:
            throw new RuntimeException("自动分页不支持制定的Dialect配置");
          }
          String sql = dialect.prepareStatement(mappedStatement, boundSql, connection, page);
          ReflectHelper.setValueByFieldName(boundSql, "sql", sql);
        }
      }
    }
    return invocation.proceed();
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  public void setProperties(Properties properties) {
  }

}
