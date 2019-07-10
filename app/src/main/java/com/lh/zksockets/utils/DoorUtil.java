package com.lh.zksockets.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DoorUtil {

    public static int opendoor(String IP) {

        byte[] byteCmd = new byte[]{(byte) 0x17, (byte) 0x40,
                (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff,
                (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00};


        byteCmd[8] = (byte) 1;

//        byteCmd[40] = (byte) (tag & 0xff);
//        byteCmd[41] = (byte) ((tag >> 8) & 0xff);
//        byteCmd[42] = (byte) ((tag >> 16) & 0xff);
//        byteCmd[43] = (byte) ((tag >> 24) & 0xff);


        int ret = -13;
        byte content[] = null;
        DatagramSocket datagramSocket = null;
        try {
            ELog.i("=======opendoor=====0000000===========");
            datagramSocket = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(byteCmd, byteCmd.length);
            dp.setSocketAddress(new InetSocketAddress(IP, 60000));
            ELog.i("=======opendoor=====222222===========");
            datagramSocket.send(dp);//发送一条信息
            ELog.i("=======opendoor=====333333===========");

//            byte recvDataByte[] = new byte[64];
//
//            Thread.sleep(200);
//            DatagramPacket dataPacket = new DatagramPacket(recvDataByte, recvDataByte.length);
//            ELog.i("=======opendoor=====44444444===========");
//            datagramSocket.receive(dataPacket);
//            ELog.i("=======opendoor=====555555555===========");
//
//            content = dataPacket.getData();
//            ELog.i("=====opendoor===content=======" + content);

        } catch (UnknownHostException e) {
            e.printStackTrace();
            ELog.i("=======opendoor====UnknownHostException===========");
        } catch (SocketException e) {
            e.printStackTrace();
            ELog.i("=======opendoor====SocketException===========");
        } catch (IOException e) {
            e.printStackTrace();
            ELog.i("======opendoor=====IOException===========");
        } catch (Exception e) {
            e.printStackTrace();
            ELog.i("=======opendoor====Exception===========");
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }

            if ((content != null) && (content.length == 64)) {
                ret = content[8];
            } else {
                ret = -13;
            }

            ELog.i("=====finally===11111========" + ret);
        }

        return ret;

    }
}
