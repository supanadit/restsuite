package com.supanadit.restsuite.listener;

import java.awt.event.*;
import javax.swing.*;

public class EditableCellFocusAction extends WrappedAction implements ActionListener {
    private JTable table;

    /*
     *  Specify the component and KeyStroke for the Action we want to wrap
     */
    public EditableCellFocusAction(JTable table, KeyStroke keyStroke) {
        super(table, keyStroke);
        this.table = table;
    }

    /*
     *  Provide the custom behaviour of the Action
     */
    public void actionPerformed(ActionEvent e) {
        int originalRow = table.getSelectedRow();
        int originalColumn = table.getSelectedColumn();

        invokeOriginalAction(e);

        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();

        //  Keep invoking the original action until we find an editable cell

        while (!table.isCellEditable(row, column)) {
            invokeOriginalAction(e);

            //  We didn't move anywhere, reset cell selection and get out.

            if (row == table.getSelectedRow() && column == table.getSelectedColumn()) {
                table.changeSelection(originalRow, originalColumn, false, false);
                break;
            }

            row = table.getSelectedRow();
            column = table.getSelectedColumn();

            //  Back to where we started, get out.

            if (row == originalRow
                    && column == originalColumn) {
                break;
            }
        }
    }
}