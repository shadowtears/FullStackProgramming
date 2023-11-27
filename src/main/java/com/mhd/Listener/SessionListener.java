package com.mhd.Listener;

import com.mhd.util.MySessionContext;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author MouHongDa
 * @date 2023/11/26 19:00
 */
public class SessionListener implements HttpSessionListener {

    private MySessionContext myc = MySessionContext.getInstance();

    public SessionListener() {
    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        myc.addSession(session);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        myc.delSession(session);
    }
}