package frc.robot.Tools;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.button.Trigger;

/** Add your docs here. */
public class TriggerButton extends Trigger{
    public TriggerButton(BooleanSupplier bs) {
		super(bs);
	}
}