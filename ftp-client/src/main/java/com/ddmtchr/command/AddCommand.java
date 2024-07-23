package com.ddmtchr.command;

import com.ddmtchr.util.FTPWorker;

public class AddCommand extends AbstractCommand implements Command {
    private final String name;

    public AddCommand(FTPWorker ftpWorker, String name) {
        super(ftpWorker);
        this.name = name;
    }

    @Override
    public void execute() {
        worker.addStudent(name);
    }
}
