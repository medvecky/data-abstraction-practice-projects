package model;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private final String name;
    private final String number;
    private final ContactType type;
    private List<String> callLog;

    public Contact(String name, String number, ContactType type) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.callLog = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public ContactType getType() {
        return type;
    }

    public List<String> getCallLog() {
        return callLog;
    }



    //    REQUIRES: date in format MM/DD/YY
//    MODIFIES: this
//    EFFECTS: add consumed date to log
    public void call(String date) {
        callLog.add(date);
    }

    public String generateResponse() {
        String result = "Default";
        switch (type) {
            case WORK:
                result = "I'll get back to you at work.";
                break;
            case FAMILY:
                result = "See you at home.";
                break;
            case FRIEND:
                result = "Meet you at the cafe.";
        }
        return result;
    }
}
