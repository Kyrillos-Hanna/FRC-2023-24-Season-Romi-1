// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

public class MoveForward extends CommandBase {
     private RomiDrivetrain m_RomiDrivetrain;
     private int distance;
     
  /** Creates a new MoveForward. */
  public MoveForward(RomiDrivetrain romiDrivetrain, int inches) {
    m_RomiDrivetrain = romiDrivetrain;
    distance = inches;
    // Use adRequirements() here to declare subsystem dependencies.
    addRequirements(m_RomiDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_RomiDrivetrain.resetEncoders();
    m_RomiDrivetrain.arcadeDrive(0,0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_RomiDrivetrain.arcadeDrive(0.5,0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_RomiDrivetrain.arcadeDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_RomiDrivetrain.getAverageDistanceInch() >= distance;
  }
}
