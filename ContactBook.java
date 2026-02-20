import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Contact {
    String name;
    String phnumber;
    String email;
    String address;

    Contact(String name, String phnumber, String email, String address) {
        this.name = name;
        this.phnumber = phnumber;
        this.email = email;
        this.address = address;
    }
}

public class ContactBook {
    static HashMap<String, Contact> contact = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void addContact() {
        while (true) {
            System.out.print("Enter the name: ");
            String name = sc.nextLine();
            System.out.print("Enter the phone number: ");
            String phnumber = sc.nextLine();
            System.out.print("Enter the email: ");
            String email = sc.nextLine();
            System.out.print("Enter the address: ");
            String address = sc.nextLine();
            contact.put(name, new Contact(name, phnumber, email, address));
            System.out.println("Contact is added");
            System.out.print("Want to stop: (yes or no): ");
            String e = sc.nextLine();
            if (e.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }

    public static void viewContact() {
        if (contact.isEmpty()) {
            System.out.println("No contact");
            return;
        }
        for (Map.Entry<String, Contact> entry : contact.entrySet()) {
            Contact c = entry.getValue();
            System.out.println("Name: " + c.name + ", Phone: " + c.phnumber + ", Email: " + c.email + ", Address: " + c.address);
        }
    }

    public static void searchContact(String query) {
        boolean found = false;
        for (Contact c : contact.values()) {
            if (c.name.contains(query) || c.phnumber.contains(query)) {
                System.out.print(c.name + ",");
                System.out.print(c.phnumber + ",");
                System.out.print(c.email + ",");
                System.out.print(c.address + ",");
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contact found for query: " + query);
        }
    }

    public static void updateContact(String name, String phnumber, String email, String address) {
        if (contact.containsKey(name)) {
            Contact c = contact.get(name);
            if (email != null) {
                c.email = email;
            } else if (phnumber != null) {
                c.phnumber = phnumber;
            } else if (address != null) {
                c.address = address;
            }
        } else {
            System.out.println("Contact not found.");
        }
    }

    public static void deleteContact(String name) {
        if (contact.containsKey(name)) {
            contact.remove(name);
            System.out.println("Contact deleted.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Contact\n2. View Contacts\n3. Search Contact\n4. Update Contact\n5. Delete Contact");
            System.out.print("Enter the choice: ");
            int cmd = Integer.parseInt(sc.nextLine());
            if (cmd == 1) {
                addContact();
            } else if (cmd == 2) {
                viewContact();
            } else if (cmd == 4) {
                System.out.print("Enter the parameter to change (email/phnumber/address): ");
                String a = sc.nextLine();
                System.out.print("Enter the value of parameter: ");
                String b = sc.nextLine();
                System.out.print("Enter the name of the person: ");
                String c = sc.nextLine();
                if (a.equals("email")) {
                    updateContact(c, null, b, null);
                } else if (a.equals("phnumber")) {
                    updateContact(c, b, null, null);
                } else if (a.equals("address")) {
                    updateContact(c, null, null, b);
                }
            } else if (cmd == 3) {
                System.out.print("Enter the name or phone number for search: ");
                String q = sc.nextLine();
                searchContact(q);
            } else if (cmd == 5) {
                System.out.print("Enter the name to delete: ");
                String a = sc.nextLine();
                deleteContact(a);
            } else {
                System.out.println("Invalid choice.");
            }
            System.out.print("Want to perform more function (yes/no): ");
            String i = sc.nextLine();
            if (i.equalsIgnoreCase("no")) {
                break;
            }
        }
        sc.close();
    }
}
