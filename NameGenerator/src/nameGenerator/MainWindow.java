package nameGenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainWindow{

	private JFrame frame;
	
	private Options op = new Options();
	private Generator gen = new Generator(op);
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnGenerate, saveButton;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Names Generator");
		frame.setBounds(100, 100, 574, 285);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel options = new JPanel();
		options.setPreferredSize(new Dimension(200,285));
		frame.getContentPane().add(options, BorderLayout.WEST);

		String[] typeNames = op.getTypeNames();
		options.setLayout(null);
		JComboBox<String> comboBox = new JComboBox<>(typeNames);
		comboBox.setLocation(82, 46);
		comboBox.setSize(108,20);
		options.add(comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()==1){
					textField_2.setVisible(true);
					btnGenerate.setEnabled(true);
					saveButton.setEnabled(false);
				}
				else
				{
					textField_2.setVisible(false);
					btnGenerate.setEnabled(false);
					saveButton.setEnabled(true);
				}
				
			}
		});
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setFont(new Font("Arial", Font.PLAIN, 15));
		lblOptions.setBounds(72, 11, 56, 14);
		options.add(lblOptions);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 33, 200, 2);
		options.add(separator_1);
		
		JLabel lblNewLabel = new JLabel("Type:");
		lblNewLabel.setBounds(10, 49, 46, 14);
		options.add(lblNewLabel);
		
		JLabel lblMaxLetters = new JLabel("Min letters:");
		lblMaxLetters.setBounds(10, 87, 65, 14);
		options.add(lblMaxLetters);
		
		textField = new JTextField();
		textField.setBounds(82, 84, 39, 20);
		options.add(textField);
		textField.setColumns(10);
		
		JLabel lblMaxLetters_1 = new JLabel("Max letters:");
		lblMaxLetters_1.setBounds(10, 118, 65, 14);
		options.add(lblMaxLetters_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(82, 115, 39, 20);
		options.add(textField_1);
		textField_1.setColumns(10);
		
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setBounds(24, 165, 65, 23);
		options.add(rdbtnNewRadioButton);
	
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Female");
		rdbtnNewRadioButton_1.setBounds(111, 165, 70, 23);
		options.add(rdbtnNewRadioButton_1);
		
		bg.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton);
		
		saveButton = new JButton("Save");
		saveButton.setBounds(57, 222, 89, 23);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField_2.setVisible(false);
				try{
					op.setMax(Integer.parseInt(textField_1.getText()));    
					op.setMin(Integer.parseInt(textField.getText()));
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(frame,"The entered value is incorrect!","Error!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(Integer.parseInt(textField.getText())>Integer.parseInt(textField_1.getText())){
					JOptionPane.showMessageDialog(frame,"The entered value is incorrect!","Error!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				op.go(comboBox.getSelectedIndex());
				
				if(rdbtnNewRadioButton.isSelected()){
					op.setGender("male");
				}
				else{
					op.setGender("female");
				}
				op.saveTab();
				op.closeDb();
				btnGenerate.setEnabled(true);
			}
		});
		options.add(saveButton);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		frame.getContentPane().add(separator, BorderLayout.CENTER);
		
		JPanel generator = new JPanel();
		generator.setPreferredSize(new Dimension(365,285));
		frame.getContentPane().add(generator, BorderLayout.EAST);
		generator.setLayout(null);
		
		JLabel lbl = new JLabel("",SwingConstants.CENTER);
		lbl.setForeground(Color.ORANGE);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl.setBounds(46, 75, 285, 31);
		generator.add(lbl);
		
		textField_2 = new JTextField("");
		textField_2.setBounds(82, 138, 199, 20);
		textField_2.setVisible(false);
		generator.add(textField_2);
		textField_2.setColumns(10);
		
		btnGenerate = new JButton("GENERATE");
		btnGenerate.setBounds(115, 206, 146, 23);
		generator.add(btnGenerate);
		btnGenerate.setEnabled(false);
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rndName = null;
				if(comboBox.getSelectedIndex()==1)
				rndName = gen.gameNameGenerator(textField_2.getText());
				else
				rndName = gen.randomName();
				if(rndName!=null){
					lbl.setText(rndName);}
				else
					JOptionPane.showMessageDialog(frame, "Name no found");
				
			}
		});
	}
}
