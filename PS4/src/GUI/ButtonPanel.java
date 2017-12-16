package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Core.ConnectFourModel;

public class ButtonPanel extends JPanel implements ActionListener {
  private ConnectFourModel main;
  private JButton bStart ;
  private JLabel turnInfo;
  private JLabel playerInfo;
  private JLabel winInfo;
  private int mode;

  public ButtonPanel(ConnectFourModel main) {
    this.mode = ConnectFourModel.TWIN;
    this.main = main;
    this.setPreferredSize(new Dimension(200, 400));
    setBackground(Color.GRAY);

    bStart = new JButton("Start");
    bStart.setFont(new Font("Arial", Font.PLAIN, 20));
    bStart.setPreferredSize(new Dimension(180, 60));
    bStart.setFocusPainted(false);
    bStart.setActionCommand("start");
    bStart.addActionListener(this);
    
    turnInfo = new JLabel();
    turnInfo.setFont(new Font("Arial", Font.PLAIN, 20));
    turnInfo.setPreferredSize(new Dimension(180, 30));
    turnInfo.setText("");
    
    playerInfo = new JLabel();
    playerInfo.setFont(new Font("Arial", Font.PLAIN, 20));
    playerInfo.setPreferredSize(new Dimension(180, 30));
    playerInfo.setText("");
    
    winInfo = new JLabel();
    winInfo.setFont(new Font("Arial", Font.PLAIN, 20));
    winInfo.setPreferredSize(new Dimension(180, 120));
    winInfo.setText("");
    
    this.add(bStart);
    this.add(turnInfo);
    this.add(playerInfo);
    this.add(winInfo);
  }

  public void actionPerformed(ActionEvent event) {
    if (event.getActionCommand().equals("start")) {
      Object[] options = {"Two Players", "VS COMP"};
      int option =
          JOptionPane.showOptionDialog(this.getParent(), "Please select mode:", "mode select",
              JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      if (option == JOptionPane.YES_OPTION) {
        reset();
        mode = ConnectFourModel.TWIN;
        main.reset();
        main.modeSet(ConnectFourModel.TWIN); 
        bStart.setText("Restart");
      } else if (option == JOptionPane.NO_OPTION) {
        reset();
        mode = ConnectFourModel.COMP;
        main.reset();
        main.modeSet(ConnectFourModel.COMP);
        bStart.setText("Restart");
      }
    }
  }
  
  public void reset(){
    turnInfo.setText("");
    playerInfo.setText("");
    winInfo.setText("");
  }
  
  public void update() {
    int turn = main.getTurn();
    int player = main.getPlayer();
    turnInfo.setText("Turn: " + turn);
    if(mode == ConnectFourModel.TWIN){
      if(player == 1){
        playerInfo.setForeground(Color.RED);
        playerInfo.setText("Player 1's Turn");
      }
      else{
        playerInfo.setForeground(Color.BLUE);
        playerInfo.setText("Player 2's Turn");
      }
    }
    if(mode == ConnectFourModel.COMP){
      if(player == 1){
        playerInfo.setForeground(Color.RED);
        playerInfo.setText("Your Turn");
      }
      else{
        playerInfo.setForeground(Color.BLUE);
        playerInfo.setText("COMP is thinking...");
      }
    }
  }
  
  public void showResult(int result){
    if (result == 0) {
      winInfo.setText("Draw!");
    } else {
      if(mode == ConnectFourModel.TWIN){
        winInfo.setText("Player " + result + " wins!");
      }
      else{
        if(result == 1){
          winInfo.setText("You Win!");
        }
        else{
          winInfo.setText("Try Again!");
        }
      }
    }
  }
}

