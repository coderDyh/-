package guc;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 19:20
 * @description 管道
 */
public class Piped {

    public static void main(String[] args) throws Exception{
        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();
        pipedWriter.connect(pipedReader);
        Thread printThread = new Thread(new Print(pipedReader), "PrintThread");
        printThread.start();
        int recieved = 0;
        try{
            while ((recieved = System.in.read()) != -1){
                pipedWriter.write(recieved);
            }
        }finally {
            pipedWriter.close();
        }
    }
    static class Print implements Runnable{
        private PipedReader pipedReader;

        public Print(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }


        @Override
        public void run() {
            int recieved = 0;
            while (true){
                try {
                    if (!((recieved = pipedReader.read()) != -1)) {
                        System.out.println((char) recieved);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
