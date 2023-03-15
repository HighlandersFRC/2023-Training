package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Defaults.MotorTestDefault;
import com.ctre.phoenixpro.configs.MotorOutputConfigs;
import com.ctre.phoenixpro.configs.TalonFXConfigurator;
import com.ctre.phoenixpro.controls.TorqueCurrentFOC;
import com.ctre.phoenixpro.controls.VoltageOut;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.InvertedValue;

public class MotorTest extends SubsystemBase {
  private final TalonFX grabberMotor = new TalonFX(15, "Canivore");
  private final TorqueCurrentFOC torqueRequest = new TorqueCurrentFOC(10, 0.2, 0, false);
  private final VoltageOut percentRequest = new VoltageOut(-6);
  
  private TalonFXConfigurator configurator = grabberMotor.getConfigurator();

  private MotorOutputConfigs motorOutputConfigs = new MotorOutputConfigs();

  public MotorTest() {
    setDefaultCommand(new MotorTestDefault(this));
  }

  public void init() {
    motorOutputConfigs.Inverted = InvertedValue.Clockwise_Positive;
    configurator.apply(motorOutputConfigs);
    setDefaultCommand(new MotorTestDefault(this));

  }

  public double getVelocity() {
    double velocity = grabberMotor.getVelocity().getValue();
    return velocity;
  }

  public void setGrabberMotorMaxPercent(double percent) {
    grabberMotor.setControl(this.percentRequest.withOutput(percent));
  }

  public void setIntakeTorqueOutput(double amps, double maxPercent){
    grabberMotor.setControl(this.torqueRequest.withOutput(amps).withMaxAbsDutyCycle(maxPercent));
  }

  @Override
  public void periodic() {
  }
}
