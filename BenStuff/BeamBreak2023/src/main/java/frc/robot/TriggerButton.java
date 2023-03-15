package frc.robot;


import java.util.function.BooleanSupplier;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class TriggerButton extends Trigger {
	public TriggerButton(BooleanSupplier bs) {
		super(bs);
	}
   }

    