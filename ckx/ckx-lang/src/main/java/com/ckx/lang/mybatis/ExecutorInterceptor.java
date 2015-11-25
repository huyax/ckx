package com.ckx.lang.mybatis;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts(@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
    RowBounds.class, ResultHandler.class }))
public class ExecutorInterceptor implements Interceptor {
  private final static Log log = LogFactory.getLog(ExecutorInterceptor.class);

  @SuppressWarnings("unused")
  private PluginDialect.Type getDialectType(MappedStatement mappedStatement) {
    try {
      Configuration configuration = mappedStatement.getConfiguration();
      return PluginDialect.Type.loadValue(configuration.getDatabaseId());
    } catch (Exception e) {
      log.error("mybatis 配置错误-->未配置dialect", e);
    }
    return null;
  }

  public Object intercept(Invocation invocation) throws Throwable {
    // Object paramObject = invocation.getArgs()[1];
    // if (paramObject instanceof Pager) {
    // Pager page = (Pager) paramObject;
    // if (!page.isSimpleQuery()) {
    // PluginDialect dialect = null;
    // MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
    // switch (getDialectType(mappedStatement)) {
    // case MySQL:
    // dialect = PluginDialectMySQL5.getInstance();
    // break;
    // default:
    // throw new RuntimeException("自动分页不支持制定的Dialect配置");
    // }
    // }
    // }
    return invocation.proceed();
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  public void setProperties(Properties properties) {
  }

}
