package com.ddmtchr.command;

import com.ddmtchr.util.FTPWorker;

public abstract class AbstractCommand {
    protected final FTPWorker worker;

    public AbstractCommand(FTPWorker ftpWorker) {
        worker = ftpWorker;
    }
}
