package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.model.Request;
import io.reactivex.subjects.BehaviorSubject;

import javax.swing.*;

class RequestMouseMenu extends JPopupMenu {
    protected BehaviorSubject<Request> subject;
    JMenuItem addData;

    public RequestMouseMenu(BehaviorSubject<Request> subject) {
        this.subject = subject;
        addData = new JMenuItem("Add");
        addData.addActionListener((e) -> {
            this.subject.onNext(new Request("", ""));
        });
        add(addData);
    }
}