package Serial;

import com.fazecast.jSerialComm.SerialPort;

import java.util.*;


public class SimpleSerial {


    long time = System.currentTimeMillis();
    int timeout = 15000;
    int gridPos = 0;
    HashMap<String, SerialComm> portMap = new HashMap<String, SerialComm>();
    HashMap<String, ArrayList<String[]>> portBuffer = new HashMap<String, ArrayList<String[]>>();

//    boolean calibited


    public SimpleSerial(){

    }

    public void setup(){
        //sets up the environment to communicate correctly with the robot

        //list ports
        ArrayList<SerialComm> ports = new ArrayList<SerialComm>();
        SerialPort[] listPorts = SerialComm.listPorts();

        //open all ports
        for (SerialPort serialPort : listPorts) {
            boolean reading = true;
            SerialComm port = new SerialComm(serialPort);
            port.openPort();

            //send id request
            String serialCommand = SerialDecode.encode(new String[]{"req","id"});
            long time = System.currentTimeMillis();



            boolean send = true;
            int sendCount = 0;
            int maxSendCount= 5;
            //read til response
            while(reading) {
                if(sendCount >maxSendCount){
                    reading = false;
                }

                if(send){
                    port.writeLine(serialCommand);
                    send = false;
                    time = System.currentTimeMillis();
                    sendCount++;
                }


                String[] readableLines = port.readLines();

                if(!(readableLines.length <= 0)){
                    //if not empty
                    String[][] input = SerialDecode.decode(readableLines);
                    for (String[] strings : input) {
                        if (strings.length == 3) {
                            if (Objects.equals(strings[0], "req") && Objects.equals(strings[1], "id")) {
                                portMap.put(strings[2], port);
                                reading= false;
                            }
                        }
                    }
                }

                if(System.currentTimeMillis() - time > 2000){
                    send = true;
                    sendCount++;
                }
            }
        }

        //wait til fully calibrated
        this.waitForCode("ard_XY", new String[]{"status", "calibrated"});

    }


    public Set<String> listKeys() {
        //list the keys to access the opened serial ports
        return this.portMap.keySet();
    }

    public boolean grid(int vakPosition){
        //go to warehouse location with corresponding number
        String SerialCommand = SerialDecode.encode(new String[]{"grid", String.valueOf(vakPosition)});
        portMap.get("ard_XY").writeLine(SerialCommand);
        this.gridPos = vakPosition;
        return this.waitForCode("ard_XY", new String[]{"grid", "success"});
    }

    public boolean packet(String action,int position){
        //do packet relate actions

        //filter commands
//        if(!Objects.equals(action, "place") && !Objects.equals(action, "pick") && !Objects.equals(action, "destroy")){
//            return false;
//        }
        if(position < 1 || position>3){
            return false;
        }

        if(action.equals("place")){
            String SerialCommand = "";


            //step 1 Y up
            SerialCommand = SerialDecode.encode(new String[]{"packet", "pick","2"});
            portMap.get("ard_XY").writeLine(SerialCommand);
            if(!this.waitForCode("ard_XY", new String[]{"packet", "pick", "2", "success"})){System.out.println("error step 2");return false;}


            //step 2 Z out
            SerialCommand = SerialDecode.encode(new String[]{"packet", "pick","1", String.valueOf(position)});
            portMap.get("ard_Z").writeLine(SerialCommand);
            if(!this.waitForCode("ard_Z", new String[]{"packet", "pick", "1", "success"})){System.out.println("error step 1");return false;}


            //step 3 Y down
            this.grid(gridPos);


            //step 3 Z in
            SerialCommand = SerialDecode.encode(new String[]{"packet", "pick","3"});
            portMap.get("ard_Z").writeLine(SerialCommand);
            if(!this.waitForCode("ard_Z", new String[]{"packet", "pick", "3", "success"})){System.out.println("error step 3");return false;}


            return true;
        }else if(action.equals("pick")){
            String SerialCommand = "";

            //step 1 Z out
            SerialCommand = SerialDecode.encode(new String[]{"packet", "pick","1", String.valueOf(position)});
            portMap.get("ard_Z").writeLine(SerialCommand);
            if(!this.waitForCode("ard_Z", new String[]{"packet", "pick", "1", "success"})){System.out.println("error step 1"); return false;}

            //step 2 Y up
            SerialCommand = SerialDecode.encode(new String[]{"packet", "pick","2"});
            portMap.get("ard_XY").writeLine(SerialCommand);
            if(!this.waitForCode("ard_XY", new String[]{"packet", "pick", "2", "success"})){System.out.println("error step 2");return false;}

            //step 3 Z in
            SerialCommand = SerialDecode.encode(new String[]{"packet", "pick","3"});
            portMap.get("ard_Z").writeLine(SerialCommand);
            if(!this.waitForCode("ard_Z", new String[]{"packet", "pick", "3", "success"})){System.out.println("error step 3");return false;}



            return true;
        }else{ return false;}


    }

    public boolean packet(String action){
        if(action.equals("dump")){
            String SerialCommand = "";

            //step 1 special dump
            SerialCommand = SerialDecode.encode(new String[]{"dump"});
            portMap.get("ard_XY").writeLine(SerialCommand);
            if(!this.waitForCode("ard_XY", new String[]{"dump","success"})){System.out.println("error step 1"); return false;}
            return true;
        }
        return false;
    }

    public String[] read(String portId){
        //read send data from port
        return portMap.get(portId).readLines();
    }

    public boolean hasCode(String[] lines,String[] searchCode){
        //check if specific code has been send
        String[][] codes = SerialDecode.decode(lines);
        for (String[] code : codes) {
            for (int c = 0; c < code.length; c++) {
                if (Arrays.equals(code, searchCode)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean waitForCode(String portId,String[] searchCode){
        //_____!deletes data from buffer____
        //wait til it has the code or timeout is reached

        boolean reading = true;
        long time = System.currentTimeMillis();
        while(true){
            String[] lines =  this.read(portId);
            if(this.hasCode(lines, searchCode)){
                return true;
            }

            if(System.currentTimeMillis() - time > timeout){
                return false;
            }

        }
    }


    public void close(){
        //closes all opened ports by this program
        for (Object key : this.listKeys().toArray()) {
            portMap.get(key).closePort();
        }

    }


    public void dance(){
        int dance[] = new int[]{1,2,3,4,5,3,8,13,18,23,3,1,6,11,16,21,11,15,5,10,15,20,25};


        for(int i=0;i<dance.length;i++){
            this.grid(dance[i]);
        }
    }

    public void delay(long delay){
        //to prevent serial overloading
        long time = System.currentTimeMillis();
        boolean wait = true;
        while(wait){
            if(System.currentTimeMillis() - time > delay){
                wait = false;
            }
        }
    }
}
