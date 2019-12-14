package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.model.Request;
import io.reactivex.subjects.BehaviorSubject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RequestMouseMenuListener extends MouseAdapter {
    protected BehaviorSubject<Request> subject;

    public RequestMouseMenuListener(BehaviorSubject<Request> subject) {
        this.subject = subject;
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        RequestMouseMenu menu = new RequestMouseMenu(this.subject);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

