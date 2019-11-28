package org.javaschool.lab5;

public enum MessageType {
    INFO("INFO")
    , WARNING("WARNING")
    , CRITICAL ("CRITICAL")
    , FATAL ("FATAL")
    ;

    private MessageType(String printableName) {

    }

    private String printableName;

    @Override
    public String toString() {
        if(this == INFO) {
            return "";
        } else {
            return printableName;
        }
        //return "MessageType{" + printableName + "}";
    }
}
