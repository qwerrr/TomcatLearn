package org.yan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * Copyright: Copyright (c) 2002
 * Company: Brainy Software
 * @author Budi Kurniawan
 * @version 1.0
 */

public class HttpSniffer extends JFrame {

    private static final int BUFFER_LENGTH = 2048;

    JLabel jLabel1 = new JLabel();
    JTextField address = new JTextField();
    JLabel jLabel2 = new JLabel();
    JTextField port = new JTextField();


    JLabel jLabel3 = new JLabel();
    JTextField command = new JTextField();

    JLabel jLabel4 = new JLabel();
    JTextField requestBody = new JTextField();

    JButton send = new JButton();

    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea response = new JTextArea();

    public HttpSniffer() {
        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
            HttpSniffer frame = new HttpSniffer();
            frame.setBounds(0, 0, 600, 400);
            frame.setVisible(true);
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);

        jLabel1.setText("Address");
        jLabel1.setBounds(new Rectangle(8, 7, 69, 24));
        address.setText("127.0.0.1");
        address.setBounds(new Rectangle(74, 7, 143, 24));


        jLabel2.setText("Port");
        jLabel2.setBounds(new Rectangle(230, 7, 30, 24));
        port.setText("8080");
        port.setBounds(new Rectangle(265, 7, 72, 24));

        jLabel3.setText("head");
        jLabel3.setBounds(new Rectangle(8, 39, 74, 24));
        command.setText("GET /index.jsp HTTP/1.1");
        command.setBounds(new Rectangle(74, 39, 263, 24));

        jLabel4.setText("body");
        jLabel4.setBounds(new Rectangle(8, 71, 74, 24));
        requestBody.setText("");
        requestBody.setBounds(new Rectangle(74, 71, 499, 24));

        send.setText("Send Request");
        send.setBounds(new Rectangle(347, 7, 225, 55));
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                send_actionPerformed(e);
            }
        });

        jScrollPane1.setBounds(new Rectangle(8, 103, 565, 241));
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(address, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(port, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(command, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(requestBody, null);
        this.getContentPane().add(send, null);
        this.getContentPane().add(jScrollPane1, null);
        jScrollPane1.getViewport().add(response, null);
    }

    void send_actionPerformed(ActionEvent e) {
        response.setText("");
        String host = "";
        int portNumber = 0;
        boolean ok = true;
        try {
            host = address.getText();
            portNumber = Integer.parseInt(port.getText());
        }catch (Exception ex) {
            ex.printStackTrace();
            ok = false;
        }

        if (ok) {
            try {
                Socket socket = new Socket(host, portNumber);

                OutputStream os = socket.getOutputStream();

                String message = command.getText();
                os.write(message.getBytes());
                os.write("\r\n".getBytes());
                os.write("Host: localhost:8080".getBytes());
                os.write("\r\n".getBytes());
                os.write("Connection: Close".getBytes());
                os.write("\r\n\r\n".getBytes());
                //发送请求体
                if(requestBody.getText() != null && requestBody.getText().trim() != ""){
                    os.write(requestBody.getText().trim().getBytes());
                }
                os.flush();
                socket.shutdownOutput();

                InputStream is = socket.getInputStream();

                //读取响应
                byte[] buffer = new byte[BUFFER_LENGTH];
                int len = is.read(buffer);
                byte[] result = new byte[len];
                System.arraycopy(buffer,0,result,0,len);
                response.setText(new String(result)+"\n");
                System.out.println("响应:"+new String(result));
                System.out.println("响应长度:"+new String(result).length());
                is.close();
                socket.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String delEndCrlf(String str){
        if(str.endsWith("\r\n")){
            return str.substring(0,str.length()-2);
        }else if(str.endsWith("\n")){
            return str.substring(0,str.length()-1);
        }
        return str;
    }
}