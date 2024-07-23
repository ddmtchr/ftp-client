package com.ddmtchr.command;

import com.ddmtchr.util.FTPWorker;

public class ListCommand extends AbstractCommand implements Command {
    public ListCommand(FTPWorker ftpWorker) {
        super(ftpWorker);
    }

    @Override
    public void execute() {
        System.out.println(worker.listStudents());
    }
}
