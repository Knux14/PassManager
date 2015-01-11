package eu.knux.passmanager.Objects;

import java.util.ArrayList;

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

public class Category {

	private String name;
	private ArrayList<Password> passList;
	
	public Category(String name){
		this.name = name;
		this.passList = new ArrayList<>();
	}
	
	public void rename(String name) {
		this.name = name;
	}
	
	public void addPassword(Password pass) {
		passList.add(pass);
	}
	
	public void remPass(String name){
		for(Password pass : passList){
			if (pass.getName().equalsIgnoreCase(name))
				passList.remove(pass);
		}
	}
	
	public void remPass(Password pass) {
		passList.remove(pass);
	}

	public ArrayList<Password> getArray(){
		return passList;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
}
