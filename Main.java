package sakinana_CSCI201_Assignment1;

import java.io.*;
import java.util.*;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Main {

	/**
	 * Uses GSON to read the file inputed by the user
	 */
	String fileName;
	int chiralFrequency = -1;
	boolean timefall = false;
	String guid = null, name= null, phone= null, address= null; 
	
	private int searching(int option, int cf, String n ,String fileName, int min, int max) {
		List<WristCuff> users = new ArrayList<>();
		
		try (JsonReader reader = new JsonReader(new FileReader("src/" +  fileName))) {
			reader.beginArray();
			while(reader.hasNext()) {
				WristCuff user = new WristCuff();
				reader.beginObject();
				
				while(reader.hasNext()) {
					String val = reader.nextName();
					
					if(val.equals("chiralFrequency")) {
						chiralFrequency = reader.nextInt();
						user.setCF(chiralFrequency);
					} else if(val.equals("timefall")) {
						timefall = reader.nextBoolean();
						user.setTimeFall(timefall);
					} else if(val.equals("guid")) {
						guid = reader.nextString();
						user.setGuid(guid);
					} else if(val.equals("name")) {
						name = reader.nextString();
						user.setName(name);
					} else if(val.equals("phone")) {
						phone = reader.nextString();
						user.setPhone(phone);
					} else if(val.equals("address")) {
						address = reader.nextString();
						user.setAddress(address);
					} 
				
				}
				reader.endObject();
				users.add(user);
				
				if(option == 2 && chiralFrequency == cf) {
					System.out.println("\nShelter information: ");
					System.out.println("- Chiral Frequency: " + chiralFrequency);
					if(timefall == false) {
						System.out.println("- Timefall: None");
					} else {
						System.out.println("- Timefall: Current");
					}
					System.out.println("- Guid: " + guid);
					System.out.println("- Name: " + name);
					System.out.println("- Phone: " + phone);
					System.out.println("- Address: " + address);
					return 0;
				} else if(option == 3 && n.equalsIgnoreCase(name)) {
					System.out.println("\nShelter information: ");
					System.out.println("- Chiral Frequency: " + chiralFrequency);
					if(timefall == false) {
						System.out.println("- Timefall: None");
					} else {
						System.out.println("- Timefall: Current");
					}
					System.out.println("- Guid: " + guid);
					System.out.println("- Name: " + name);
					System.out.println("- Phone: " + phone);
					System.out.println("- Address: " + address);
					return 2;
				}
			}
		} catch (IOException e) {
			System.err.println("Error: Missing parameters");
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			System.err.println("Error: File could not be parsed");
			e.printStackTrace();
		} catch(Exception e) {
			System.err.println("Error: Malfunctioned file");
			e.printStackTrace();
		}
		if(option == 2) {
			System.out.println("That Chiral frequency does not exist.");
		} else if(option == 3) {
			System.out.println("No such shelter...");
		} else if(option == 4) {
			users.sort(Comparator.comparingInt(WristCuff::getCF));
			Gson gson = new Gson();
			String json = gson.toJson(users);
			
			try(FileWriter writer = new FileWriter("src/" + fileName)) {
				writer.write(json);
				System.out.println("Shelters successfully sorted by Chiral frequency.");
			} catch(IOException e) {
				e.printStackTrace();			
			} 
			
		} else if(option == 1) {
			int count = 0;
			for(WristCuff user: users) {
				if(user.getCF() >= min && user.getCF() <= max && user.getTimeFall() == false) {
					count++;
				}
			}
			if(count == 0) {
				System.out.println("No results \n");
			} else {
				System.out.println("\n" + count + " results \n");
			}
			
			for(WristCuff user: users) {
				if(user.getCF() >= min && user.getCF() <= max && user.getTimeFall() == false) {
					System.out.println("Shelter information: ");
					System.out.println("- Chiral Frequency: " + user.getCF());
					System.out.println("- Timefall: None");
					System.out.println("- Guid: " + user.getGuid());
					System.out.println("- Name: " + user.getName());
					System.out.println("- Phone: " + user.getPhone());
					System.out.println("- Address: " + user.getAddress() + "\n");
				}
			}
		} else if(option == 5) {
			String[] pieces = n.split(",");
			int[] vals = new int[pieces.length];
			int countVal = 0;
			
			for(int i = 0; i < pieces.length; i++) {
				vals[i] = Integer.parseInt(pieces[i].trim());
			}
			System.out.println("=== Commencing timefall shelter search ===");

			for(WristCuff user: users) {
				if(user.getCF() == min && user.getTimeFall() == false) {
					System.out.println("=== Matching timefall shelter found! ===");
					System.out.println("Shelter information: ");
					System.out.println("- Chiral Frequency: " + user.getCF());
					System.out.println("- Timefall: None");
					System.out.println("- Guid: " + user.getGuid());
					System.out.println("- Name: " + user.getName());
					System.out.println("- Phone: " + user.getPhone());
					System.out.println("- Address: " + user.getAddress() + "\n");
					System.out.println("=== Commencing Chiral jump, see you in safety. ===");
					break;
					
				} else if(user.getCF() == min && user.getTimeFall() == true) {
					System.out.println("=== Chiral frequency " + min + " unstable, Chiral jump unavailable.");
					System.out.println("=== Removing target shelter from the list of shelters and saving updated list to disk. ===");
					countVal++;
				}
				for(int i = 0; i < vals.length; i++) {
					if(user.getCF() == vals[i] && user.getTimeFall() == false && vals[i] != min) {
							System.out.println("=== Matching timefall shelter found! ===");
							System.out.println("Shelter information: ");
							System.out.println("- Chiral Frequency: " + user.getCF());
							System.out.println("- Timefall: None");
							System.out.println("- Guid: " + user.getGuid());
							System.out.println("- Name: " + user.getName());
							System.out.println("- Phone: " + user.getPhone());
							System.out.println("- Address: " + user.getAddress() + "\n");
							System.out.println("=== Commencing Chiral jump, see you in safety. ===");
							break;
						} else if(user.getCF() == vals[i] && user.getTimeFall() == true && vals[i] != min){
							System.out.println("=== Chiral frequency " + vals[i] + " unstable, Chiral jump unavailable.");
							System.out.println("=== Removing target shelter from the list of shelters and saving updated list to disk. ===");
							countVal++;
				
						}
					
				}
				List<WristCuff> filteredUser = new ArrayList<>();
				for(WristCuff user2 : users) {
					if(user2.getTimeFall() == false) {
						filteredUser.add(user2);
					}
				}
				Gson gson = new Gson();
				String json = gson.toJson(filteredUser);
				
				try(FileWriter writer = new FileWriter("src/" + fileName)) {
					writer.write(json);
				} catch(IOException e) {
					e.printStackTrace();			
				} 
				
			}
			if(countVal == vals.length) {
				System.out.println("=== No shelter available. You are DOOMED. ===");
			}
		}
		return 1;
	}
	/**
	 * Prints the option menu
	 */
	private void displayOptions() {
		System.out.println(
				"\n\t1) List all available shelters within the min and max of supported Chiral frequencies\n"
				+ "\t2) Search for a shelter by Chiral frequency\n"
				+ "\t3) Search for a shelter by name\n"
				+ "\t4) Sort shelters by Chiral frequency\n"
				+ "\t5) Jump to a shelter with the lowest supported Chiral frequency"
		);
	}


	public static void main(String[] args) {
		Main solution = new Main();
		System.out.println("Welcome to Bridges Link.\n");
		boolean val = true;
		int input = 0;
		do {
			System.out.print("Please provide timefall shelter data source: ");
			
			Scanner scanner = new Scanner(System.in);
			String fileName = scanner.nextLine();
				
			File file = new File("src/" + fileName);
			val = file.exists();
			if(file.exists()) {
				
				System.out.println("=== Data accepted ===\r\n");
				System.out.print("Please provide supported Chiral frequencies: ");
				String values = scanner.nextLine();
				
				do {
					solution.displayOptions();
					System.out.print("Choose an option: ");
					input = scanner.nextInt();
					scanner.nextLine();
					int frequency = 0;
					
					if(input == 2) {
						do {
							System.out.print("\nWhat Chiral frequency are you looking for? \n");
							frequency = scanner.nextInt();
						} while(solution.searching(2, frequency, "", fileName, 0 , 0) == 1);
						
					} 
					
					else if(input == 3) {
						String name;
						do {
							System.out.print("\nWhat shelter’s name are you looking for? ");
							
							name = scanner.nextLine();
						} while(solution.searching(3, 0, name, fileName, 0, 0) == 1);
						
					} 
					
					else if(input == 4) {
						solution.searching(4,0, "", fileName, 0, 0);
					} else if(input == 1) {
						String[] pieces = values.split(",");
						int[] vals = new int[pieces.length];
						for(int i = 0; i < pieces.length; i++) {
							if(pieces.length > 1) {
								vals[i] = Integer.parseInt(pieces[i].trim());
							}
						}
						int min = vals[0];
						int max = vals[0];
						
						for(int j = 1; j < vals.length; j++) {
							if(vals[j] < min) {
								min = vals[j];
							} 
							if(vals[j] > max)  {
								max = vals[j];
							}
						}
						if(pieces.length == 1) {
							System.out.println("No supported Chiral frequencies");
						} else {
							solution.searching(1, 0, "", fileName, min, max);
						}
		
					}
				} while(input < 5);
				
				if(input == 5) {
					String[] pieces = values.split(",");
					int[] vals = new int[pieces.length];
					
					for(int i = 0; i < pieces.length; i++) {
						if(pieces.length > 1) {
							vals[i] = Integer.parseInt(pieces[i].trim());
						}
					}
					int min = vals[0];
					int max = vals[0];
					
					for(int j = 1; j < vals.length; j++) {
						if(vals[j] < min) {
							min = vals[j];
						} 
						if(vals[j] > max)  {
							max = vals[j];
						}
					}
					if(pieces.length == 1) {
						System.out.println("No supported Chiral frequencies");
					} else {
						solution.searching(5, 0, values, fileName, min, 0);					}
				}
				
			} else {
				System.out.println("The file " + fileName + " could not be found.");
			}
			} while(val == false);
		
	}
}
