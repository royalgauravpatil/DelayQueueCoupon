package Com.DelayQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;


public class CouponManager {
    private final DelayQueue<MedicineCoupon> couponQueue = new DelayQueue<>();

    public void addCoupons() throws ParseException {
        couponQueue.put(new MedicineCoupon("PARA10", "29/01/2019", TimeUnit.SECONDS));
        couponQueue.put(new MedicineCoupon("IBU20", "12/02/2020", TimeUnit.SECONDS));
        couponQueue.put(new MedicineCoupon("ASPIRIN30", "07/04/2002", TimeUnit.SECONDS));
        couponQueue.put(new MedicineCoupon("VITC40", "16/03/1998", TimeUnit.SECONDS));
        couponQueue.put(new MedicineCoupon("ZINC50", "19/07/2003", TimeUnit.SECONDS));
    }

    public void startCouponExpirationHandler() {
        new Thread(() -> {
            try {
                while (true) {
                    MedicineCoupon coupon = couponQueue.take(); 
                    System.out.println("Expired: " + coupon);
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException, ParseException {
        CouponManager manager = new CouponManager();
        manager.addCoupons();
        manager.startCouponExpirationHandler();
        Thread.sleep(30000);  
    }
}
