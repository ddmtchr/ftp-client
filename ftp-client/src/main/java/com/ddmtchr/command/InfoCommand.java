package com.ddmtchr.command;

import com.ddmtchr.util.FTPWorker;

public class InfoCommand extends AbstractCommand implements Command {
    private final long id;

    public InfoCommand(FTPWorker ftpWorker, long id) {
        super(ftpWorker);
        this.id = id;
    }

    @Override
    public void execute() {
        System.out.println(worker.infoById(id));
    }
}
