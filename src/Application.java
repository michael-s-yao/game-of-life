/*******************************************************************************
 * Copyright 2020 Michael S. Yao
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Application {
    static int width = 600;
    static int height = 800;
	
    static String htmlHead = "<html>";
    static String htmlTail = "</html>";

    public static void main(String[] args) {    
        JFrame frame = new JFrame("Game of Life Simulator"); 

        JButton b = new JButton("Start the Game of Life!");    
        b.setBounds(200, 120, 200, 30);    

        JLabel label = new JLabel("Enter Board Size: ");		
        label.setBounds(100, 50, 200, 30);

        JLabel label2 = new JLabel("Starting Board Configuration: ");
        label2.setBounds(100, 85, 250, 30);

        JLabel out = new JLabel();
        out.setBounds(150, 150, 450, 400);

        JTextField boardSize = new JTextField(args[0]);
        boardSize.setBounds(235, 50, 250, 30);

        StringBuilder initConfig = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (i == args.length - 1) { initConfig.append(args[i]); }
            else { initConfig.append(args[i] + " "); }
        }

        JTextField configuration = new JTextField(initConfig.toString());
        configuration.setBounds(310, 85, 175, 30);

        frame.add(out);
        frame.add(boardSize);
        frame.add(configuration);
        frame.add(label);
        frame.add(label2);
        frame.add(b); 
        frame.setSize(width, height);    
        frame.setLayout(null);    
        frame.setVisible(true); 
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ActionListener actionListener = new ActionListener() {
            int size = Integer.parseInt(boardSize.getText());
            String[] initial = configuration.getText().split(" ");

            GameOfLife program = new GameOfLife(size, initial);

            public void actionPerformed(ActionEvent actionEvent) {
                out.setText(htmlHead + program.toString() + htmlTail);
                out.setFont(new Font("Courier", Font.BOLD, 20));
                program.round();
            }
        };

        b.addActionListener(new ActionListener() {
	        
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Timer timer = new Timer(1000, actionListener);
                timer.start();
            }          
        });
    }         
}   
