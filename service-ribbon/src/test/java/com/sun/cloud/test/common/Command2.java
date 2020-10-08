package com.sun.cloud.test.common;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Command2 extends Thread implements SerialPortEventListener {
    //监听器，我的理解是独立开辟一个线程监听串口数据
    static CommPortIdentifier portId; //串口通信管理类
    static Enumeration<?> portList; //有限链接上的端口的枚举
    InputStream inputStream; //从串口来的输入流
    static OutputStream outputStream;//从串口输出的流
    static SerialPort serialPort; //串口的引用
    //堵塞队列用来存放读到的数据
    private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();

    /**
     * 持续坚挺端口上是否有数据流
     *
     * @param event
     */
    @Override
    public void serialEvent(SerialPortEvent event) {

        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE: //当有可用数据时读取数据
                byte[] readBuffer = new byte[20];
                try {
                    int numBytes = -1;
                    while (inputStream.available() > 0) {
                        numBytes = inputStream.read(readBuffer);

                        if (numBytes > 0) {
                            msgQueue.add(new Date() + "真是收到的数据为:-------"
                                    + new String(readBuffer));
                            readBuffer = new byte[20]; //重新构造缓冲对象
                        } else {
                            msgQueue.add("额----------没有读到数据");
                        }


                    }
                } catch (IOException e) {

                }
                break;
        }
    }


    public int startComPort() {
        //通过串口通信管理类获得当前链接上的串口列表
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {

            //获取相应串口对象
            portId = (CommPortIdentifier) portList.nextElement();

            System.out.println("设备类型：-------》" + portId.getPortType());
            System.out.println("设备名称：-------》" + portId.getName());
            //判断端口类型是否串口
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                //判断如果COM4串口存在，就打开改串口
                if (portId.getName().equals(BasicConstants.SERIAL_PORT)) {
                    try {
                        //打开串口名字为 COM4 (名字任意)，延迟为2毫秒
                        CommPort commPort = portId.open(BasicConstants.SERIAL_PORT, 2000);
                        serialPort = (SerialPort)commPort;
                    } catch (PortInUseException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    //设置当前串口的输入流输出流
                    try {
                        inputStream = serialPort.getInputStream();
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    //给当前串口添加一个监听器
                    try {
                        serialPort.addEventListener(this);
                    } catch (TooManyListenersException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    //设置监听器生效，即：当有数据时通知
                    serialPort.notifyOnDataAvailable(true);

                    //设置串口的一些读写参数
                    try {
                        //比特率 数据位 停止位 奇偶校验位
                        serialPort.setSerialPortParams(
                                BasicConstants.BAUD_RATE,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE
                        );
                    } catch (UnsupportedCommOperationException e) {
                        e.printStackTrace();
                        return 0;
                    }

                    return 1;
                }
            }

        }
        return 0;
    }

    @Override
    public void run() {
        try {
            System.out.println("-------------------任务处理线程运行了--------------------------");
            while (true) {
                //如果堵塞队列中存在数据就将其输出
                if (msgQueue.size() > 0) {
                    System.out.println(msgQueue.take());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Command2 command2 = new Command2();
        int i = command2.startComPort();
        if (i == 1) {
            //启动线程来处理收到的数据
            command2.start();

            try {
                String str = "哈哈 ------ 你好";
                System.out.println("发出字节数: " + str.getBytes("gbk").length);
                outputStream.write(
                        str.getBytes("gbk"),
                        0,
                        str.getBytes("gbk").length
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }

    }
}
