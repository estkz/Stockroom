package Serial;
import com.fazecast.jSerialComm.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SerialComm {
    public boolean display = false;
    private SerialPort port;
    ArrayList<ArrayList<Byte>> data = new ArrayList<ArrayList<Byte>>();
    ArrayList<Byte> buffer = new ArrayList<Byte>();

    public SerialComm(SerialPort port) {
        this.port = port;
    }

    public SerialComm() {}

    public void setPort(SerialPort port){
        if(this.port.isOpen()){
            this.port.closePort();
        }

        this.port = port;
    }
    public static SerialPort[] listPorts(){
        return SerialPort.getCommPorts();
    }
    public void displayInfo(){
        if(display) {
            System.out.println("ReadBufferSize: " + port.getDeviceReadBufferSize() + " Bytes");
            System.out.println("WriteBufferSize: " + port.getDeviceWriteBufferSize() + " Bytes");
            System.out.println("baudrate: " + port.getBaudRate());
            System.out.println("description: " + port.getPortDescription());
        }
    }
    public void openPort(){
        if(display){System.out.println("trying to open serial port");}

        port.openPort();
        this.flush();

    }
    public void closePort(){
        port.closePort();
    }


    public String readLoose(){
        byte[] readBuffer;
        if(port.bytesAvailable() > 0){
            readBuffer = new byte[port.bytesAvailable()];
            int numRead = port.readBytes(readBuffer, readBuffer.length);
            return new String(readBuffer);
        }
        return null;
    }
    public String[] readLines(){
        ArrayList<ArrayList<Byte>> data = new ArrayList<ArrayList<Byte>>();
        String[] stringData = new String[0];
        byte[] readBuffer;
        boolean lineCreated = false;
        boolean remain = false;

        //are bytes available
        if(port.bytesAvailable() > 0){

            //read buffer
            readBuffer = new byte[port.bytesAvailable()];
            int numRead = port.readBytes(readBuffer, readBuffer.length);


            //get saved buffer
            //put read  buffer in saved buffer
            for (byte b : readBuffer) {
                buffer.add(b);
            }


            //get data lines from saved buffer (split on LF NL)
            ArrayList<ArrayList<Integer>> sublists = new ArrayList<ArrayList<Integer>>();
            int startPointer = 0;
            for (int i =0;i<buffer.size();i++){
                if(buffer.get(i) == 13 && (i+1 < buffer.size()) && buffer.get(i + 1) == 10){
                    /*LF char code 13 , NL char code 10*/

                    //add slice points to a list
                    if((i-1) > 0 &&  startPointer!=i-1) {
                        ArrayList<Integer> sublist = new ArrayList<Integer>();
                        sublist.add(startPointer);
                        sublist.add(i - 1);
                        sublists.add(sublist);
                        lineCreated = true;
                    }
                    //set start point for next line
                    if((i+2 < buffer.size())){
                        startPointer=(i+2);
                    }
                }
            }

            int maxLinePos = 0;
            for (ArrayList<Integer> sublist : sublists) {
                Collections.sort(sublist);
                int[] pos = {sublist.get(0),sublist.get(1)};//only for making sublist

                if(maxLinePos < pos[1]){
                    maxLinePos = pos[1];
                }

                //slice the data
                List<Byte> bufferSubList = buffer.subList(pos[0], pos[1]+1);

                //add bufferSubList to dataSublist
                ArrayList<Byte> dataSublist = new ArrayList<Byte>(bufferSubList);

                //add dataSublist to data
                data.add(dataSublist);
            }

            boolean removeFromHere = false;
            for(int b = buffer.size()-1; b>-1;b--){
                if(buffer.get(b) == 10 && b-1>-1 && buffer.get(b-1) == 13){
                    removeFromHere = true;
                }
//                if (buffer.get(b) == 10 || buffer.get(b) == 13){
                    /*radical remove*/
//                    removeFromHere = true;
//                }

                if(removeFromHere){
                    buffer.subList(0, b + 1).clear();
                    break;
                }
            }


            //convert bytes to string
            stringData = new String[data.size()];
            for(int i = 0;i<data.size(); i++) {
                StringBuilder line = new StringBuilder();

                for (int i2 = 0; i2 < data.get(i).size(); i2++) {
                     line.append(Character.toString(data.get(i).get(i2)));
                }
                stringData[i] = line.toString();
            }

            //return data
            return stringData;

        }

        //return empty data
        return stringData;
    }
    public void writeLine(String inputString){

        //string to byte
        byte[] byteArray = inputString.getBytes();

        //add LF and NL char to end
        byte[] byteArray2 = new byte[byteArray.length+2];
        for(int i = 0;i<byteArray.length;i++){
            byteArray2[i] = byteArray[i];
        }
        byteArray2[byteArray.length] = 13;
        byteArray2[byteArray.length+1] = 10;
        byteArray = byteArray2;


        //write byte
        port.writeBytes(byteArray, byteArray.length);
    }

    public void writeMultiTimes(String inputString,int repeatTimes,int delayMS){
        int i = 1;
//        boolean repeat = true;
        long time = 0;
        while(true) {
            if(System.currentTimeMillis() - time > delayMS) {
                if (i > repeatTimes) {
//                    repeat = false;
                    break;
                }
                this.writeLine(inputString);
                time = System.currentTimeMillis();
                i++;
            } else if (port.bytesAvailable()>0) {
                System.out.println("bytes detected");
                break;
            }

        }
    }


        public void flush(){
            if(display){System.out.println("start flush");}
            boolean running = true;
            int delay = 1000;
            long time = 0;

            while(true) {
                port.flushIOBuffers();
                if(running){
                    time = System.currentTimeMillis();
                    running = false;
                }else if(System.currentTimeMillis()-time > delay){
                    break;
                }
            }
            if(display){System.out.println("end flush");}
        }

}




