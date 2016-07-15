package com.rskpdev;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**This class is for writing logs to
 *output
 */

public class UiLogStream extends PrintStream {
    public UiLogStream() {
        super(new OutputStream() {
            StringBuffer buffer = new StringBuffer();

            @Override
            public synchronized void write(int ch) throws IOException {
                buffer.append((char) ch);
            }

            @Override
            public synchronized void flush() {
                buffer = new StringBuffer();
            }
        }, true);
    }
}