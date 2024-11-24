package view;

import constants.Constants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Break session view page.
 */
public class BreakSessionView {
    private final String viewName = "break session";

    private final JLabel timerLabel;
    private Timer swingTimer;
    private long remainingTime = Constants.DEFAULT_BREAK_MINUTES;

    public BreakSessionView(JLabel timerLabel) {
        this.timerLabel = timerLabel;
    }
}
