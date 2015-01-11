package eu.knux.passmanager.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import eu.knux.passmanager.Main;
import eu.knux.passmanager.Objects.Category;
import eu.knux.passmanager.Objects.Password;
import eu.knux.passmanager.helper.FileHelper;

/**
 * Copyright 2015 Nathan, Knux14, J.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

public class MainFrame extends JFrame {
	
	private JSplitPane splitView;
	private JTree pswdTree;
	private JPanel rightPanel;
	
	public MainFrame(){
		setTitle("PassManager");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	
		rightPanel = new JPanel();
		pswdTree = new JTree();
		
		pswdTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		splitView = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitView.setOneTouchExpandable(true);
		splitView.setDividerLocation(200);
		
		splitView.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		splitView.setRightComponent(rightPanel);
		
		add(splitView, BorderLayout.CENTER);
		
		JMenuBar jmb = new JMenuBar();
		
		JMenu file = new JMenu("Menu");
		{
			JMenuItem newCont = new JMenuItem("New container");
			file.add(newCont);
			
			JMenuItem open = new JMenuItem("Open...");
			file.add(open);
			
			JMenuItem options = new JMenuItem("Options");
			file.add(options);
			
			file.addSeparator();
			
			JMenuItem exit = new JMenuItem("Exit");
			file.add(exit);
		}
		jmb.add(file);
		
		JMenu container = new JMenu("Container");
		{
			JMenuItem changepw = new JMenuItem("Change Password");
			container.add(changepw);
			
			JMenuItem lock = new JMenuItem("Lock");
			container.add(lock);
		}
		jmb.add(container);
		
		JMenu help = new JMenu("?");
		{
			JMenuItem about  = new JMenuItem("About PassManager");
			help.add(about);
			JMenuItem online = new JMenuItem("Online");
			help.add(online);
			JMenuItem aboutus = new JMenuItem("About us");
			help.add(aboutus);
		}
		jmb.add(help);
		
		setJMenuBar(jmb);
		
		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(null);
		updatePasswords(jfc.getSelectedFile());
		
		pswdTree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean isLeaf, int row, boolean focused) {
                Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, isLeaf, row, focused);
                
                // WTF.
                /**
                 * @TODO
                 * Find out WHY THE FUCK object is an instance of but is not an instance of the thing it is.
                 * Then, use "setIcon" function to set the icon by decoding Base64 stored password (Do not do it there, 
                 * It refreshes oftenly, only needed once)
                 */
                return c;
            }
        });
		
		pswdTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath tp = pswdTree.getSelectionPath();
				DefaultMutableTreeNode selected = (DefaultMutableTreeNode)tp.getLastPathComponent();
				JPanel panel = null;
				if (selected.getUserObject() instanceof Category){
					Category c = (Category)selected.getUserObject();	
				} else if(selected.getUserObject() instanceof Password) {
					Password p = (Password)selected.getUserObject();
					rightPanel = new PanelPassword(p, false);
				}
				rightPanel = panel;
				splitView.setRightComponent(rightPanel);
			}
		});
	}
	
	
	public void updatePasswords(File f){
		Main.passList = FileHelper.loadPassword(f);
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Container's Root");
		for (Category c : Main.passList.values()) {
			if (!c.getName().equals("root")){
				DefaultMutableTreeNode cat = new DefaultMutableTreeNode(c);
				for (Password p : c.getArray()) {
					DefaultMutableTreeNode pass = new DefaultMutableTreeNode(p);
					cat.add(pass);
				}
				rootNode.add(cat);
			}
		}
		for (Password p : Main.passList.get("root").getArray()){
			DefaultMutableTreeNode pass = new DefaultMutableTreeNode(p);
			rootNode.add(pass);
		}
		DefaultTreeModel model = new DefaultTreeModel(rootNode);
		pswdTree.setModel(model);
		splitView.setLeftComponent(new JScrollPane(pswdTree));
	}
}
