package Control;

import Model.Order;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class OrderTimerManager {
    private ScheduledExecutorService executor;

    public OrderTimerManager() {
        this.executor = Executors.newScheduledThreadPool(10);
    }

    private long calculateDelay(LocalDateTime deliveryTime) {
        LocalDateTime now = LocalDateTime.now();
        return java.time.Duration.between(now, deliveryTime).getSeconds();
    }

    public void showRemainingTime(Order order) {
        if (order.getTimeOfDelivery() == null) {
            System.out.println("i think the order is canceled"); //ما متأكد اذا هاي الحالة الي بدنا ياها بظن بيكفي نتأكد من البيمنت لنشوفو اذا مكنسل بس لو صار غلط وفوتناه فمو مشكلة

            return;
        }
        // هاد البني ادم رح يخزن  الtask الي عمبيتم تشغيلها
        final ScheduledFuture<?>[] taskHolder = new ScheduledFuture<?>[1];

        taskHolder[0] = executor.scheduleAtFixedRate(() -> {
            long remainingTime = calculateDelay(order.getTimeOfDelivery());

            if (remainingTime <= 0) {
                order.setState(Order.Status.DELIVERED);

                System.out.println("Order delivered!");

                taskHolder[0].cancel(false);
            } else {
                order.setState(Order.Status.PREPARED);
                System.out.println("Time remaining for Order: " + remainingTime + " seconds.");
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

}
