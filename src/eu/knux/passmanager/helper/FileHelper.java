package eu.knux.passmanager.helper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import eu.knux.passmanager.Objects.Category;
import eu.knux.passmanager.Objects.Password;

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

public class FileHelper {

	public static LinkedHashMap<String, Category> loadPassword(File f){
		SAXBuilder builder = new SAXBuilder();
		Element racine = null;
		LinkedHashMap<String, Category> categoriesReturned = new LinkedHashMap<>();
		try {
			Document doc = builder.build(f);
			racine = doc.getRootElement();
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		
		if (racine != null){
			List<Element> categories = racine.getChildren("category");
			categoriesReturned.put("root", new Category("root"));
			for (Element e : categories){
				String name = e.getAttributeValue("name");
				List<Element> passes = e.getChildren("password");
				for(Element e2 : passes){
					Category currCate = null;
					if(name != null && !categoriesReturned.containsKey(name)) {
						categoriesReturned.put(name, new Category(name));
					}	
					currCate = (name == null) ? categoriesReturned.get("root") : categoriesReturned.get(name);
					
					Password p = new Password();
					p.setName(e2.getChildText("name"));
					p.setPass(e2.getChildText("pass"));
					p.setComment(e2.getChildText("comment"));
					p.setEncrypted(true);
					currCate.addPassword(p);
				}
			}
		}
		
		return categoriesReturned;
	}
	
	public static LinkedHashMap<String, Category> loadPassword(String t){
		return loadPassword(new File(t));
	}
	
}
