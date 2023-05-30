package Serial;

import java.util.ArrayList;

public class SerialDecode {
    private static final char prefix = '_';
    private static final char suffix = ';';
    private static final char nameDivider = ':';
    private static final char paramDivider = ',';

    public static String encode(String[] output){
        StringBuilder s = new StringBuilder();
        s.append(prefix);
        for (int i = 0;i<output.length;i++) {
            s.append(output[i]);

            if(i<output.length-1) {
                if (i < 1) {
                    s.append(nameDivider);
                } else {
                    s.append(paramDivider);
                }
            }


        }
        s.append(suffix);
        return s.toString();
    }

    public static String[][] decode(String[] input){
        ArrayList<ArrayList<String>> actions = new ArrayList<ArrayList<String>>();
        for (String s : input) {
            if (s.charAt(0) == prefix && s.charAt(s.length() - 1) == suffix) {// _  ;
                ArrayList<String> action = new ArrayList<String>();

                int beginPointer = 1;
                int endPointer = 0;

                for (int c = 1; c < s.length() - 1; c++) {
                    endPointer = c;
                    boolean slice = false;
                    boolean sliceInclude = false;

                    if (s.charAt(c) == nameDivider) {
                        slice = true;
                    } else if (s.charAt(c) == paramDivider) {
                        slice = true;
                    } else if (c >= s.length() - 2) {
                        slice = true;
                        sliceInclude = true;
                    }

                    String sliced = "";
                    if (slice) {

                        if (sliceInclude) {
                            action.add(s.substring(beginPointer, (endPointer + 1)));
                        } else {
                            action.add(s.substring(beginPointer, endPointer));
                        }
                        beginPointer = endPointer + 1;
                    }

                }
                actions.add(action);
            }

        }

        //make array from arraylist
        String[][] actionsArray = new String[actions.size()][];
        for(int i=0;i<actions.size();i++) {
            String[] actionArray = new String[actions.get(i).size()];
            for (int i2 = 0; i2 < actions.get(i).size(); i2++) {
                actionArray[i2] = actions.get(i).get(i2);
            }
            actionsArray[i] = actionArray;
        }


        return actionsArray;
    }

}
