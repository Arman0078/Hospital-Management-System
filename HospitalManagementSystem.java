import java.util.*;

public class HospitalManagementSystem {

    // ===== MODELS =====
    static class Patient { int id; String name; int age; String disease; 
        Patient(int id, String name, int age, String disease){ this.id=id;this.name=name;this.age=age;this.disease=disease;} }
    static class Doctor { int id; String name; String specialization; 
        Doctor(int id,String name,String specialization){this.id=id;this.name=name;this.specialization=specialization;} }
    static class Appointment { int id; String patientName, doctorName, date;
        Appointment(int id,String patientName,String doctorName,String date){this.id=id;this.patientName=patientName;this.doctorName=doctorName;this.date=date;} }
    static class Bill { int id; String patientName; double amount;
        Bill(int id,String patientName,double amount){this.id=id;this.patientName=patientName;this.amount=amount;} }

    // ===== DATA =====
    static List<Patient> patients = new ArrayList<>();
    static List<Doctor> doctors = new ArrayList<>();
    static List<Appointment> appointments = new ArrayList<>();
    static List<Bill> bills = new ArrayList<>();

    static int patientId=1, doctorId=1, appointmentId=1, billId=1;

    // ===== LOGIN METHOD =====
    static boolean login(Scanner sc) {
        String username = "admin";
        String password = "admin123";
        int attempts = 3;

        while(attempts>0) {
            System.out.print("Username: "); String u = sc.nextLine();
            System.out.print("Password: "); String p = sc.nextLine();
            if(u.equals(username) && p.equals(password)) {
                System.out.println("Login successful! Welcome " + username);
                return true;
            } else {
                attempts--;
                System.out.println("Incorrect! Attempts left: " + attempts);
            }
        }
        System.out.println("Too many failed attempts. Exiting...");
        return false;
    }

    // ===== MAIN =====
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if(!login(sc)) System.exit(0);  // <-- call login here

        while(true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Book Appointment");
            System.out.println("4. View All Records");
            System.out.println("5. Search Patient");
            System.out.println("6. Delete Patient");
            System.out.println("7. Generate Bill");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch(choice) {
                case 1: addPatient(sc); break;
                case 2: addDoctor(sc); break;
                case 3: bookAppointment(sc); break;
                case 4: viewAll(); break;
                case 5: searchPatient(sc); break;
                case 6: deletePatient(sc); break;
                case 7: generateBill(sc); break;
                case 8: System.out.println("Exiting..."); System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // ===== FUNCTIONS =====
    static void addPatient(Scanner sc){
        System.out.print("Patient Name: "); String n=sc.nextLine();
        System.out.print("Age: "); int a=sc.nextInt(); sc.nextLine();
        System.out.print("Disease: "); String d=sc.nextLine();
        patients.add(new Patient(patientId++, n, a, d));
        System.out.println("Patient added!");
    }

    static void addDoctor(Scanner sc){
        System.out.print("Doctor Name: "); String n=sc.nextLine();
        System.out.print("Specialization: "); String s=sc.nextLine();
        doctors.add(new Doctor(doctorId++, n, s));
        System.out.println("Doctor added!");
    }

    static void bookAppointment(Scanner sc){
        System.out.print("Patient Name: "); String p=sc.nextLine();
        System.out.print("Doctor Name: "); String d=sc.nextLine();
        System.out.print("Date (DD-MM-YYYY): "); String dt=sc.nextLine();
        appointments.add(new Appointment(appointmentId++, p,d,dt));
        System.out.println("Appointment booked!");
    }

    static void viewAll() {

    System.out.println("\n================ HOSPITAL RECORDS ================");

    // ---- Patients ----
    System.out.println("\n--- Patients ---");
    if (patients.isEmpty()) {
        System.out.println("No patients available.");
    } else {
        System.out.printf("%-5s %-15s %-10s %-15s%n", "ID", "Name", "Age", "Disease");
        for (Patient p : patients) {
            System.out.printf("%-5d %-15s %-10d %-15s%n",
                    p.id, p.name, p.age, p.disease);
        }
    }

    // ---- Doctors ----
    System.out.println("\n--- Doctors ---");
    if (doctors.isEmpty()) {
        System.out.println("No doctors available.");
    } else {
        System.out.printf("%-5s %-15s %-20s%n", "ID", "Name", "Specialization");
        for (Doctor d : doctors) {
            System.out.printf("%-5d %-15s %-20s%n",
                    d.id, d.name, d.specialization);
        }
    }

    // ---- Appointments ----
    System.out.println("\n--- Appointments ---");
    if (appointments.isEmpty()) {
        System.out.println("No appointments booked.");
    } else {
        System.out.printf("%-5s %-15s %-15s %-15s%n",
                "ID", "Patient", "Doctor", "Date");
        for (Appointment a : appointments) {
            System.out.printf("%-5d %-15s %-15s %-15s%n",
                    a.id, a.patientName, a.doctorName, a.date);
        }
    }

    // ---- Bills ----
    System.out.println("\n--- Bills ---");
    if (bills.isEmpty()) {
        System.out.println("No bills generated.");
    } else {
        System.out.printf("%-5s %-15s %-10s%n", "ID", "Patient", "Amount");
        for (Bill b : bills) {
            System.out.printf("%-5d %-15s ₹%-10.2f%n",
                    b.id, b.patientName, b.amount);
        }
    }

    System.out.println("\n=================================================");
}


    static void searchPatient(Scanner sc){
        System.out.print("Enter patient name: "); String name=sc.nextLine();
        for(Patient p:patients){
            System.out.printf("%-5s %-15s %-10s %-15s%n", "ID", "Name", "Age", "Disease");
            
            if(p.name.equalsIgnoreCase(name)){
                
                System.out.printf("%-5d %-15s %-10d %-15s%n",
                    p.id, p.name, p.age, p.disease);
                return;
            }
        }
        System.out.println("Patient not found");
    }

    static void deletePatient(Scanner sc){
        System.out.print("Enter patient ID: "); int id=sc.nextInt(); sc.nextLine();
        patients.removeIf(p->p.id==id);
        System.out.println("Patient deleted if existed");
    }

    static void generateBill(Scanner sc){
        System.out.print("Patient Name: "); String n=sc.nextLine();
        System.out.print("Amount: "); double amt=sc.nextDouble(); sc.nextLine();
        bills.add(new Bill(billId++, n, amt));
        System.out.println("Bill generated!");
    }
}
