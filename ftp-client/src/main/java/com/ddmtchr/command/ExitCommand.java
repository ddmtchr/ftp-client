package com.ddmtchr.command;

import com.ddmtchr.util.FTPWorker;

public class ExitCommand extends AbstractCommand implements Command {
    public ExitCommand(FTPWorker ftpWorker) {
        super(ftpWorker);
    }

    @Override
    public void execute() {
        worker.saveAndExit();
    }
}
