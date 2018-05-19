package com.SilverJolteon.urlscanner;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI{
	private String title = "Title";
	private int x, y, vPos;
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	
	GUI(String title, int x, int y){
		this.title = title;
		this.x = x;
		this.y = y;
		//Frame Init
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon(getClass().getResource("images/icon.png"), "icon").getImage());
		frame.setVisible(true);
		//Panel Init
		
		panel = new JPanel(new GridBagLayout());
		panel.setPreferredSize(new Dimension(x, 0));
		panel.setLayout(null);
		scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(32, 64, x-64, y - 128);
		frame.add(scrollPane);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(x, y));
        contentPane.add(scrollPane);
        frame.setContentPane(contentPane);
		frame.pack();
	}
	
	public void header(String text, String pos, int yPos, int fontSize){
		JLabel label = new JLabel(text);
		label.setFont(new Font("sans serif", Font.PLAIN, fontSize));
		frame.add(label);
		Dimension size = label.getPreferredSize();
		int width = label.getFontMetrics(label.getFont()).stringWidth(text);
		int xPos = 0;
		if(pos.toLowerCase() == "center") xPos = (x - width) / 2;
		else if(pos.toLowerCase() == "left") xPos = 16;
		else if(pos.toLowerCase() == "right") xPos = x - width - 16;
		label.setBounds(xPos-1, yPos-8, size.width, size.height);
		frame.pack();
	} 
	
	public void print(String text, int xPos, int yPos, int fontSize){
		JLabel label = new JLabel(text);
		label.setFont(new Font("sans serif", Font.PLAIN, fontSize));
		panel.add(label);
		Dimension size = label.getPreferredSize();
		label.setBounds(xPos-1, yPos-8, size.width, size.height);
		frame.pack();
	}
	
	public void print(String text, String pos, int yPos, int fontSize){
		JLabel label = new JLabel(text);
		label.setFont(new Font("sans serif", Font.PLAIN, fontSize));
		panel.add(label);
		Dimension size = label.getPreferredSize();
		int width = label.getFontMetrics(label.getFont()).stringWidth(text);
		int xPos = 0;
		if(pos.toLowerCase() == "center") xPos = (x - width) / 2;
		else if(pos.toLowerCase() == "left") xPos = 16;
		else if(pos.toLowerCase() == "right") xPos = x - width - 92;
		label.setBounds(xPos-1, yPos-8, size.width, size.height);
		frame.pack();
	}
	
	public void scroll(int vPos){
		this.vPos = vPos;
		panel.setPreferredSize(new Dimension(x, vPos));
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		frame.pack();
	}
	
	public void cls(){
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}
	
	public void sleep(int ms){
		try{
			Thread.sleep(ms);
		}
		catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}
}