// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.RomiGyro;
import frc.robot.subsystems.RomiDrivetrain;
import java.lang.Math;


public class BalanceRomi extends CommandBase {

  private RomiDrivetrain m_RomiDrivetrain = new RomiDrivetrain();
  private RomiGyro m_RomiGyro = new RomiGyro();
  //determines how much the romi must turn in order for romi to be considered "tilted" 
  private int angleMeasure = 0;

  //boolean value to see if romi had been tiled when when trying to cross the  platform or not 
  private boolean alreadyTilted = false;


  /** Creates a new BalanceRomi. */
  public BalanceRomi(RomiDrivetrain romiDrivetrain, RomiGyro romiGryo, int angle){
    // Use addRequirements() here to declare subsystem dependencies.
    angleMeasure = angle;
    m_RomiDrivetrain = romiDrivetrain;
    m_RomiGyro = romiGryo;
    addRequirements(romiDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_RomiDrivetrain.arcadeDrive(0,0);
    m_RomiGyro.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_RomiDrivetrain.arcadeDrive(0.5, 0);
    //once the romi has tileted,  set alreadyilted to true
    if (m_RomiGyro.getAngleY() > Math.abs(angleMeasure)){
      alreadyTilted = true;
      //goes slower so it doesn't fall off
      m_RomiDrivetrain.arcadeDrive(0.2, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_RomiDrivetrain.arcadeDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //if the the romi has not been tilted, it will return false.
    //if the romi has been tilted, then becomes straight again , then it is on the charging station, and the program will end 
    if (m_RomiGyro.getAngleY() == 0) {
      return alreadyTilted;
    } else {
      return false;
    }
  }
}
