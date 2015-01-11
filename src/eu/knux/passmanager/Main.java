package eu.knux.passmanager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel;

import eu.knux.passmanager.Objects.Category;
import eu.knux.passmanager.gui.MainFrame;

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


public class Main {

	public static LinkedHashMap<String, Category> passList;
	public static ArrayList<Category> passwordList;

	public static void main(String args[]) {
		useSubstanceLAF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
			}
		});
	}

	/**
	 * Known bug in Substance, need to call it twice. If somebody knows how to
	 * fix, please PR ;)
	 **/
	public static void useSubstanceLAF() {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel(new SubstanceGraphiteGlassLookAndFeel());
		} catch (Exception e) { }
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel(new SubstanceGraphiteGlassLookAndFeel());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
