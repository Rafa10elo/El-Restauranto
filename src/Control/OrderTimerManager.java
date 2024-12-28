package Control;

import Model.Order;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format(java.util.Locale.ENGLISH,"%02d : %02d : %02d", hours, minutes, secs);
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm:ss");
        return dateTime.format(formatter);
    }

    public void showRemainingTime(Order order, JLabel timeLabel, JLabel stateLabel) {
        if (order.getTimeOfDelivery() == null) {
            timeLabel.setText("00:00:00" + "          ");
            stateLabel.setText("Canceled");
            return;
        }

        final ScheduledFuture<?>[] taskHolder = new ScheduledFuture<?>[1];

        taskHolder[0] = executor.scheduleAtFixedRate(() -> {
            long remainingTime = calculateDelay(order.getTimeOfDelivery());
            if (remainingTime < 0) {
                SwingUtilities.invokeLater(() -> {
                    order.setState(Order.Status.DELIVERED);
//                    stateLabel.setText("Delivered at " + formatDateTime(order.getTimeOfDelivery()));
                    stateLabel.setText("Delivered");
                    String deliveredTime = formatTime(order.getTimeOfDelivery().toLocalTime().toSecondOfDay());
                    timeLabel.setText(deliveredTime + "          ");
                });
                taskHolder[0].cancel(false);
            } else {

                    order.setState(Order.Status.PREPARING);
                    stateLabel.setText("Preparing");
                    String styledTime = formatTime(remainingTime);
                    timeLabel.setText(" " +styledTime+ "       ");

            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
