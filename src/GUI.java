/*Name: Victor Nikhil Antony
*NetID: vantony
*Assignment: Project #3
*MW 1400-1515
*TA Name: Michael Henry
*I did not collaborate with anyone on this assignment
 */


//Imports required Java packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

//Creates that class GUI that extends JFrame and implements ActionListener, ChangeListener, ItemListener
public class GUI extends JFrame implements ActionListener, ChangeListener, ItemListener {
	
	//Declares the objects needed to set up the GUI user interface
	protected Canvas canvas;
	protected JTextField angleField;
	protected JTextField speedField;
	protected JTextField timerField;
	protected JButton launchButton;
	protected JButton clearButton;
	protected JButton addButton;
	protected JButton resetButton;
	protected JLabel speedLabel;
	protected JLabel angleLabel;
	protected JLabel timerLabel;
	protected JLabel countLabel;
	protected JLabel colourLabel;
	protected JLabel explosionLabel;
	protected JLabel trajectoryLabel;
	protected JLabel length; 
	JPanel topPanel;
	JPanel sidetopPanel;
	JPanel superPanel;
	protected JComboBox colourCombo;
	protected JComboBox explosionCombo;
	protected JComboBox trajectoryCombo;
	protected String [] colour = {"Black", "Blue", "Red","Yellow","Cyan","Dark Gray","Gray","Light Gray","Green","Magenta","Orange", "Pink"};
	protected String [] explosion = {"Standard", "Splinter", "360", "Light Up","Colored", "Squared"};
	protected String [] trajectory = {"Normal", "Split", "Bunker Buster"};
	JPanel sidePanel;
	
	//Declares variables to be used in calculations and calling on methods
	int angle;
	int speed;
	int time;
	int route;
	int color;
	int explosive;
	double angle_rad;
	int x_final;
	int y_final;
	int xf, yf;
	int height, width;
	int x_initial, y_initial;
	int split_x, split_y;
	int local_angle, local_speed, local_time,local_route, local_explosive;
	Color local_color;
	int count = 0;
	
	//Creates Array Lists to allow for registration of mutliple projectile and their rendering
	ArrayList<Integer> angles = new ArrayList<Integer>();
	ArrayList<Integer> speeds = new ArrayList<Integer>();
	ArrayList<Integer> times = new ArrayList<Integer>();
	ArrayList<Integer> routes = new ArrayList<Integer>();
	ArrayList<Integer> colors = new ArrayList<Integer>();
	ArrayList<Integer> explosives = new ArrayList<Integer>();
	ArrayList<Color> usercolors = new ArrayList<Color>();

	
	
	
	//The constructor for GUI 
	public GUI() {
		//Call on the super method
		super();
		
		//Sets the title, size, closeoperation and border layout as layout
		setTitle("Graphical Fireworks Simulator");
		setSize(700, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//Creates new canvas while setting its characteristics and white as background
		canvas = new Canvas();
		canvas.setVisible(true);
		canvas.setEnabled(true);
		canvas.setFocusable(true);
		canvas.setBackground(Color.WHITE);
		canvas.validate();
		canvas.setPreferredSize(new Dimension(300,300));
		
		//Adds canvas to the center section of the frame
		add(canvas, BorderLayout.CENTER);
		
		//Gets the width and height of the frame and sets it to width & height;
		int width = getWidth();
		int height = getHeight();
	
		//Creates new JPanel and sets layout to flowlayout
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		//Sets size such that top panel spans the width and a certain ratio of the height of the frame
		topPanel.setPreferredSize(new Dimension((this.getWidth()), (this.getHeight()/8 + 2)));
		
		//Creates new JTextField for user to input angles into and adds actionlistener to pick up inputs
		angleField = new JTextField();
		angleField.addActionListener(new ActionListener(){
			@Override
			//action performed method that is triggered when action listener senses action
			public void actionPerformed(ActionEvent e_angle) {
				//inserts 0 to ArrayList angles at index count.
				angles.add(count, 0);
				
				//sets angle to value in textfield using parseint method
				angle = Integer.parseInt(angleField.getText());
				
				//inserts value of angle to ArrayList angles at index count.
				angles.add(count, angle);
			}
		});
		
		//Creates new JTextField for user to input speeds into and adds actionlistener to pick up inputs
		speedField = new JTextField();
		speedField.addActionListener(new ActionListener(){
			@Override
			//action performed method that is triggered when action listener senses action
			public void actionPerformed(ActionEvent e_speed) {
				//inserts 0 to ArrayList speeds at index count.
				speeds.add(count, 0);
				
				//sets angle to value in textfield using parseint method
				speed = Integer.parseInt(speedField.getText());
				
				//inserts value of angle to ArrayList speeds at index count.
				speeds.add(count, speed);
			}
		});
		
		//Creates new JTextField for user to input times into and adds actionlistener to pick up inputs
		timerField = new JTextField();
		timerField.addActionListener(new ActionListener(){
			@Override
			//action performed method that is triggered when action listener senses action
			public void actionPerformed(ActionEvent e_timer) {
				//inserts 0 to ArrayList times at index count.
				times.add(count, 0);
				
				//sets angle to value in textfield using parseint method
				time = Integer.parseInt(timerField.getText());
				
				//inserts value of angle to ArrayList times at index count.
				times.add(count, time);
			}
		});
		
		//Creates and sets titles for labels for the TextFields and ComboBoxes
		//To be used in the UI to make the information more comprehensible for user
		speedLabel = new JLabel("Enter Speed " + (count+1) + ":");
		angleLabel = new JLabel("Enter Angle: " + (count+1) + ":");
		timerLabel = new JLabel("Enter Time " + (count+1) + ":");
		trajectoryLabel = new JLabel("Choose Trajectory "+ (count+1) + ":");
		colourLabel = new JLabel("Choose Colour "+ (count+1) + ":");
		explosionLabel = new JLabel("Choose Explosion "+ (count+1) + ":");
		
		//Sets preferred sizes for TextFields to enhance user experience
		angleField.setPreferredSize(new Dimension(50, 20));
		speedField.setPreferredSize(new Dimension(50, 20));
		timerField.setPreferredSize(new Dimension(50, 20));
		
		//Creates new JComboBox for user to choose color from and adds actionlistener to pick up choices
		JComboBox colourCombo = new JComboBox(colour);
		colourCombo.addActionListener(new ActionListener(){
			@Override
			//action performed method that is triggered when action listener senses action
			public void actionPerformed(ActionEvent e_color) {
				//sets BLACK to ArrayList usercolors at index count.
				usercolors.add(count, Color.BLACK);
				
				//sets color to index of selected value
				color = colourCombo.getSelectedIndex();
				
				//sets users color choice by running index through the getColor method to ArrayList usercolors at index count.
				usercolors.add(count, getColor(color));
			}
		});
		
		//Creates new JComboBox for user to choose explosion from and adds actionlistener to pick up choices
		JComboBox explosionCombo = new JComboBox(explosion);
		explosionCombo.addActionListener(new ActionListener(){
			@Override
			//action performed method that is triggered when action listener senses action
			public void actionPerformed(ActionEvent e_explosion) {
				//sets 0 to ArrayList explosives at index count.
				explosives.add(count, 0);
				
				//sets explosive to index of selected value
				explosive = explosionCombo.getSelectedIndex();
				
				//sets value of explosive to ArrayList explosives at index count.
				explosives.add(count, explosive);
			}
		});
		
		//Creates new JComboBox for user to choose trajectory from and adds actionlistener to pick up choices
		JComboBox trajectoryCombo = new JComboBox(trajectory);
		trajectoryCombo.addActionListener(new ActionListener(){
			@Override
			//action performed method that is triggered when action listener senses action
			public void actionPerformed(ActionEvent e_trajectory) {
				//sets 0 to ArrayList routes at index count.
				routes.add(count, 0);
				
				//sets route to index of selected value
				route = trajectoryCombo.getSelectedIndex();
				
				//sets value of route to ArrayList routes at index count.
				routes.add(count, route);
			}
		});
	
		//Adds all the UI features creates above to topPanel and sets border for it.
		topPanel.add(angleLabel);
		topPanel.add(angleField);
		topPanel.add(speedLabel);
		topPanel.add(speedField);
		topPanel.add(timerLabel);
		topPanel.add(timerField);
		topPanel.add(colourLabel);
		topPanel.add(colourCombo);
		topPanel.add(trajectoryLabel);
		topPanel.add(trajectoryCombo);
		topPanel.add(explosionLabel);
		topPanel.add(explosionCombo);
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Creates count label to keep track of how many projectiles the user is launching and sets it title
		countLabel = new JLabel("#" + (count+1));
		
		//Creates new JButton, sets its label and adds Action Listener to it
		//This buttoon and its associated method is supposed to allow user to add multiple projectiles 
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener(){
			@Override
			//action performed method that is triggered when action listener senses action
			public void actionPerformed(ActionEvent add) {
				//adds to the count so that all values inserted now are being registered for the next projectile
				count++;
				
				//Resets labels so that they are regenerated for the UI
				speedLabel.setText("Enter Speed " + (count+1) + ":");
				angleLabel.setText("Enter Speed " + (count+1) + ":");
				colourLabel.setText("Choose Speed " + (count+1) + ":");
				explosionLabel.setText("Choose Speed " + (count+1) + ":");
				trajectoryLabel.setText("Choose Speed " + (count+1) + ":");
				countLabel.setText("#" + (count+1));
				timerLabel.setText("Enter Timer " + (count+1) + ":");
				System.out.println("Added" + count);
				
				
				
			}
		});
		
		//Adds the count label and the addButton to topPanel
		topPanel.add(countLabel);
		topPanel.add(addButton);
		
		//Adds topPanel to JFrame's north section
		add(topPanel, BorderLayout.NORTH);
	
		//Creates new JPanel, sets layout to flowlayout and sets Border
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Creates JButtons, sets their labels and adds them to bottomPanel
		launchButton = new JButton("Launch Projectile");
		clearButton = new JButton("Reset");
		bottomPanel.add(launchButton);
		bottomPanel.add(clearButton);
	
		//This button and its associated method is supposed to tell the frame to render the projectiles that have been entered 
		launchButton.addActionListener(new ActionListener(){
			@Override
			//action performed method that is triggered when action listener senses action
			public void actionPerformed(ActionEvent launch) {
				System.out.println(launch);
				
				//Calls on method Launch Projectile that draws the trajectories on the canvas
				launchProjectile();
			}
		});
		
		//This buttoon and its associated method is supposed to tell the program to essentially regenerate and reset to original settings
		clearButton.addActionListener(new ActionListener(){
			@Override
			//action performed method that is triggered when action listener senses action
			public void actionPerformed(ActionEvent clear) {
				System.out.println(clear);
				
				//Clears the canvas
				Graphics g = canvas.getGraphics();
				g.clearRect(0, 0, width, height);
				
				//Sets count to 0 and resets the labels of UI
				count = 0;
				speedLabel.setText("Enter Speed " + (count+1) + ":");
				angleLabel.setText("Enter Speed " + (count+1) + ":");
				colourLabel.setText("Choose Speed " + (count+1) + ":");
				explosionLabel.setText("Choose Speed " + (count+1) + ":");
				trajectoryLabel.setText("Choose Speed " + (count+1) + ":");
				countLabel.setText("#" + (count+1));
				timerLabel.setText("Enter Timer " + (count+1) + ":");
				
				//Clears the arrayLists such that all information inputed now will be processed fresh
				speeds.clear();
				angles.clear();
				times.clear();
				routes.clear();
				usercolors.clear();
				explosives.clear();
				
				//Presets values to beginning indexes to prevent program crash sure to indexOutofBound Exception
				angles.add(0, 0);
				speeds.add(0, 0);
				times.add(0, 0);
				routes.add(0, 0);
				explosives.add(0, 0);
				usercolors.add(0, Color.BLACK);
				
			}
		});
		
		//Presets values to beginning indexes to prevent program crash sure to indexOutofBound Exception
		angles.add(0, 0);
		speeds.add(0, 0);
		times.add(0, 0);
		routes.add(0, 0);
		explosives.add(0, 0);
		usercolors.add(0, Color.BLACK);
		
		//Adds bottomPanel to the south section of the Frame
		add(bottomPanel, BorderLayout.SOUTH);
		
	}
	
	
	
	//Main method that starts the program
	public static void main (String[] args) {
		//Creates new instance of the GUI class and makes it visible
		new GUI().setVisible(true);
	
	}
	
	
	
	//Other methods for the implemented listensers 
	@Override
	public void stateChanged(ChangeEvent e) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}
	
	//getColor Method that return the color corresponding to the index of the colorCombo
	public Color getColor(int color) {
		
		//If-elseif-else statement that determines which color to return
		if (color == 1) {
			return Color.BLUE;
		} else if (color == 2) {
			return Color.RED;
		} else if (color == 3 ) {
			return Color.YELLOW;
		} else if (color == 4) {
			return Color.CYAN;
		}else if (color == 5) {
			return Color.DARK_GRAY;
		}else if (color == 6) {
			return Color.GRAY;
		}else if (color == 7) {
			return Color.LIGHT_GRAY;
		}else if (color == 8) {
			return Color.GREEN;
		}else if (color == 9) {
			return Color.MAGENTA;
		}else if (color == 10) {
			return Color.ORANGE;
		}else if (color == 11) {
			return Color.PINK;
		}else {
			return Color.BLACK;
		}
		
	}
	
	//getExplosiion method that calls the method that draws explosion at the end 
	//of the trajectory of the firework based on the user's choice as shown by int explosive
	public void getExplosion(int xf, int yf, int explosive, Color usercolor) {
		//If-elseif-else statement that determines which method to call
		if (explosive == 0) {
			StandardShot(xf, yf, canvas, usercolor); 
		} else if (explosive == 1) {
			SplinterShot(xf, yf, canvas, usercolor);
		} else if (explosive == 2) {
			simpleCircular(xf, yf, canvas);
		} else if (explosive == 3) {
			checkered(canvas, xf, yf);
		}else if (explosive == 4) {
			LinearColored(xf, yf, canvas);
		}else {
			star(xf, yf, canvas);
		}
	}
	
	//SplinterShot method that draws splinter explosion
	public void SplinterShot(int xf, int yf, Canvas canvas, Color color) {
		//gets graphics that can draw on the canvas
		Graphics g = canvas.getGraphics();
		
		//sets color to user's choice
		g.setColor(color);
		
		//drawing commnads that make the pattern that represents the explosion of said type
		g.fillOval(xf + 25, yf, 10, 10);
		g.drawLine(xf, yf, xf + 25, yf);
		g.fillOval(xf + 25, yf + 25, 10, 10);
		g.drawLine(xf, yf, xf + 25, yf + 25);
		g.fillOval(xf - 25, yf + 25, 10, 10);
		g.drawLine(xf, yf, xf - 25, yf + 25);
		g.fillOval(xf, yf + 25, 10, 10);
		g.drawLine(xf, yf, xf, yf + 25);
		g.fillOval(xf + 25, yf - 25, 10, 10);
		g.drawLine(xf, yf, xf + 25, yf - 15);
		
	}
	
	//StandardShot method that draws standard explosion
	public void StandardShot(int xf, int yf, Canvas canvas, Color color) {
		//gets graphics that can draw on the canvas
		Graphics g = canvas.getGraphics();
		
		//sets color to user's choice
		g.setColor(color);
		
		//drawing commnads that make the pattern that represents the explosion of said type
		g.drawOval(xf-8, yf, 30, 30);
		g.drawOval(xf-12, yf-3, 40, 40);
		g.drawOval(xf-14, yf-6, 50, 50);
		
		
	}
	
	
	//LinearColored method that draws Colored explosion
	public void LinearColored(int xf, int yf, Canvas canvas) {
		
		//gets graphics that can draw on the canvas
		Graphics g = canvas.getGraphics();
		
		//Sets counter to 0
		int counter = 0;
		
		//For loop that make the pattern that represents the explosion of said type
		for (int i = 1; i <= 50; i += 5) {
			
			//if-elseif-else statents that change the color every time for loop is run
			if (counter == 0) {
				g.setColor(Color.BLACK);
			} else if (counter == 1) {
				g.setColor(Color.BLUE);
			}else if (counter == 2) {
				g.setColor(Color.GRAY);
			}else if (counter == 3) {
				g.setColor(Color.PINK);
			}else if (counter == 4) {
				g.setColor(Color.YELLOW);
			}else if (counter == 5) {
				g.setColor(Color.CYAN);
			}else if (counter == 6) {
				g.setColor(Color.RED);
			}else if (counter == 7) {
				g.setColor(Color.GREEN);
			}else if (counter == 8) {
				g.setColor(Color.WHITE);
			}else {
				g.setColor(Color.MAGENTA);
			}
			
			counter ++;
			
			//drawing commnads that make the pattern that represents the explosion of said type
			g.drawLine(xf + counter, yf, xf + i, yf + i);
			g.drawLine(xf + counter, yf, xf - i, yf + i);
			g.drawLine(xf + counter, yf, xf + i, yf - i);
			g.drawLine(xf + counter, yf, xf - i, yf - i);
			g.drawLine(xf + counter, yf, xf, yf + i);
			g.drawLine(xf + counter, yf, xf, yf - i);
			g.drawLine(xf + counter, yf, xf + i, yf);
			g.drawLine(xf + counter, yf, xf - i, yf);
			
		}
		
		//Sets counter to 0
		counter = 0;
		
	}
	
	//LaunchProjectile method that renders the projectile's path and the explosion
	public void launchProjectile() {
		
		//Gets the size of angles ArrayList which would in any case be the longest one
		int length = angles.size();
		
		//For loop that iterates through every ArrayList for all their values
		for (int i = 0; i < length; i++) {
			
		//Creates 'local' instances of variables required for calculations
		// and sets their value to the value at index i of corresponsing ArrayLists
		local_angle = angles.get(i);
		local_speed = speeds.get(i);
		local_time = times.get(i);
		local_route = routes.get(i);
		local_color = usercolors.get(i);
		local_explosive = explosives.get(i);
		
		//If conditional which makes sure that projectile is only drawn if angle, speed and time are not 0
		if (local_angle != 0 && local_speed !=0 && local_time != 0) {
			
		//if-elseif-else statement that determines which type of trajectory to draw
			//and calls on appropriate method
		if (local_route == 2) {
			BunkerBuster(canvas, local_color, local_explosive, local_color, local_time, local_angle);
		} else if (local_route == 0){
			trajectory(local_angle, local_speed, local_color, canvas, local_time);
			getExplosion(xf, yf, local_explosive, local_color);
		} else {
			splittrajectory(local_angle, local_speed, local_color, canvas, local_explosive, local_time); 
				}
			}
		}
	}
	
	//simpleCircular method that draws 360 explosion
	public void simpleCircular(int x, int y, Canvas canvas) {
		
		//gets graphics that can draw on the canvas
		Graphics g = canvas.getGraphics();
		
		//Creates booleans that control the alternation of color in the pattern
		boolean red = true;
		boolean blue = false;
		
		//for loop that draws the pattern that represent the said explosion
		for (int i = 1; i <= 12; i++) {
		
		//if else statement that alternate colors
		if(red == true) {
			g.setColor(Color.RED);
			red = false;
			blue = true;
		}else {
			g.setColor(Color.BLUE);
			red = true;
			blue = false;
		}
		
		//Drawing command that draws ever bigger circles
		g.drawOval(x - i*5,y - i*5, i*10, i*10);
		}

	}
	
	//trajectory method that draws the trajectory of the firework based on input
	public void trajectory(int angle, int speed, Color usercolor, Canvas canvas, int t) {
		
		//gets the dimension of the canvas and sets it to width and height
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		//gets graphics that can draw on the canvas
		Graphics g = canvas.getGraphics();
		
		//Sets color to user's choice
		g.setColor(usercolor);
			
		//converts user's input as angle into radians
		angle_rad = Math.toRadians(angle);

		
		//Calculates the required parts of the equation that represents paricle motion
		int x = (int) (speed*Math.cos(angle_rad));
		int vox = (int) (speed*Math.cos(angle_rad));
		int voy = (int) (speed*Math.sin(angle_rad));
		
		//sets x_initial and y_inital to 0, so that the trajectory would start at 0;
		x_initial = 0;
		y_initial = 0;
		
		//For loop that draws the trajectory for time t
		//It increments from 0 to t slowly and basically adds lines back to back to create the curve
		//that represents the trajectory
		for (double time = 0; time <= t; time += 1) {
		
		//sets x_initial and y_inital to x_final & y_final, so next line in the loop is added to the previous one
		x_initial = x_final;
		y_initial = y_final;
		
		//calculates x and y coordinates of firework at time time
		x_final = (int)(x*time);
		y_final = (int)(((voy*x_final)/vox)-(0.5*9.8*((x_final*x_final)/(vox * vox))));
		
		//draws line from initial point to point after +.1 time
		g.drawLine(x_initial, height-y_initial, x_final, height-y_final);
		
		
		}
		//sets xf and yf to final position of the firework are time t
		xf = x_final;
		yf = height-y_final;
		
		//sets these to make sure when the method is called again it is 'fresh'
		x = 0;
		vox = 0;
		voy = 0;
		x_final = 0;
		y_final = 0;
		
		//sets split_y and split_x to x_final & y_final
		//to be used in splittrajectory method
		split_x = x_final;
		split_y = y_final;
		
		
		
	}
	
	
	//splittrajectory method that renders firework that splits off into smaller parts and then explodes else where
	public void splittrajectory(int angle, int speed, Color usercolor, Canvas canvas, int explosive, int t) {
		
		//Calls on trajectory method to draw path till the point where it splits off
		trajectory(angle, speed, usercolor, canvas, t);
		
		//gets graphics that draws on canvas
		Graphics g = canvas.getGraphics();
	
		System.out.println(split_x + " " + split_y);
		
		//calls on small trajectory method that calculate where the split off particles end up relative to position of original particle
		//the firework is programmed to be split off into six direction noted by angles -- 15, 25, 30, 45, 60, 75
		smalltrajectory(split_x , split_y, speed, 30, canvas, Color.MAGENTA, explosive);
		smalltrajectory(split_x , split_y, speed, 45, canvas, Color.GRAY, explosive);
		smalltrajectory(split_x , split_y, speed, 60, canvas, Color.BLUE, explosive);
		smalltrajectory(split_x , split_y, speed, 15, canvas, Color.PINK, explosive);
		smalltrajectory(split_x , split_y, speed, 75, canvas, Color.GREEN, explosive);
		smalltrajectory(split_x , split_y, speed, 25, canvas, Color.ORANGE, explosive);
	
		
	}
	
	//BunkerBuster method that draws the trajectory for the bunkerbuster firework
	public void BunkerBuster(Canvas canvas, Color color, int explosive, Color usercolor, int time, int angle) {
		
		//gets the dimension of the canvas and sets it to width and height
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		//gets graphics that draws on canvas
		Graphics g = canvas.getGraphics();
		
		//sets boolean first to true
		boolean first = true;
		
		//sets color to user's choice of color
		g.setColor(color);
	
		//converts user's inputed angle from radians to degrees 
		angle_rad = Math.toRadians(angle);

		//Calculates the required parts of the equation that represents paricle motion
		int x = (int) (speed*Math.cos(angle_rad));
		int vox = (int) (speed*Math.cos(angle_rad));
		int voy = (int) (speed*Math.sin(angle_rad));
		
		
		//For loop that draws the trajectory for time 5
			//It increments from 0 to t slowly and basically adds lines back to back to create the curve
			//that represents the trajectory as it shoots off and then comes down to explode
		for (double t = 0.1; t <= time; t += 1) {
		
		//if else loop that allows for the initial straight liftoff of the firework
		if (first)
			{
			x_initial = 0;
			y_initial = height;
			first = false;
		} else {
			
			//sets x_initial and y_inital to x_final & y_final, so next line in the loop is added to the previous one
			x_initial = x_final;
			y_initial = y_final;
		}
		
		
		//calculates x and y coordinates of firework at time time
		x_final = (int)(x*t);
		y_final = (int)((int)((voy*x_final)/vox)-(0.5*9.8*((x_final*x_final)/(vox * vox))));
		
		//draws line from initial point to point after +1 time
		g.drawLine(x_initial, y_initial, x_final, y_final);

		if (t >=5) {
			break;
		}
		
		
		}
		
		//draws user's choice of explosion at the end of the path of projectile
		getExplosion(x_final, y_final, explosive, usercolor);
		
		//sets split_y and split_x to x_final & y_final
		//to be used in splittrajectory method
		x_final = 0;
		y_final = 0;
		
	}
	
	//smalltrajectory method that determines the route of the split off parts of the firework
	public void smalltrajectory(int a, int b, int speed, int angle, Canvas canvas, Color usercolor, int explosive) {
		
		//gets graphics that draws on canvas
		Graphics g = canvas.getGraphics();
		
		//sets color to user's choice of color
		g.setColor(usercolor);
		
		//sets height to height of canvas
		int height = canvas.getHeight();
		
		//sets x_final & y_final to 0;
		double x_final = 0;
		double y_final = 0;
		
		System.out.println(a + " " + b);
		System.out.println(x_final + " " + y_final);

		//Converts user's inputed angles from degrees to radians
		angle_rad = Math.toRadians(angle);

		//Calculates the required parts of the equation that represents paricle motion
		double xs = (speed*Math.cos(angle_rad));
		double voxs = (speed*Math.cos(angle_rad));
		double voys = (speed*Math.sin(angle_rad));
		
		//initializes variables below
		double x_initial, y_initial;
		
	
		//For loop that draws the trajectory for time 2 so as to make the firework explode 2 second after split
		//It increments from 0 to t slowly and basically adds lines back to back to create the curve
		//that represents the trajectory as it shoots off and then comes down to explode
		
		for (double time = 1; time <= 2; time += .1) {
			
			//sets x_initial and y_inital to x_final & y_final, so next line in the loop is added to the previous one
			x_initial = x_final;
			y_initial = y_final;
			
	
		System.out.println(xs + " " + voxs + " " + voys );
		System.out.println(x_initial + " " + y_initial);
		
		//calculates x and y coordinates of firework at time time
		x_final = (xs*time);
		y_final = ((voys*x_final)/voxs)-(0.5*9.8*((x_final*x_final)/(voxs * voxs)));
		
		//draws line from initial point to point after +1 time
		g.drawLine((int)x_initial + a, (int)(height-y_initial-b), (int)x_final+a, (int)(height-y_final-b));
		
		
		}
		
		//draws the explosion of user's choice at the end of the split's trajectory
		getExplosion((int)(x_final+a), (int)(height-y_final-b), explosive, usercolor);
		
		//sets values to make sure the function works appropriately when it is called again
		xs = 0;
		voxs = 0;
		voys = 0;
		x_final= a;
		y_final= b;
		
	}
	
	//checkered method that draws the trajectory for the Light the Sky firework
	public void checkered(Canvas canvas, int xf, int yf) {
		
		//gets the dimension of the canvas and sets it to width and height
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		//gets graphics that draws on canvas
		Graphics g = canvas.getGraphics();
		
		//declares x & y
		int x, y;
		
		//sets color to yellow
		g.setColor(Color.YELLOW);
		
		//draws line from point of explosion perpendicularly to the groud
		g.drawLine(xf, yf, xf, height);
		
		//for loop that draws the pattern that represent the said explosion
		for (int i = 1; i < 15 ; i = i + 1) {
			
			x = xf/15;
			y = yf/15;
			g.drawLine(xf, yf + y*(i), xf + 2*x*i, height);
			
			}
		
	}
	
	public void star(int xf, int yf, Canvas canvas) {
		
		//Sets width and height to the width and height of the frame allowing for proper regeneration in times of resizing 
		int width = getWidth();
		int height = getHeight();
				
		//gets graphics that draws on canvas
		Graphics g = canvas.getGraphics();
			
		//Declares variables x & y
		int x, y;
				
		//Sets color to black and draws a lines on the edges of the canvas
		g.setColor(Color.BLACK);

		int counter = 0;
		
		//For loop that make the pattern that represents the explosion of said type
		for (int i = 1; i <= 8; i += 1) {
					
		//if-elseif-else statents that change the color every time for loop is run
		if (counter == 0) {
			g.setColor(Color.BLACK);
		} else if (counter == 1) {
			g.setColor(Color.BLUE);
		}else if (counter == 2) {
			g.setColor(Color.GRAY);
		}else if (counter == 3) {
			g.setColor(Color.PINK);
		}else if (counter == 4) {
			g.setColor(Color.YELLOW);
		}else if (counter == 5) {
			g.setColor(Color.CYAN);
		}else if (counter == 6) {
			g.setColor(Color.RED);
		}else if (counter == 7) {
			g.setColor(Color.GREEN);
		}else if (counter == 8) {
			g.setColor(Color.WHITE);
		}else {
			g.setColor(Color.MAGENTA);
			}
					
		counter ++;
					
		//drawing commnads that make the pattern that represents the explosion of said type
			g.drawRect(xf + counter, yf, xf + i, yf + i);
			g.drawRect(xf + counter, yf, xf - i, yf + i);
			g.drawRect(xf + counter, yf, xf + i, yf - i);
			g.drawRect(xf + counter, yf, xf - i, yf - i);
			g.drawRect(xf + counter, yf, xf, yf + i);
			g.drawRect(xf + counter, yf, xf, yf - i);
			g.drawRect(xf + counter, yf, xf + i, yf);
			g.drawRect(xf + counter, yf, xf - i, yf);
					

			
				}
		
		
	}
	
	
	
	
}



