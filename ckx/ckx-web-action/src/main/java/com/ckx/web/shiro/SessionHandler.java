package com.ckx.web.shiro;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * session状态监听
 */
public class SessionHandler implements SessionListener {

    private Log log = LogFactory.getLog(getClass());

    public void onExpiration(Session arg0) {
        log.debug("session过期！[sessionId ==> " + arg0.getId() + "]");
    }

    public void onStart(Session arg0) {
        log.debug("session创建！[sessionId ==> " + arg0.getId() + "]");
    }

    public void onStop(Session arg0) {
        log.debug("session销毁！[sessionId ==> " + arg0.getId() + "]");
    }

}
