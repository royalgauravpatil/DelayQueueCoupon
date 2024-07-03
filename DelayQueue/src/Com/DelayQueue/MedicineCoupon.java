package Com.DelayQueue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MedicineCoupon implements Delayed {
    private final String code;
    private final long expirationTime;

    public MedicineCoupon(String code, String expirationDate, TimeUnit unit) throws ParseException {
        this.code = code;
        this.expirationTime = parseExpirationDate(expirationDate);
    }

    private long parseExpirationDate(String expirationDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(expirationDate);
        return date.getTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expirationTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        return Long.compare(this.expirationTime, ((MedicineCoupon) other).expirationTime);
    }

    public String getExpirationDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(expirationTime));
    }

    @Override
    public String toString() {
        return "Coupon{" + "code='" + code + "', expires at=" + getExpirationDate() + '}';
    }
}
