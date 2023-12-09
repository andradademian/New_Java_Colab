//package map.project.demo.UI;
//
//import map.project.demo.Controller.ActorController;
//import map.project.demo.Controller.AwardController;
//import map.project.demo.AwardFactory.AwardFactory;
//import map.project.demo.Domain.Actor;
//import map.project.demo.Domain.Award;
//import map.project.demo.Domain.Movie;
//
//import java.util.Date;
//import java.util.Scanner;
//import java.util.Vector;
//
//public class AwardUI {
//    private final AwardController awardController;
//
//    public AwardUI(AwardController awardController) {
//        this.awardController = awardController;
//    }
//
//    public void mainAwardUI() {
//        int choice;
//        do {
//            this.awardController.printAllAwards();
//            awardMenu();
//            Scanner keyboard = new Scanner(System.in);
//            System.out.println("Your choice:");
//            choice = keyboard.nextInt();
//            if (choice == 1) {
//                addAnAward();
//            }
//            if (choice == 2) {
//                deleteAnAward();
//            }
//            if (choice == 3) {
//                awardController.deleteAllAwards();
//            }
//            if (choice == 4) {
//                updateAnAward();
//            }
//        } while (choice > 0 && choice < 5);
//    }
//
//    public static void awardMenu() {
//        System.out.println("What do you want to do?");
//        System.out.println("1. Add an award");
//        System.out.println("2. Delete an award");
//        System.out.println("3. Delete all awards");
//        System.out.println("4. Update an award");
//        System.out.println("5. Exit");
//    }
//
//    public void addAnAward() {
//        Scanner keyboard = new Scanner(System.in);
//
//        System.out.println("Enter type of award (Oscar, GoldenGlobe, PalmeDor):");
//        String type = keyboard.next();
//
//        System.out.println("Enter ID for award:");
//        String id = keyboard.next();
//
//        System.out.print("Enter category (");
//        for (String category : AwardFactory.getAwardCategories()) {
//            System.out.print(category + " ");
//        }
//        System.out.print("):");
//        System.out.println();
//        String category = keyboard.next();
//
//        this.awardController.addAward(type, id, category);
//    }
//
//    public void deleteAnAward() {
//        this.awardController.printAllAwards();
//        System.out.println("Enter ID of the award you want to delete:");
//        Scanner keyboard = new Scanner(System.in);
//        String id = keyboard.next();
//        this.awardController.deleteAward(id);
//    }
//
//    public void updateAnAward() {
//        this.awardController.printAllAwards();
//        System.out.println("Enter ID of the award you want to update:");
//        Scanner keyboard = new Scanner(System.in);
//        String id = keyboard.next();
//        updateAward(id);
//    }
//
//    public void updateAward(String awardId) {
//        Award awardToUpdate = awardController.findAwardById(awardId);
//        if (awardToUpdate != null) {
//            System.out.println("What do you want to do?");
//            System.out.println("1. Update category");
//            System.out.println("2. Nothing");
//            System.out.println("Enter your choice:");
//            Scanner keyboard = new Scanner(System.in);
//            int choice = keyboard.nextInt();
//            if (choice == 1) {
//                System.out.print("Enter new category (");
//                for (String category : AwardFactory.getAwardCategories()) {
//                    System.out.print(category + " ");
//                }
//                System.out.print("):");
//                System.out.println();
//                String category = keyboard.next();
//                awardController.updateAwardCategory(awardToUpdate, category);
//            }
//        }
//    }
//}
