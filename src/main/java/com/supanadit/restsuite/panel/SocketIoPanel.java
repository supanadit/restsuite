package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.InputSocketIoListener;
import com.supanadit.restsuite.component.InputSocketIoMessage;
import com.supanadit.restsuite.component.InputSocketIoURL;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.socket.client.IO;
import io.socket.client.Socket;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SocketIoPanel extends JPanel {
    Socket socket;
    boolean isConnected = false;
    String connectionText = "Connect";
    JButton connectDisconnectButton;
    JButton emitButton;
    ArrayList<String> listenerList;

    public SocketIoPanel() {
        this.setLayout(new MigLayout("insets 10 10 10 10"));

        listenerList = new ArrayList<>();

        Color background = UIManager.getColor("Table.background");
        Color lineColor = UIManager.getColor("Table.gridColor");
        Color fontColor = UIManager.getColor("FormattedTextField.foreground");
        Color selectionColor = UIManager.getColor("FormattedTextField.selectionBackground");

        connectDisconnectButton = new JButton(connectionText);

        JComboBox<String> messageSendType = new JComboBox<>();
        messageSendType.addItem("String");
        messageSendType.addItem("JSON");
        messageSendType.addItem("Array");
        messageSendType.addItem("Object");
        messageSendType.addItem("Number");
        messageSendType.addItem("Boolean");

        InputSocketIoMessage emitChannel = InputSocketIoMessage.getComponent();

        JPanel socketIoHeadPanel = new JPanel(new MigLayout("fill"));
        socketIoHeadPanel.add(new JLabel("SocketIO URL"), "growx,pushx,wrap");
        InputSocketIoURL socketIoURL = InputSocketIoURL.getComponent();
        socketIoHeadPanel.add(socketIoURL, "growx,pushx");
        socketIoHeadPanel.add(connectDisconnectButton);
        JPanel socketIoLeftPanel = new JPanel(new MigLayout("w 200"));
        socketIoLeftPanel.add(new JLabel("Send Message"), "growx,pushx,wrap");
        socketIoLeftPanel.add(emitChannel, "growx,pushx,wrap");
        socketIoLeftPanel.add(messageSendType, "growx,pushx,wrap");

        RSyntaxTextArea emitBody = new RSyntaxTextArea();
        emitBody.setCodeFoldingEnabled(true);
        emitBody.setCurrentLineHighlightColor(background);
        emitBody.setBackground(background);
        emitBody.setTabLineColor(background);
        emitBody.setBorder(BorderFactory.createLineBorder(lineColor));
        emitBody.setForeground(fontColor);
        emitBody.setSelectionColor(selectionColor);
        // This Code let selected text to be white color
        emitBody.setUseSelectedTextColor(true);
        emitBody.setSelectedTextColor(Color.white);

        emitButton = new JButton("Emit");
        emitButton.setEnabled(isConnected);
        InputSocketIoListener inputListener = InputSocketIoListener.getComponent();
        RTextScrollPane emitBodyScrollPane = new RTextScrollPane(emitBody);

        DefaultTableModel listenerDefaultModel = new DefaultTableModel();
        listenerDefaultModel.addColumn("Listener");

        JTable listenerTable = new JTable(listenerDefaultModel);
        JScrollPane listenerScrollPane = new JScrollPane(listenerTable);

        socketIoLeftPanel.add(emitBodyScrollPane, "grow,push,wrap");
        socketIoLeftPanel.add(emitButton, "growx,pushx,wrap");
        JPanel socketIoRightPanel = new JPanel(new MigLayout("w 200"));
        socketIoRightPanel.add(new JLabel("Listener"), "pushx,growx,wrap");
        socketIoRightPanel.add(inputListener, "pushx,growx,wrap");
        socketIoRightPanel.add(new JButton("Add Listener"), "pushx,growx,wrap");
        socketIoRightPanel.add(new JSeparator(), "pushx,growx,wrap");
        socketIoRightPanel.add(listenerScrollPane, "push,grow");
        this.add(new JLabel("Response Message"), "growx,pushx,wrap");

        RSyntaxTextArea responseBody = new RSyntaxTextArea();
        responseBody.setCodeFoldingEnabled(true);
        responseBody.setCurrentLineHighlightColor(background);
        responseBody.setBackground(background);
        responseBody.setTabLineColor(background);
        responseBody.setBorder(BorderFactory.createLineBorder(lineColor));
        responseBody.setForeground(fontColor);
        responseBody.setSelectionColor(selectionColor);
        // This Code let selected text to be white color
        responseBody.setUseSelectedTextColor(true);
        responseBody.setSelectedTextColor(Color.white);

        RTextScrollPane responseBodyScrollPane = new RTextScrollPane(responseBody);

        this.add(responseBodyScrollPane, "grow,push");
        this.add(socketIoHeadPanel, "north");
        this.add(socketIoLeftPanel, "west");
        this.add(socketIoRightPanel, "east");

        connectDisconnectButton.addActionListener(e -> {
            String url = socketIoURL.getText();
            if (!isConnected) {
                if (!url.isEmpty()) {
                    try {
                        socket = IO.socket(url);
                        Observable<Long> tryConnect = Observable.interval(1, TimeUnit.SECONDS)
                                .take(4) // Waiting for 4 second
                                .map(v -> v + 1)
                                .doOnComplete(() -> {
                                    if (!isConnected) {
                                        responseBody.append("Could not connect to ".concat(socketIoURL.getText()).concat("\n"));
                                        setStatus(false);
                                    }
                                });
                        Disposable connectDisposable = tryConnect.subscribe();
                        socket.on(Socket.EVENT_CONNECT, args -> {
                            connectDisposable.dispose();
                            setStatus(true);
                        }).on("reply", args -> {
                            String body = Arrays.toString(args)
                                    .replace(",", "")
                                    .replace("[", "")
                                    .replace("]", "")
                                    .trim();

                            responseBody.append("reply".concat(" : ").concat(body).concat("\n"));
                        }).on(Socket.EVENT_DISCONNECT, args -> {
                            setStatus(false);
                            responseBody.append("Disconnected from ".concat(socketIoURL.getText()).concat("\n"));
                        });
                        socket.connect();
                        connectDisconnectButton.setEnabled(false);
                        connectDisconnectButton.setText("Connecting");
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                setStatus(false);
            }
        });

        emitButton.addActionListener(e -> {
            if (socket != null) {
                if (!(emitChannel.getText().isEmpty() && emitBody.getText().isEmpty())) {
                    socket.emit(emitChannel.getText(), emitBody.getText());
                }
            }
        });
    }

    public void setStatus(boolean status) {
        isConnected = status;

        emitButton.setEnabled(isConnected);
        // It always be true
        connectDisconnectButton.setEnabled(true);
        if (isConnected) {
            connectDisconnectButton.setText(getButtonText());
        } else {
            if (socket != null) {
                connectDisconnectButton.setText(getButtonText());
                socket.close();
            }
        }
    }

    public String getButtonText() {
        return (isConnected) ? "Disconnect" : "Connect";
    }
}
