package com.ddmtchr;

import com.ddmtchr.util.FTPWorker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.UnknownHostException;

public class ConnectionTest {

    @Test(expectedExceptions = sun.net.ftp.FtpLoginException.class)
    public void testInvalidCredentials() throws IOException {
        FTPWorker client = new FTPWorker("ftp://invalid:invalid@192.168.3.23:21");
    }

    @Test(expectedExceptions = UnknownHostException.class)
    public void testInvalidIP() throws IOException {
        FTPWorker client = new FTPWorker("ftp://invalid:invalid@492.168.333.23:21");
    }

    @Test
    public void testConnectionOk() throws IOException {
//        FTPWorker client = new FTPWorker("ftp://ftpuser:qwerty@192.168.3.23:21"); // connected and received list. depends on server
//        Assert.assertNotNull(client.listStudents());
    }

}
