import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        boolean isRunning = true;

        String[] brands = {"Dell", "HP", "Lenovo", "Apple", "Lenovo"};
        String[] models = {"Latitude 5420", "Elitebook 840 G3", "ThinkPad T490", "MacBook Air A1466", "X1 Yoga (3rd Gen)"};
        double[] prices = {250, 220, 350, 150, 335};
        int[] cartID = {1, 2, 3, 4, 5};
        int[] quantity = {8, 4, 3, 2, 9};
        int[] cartQTY = new int[brands.length];

        while (isRunning) {
            System.out.println("----------------------------------------");
            System.out.println("Welcome to the Refurbished Laptop Shop");
            System.out.println("----------------------------------------");
            System.out.println("Here is the Main Menu:");
            System.out.println("----------------------------------------");
            System.out.println("1. View Inventory");
            System.out.println("2. Search");
            System.out.println("3. Add to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Remove from Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.println("----------------------------------------");

            System.out.print("Enter a Choice (1-7): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("----------------------------------------");

            switch (choice) {
                case 1 -> viewInventory(brands, models, prices, cartID, quantity);
                case 2 -> searchInventory(cartID, prices, models, brands);
                case 3 -> addToCart(cartQTY, quantity, cartID, brands, models, prices);
                case 4 -> viewCart(cartQTY, quantity, cartID, brands, models, prices);
                case 5 -> removeItem(cartQTY, quantity, cartID, brands, models, prices);
                case 6 -> checkout(cartQTY, quantity, cartID, brands, models, prices);
                case 7 -> {
                    System.out.println("Goodbye!");
                    isRunning = false;
                }
                default -> System.out.println("Invalid Choice! Try Again!");
            }
        }
    }

    static void viewInventory(String[] brands, String[] models, double[] prices, int[] cartID, int[] quantity) {
        System.out.println("------ INVENTORY ------");
        System.out.printf("%-5s %-12s %-22s %12s %6s%n",
                "ID", "Brand", "Model", "Price", "Qty");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < brands.length; i++) {
            System.out.printf("%-5d %-12s %-22s %12s %6d%n",
                    cartID[i], brands[i], models[i], String.format("$%.2f", prices[i]), quantity[i]);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.println("Press Enter to return to Main Menu...");
        scanner.nextLine();
    }

    static void searchInventory(int[] cartID, double[] prices, String[] models, String[] brands) {
        String Search;
        int idNum;
        String modelNum;
        String brandNum;
        double priceNum = 0;

        System.out.print("What do you want to Search? (ID, Model, Brand, or Price)?: ");
        Search = scanner.nextLine();

        if (Search.equalsIgnoreCase("ID")) {
            System.out.print("Enter the ID Number: ");
            idNum = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n----------------------------------------");

            for (int i = 0; i < cartID.length; i++) {
                if (idNum == cartID[i]) {
                    System.out.println("Found!");
                    System.out.printf("%2d. %-12s %-22s %10s%n",
                            cartID[i], brands[i], models[i], String.format("$%.2f", prices[i]));
                }
            }
        } else if (Search.equalsIgnoreCase("Model")) {
            System.out.print("Enter the Model: ");
            modelNum = scanner.nextLine();
            System.out.println("\n----------------------------------------");

            for (int i = 0; i < models.length; i++) {
                if (modelNum.equalsIgnoreCase(models[i])) {
                    System.out.println("Found!");
                    System.out.printf("%2d. %-12s %-22s %10s%n",
                            cartID[i], brands[i], models[i], String.format("$%.2f", prices[i]));

                }
            }
        } else if (Search.equalsIgnoreCase("Brand")) {
            System.out.print("Enter the Brand: ");
            brandNum = scanner.nextLine();
            System.out.println("\n----------------------------------------");

            for (int i = 0; i < brands.length; i++) {
                if (brandNum.equalsIgnoreCase(brands[i])) {
                    System.out.println("Found!");
                    System.out.printf("%2d. %-12s %-22s %10s%n",
                            cartID[i], brands[i], models[i], String.format("$%.2f", prices[i]));

                }
            }
        } else if (Search.equalsIgnoreCase("Price")) {
            System.out.print("Enter a Price: ");
            priceNum = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("\n----------------------------------------");

            for (int i = 0; i < prices.length; i++) {
                if (priceNum == prices[i]) {
                    System.out.println("Found!");
                    System.out.printf("%2d. %-12s %-22s %10s%n",
                            cartID[i], brands[i], models[i], String.format("$%.2f", prices[i]));
                }
            }
        } else {
            System.out.println("Not Found! Try Again");
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("Press Enter to return to Main Menu...");
        scanner.nextLine();
    }

    static void addToCart(int[] cartQTY, int[] quantity, int[] cartID,
                          String[] brands, String[] models, double[] prices) {

        System.out.print("What Item do you want to add? (Enter by ID Number): ");
        int addItem = scanner.nextInt();
        scanner.nextLine();

        int n = cartID.length;
        if (addItem < 1 || addItem > n) {
            System.out.println("Invalid ID. Please enter a number between 1 and " + n + ".");
            System.out.println("Press Enter to return to Main Menu...");
            scanner.nextLine();
            return;
        }

        int idx = addItem - 1;

        if (cartID[idx] != addItem) {
            System.out.println("ID not found.");
            System.out.println("Press Enter to return to Main Menu...");
            scanner.nextLine();
            return;
        }

        int available = quantity[idx] - cartQTY[idx];
        if (available <= 0) {
            System.out.println("Sorry, no stock left for that item.");
            System.out.println("Press Enter to return to Main Menu...");
            scanner.nextLine();
            return;
        }

        System.out.print("How many do you want (1-" + available + "): ");
        int toAdd = scanner.nextInt();
        scanner.nextLine();

        if (toAdd < 1) {
            System.out.println("Quantity must be at least 1.");
            System.out.println("Press Enter to return to Main Menu...");
            scanner.nextLine();
            return;
        }
        if (toAdd > available) {
            System.out.println("Only " + available + " available. Adding " + available + ".");
            toAdd = available;
        }

        cartQTY[idx] += toAdd;

        System.out.println("Added!");
        System.out.printf("%2d. %-5s %-24s %s | Added: %-2d In cart: %-2d Stock left: %-2d%n",
                cartID[idx],
                brands[idx],
                models[idx],
                String.format("$%.2f", prices[idx]),
                toAdd,
                cartQTY[idx],
                (quantity[idx] - cartQTY[idx]));

        System.out.println("Press Enter to return to Main Menu...");
        scanner.nextLine();
    }

    static void viewCart(int[] cartQTY, int[] quantity, int[] cartID,
                         String[] brands, String[] models, double[] prices) {

        for (int i = 0; i < cartQTY.length; i++) {
            System.out.println("You Have " + cartQTY[i] + " " + brands[i] + " " + models[i]);
        }

    }

    static void removeItem(int[] cartQTY, int[] quantity, int[] cartID,
                           String[] brands, String[] models, double[] prices) {

        System.out.print("Which Item do You want to remove (by ID)? (1-" + cartID.length + "): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id < 1 || id > cartID.length) {
            System.out.println("Invalid ID. Please enter a number between 1 and " + cartID.length + ".");
            System.out.print("Press Enter to return to Main Menu...");
            scanner.nextLine();
            return;
        }
        int idx = id - 1;

        if (cartQTY[idx] == 0) {
            System.out.println("This item isn't in your cart");
            System.out.print("Press Enter to Return to the Main Menu...");
            scanner.nextLine();
        }

        System.out.print("How many items to remove? (1-" + cartQTY[idx] + " , or 0 = remove all: ");
        int remove = scanner.nextInt();
        scanner.nextLine();

        if (remove == 0) {
            remove = cartQTY[idx];
        } else if (remove < 1) {
            System.out.println("Invalid! Quantity must be greater than 1");
            System.out.print("Press Enter to return to the Main Menu...");
            scanner.nextLine();
            return;
        }

        cartQTY[idx] -= remove;
        System.out.println("Removed!");
        System.out.printf("%2d. %-12s %-22s %10s%n | Removed: %-2d In cart: %-2d Stock left: %-2d%n",
                cartID[idx],
                brands[idx],
                models[idx],
                String.format("$%.2f", prices[idx]),
                remove,
                cartQTY[idx],
                (quantity[idx] - cartQTY[idx]));

        System.out.println("Press Enter to return to Main Menu...");
        scanner.nextLine();
    }

    static void checkout(int[] cartQTY, int[] quantity, int[] cartID,
                         String[] brands, String[] models, double[] prices) {

        System.out.println("------ RECEIPT ------");
        System.out.printf("%-5s %-12s %-22s %5s %10s %10s%n",
                "ID", "Brand", "Model", "Qty", "Unit", "Line");
        System.out.println("---------------------------------------------------------------------");

        boolean any = false;
        double subtotal = 0.0;

        // loop all items; print only those with qty > 0
        for (int i = 0; i < brands.length; i++) {
            int qty = cartQTY[i];
            if (qty > 0) {
                any = true;
                double unit = prices[i];
                double line = unit * qty;
                subtotal += line;

                System.out.printf("%2d.  %-12s %-22s %5d %10s %10s%n",
                        cartID[i],
                        brands[i],
                        models[i],
                        qty,
                        String.format("$%.2f", unit),
                        String.format("$%.2f", line));
            }
        }

        if (!any) {
            System.out.println("Your cart is empty.");
            System.out.println("Press Enter to return to Main Menu...");
            scanner.nextLine();
            return;
        }

        System.out.println("---------------------------------------------------------------------");
    }
}


