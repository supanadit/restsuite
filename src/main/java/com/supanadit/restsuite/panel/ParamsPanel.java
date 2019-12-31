package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.RequestTable;
import com.supanadit.restsuite.helper.UrlParser;
import io.reactivex.subjects.PublishSubject;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class ParamsPanel extends JPanel {
    PublishSubject<String> urlSubject;
    RequestTable paramsTable;

    public ParamsPanel(PublishSubject<String> urlSubject) {
        super(new MigLayout());

        this.urlSubject = urlSubject;

        this.paramsTable = new RequestTable(false, false, false);

        this.urlSubject.throttleWithTimeout(300, TimeUnit.MILLISECONDS).subscribe((s) -> {
            UrlParser urlParser = new UrlParser(s);
            this.paramsTable.setFromRequestArrayList(urlParser.getQueryParams());
        });

        this.add(paramsTable, "growx,pushx");
    }
}