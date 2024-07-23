package com.ddmtchr.command;

import com.ddmtchr.util.FTPWorker;

public class HelpCommand extends AbstractCommand implements Command {

    public HelpCommand(FTPWorker ftpWorker) {
        super(ftpWorker);
    }

    @Override
    public void execute() {
        worker.printHelp();
    }
}
