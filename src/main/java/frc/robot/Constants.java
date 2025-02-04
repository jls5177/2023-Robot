package frc.robot;

import com.pathplanner.lib.PathConstraints;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.apriltag.AprilTag;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.PIDGains;
import frc.lib.config.SwerveModuleConstants;

import java.util.ArrayList;

public final class Constants {

  public static final class OIConstants {
    public static final int kDriverController = 0;
    public static final int kOperatorController = 1;
    public static final double kDriveDeadband = 0.05;
    public static final double kArmManualDeadband = 0.05;
    public static final double kArmManualScale = 0.5;
  }

  public static final class Swerve {
    public static final double stickDeadband = 0.1;

    public static final int pigeonID = 6;
    public static final boolean invertGyro = false; // Always ensure Gyro is CCW+ CW-

    /* Drivetrain Constants */
    public static final double trackWidth = Units.inchesToMeters(17.25);
    public static final double wheelBase = Units.inchesToMeters(31.7);
    public static final double wheelDiameter = Units.inchesToMeters(3.95);
    public static final double wheelCircumference = wheelDiameter * Math.PI;

    public static final double openLoopRamp = 0.25;
    public static final double closedLoopRamp = 0.0;

    public static final double driveGearRatio = (6.75 / 1.0); // 6.12:1
    public static final double angleGearRatio = (( 150.0 / 7.0) / 1.0); // 150/7:0

    public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
        new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
        new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
        new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
        new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

    /* Swerve Compensation */
    public static final double voltageComp = 12.0;

    /* Swerve Current Limiting */
    public static final int angleContinuousCurrentLimit = 20;
    public static final int driveContinuousCurrentLimit = 80;

    public static final double pitchSetPoint = 0.0;

    public static final double drivePitchKP = 0.04;
    public static final double drivePitchKI = 0.00005;
    public static final double drivePitchKD = 0.000000000000001;
    public static final double drivePitchKFF = 0.000000000000001;

    /* Angle Motor PID Values */
    public static final double angleKP = 0.01;
    public static final double angleKI = 0.0;
    public static final double angleKD = 0.005;
    public static final double angleKFF = 0.0;

    /* Drive Motor PID Values */
    public static final double driveKP = 0.0;
    public static final double driveKI = 0.0;
    public static final double driveKD = 0.0;
    public static final double driveKFF = 0.0;

    /* Drive Motor Characterization Values */
    public static final double driveKS = 0.11979;
    public static final double driveKV = 2.3823;
    public static final double driveKA = 0.30034;

    /* Drive Motor Conversion Factors */
    public static final double driveConversionPositionFactor = (wheelDiameter * Math.PI) / driveGearRatio;
    public static final double driveConversionVelocityFactor = driveConversionPositionFactor / 60.0;
    public static final double angleConversionFactor = 360.0 / angleGearRatio;

    /* Swerve Profiling Values */
    public static final double maxSpeed = 4.4196; // meters per second
    // public static final double maxAngularVelocity = 3;
    public static final double maxAngularVelocity =  4.4196;
    public static final double mediumSpeed = 2.8; // meters per second
    public static final double mediumAngularVelocity = 2.8;
    public static final double slowSpeed = 1;
    public static final double slowAngularVelocity = 1;


//Tony's hypothesis on the angle issue with fast mode option
//1)the pid function might take into account the max speed and it might be doing weird thigs if we are driving at a speed that is not max speed
//2)we should just use the setSpeed() method in swerve modules.java insteadof having custom porofiling methods, it might fix our problem.


    /* Neutral Modes */
    public static final IdleMode angleNeutralMode = IdleMode.kBrake;
    public static final IdleMode driveNeutralMode = IdleMode.kBrake;

    /* Motor Inverts */
    public static final boolean driveInvert = false;
    public static final boolean angleInvert = true;

    /* Angle Encoder Invert */
    public static final boolean canCoderInvert = false;

    /* Module Specific Constants */
    /* Front Left Module - Module 0 */
    public static final class Mod0 {
      public static final int driveMotorID = 11;
      public static final int angleMotorID = 12;
      public static final int canCoderID = 1;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(1.413);
      public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID,
          canCoderID, angleOffset);
    }

    /* Front Right Module - Module 1 */
    public static final class Mod1 {
      public static final int driveMotorID = 9;
      public static final int angleMotorID = 10;
      public static final int canCoderID = 2;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(141.328);
      public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID,
          canCoderID, angleOffset);
    }

    /* Back Left Module - Module 2 */
    public static final class Mod2 {
      public static final int driveMotorID = 1;
      public static final int angleMotorID = 2;
      public static final int canCoderID = 3;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(142.466);
      public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID,
          canCoderID, angleOffset);
    }

    /* Back Right Module - Module 3 */
    public static final class Mod3 {
      public static final int driveMotorID = 3;
      public static final int angleMotorID = 4;
      public static final int canCoderID = 4;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(-126.211);
      public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID,
          canCoderID, angleOffset);
    }
  }

  public static final class AutoConstants {
    public static final PathConstraints constraints = new PathConstraints(1, 1);

    public static final double kMaxSpeedMetersPerSecond = 1;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 1;
  }

  public static final class PhotonVision{
    public static final String photonVisionName = "Arducam_OV9281_USB_Camera";
    public static final Transform3d robotToCam =
    new Transform3d(
            new Translation3d(Units.inchesToMeters(11.4), 0.0, Units.inchesToMeters(6.4)),
            new Rotation3d(
                    0, 0,
                    0));
}

  public static final class AprilTags {
    public static final AprilTag tag1 = new AprilTag(1, FieldConstants.aprilTags.get(1));
    public static final AprilTag tag2 = new AprilTag(2, FieldConstants.aprilTags.get(2));
    public static final AprilTag tag3 = new AprilTag(3, FieldConstants.aprilTags.get(3));
    public static final AprilTag tag4 = new AprilTag(4, FieldConstants.aprilTags.get(4));
    public static final AprilTag tag5 = new AprilTag(5, FieldConstants.aprilTags.get(5));
    public static final AprilTag tag6 = new AprilTag(6, FieldConstants.aprilTags.get(6));
    public static final AprilTag tag7 = new AprilTag(7, FieldConstants.aprilTags.get(7));
    public static final AprilTag tag8 = new AprilTag(8, FieldConstants.aprilTags.get(8));
    public static final ArrayList<AprilTag> aprilTagList = new ArrayList<>();

    static {
      aprilTagList.add(tag1);
      aprilTagList.add(tag2);
      aprilTagList.add(tag3);
      aprilTagList.add(tag4);
      aprilTagList.add(tag5);
      aprilTagList.add(tag6);
      aprilTagList.add(tag7);
      aprilTagList.add(tag8);
    }
  }

  public static final class Arm {
    public static final int kArmCanId = 13;
    public static final boolean kArmInverted = false;
    public static final int kCurrentLimit = 40;

    // public static final double kSoftLimitReverse = 0.0;
    // public static final double kSoftLimitForward = 4.6;
    public static final double kSoftLimitReverse = -7;
    public static final double kSoftLimitForward = 8.6;

    public static final double kArmGearRatio = 1.0 / (48.0 * 4.0); 
    public static final double kPositionFactor = kArmGearRatio * 2.0 * Math.PI; //multiply SM value by this number and get arm position in radians
    public static final double kVelocityFactor = kArmGearRatio * 2.0 * Math.PI / 60.0;
    public static final double kArmFreeSpeed = 5676.0 * kVelocityFactor;
    public static final double kArmZeroCosineOffset = - Math.PI / 6; //radians to add to converted arm position to get real-world arm position (starts at ~30deg angle)
    public static final ArmFeedforward kArmFeedforward = new ArmFeedforward(0.0, 0.4, 12.0/kArmFreeSpeed, 0.0);
    public static final PIDGains kArmPositionGains = new PIDGains(0.6, 0.0, 0.0);
    public static final TrapezoidProfile.Constraints kArmMotionConstraint = new TrapezoidProfile.Constraints(2.0, 2.0);
    public static final TrapezoidProfile.Constraints kSlowArmMotionConstraint = new TrapezoidProfile.Constraints(1.0, 1.0);



    public static final double kHomePosition = 0.0;
    // public static final double kScoringPosition = 3.05;
    public static final double kScoringPosition = -3.05;
    public static final double kIntakePosition = 4.64;
    public static final double kFeederPosition = 2.95;
    public static final double l2ConeScoringPostition = 3.38321;
    public static final double l2CubeScoringPostition = 3.45332;
    public static final double ItemHoldPosition = 4.44819;
    public static final double DoubleSubstatiionPositiion = 2.95;





}

public static final class Gripper {
    public static final int kGripperCanId = 14;
    public static final double kSoftLimitReverse = -40.0;
    public static final double kSoftLimitForward = 5.0;
    public static final double kClosePosition = 0;
    public static final double kCloseConePosition = 0;
    public static final double kCloseCubePosition = -8.0;
    public static final double kOpenPosition = -50.0;
    public static final double kSafePosition = -29.0;
    public static final int kCurrentLimit = 10;
    public static final PIDGains kPositionPIDGains = new PIDGains(0.2, 0.0, 0.0);
}

public static final class Autonomous {
	public static final PathConstraints constraints = new PathConstraints(1, 1);

	public static final double kMaxSpeedMetersPerSecond = .5;
	public static final double kMaxAccelerationMetersPerSecondSquared = 1;
	public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
	public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

	public static final double kPXController = 1;
	public static final double kPYController = 1;
	public static final double kPThetaController = 0.8;// change this if ossolation, less if ossolating, more if not working
}


public enum SEGMENT { // Numbers in order of segment from left to right (driver station POV)
  // TODO: the "level" is the height of the arm
	CONE_1(0), CONE_2(31.8), CONE_3(35.2), CONE_4(-1), CONE_5(-1), CONE_6(-1),
	CUBE_1(0), CUBE_2(20.6), CUBE_3(35.2), HUMANPLAYER(-1), 
	DOUBLE_SUBSTATIOIN(-1), BEFORE_DOUBLE_SUBSTATIOIN(-1);

	// intake Ground Cube: 0
	// intake Cone Upright: 12
	// intake Cone Tipped: 0

	// intake Cone Single HP: 8.9
	// intake Cube Single HP: 11.8

	private double level;

	private SEGMENT(double level) {
		 this.level = level;
	}

	public static SEGMENT getSegment(int level, boolean cone) {
		 if (cone) {
			  switch (level) {
					case 1:
						 return CONE_1;
					case 2:
						 return CONE_2;
					case 3:
						 return CONE_3;
			  }
		 } else {
			  switch (level) {
					case 1:
						 return CUBE_1;
					case 2:
						 return CUBE_2;
					case 3:
						 return CUBE_3;
			  }
		 }
		 return null;
	}

	public double getValue() {
		 return level;
	}

}
}