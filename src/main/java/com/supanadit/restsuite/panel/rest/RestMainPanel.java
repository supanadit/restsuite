package com.supanadit.restsuite.panel.rest;

import net.miginfocom.swing.MigLayout;
import org.apache.batik.transcoder.TranscoderException;

import javax.swing.*;
import java.io.IOException;

public class RestMainPanel extends JPanel {
    public RestMainPanel() throws IOException, TranscoderException {
        setLayout(new MigLayout("insets 0 0 0 0"));

        RestPanel restPanel = new RestPanel();

        add(restPanel, "push,grow");
    }
}
