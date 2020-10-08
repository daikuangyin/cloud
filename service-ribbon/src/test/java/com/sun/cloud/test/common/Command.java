package com.sun.cloud.test.common;

import org.apache.commons.lang3.StringUtils;
//import org.apache.mina.core.buffer.IoBuffer;
//import org.apache.mina.core.future.ConnectFuture;
//import org.apache.mina.core.service.IoHandlerAdapter;
//import org.apache.mina.core.session.IoSession;
//import org.apache.mina.filter.logging.LoggingFilter;
//import org.apache.mina.transport.serial.SerialAddress;
//import org.apache.mina.transport.serial.SerialConnector;

public class Command {



//    /**
//     * 发送指令至串口
//     *
//     * @param command
//     */
//    private void sendCommand(String command) {
//        if (StringUtils.isNotBlank(command)) {
//            IoBuffer buffer = IoBuffer.wrap(command.getBytes());
//            IoSession session = null;
//            try {
//                //创建串口连接
//                SerialConnector connector = new SerialConnector();
//                //绑定处理handler
//                connector.setHandler(new IoHandlerAdapter());
//                //设置过滤器
//                connector.getFilterChain().addLast("logger", new LoggingFilter());
//                //配置串口连接
//                SerialAddress address = new SerialAddress(
//                        BasicConstants.SERIAL_PORT,
//                        BasicConstants.BAUD_RATE,
//                        SerialAddress.DataBits.DATABITS_8,
//                        SerialAddress.StopBits.BITS_1,
//                        SerialAddress.Parity.NONE,
//                        SerialAddress.FlowControl.NONE);
//                ConnectFuture future = connector.connect(address);
//                future.await();
//                session = future.getSession();
//                session.write(buffer);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (session != null) {
//                    session.close(true);
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Command command = new Command();
//        command.sendCommand("whefkujehsdk");
//    }
}
