package com.supanadit.restsuite.panel.api.request.tab.param;

import com.supanadit.restsuite.component.table.RequestTable;
import com.supanadit.restsuite.helper.UrlParser;
import io.reactivex.disposables.Disposable;
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

        paramsTable = new RequestTable(false, null);

        Disposable disposable = urlSubject.throttleWithTimeout(300, TimeUnit.MILLISECONDS).subscribe((s) -> {
            UrlParser urlParser = new UrlParser(s);
            paramsTable.setFromRequestArrayList(urlParser.getQueryParams());
        });

        add(paramsTable, "growx,pushx");
    }
}
