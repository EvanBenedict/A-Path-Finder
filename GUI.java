import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GUI implements ActionListener {
	
	private JFrame frame;
	
	private Dimension screen;
	
	private int gridSize, blockSize, reqWindowSize, width, height;
	
	
	public GUI (Grid grid, int gridSize) {
		this.gridSize = gridSize;
		screen = Toolkit.getDefaultToolkit().getScreenSize();	
		
		frame = new JFrame();
		createScale();
		
		frame.setContentPane(grid);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	public void createScale() {
		height = screen.height - 100;
		width = screen.width;
		
		if (width >= height) {
			blockSize = (int) (height / gridSize);
			reqWindowSize = gridSize * blockSize;
			frame.setLocation((width - reqWindowSize) / 2, (height - reqWindowSize) / 2);
			
		} else if (height > width) {
			blockSize = (int) (width / gridSize);
			reqWindowSize = gridSize * blockSize;
			frame.setLocation((height - reqWindowSize) / 2, (width - reqWindowSize) / 2);
		}
		
		frame.getContentPane().setPreferredSize(new Dimension(reqWindowSize + 1, reqWindowSize + 1));
		frame.pack();
	}

	public int getBlockSize() {
		return blockSize;
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
