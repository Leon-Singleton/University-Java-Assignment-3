import ShefRobot.*;
/**
* @author Leon Singleton, Jaafer Hussain and Ben Eggelton
* All members contributed equally to writing of the code and its containing algorithms
*/
public class Assignment3 {
	
	/** 
	*The robot will move forward until it reaches a black line
	*@param myRobot The robot object created from the Shefrobot package
	*@param largeMotor One of the two motor objects of the robot
	*@param mediumMotor One of the two motor objects of the robot
	*/
	public static void findBlackLine(Robot myRobot, Motor largeMotor, Motor mediumMotor) {
		//The set speed method is used to set the movement speed of the motor object
		largeMotor.setSpeed(250);
		mediumMotor.setSpeed(250);
		//The following two lines of code make the two motors move forward at the set speed
		largeMotor.forward();
		mediumMotor.forward();
	}
	
	/** 
	*The robot Will move in a turning motion until it finds the black line again from being on an area of white
	*@param myRobot The robot object created from the Shefrobot package
	*@param largeMotor One of the two motor objects of the robot
	*@param mediumMotor One of the two motor objects of the robot
	*/
	public static void moveLeftOnLine(Robot myRobot, Motor largeMotor, Motor mediumMotor) {
		largeMotor.setSpeed(220);
		mediumMotor.setSpeed(0);
		largeMotor.forward();
		mediumMotor.forward();
		//The sleep method is used to instruct the robot for how long to carry out an action for before moving to the next action
		myRobot.sleep(5);
	}
	
	/** 
	*The robot Will move in a turning motion until it finds an area of white from being on the black line
	*@param myRobot The robot object created from the Shefrobot package
	*@param largeMotor One of the two motor objects of the robot
	*@param mediumMotor One of the two motor objects of the robot
	*/
	public static void moveRightOnLine(Robot myRobot, Motor largeMotor, Motor mediumMotor) {
		largeMotor.setSpeed(0);
		mediumMotor.setSpeed(220);
		largeMotor.forward();
		mediumMotor.forward();
		myRobot.sleep(5);
	}
	
	/** 
	*The robot Will move in a turning motion until it finds a non-white area
	*@param myRobot The robot object created from the Shefrobot package
	*@param largeMotor One of the two motor objects of the robot
	*@param mediumMotor One of the two motor objects of the robot
	*/
	public static void moveRightOnColour(Robot myRobot, Motor largeMotor, Motor mediumMotor) {
		largeMotor.setSpeed(80);
		mediumMotor.setSpeed(0);
		largeMotor.forward();
		mediumMotor.forward();
		myRobot.sleep(5);
	}
	
	/** 
	*The robot Will move in a turning motion until it finds a white area
	*@param myRobot The robot object created from the Shefrobot package
	*@param largeMotor One of the two motor objects of the robot
	*@param mediumMotor One of the two motor objects of the robot
	*/
	public static void moveLeftOnColour(Robot myRobot, Motor largeMotor, Motor mediumMotor) {
		largeMotor.setSpeed(0);
		mediumMotor.setSpeed(80);
		largeMotor.forward();
		mediumMotor.forward();
		myRobot.sleep(5);
	}
	
	/** 
	*This method is used to make the robot sing upon finding a matching colour that it has previously seen before
	*@param myRobot The robot object created from the Shefrobot package
	*@param mySpeaker The speaker object of the robot so that it can output sound
	*/
	public static void sing(Robot myRobot, Speaker mySpeaker) {
		//A constant is used to set each tone to play for an equal length of time
		final int TONE_TIME = 300;
		//Uses the setvolume method of the shefRobot package to set the volume of the myspeaker object
		mySpeaker.setVolume(100);
		//The robot repeatedly sings the same tune three times
		for (int i =0; i<3; i++) {
			mySpeaker.playTone(130, TONE_TIME);
			mySpeaker.playTone(2050, TONE_TIME);
			mySpeaker.playTone(130, TONE_TIME);
			mySpeaker.playTone(250, TONE_TIME);
			mySpeaker.playTone(1170, TONE_TIME);
			mySpeaker.playTone(7000, TONE_TIME);
		}
	}
	
	/** 
	*This method is used to make the robot dance once it returns to a colour it has met before
	*@param myRobot The robot object created from the Shefrobot package
	*@param largeMotor One of the two motor objects of the robot
	*@param mediumMotor One of the two motor objects of the robot
	*/
	public static void dance(Robot myRobot, Motor largeMotor, Motor mediumMotor) {
		//The stop method is used to stop the motor object of the robot from moving
		largeMotor.stop();
		mediumMotor.setSpeed(1000);
		mediumMotor.forward();
		myRobot.sleep(3000);
		mediumMotor.stop();
		largeMotor.setSpeed(1000);
		largeMotor.forward();
		myRobot.sleep(3000);
		largeMotor.stop();
	}
	
	/** 
	*This method is used so that the robot traverses the black line, this is achived by following the edge of the black line,
	*so the robot will re-adjust itself when it detects either black or white so that it can remain on the edge of a black line.
	*@param myRobot The robot object created from the Shefrobot package
	*@param largeMotor One of the two motor objects of the robot
	*@param mediumMotor One of the two motor objects of the robot
	*@param mySensor An object of the robot that is used to detect colour 
	*/
	public static void traverseBlackLine(Robot myRobot, Motor largeMotor, Motor mediumMotor, ColorSensor mySensor) {
		//if the robot detects black then it re-adjusts its position so that it follows the edge of the black line
		if (mySensor.getColor() == ColorSensor.Color.BLACK) {
			moveLeftOnLine(myRobot, largeMotor, mediumMotor);
		}
		//if the robot detects white then it re-adjusts its position so that it follows the edge of the black line
		if (mySensor.getColor() == ColorSensor.Color.WHITE) {
			moveRightOnLine(myRobot, largeMotor, mediumMotor);
		}
	}
	/** 
	*The robot navigates around the circumference of a circle in an alternate directon each time it reaches a new colour
	*@param myRobot The robot object created from the Shefrobot package
	*@param largeMotor One of the two motor objects of the robot
	*@param mediumMotor One of the two motor objects of the robot
	*@param mySensor An object of the robot that is used to detect colour 
	*@param count A count value is passed to the method and is incremented each time the robot detects a colour and its value is manipulated 
	*to determine which direction of the circumference of the circle to travel (clockwise or anticlockwise) to create randomness
	*/
	public static void traverseColour(Robot myRobot, Motor largeMotor, Motor mediumMotor, ColorSensor mySensor, int count) {
		//This while loop will repeat itself until the robot returns to a black colour again
		while (mySensor.getColor() != ColorSensor.Color.BLACK) {
			//robot uses modulus calculation to alternate the turning direction whilst on a colour so that it can follow the edge of the colour
			if (count % 2 == 0) {
				//The if statement is true if the sesnsor detects a colour and turns to find the edge of the colour again
				if ((mySensor.getColor() != ColorSensor.Color.BLACK) &&
				(mySensor.getColor() != ColorSensor.Color.WHITE)){
					moveRightOnColour(myRobot, largeMotor, mediumMotor);
				}
				//The if statement is true if the sesnsor detects a white area and turns to find the edge of the colour again
				if (mySensor.getColor() == ColorSensor.Color.WHITE) {
					moveLeftOnColour(myRobot, largeMotor, mediumMotor);
				}
			}
			else {

				if ((mySensor.getColor() != ColorSensor.Color.BLACK) &&
				(mySensor.getColor() != ColorSensor.Color.WHITE )){
					moveLeftOnColour(myRobot, largeMotor, mediumMotor);
				}

				if (mySensor.getColor() == ColorSensor.Color.WHITE) {
					moveRightOnColour(myRobot, largeMotor, mediumMotor);
				}
			}
		}
	}
	
	/**
	*This is the main program code for the robot, it manipulates other methods to perform the task of finding a matching colour the robot has
	*recognized previously and then returns to that colour and dances
	*@param args An array of string arguments that can be passed in from the command line 
	*/
	public static void main(String[] args) {
		//The robot object is created
		Robot myRobot = new Robot();
		//Assigns object identifiers for each of the robot's features we require to use
		//Speaker object is needed so the robot can "sing"
		Speaker mySpeaker = myRobot.getSpeaker();
		//motor objects are needed so the robot can move
		Motor largeMotor = myRobot.getLargeMotor(Motor.Port.A);
		Motor mediumMotor = myRobot.getMediumMotor(Motor.Port.B);
		//Coloursensor object is needed so the robot can detect colours
		ColorSensor mySensor = myRobot.getColorSensor(Sensor.Port.S1);
		
		//Creates an array that stores the colours the robot detects
		ColorSensor.Color[] stored = new ColorSensor.Color[5];
		//This count is used so that the robot can create alter its turning direction each time it reaches a colour
		int count = 0;
		//boolean value to check whether robot has finished its journey back to original colour spotted
		boolean backTrack = false;
		//boolean value to check if a colour found is already in the stored array
		boolean inArray = false;
		//boolean value to check if the program has reached a finished state
		boolean finished = false;
		//boolen which checks if robot has reached a different instance of the colour it is currently on
		boolean colourDetectedAgain = false; 
		
		//Robot continues to move forward until it reaches a black line
		while ((mySensor.getColor() != ColorSensor.Color.BLACK)) {
			findBlackLine(myRobot, largeMotor, mediumMotor);
		}
		
		//robot loops through this while loop as long as the finished state has not been reached
		while (finished != true) {

			inArray = false;
			
			traverseBlackLine(myRobot, largeMotor, mediumMotor, mySensor);
			
			//robot detects a colour and checks if it is already in array
			if  ((mySensor.getColor() != ColorSensor.Color.BLACK) &&
			(mySensor.getColor() != ColorSensor.Color.WHITE) &&
			(mySensor.getColor() != ColorSensor.Color.BROWN) &&
			(mySensor.getColor() != ColorSensor.Color.BLUE)) {
				
				//The robot stops temporarily in order to correctly store a colour in the array
          		largeMotor.stop();
          		mediumMotor.stop();
          		
          		//This for loop checks the contents of the array to see if the colour encountered is a new 
          		//colour or a colour it has encountered before
          		for (int i = 0; i<5; i++) {
          			if (stored[i] == mySensor.getColor()) {
          				//Changes the state of the boolean inArray to true if the colour has been seen before
          				inArray = true; 
          			}
          		}
                		
          		//If the robot detects a colour and it has not been stored in the array before it will store it in the array
          		if ((mySensor.getColor() != ColorSensor.Color.BROWN) &&
          		(mySensor.getColor() != ColorSensor.Color.BLUE) &&
          		(mySensor.getColor() != ColorSensor.Color.BLACK) &&
          		(mySensor.getColor() != ColorSensor.Color.WHITE) &&
          		(inArray == false)) {

          			stored[count] = mySensor.getColor();
          			//The count variable is increased  so that the robot can change direction on the next circle it encounters
          			//in order to stop the robot looping back on itself
          			count++;
          			//Calls an instance of the traverseColour method
          			traverseColour(myRobot, largeMotor, mediumMotor, mySensor, count);
				}
			}
			
			//If a colour has been spotted twice then the inArray boolean value is true and the following code is initiated
			if (inArray == true) {
				sing(myRobot, mySpeaker);
				//creates a variable for the repeated colour the robot has detected so that the robot knows which colour to look for 
				ColorSensor.Color secondColour = mySensor.getColor();
				
				//Robot traverses grid until it reaches the original colour where the backTrack boolean will become true
				while (backTrack == false) {
					
					traverseBlackLine(myRobot, largeMotor, mediumMotor, mySensor);
					
					
					//The colourDetectedAgain boolean is used to make sure the robot does not think it has found the original colour 
					//whilst it is sensing the colour it is currently on and will ensure that it finds another instance of the colour
					//it is currently on
					if (mySensor.getColor() == secondColour && colourDetectedAgain == true) {
						//When robot detects original colour it stops and dances
						backTrack = true;
						dance(myRobot, largeMotor, mediumMotor);
					}

					if ((mySensor.getColor() != ColorSensor.Color.BLACK) &&
						(mySensor.getColor() != ColorSensor.Color.WHITE) &&
						(mySensor.getColor() != ColorSensor.Color.BROWN) &&
						(mySensor.getColor() != ColorSensor.Color.BLUE &&
						 backTrack == false)) {
						 count++;
						
						traverseColour(myRobot, largeMotor, mediumMotor, mySensor, count);
						//This becomes tue as soon as the robot detects a different colour on its journey back to the matching colour
						colourDetectedAgain = true; 
					}
				}
				//The boolean finished state is set to true so that the while loop finishes and in turn the program ends
				finished = true;
			}
		}
	}
}
