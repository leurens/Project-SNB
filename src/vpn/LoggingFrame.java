package vpn;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class LoggingFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabl;
	private static OpenVPNLogReader logReader;
	private JMenuItem mntmCreateCertificate;
	private static DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
				try {
					// create the class that will get the log file. Perhaps
					// select via FileDialog?
					// FIXME change to appropriate directory or use a file
					// dialog chooser.
					logReader = new OpenVPNLogReader("C:/Users/Administrator/Desktop/openvpn-status.log");

					LoggingFrame frame = new LoggingFrame();
					
					frame.setVisible(true);
					//frame.setSize(800,600);
					frame.setTitle("Main logging Screen");
					
					for (int i = 0; i < 10; i++) 
					{
						Thread.sleep(5000);
						setOpenVpnDataModel(frame.getTabl());
						//scrollPane.getViewport().add(tabl);
						i=0;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	public JTable getTabl() {
		return tabl;
	}

	/**
	 * Create the frame.
	 */
	public LoggingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmCreateCertificate = new JMenuItem("Create certificate");
		mnFile.add(mntmCreateCertificate);

		mntmCreateCertificate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CertificateCreation cc = new CertificateCreation();
				cc.setVisible(true);
				cc.setSize(800,600);
				cc.setTitle("Create certificate for new user");
				}
			

				
				// // Here We Call an external script.
				// Process p;
				// try {
				// // TODO add correct command here; Command must be on the
				// // PATH
				//
				// p = Runtime.getRuntime().exec("uptime");
				// p.waitFor();
				// BufferedReader reader = new BufferedReader(
				// new InputStreamReader(p.getInputStream()));
				//
				// String line = "";
				// while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				// }
				//
				// } catch (IOException | InterruptedException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
				/*System.out.println("Creating certificate done");*/
				// }
			/*}*/
		});

		JMenuItem mntmLogs = new JMenuItem("Logs");
		mnFile.add(mntmLogs);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		// contentPane.add(scrollPane);

		tabl = new JTable();

		// mouselistener.
		tabl.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		// scrollPane.add(table);
		contentPane.add(scrollPane);
		setOpenVpnDataModel(tabl);
		scrollPane.getViewport().add(tabl);
		// scrollPane.setColumnHeaderView(table);
	}

	private static void setOpenVpnDataModel(JTable table) {

		model = new DefaultTableModel(null,
				new String[] { "Common Name", "IP Address", "Bytes Received",
						"Bytes Sent", "Connected Since" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		// Add all rows to the tableModel.
		for (Object[] row : logReader.getListOfVpnInformationRows()) {
			model.addRow(row);
		}

		table.setModel(model);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
