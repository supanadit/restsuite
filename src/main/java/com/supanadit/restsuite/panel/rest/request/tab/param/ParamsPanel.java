package com.supanadit.restsuite.panel.rest.request.tab.param;

import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.component.table.ParamsTable;
import com.supanadit.restsuite.helper.UrlParser;
import io.reactivex.disposables.Disposable;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class ParamsPanel extends JPanel {

    public ParamsPanel(InputTextURL inputTextURL) {
        super(new MigLayout());

        ParamsTable paramsTable = new ParamsTable(false, null);

        Disposable disposable = inputTextURL.getSubject().throttleWithTimeout(300, TimeUnit.MILLISECONDS).subscribe((s) -> {
            UrlParser urlParser = new UrlParser(s);
            paramsTable.setFromRequestArrayList(urlParser.getQueryParams());
        });

        add(paramsTable, "growx,pushx");
    }
}
