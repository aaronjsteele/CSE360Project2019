package todolist;

import java.text.SimpleDateFormat;

public class CustomDate extends java.sql.Date {

    public CustomDate(long date) {
        super(date);
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("MM/dd/yyyy").format(this);
    }
}
