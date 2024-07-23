package com.ddmtchr.command;

import com.ddmtchr.util.FTPWorker;

public class DeleteCommand extends AbstractCommand implements Command {
    private final long id;

    public DeleteCommand(FTPWorker ftpWorker, long id) {
        super(ftpWorker);
        this.id = id;
    }

    @Override
    public void execute() {
        worker.deleteStudent(id);
    }
}
