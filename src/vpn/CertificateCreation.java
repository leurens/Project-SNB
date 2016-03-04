package vpn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.PortableServer.ServantLocatorOperations;

public class CertificateCreation extends JFrame {
	
	JPanel panelCreate;
	JTextField txtCommonName;

	public CertificateCreation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelCreate = new JPanel();
		add(panelCreate);
		
		JLabel lblCommonName = new JLabel("Common name:");
		lblCommonName.setLocation(100, 200);
		panelCreate.add(lblCommonName);
		
		txtCommonName = new JTextField();
		txtCommonName.setPreferredSize( new Dimension( 200, 24 ) );
		txtCommonName.setToolTipText("Enter a common name");
		txtCommonName.setLocation(100, 300);
		panelCreate.add(txtCommonName);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//CertificateGenerator certGenerator = new CertificateGenerator();
				String commonName = txtCommonName.getText();
				//certGenerator.createCert(commonName);
				
				System.out.println("Certificate created for " + txtCommonName.getText());
			}
		});
		panelCreate.add(btnCreate);
		
	}

}
