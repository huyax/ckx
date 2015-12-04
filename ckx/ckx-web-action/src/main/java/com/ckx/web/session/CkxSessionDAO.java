package com.ckx.web.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ckx.lang.constants.Constants;
import com.ckx.web.core.redis.RedisClient;

public class CkxSessionDAO extends AbstractSessionDAO {

    private final static Logger log = LoggerFactory
            .getLogger(CkxSessionDAO.class);

    private
    @Autowired
    RedisClient client;

    private void saveSession(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            log.error("session or session id is null");
            return;
        }
        Serializable sessionId = session.getId();
        try {
            client.hSet(Constants.REDIS_HASH_SESSIONS, sessionId.toString(),
                    session, (int) session.getTimeout() / 1000);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = null;
        try {
            session = (Session) client.hGet(Constants.REDIS_HASH_SESSIONS,
                    sessionId.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return session;
    }

    @Override
    public void delete(Session session) {
        try {
            client.del(session.getId().toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Map<String, Session> map = client.hGetAll(Constants.REDIS_HASH_SESSIONS,
                Session.class);
        Set<Session> set = Collections.emptySet();
        if (map == null || map.isEmpty()) {
            return set;
        }
        for (String key : map.keySet()) {
            set.add(map.get(key));
        }
        return set;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);

    }

}