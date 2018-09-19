package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
* @ClassName: b
* @Description: 
* @author linyan
* @date 2018年1月25日 上午11:04:45
*
*/
public class b {

	
	static JDialog myFrame=new JDialog();
    public static void main(String[] args) {
            myFrame.setUndecorated(true);//不显示窗口边框和标题
            myFrame.setSize(300, 100);
            myFrame.setLayout(null);
            final JLabel jLabel=new JLabel("X");//放在右上角做关闭按钮
            jLabel.setFont(new Font("宋体", 0, 14));
            myFrame.getContentPane().setBackground(new Color(255, 255, 255));
            JPanel p=((JPanel)myFrame.getContentPane());
            p.setBorder(new LineBorder(new java.awt.Color(10,110,10), 1, false));
            myFrame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width-305, 
              Toolkit.getDefaultToolkit().getScreenSize().height-135, 300, 100);
            myFrame.getContentPane().add(jLabel);
            final JLabel jLabel2=new JLabel("X是的发生大法撒旦发");
            jLabel2.setBounds(20, 20, 100, 50);
            myFrame.getContentPane().add(jLabel2);
            jLabel.setBounds(280, 0, 20, 20);
            myFrame.setVisible(true);
            myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           
            jLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
				 myFrame.dispose();
				}
				public void mouseEntered(MouseEvent e) {     
				 super.mouseEntered(e);
				 jLabel.setForeground(Color.red);
				}
				public void mouseExited(MouseEvent e) {
				 super.mouseExited(e);
				 jLabel.setForeground(Color.BLACK);
				}
	
            });
    }
}
