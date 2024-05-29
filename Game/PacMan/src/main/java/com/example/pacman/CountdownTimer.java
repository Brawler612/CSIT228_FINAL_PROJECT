package com.example.pacman;

public class CountdownTimer extends Thread {
    private Model model;

    public CountdownTimer(Model model) {
        this.model = model;
    }

    @Override
    public void run() {
        while (model.getCountdown() > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            model.decrementCountdown();
            model.repaint();
        }
        model.startTimer();
    }
}
