package miniproject_dsa;

import java.util.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.PriorityQueue;
//import java.util.Scanner;
//import java.util.Stack;
//import java.util.Comparator;

class Book {
	String isbn;
	String name;
	String author;
	String genre;
	double rating;
	double price;
	int purchaseCount;

	public double getRating() {
		return rating;
	}

	public double getPrice() {
		return price;
	}

	public Book(String isbn, String name, String author, String genre, double rating, double price) {
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.rating = rating;
		this.price = price;
		this.purchaseCount = 0;
	}

	@Override
	public String toString() {
		return String.format("%-10s %-25s %-20s %-15s %-8.1f $%-8.2f", isbn, name, author, genre, rating, price);
	}

}

class Customer {
	String username;
	String password;
	String email;
	ArrayList<Book> cart;
	ArrayList<Book> wishlist;
	Stack<Book> purchaseHistory;

	public Customer(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.cart = new ArrayList<>();
		this.wishlist = new ArrayList<>();
		this.purchaseHistory = new Stack<>();
	}
}

class BookStore {
	ArrayList<Book> books;
	HashMap<String, Customer> customers;
	Scanner scanner;
	Customer currentUser;

	List<Book> cart;
	List<Book> wishlist;

	public BookStore() {
		books = new ArrayList<>();
		customers = new HashMap<>();
		scanner = new Scanner(System.in);

		cart = new LinkedList<>();
		wishlist = new LinkedList<>();
		seedData();
	}

	private void seedData() {
		// Seed some books for demonstration purposes
		books.add(new Book("0001", "Java Programming", "Ziva Sarav", "Education", 4.5, 29.99));
		books.add(new Book("0002", "Advanced Java", "Bailey Smith", "Education", 4.7, 35.99));
		books.add(new Book("0003", "Learning Python", "Ram Charan", "Education", 4.3, 31.99));
		books.add(new Book("0004", "The Great Adventure", "John Smith", "Fiction", 9.2, 399.99));
		books.add(new Book("0005", "Python for Beginners", "Alice Johnson", "Programming", 8.7, 249.99));
		books.add(new Book("0006", "The Silent Witness", "Emily Davis", "Mystery", 8.9, 499.99));
		books.add(new Book("0007", "The Mystery of the Lost Key", "James Taylor", "Mystery", 9.0, 450.99));
		books.add(new Book("0008", "Learning JavaScript", "Michael Brown", "Programming", 8.5, 349.99));
		books.add(new Book("0009", "Under the Stars", "David Wilson", "Fiction", 8.3, 299.99));
		books.add(new Book("0010", "Robots and Us", "Richard Lee", "Sci-Fi", 9.4, 599.99));
		books.add(new Book("0012", "Into the Unknown", "Sophia Garcia", "Sci-Fi", 9.0, 479.99));
		books.add(new Book("0012", "React and Redux", "James Taylor", "Programming", 8.4, 399.99));
		books.add(new Book("0013", "The Art of War", "Sara Martinez", "History", 8.8, 459.99));
		books.add(new Book("0014", "Python for Data Science", "Olivia Thomas", "Technology", 9.1, 379.99));
		books.add(new Book("0015", "The Power of Habit", "Charles Duhigg", "Self-Help", 9.7, 799.99));
		books.add(new Book("0016", "Java for Beginners", "Liam Anderson", "Programming", 8.6, 289.99));
		books.add(new Book("0017", "The Last Dance", "Sophia Garcia", "Fiction", 9.3, 429.99));
		books.add(new Book("0018", "The C++ Journey", "Lucas King", "Programming", 8.8, 359.99));
		books.add(new Book("0019", "The Artificial Mind", "Ziva Sarav", "Sci-Fi", 9.8, 899.99));
		books.add(new Book("0020", "Advanced Python Programming", "Alice Johnson", "Programming", 9.0, 419.99));
		books.add(new Book("0021", "Blockchain Revolution", "Daniel Scott", "Technology", 8.5, 469.99));
		books.add(new Book("0022", "Data Science for All", "Bailey Smith", "Technology", 8.7, 329.99));
		books.add(new Book("0023", "Cloud Computing Essentials", "Zoe Baker", "Technology", 8.9, 389.99));
		books.add(new Book("0024", "Web Development with Node.js", "Ethan White", "Programming", 8.6, 299.99));
		books.add(new Book("0025", "The Deep Learning Code", "Ian Goodfellow", "Technology", 9.4, 549.99));
		books.add(new Book("0026", "JavaScript for Modern Web", "Douglas Crockford", "Programming", 9.0, 249.99));
		books.add(new Book("0027", "SQL for Dummies", "Ben Clark", "Technology", 8.7, 329.99));
		books.add(new Book("0028", "The Power of Now", "Eckhart Tolle", "Self-Help", 9.2, 389.99));
		books.add(new Book("0029", "Artificial Intelligence in Practice", "Francois Chollet", "Technology", 9.3, 529.99));

		Customer customer1 = new Customer("Siddhi", "sid123", "sid123@gmail.com");
		Customer customer2 = new Customer("Srushti", "sru456", "sru456@gmail.com");
		Customer customer3 = new Customer("Arya", "arya89", "arya89@gmail.com");
		Customer customer4 = new Customer("Feyoni", "feo789", "feo789@gmail.com");

		customers.put(customer1.username, customer1);
		customers.put(customer2.username, customer2);
		customers.put(customer3.username, customer3);
		customers.put(customer4.username, customer4);
	}

	class BookRatingComparator implements Comparator<Book> {
		@Override
		public int compare(Book book1, Book book2) {
			return Double.compare(book2.getRating(), book1.getRating()); // Descending order (highest rating first)
		}
	}

	class BookPriceComparator implements Comparator<Book> {
		@Override
		public int compare(Book book1, Book book2) {
			return Double.compare(book1.getPrice(), book2.getPrice()); // Ascending order (lowest price first)
		}
	}

	public void showMainMenu() {
		while (true) {
			System.out.println("\nWelcome to Online Book Store");
			System.out.println("1. User");
			System.out.println("2. Admin");
			System.out.println("3. Browse Books");
			System.out.println("4. Exit");
			System.out.print("Choose an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1 -> userMenu();
			case 2 -> adminLogin();
			case 3 -> browseBooks(false); // Guest browsing
			case 4 -> {
				System.out.println("Exiting...");
				return;
			}
			default -> System.out.println("Invalid choice. Try again.");
			}
		}
	}

	private void userMenu() {
		System.out.println("\n1. Login");
		System.out.println("2. Sign Up(Create account)");
		System.out.print("Choose an option: ");
		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		if (choice == 1)
			loginUser();
		else if (choice == 2)
			registerUser();
		else
			System.out.println("Invalid choice.");
	}

	private void loginUser() {
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		if (customers.containsKey(username) && customers.get(username).password.equals(password)) {
			currentUser = customers.get(username);
			userDashboard();
		} else {
			System.out.println("Invalid credentials.");
		}
	}

	private void registerUser() {
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.nextLine();

		if (customers.containsKey(username)) {
			System.out.println("Username already exists.");
		} else {
			Customer newCustomer = new Customer(username, password, email);
			customers.put(username, newCustomer);
			System.out.println("Registration successful. Please login.");
		}
	}

	private void userDashboard() {
		while (true) {
			System.out.println("\nUser Dashboard");
			System.out.println("1. Browse Books");
			System.out.println("2. View Cart");
			System.out.println("3. View Wishlist");
			System.out.println("4. View Order History");
			System.out.println("5. Logout");
			System.out.print("Choose an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1 -> browseBooks(true);
			case 2 -> viewCart();
			case 3 -> viewWishlist();
			case 4 -> viewOrderHistory();
			case 5 -> {
				currentUser = null;
				System.out.println("Logged out.");
				return;
			}
			default -> System.out.println("Invalid choice. Try again.");
			}
		}
	}

	private void adminDashboard() {
		while (true) {
			System.out.println("\nAdmin Dashboard");
			System.out.println("1. Manage Books");
			System.out.println("2. View Purchase History");
			System.out.println("3. Manage Users");
			System.out.println("4. Logout");
			System.out.print("Choose an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1 -> manageBooks();
			case 2 -> viewPurchaseHistory();
			case 3 -> manageUsers();
			case 4 -> {
				System.out.println("Admin logged out.");
				return;
			}
			default -> System.out.println("Invalid choice. Try again.");
			}
		}
	}

	private void manageBooks() {
		System.out.println("\n1. Add Book");
		System.out.println("2. Edit Book");
		System.out.println("3. Remove Book");
		System.out.println("4. Back");
		System.out.print("Choose an option: ");
		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		switch (choice) {
		case 1 -> addBook();
		case 2 -> editBook();
		case 3 -> removeBook();
		case 4 -> System.out.println("Back to Admin Dashboard.");
		default -> System.out.println("Invalid choice.");
		}
	}

	private void addBook() {
		System.out.print("Enter ISBN: ");
		String isbn = scanner.nextLine();
		// Check if book already exists
		boolean exists = false;
		for (Book book : books) {
			if (book.isbn.equals(isbn)) {
				exists = true;
				break;
			}
		}

		if (exists) {
			System.out.println("Book with this ISBN already exists.");
			return;
		}

		System.out.print("Enter Book Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Author: ");
		String author = scanner.nextLine();
		System.out.print("Enter Genre: ");
		String genre = scanner.nextLine();
		System.out.print("Enter Rating: ");
		double rating = scanner.nextDouble();
		System.out.print("Enter Price: ");
		double price = scanner.nextDouble();
		scanner.nextLine(); // Consume newline

		Book newBook = new Book(isbn, name, author, genre, rating, price);
		books.add(newBook);
		System.out.println("Book added successfully.");
	}

	private void editBook() {
		System.out.print("Enter ISBN of the book to edit: ");
		String isbn = scanner.nextLine();
		boolean found = false;

		// Iterate through the books list
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			if (book.isbn.equals(isbn)) {
				found = true;
				System.out.println("Current Book Details: " + book);

				System.out.print("Enter new Name (leave blank to keep the current name): ");
				String name = scanner.nextLine();
				if (!name.isEmpty())
					book.name = name;

				System.out.print("Enter new Author (leave blank to keep the current author): ");
				String author = scanner.nextLine();
				if (!author.isEmpty())
					book.author = author;

				System.out.print("Enter new Genre (leave blank to keep the current genre): ");
				String genre = scanner.nextLine();
				if (!genre.isEmpty())
					book.genre = genre;

				System.out.print("Enter new Rating (leave blank to keep the current rating): ");
				String ratingInput = scanner.nextLine();
				if (!ratingInput.isEmpty())
					book.rating = Double.parseDouble(ratingInput);

				System.out.print("Enter new Price (leave blank to keep the current price): ");
				String priceInput = scanner.nextLine();
				if (!priceInput.isEmpty())
					book.price = Double.parseDouble(priceInput);

				System.out.println("Book details updated successfully.");
				break;
			}
		}

		if (!found) {
			System.out.println("Book with the given ISBN not found.");
		}
	}

	private void removeBook() {
		System.out.print("Enter ISBN of the book to remove: ");
		String isbn = scanner.nextLine();
		boolean removed = false;

		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).isbn.equals(isbn)) {
				books.remove(i);
				removed = true;
				System.out.println("Book removed successfully.");
				break;
			}
		}

		if (!removed) {
			System.out.println("No book found with the given ISBN.");
		}
	}

	private void viewPurchaseHistory() {
		System.out.print("Enter username of the user whose purchase history you want to view: ");
		String username = scanner.nextLine();

		Customer customer = customers.get(username);
		if (customer == null) {
			System.out.println("No user found with the given username.");
			return;
		}

		if (customer.purchaseHistory.isEmpty()) {
			System.out.println("No purchase history available for this user.");
		} else {
			System.out.println("Purchase History for " + username + ":");
			for (Book book : customer.purchaseHistory) {
				System.out.println(book);
			}
		}
	}

	private void manageUsers() {
		System.out.println("\n1. Add User");
		System.out.println("2. Edit User");
		System.out.println("3. Remove User");
		System.out.println("4. Back");
		System.out.print("Choose an option: ");
		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		switch (choice) {
		case 1 -> addUser();
		case 2 -> editUser();
		case 3 -> removeUser();
		case 4 -> System.out.println("Back to Admin Dashboard.");
		default -> System.out.println("Invalid choice.");
		}
	}

	private void addUser() {
		System.out.print("Enter Username: ");
		String username = scanner.nextLine();
		if (customers.containsKey(username)) {
			System.out.println("User with this username already exists.");
			return;
		}

		System.out.print("Enter Password: ");
		String password = scanner.nextLine();
		System.out.print("Enter Email: ");
		String email = scanner.nextLine();

		Customer newCustomer = new Customer(username, password, email);
		customers.put(username, newCustomer);
		System.out.println("User added successfully.");
	}

	private void editUser() {
		System.out.print("Enter Username of the user to edit: ");
		String username = scanner.nextLine();

		Customer customerToEdit = customers.get(username);
		if (customerToEdit == null) {
			System.out.println("No user found with the given username.");
			return;
		}

		System.out.println("Editing User: " + username);
		System.out.print("Enter new Password (leave empty to keep unchanged): ");
		String password = scanner.nextLine();
		if (!password.isEmpty())
			customerToEdit.password = password;

		System.out.print("Enter new Email (leave empty to keep unchanged): ");
		String email = scanner.nextLine();
		if (!email.isEmpty())
			customerToEdit.email = email;

		System.out.println("User details updated successfully.");
	}

	private void removeUser() {
		System.out.print("Enter Username of the user to remove: ");
		String username = scanner.nextLine();

		if (!customers.containsKey(username)) {
			System.out.println("No user found with the given username.");
			return;
		}

		customers.remove(username);
		System.out.println("User removed successfully.");
	}

	private void adminLogin() {
		System.out.print("Admin Username: ");
		String username = scanner.nextLine();
		System.out.print("Admin Password: ");
		String password = scanner.nextLine();

		if (username.equals("admin") && password.equals("admin123")) {
			System.out.println("Admin login successful.");
			adminDashboard();
		} else {
			System.out.println("Invalid admin credentials.");
		}
	}
	
	private void browseBooks(boolean loggedIn) {
	    System.out.println("\nAvailable Books:");

	    // Display sorting options
	    System.out.println("1. Sort by Rating (Highest First)");
	    System.out.println("2. Sort by Price (Lowest First)");

	    System.out.print("Choose sorting option: ");
	    int sortChoice = scanner.nextInt();
	    scanner.nextLine(); // Consume newline

	    // Create a copy of the books list to sort
	    ArrayList<Book> sortedBooks = new ArrayList<>(books);

	    // Sort based on the user's choice
	    if (sortChoice == 1) {
	        // Sort by rating in descending order
	        //Collections.sort(sortedBooks, new BookRatingComparator().reversed());
	        Collections.sort(sortedBooks, new BookRatingComparator());
	    } else if (sortChoice == 2) {
	        // Sort by price in ascending order
	        Collections.sort(sortedBooks, new BookPriceComparator());
	    } else {
	        System.out.println("Invalid sorting option, defaulting to Rating (Highest First).");
	        Collections.sort(sortedBooks, new BookRatingComparator());
	    }

	    // Display sorted books
	    for (Book book : sortedBooks) {
	        System.out.println(book);
	    }

	    if (!loggedIn) {
	        System.out.println("\nPlease login or register to add books to cart or wishlist.");
	        return;
	    }

	    System.out.println("\nBrowse Options:");
	    System.out.println("1. Search by Author");
	    System.out.println("2. Search by Genre");
	    System.out.println("3. Search by Rating");
	    System.out.println("4. Enter the ISBN Number of the book you wish to add to cart or buy now or add to wishlist");
	    System.out.println("5. Go back to dashboard");
	    System.out.print("Choose an option: ");
	    int choice = scanner.nextInt();
	    scanner.nextLine(); // Consume newline

	    while (choice != 5) {
	        switch (choice) {
	            case 1 -> searchByAuthor();
	            case 2 -> searchByGenre();
	            case 3 -> searchByRating();
	            case 4 -> searchByISBN();
	            default -> System.out.println("Invalid choice.");
	        }

	        System.out.println("\nBrowse Options:");
	        System.out.println("1. Search by Author");
	        System.out.println("2. Search by Genre");
	        System.out.println("3. Search by Rating");
	        System.out.println("4. Enter the ISBN Number of the book you wish to add to cart or buy now");
	        System.out.println("5. Go back to dashboard");
	        System.out.print("Choose an option: ");
	        choice = scanner.nextInt();
	        scanner.nextLine(); // Consume newline
	    }

	    System.out.println("Going back to dashboard...");
	}


	private void searchByAuthor() {
	    System.out.print("Enter author name: ");
	    String author = scanner.nextLine();
	    boolean found = false;

	    for (Book book : books) {
	        if (book.author.equalsIgnoreCase(author)) {
	            System.out.println(book);
	            found = true;
	        }
	    }

	    if (!found) {
	        System.out.println("No books found by author: " + author);
	    }
	}


//	private void searchByGenre() {
//		System.out.print("Enter genre: ");
//		String genre = scanner.nextLine();
//		books.stream().filter(book -> book.genre.equalsIgnoreCase(genre)).forEach(System.out::println);
//	}
	
	private void searchByGenre() {
	    System.out.print("Enter genre: ");
	    String genre = scanner.nextLine();
	    boolean found = false;

	    for (Book book : books) {
	        if (book.genre.equalsIgnoreCase(genre)) {
	            System.out.println(book);
	            found = true;
	        }
	    }

	    if (!found) {
	        System.out.println("No books found in the genre: " + genre);
	    }
	}
	
	private void searchByRating() {
	    System.out.print("Enter minimum rating: ");
	    if (scanner.hasNextDouble()) {
	        double rating = scanner.nextDouble();
	        scanner.nextLine(); // Consume newline
	        boolean found = false;

	        for (Book book : books) {
	            if (book.rating >= rating) {
	                System.out.println(book);
	                found = true;
	            }
	        }

	        if (!found) {
	            System.out.println("No books found with a rating of " + rating + " or higher.");
	        }
	    } else {
	        System.out.println("Invalid input. Please enter a numerical rating.");
	        scanner.next(); // Clear invalid input
	    }
	}

	// Search by ISBN with action options
	private void searchByISBN() {
	    System.out.print("Enter ISBN: ");
	    String isbn = scanner.nextLine();
	    boolean found = false;

	    for (Book book : books) {
	        if (book.isbn.equals(isbn)) {
	            System.out.println(book);
	            displayBookActions(book);
	            found = true;
	            break; // Exit loop once the book is found
	        }
	    }

	    if (!found) {
	        System.out.println("No book found with ISBN: " + isbn);
	    }
	}

	// Display book actions for purchasing, adding to cart, or adding to wishlist
	private void displayBookActions(Book book) {
	    System.out.println("1. Buy Now");
	    System.out.println("2. Add to Cart");
	    System.out.println("3. Add to WishList");
	    System.out.print("Choose an option: ");

	    if (scanner.hasNextInt()) {
	        int choice = scanner.nextInt();
	        scanner.nextLine(); // Consume newline
	        switch (choice) {
	            case 1 -> buyBook(book);
	            case 2 -> addToCart(book);
	            case 3 -> addToWishlist(book);
	            default -> System.out.println("Invalid choice.");
	        }
	    } else {
	        System.out.println("Invalid input. Please enter a valid option.");
	        scanner.next(); // Clear invalid input
	    }
	}

	private void buyBook(Book book) {
	    if (!currentUser.purchaseHistory.contains(book)) {
	        currentUser.purchaseHistory.push(book);
	        book.purchaseCount++;
	        System.out.println("Book purchased successfully.");
	    } else {
	        System.out.println("This book has already been purchased.");
	    }
	}

	private void addToCart(Book book) {
		if (!currentUser.cart.contains(book)) {
			currentUser.cart.add(book);
			System.out.println("Book added to cart.");
		} else {
			System.out.println("This book is already in your cart.");
		}
	}

	public void removeFromCart(Book book) {
	    boolean removed = currentUser.cart.remove(book); // Removes the book from the user's cart
	    if (removed) {
	        System.out.println("Book removed from cart: " + book);
	    } else {
	        System.out.println("Book not found in cart.");
	    }
	}

	private void viewCart() {
		System.out.println("\nYour Cart:");

		// Check if the cart is empty before printing
		if (currentUser.cart.isEmpty()) {
			System.out.println("Your cart is empty.");
			return; // Exit the method if the cart is empty
		} else {
			// Print the books in the cart if it's not empty
			currentUser.cart.forEach(System.out::println);
		}

		System.out.println("1. Remove Book from Cart");
		System.out.println("2. Buy Books");
		System.out.println("3. Back");
		System.out.print("Choose an option: ");

		if (scanner.hasNextInt()) {
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline left-over

			switch (choice) {
			case 1:
			    System.out.print("Enter ISBN of book to remove: ");
			    String isbnToRemove = scanner.nextLine();
			    boolean removed = currentUser.cart.removeIf(book -> book.isbn.equals(isbnToRemove));
			    System.out.println(removed ? "Book removed from cart." : "Book not found in the cart.");
			    break;



			case 2:
				System.out.println("Proceeding to buy the books...");
				// Process each book in the cart to buy them
				for (Book book : currentUser.cart) {
					System.out.println("Purchasing: " + book); // Debug print
					buyBook(book); // Call the buyBook method for each book in the cart
				}
				currentUser.cart.clear(); // Clear the cart after purchase
				break;

			case 3:
				System.out.println("Going back...");
				return; // Go back to the previous menu

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} else {
			System.out.println("Invalid input. Please enter a valid option.");
			scanner.nextLine(); // Clear invalid input buffer
		}
	}

	// Method to add a book to the wishlist
	public void addToWishlist(Book book) {
		currentUser.wishlist.add(book);
		System.out.println("Book added to wishlist: " + book);
	}

	// Method to remove a book from the wishlist
	public void removeFromWishlist(Book book) {
		currentUser.wishlist.remove(book);
		System.out.println("Book removed from wishlist: " + book);
	}

	private void viewWishlist() {
	    System.out.println("\nYour Wishlist:");
	    for (Book book : currentUser.wishlist) {
	        System.out.println(book);
	    }

	    System.out.println("1. Move Book to Cart");
	    System.out.println("2. Remove from Wishlist");
	    System.out.println("3. Back");
	    System.out.print("Choose an option: ");

	    if (scanner.hasNextInt()) {
	        int choice = scanner.nextInt();
	        scanner.nextLine(); // Consume newline

	        if (choice == 1) {
	            System.out.print("Enter ISBN of book to move to cart: ");
	            String isbn = scanner.nextLine();
	            Book bookToMove = null;

	            // Search for the book by ISBN in the wishlist
	            for (Book book : currentUser.wishlist) {
	                if (book.isbn.equals(isbn)) {
	                    bookToMove = book;
	                    break;
	                }
	            }

	            if (bookToMove != null) {
	                if (!currentUser.cart.contains(bookToMove)) {
	                    currentUser.cart.add(bookToMove);
	                    currentUser.wishlist.remove(bookToMove);
	                    System.out.println("Book moved to cart.");
	                } else {
	                    System.out.println("This book is already in your cart.");
	                }
	            } else {
	                System.out.println("Book not found in wishlist.");
	            }

	        } else if (choice == 2) {
	            System.out.print("Enter ISBN of book to remove from wishlist: ");
	            String isbn = scanner.nextLine();
	            boolean removed = false;

	            // Remove the book by ISBN from the wishlist
	            for (int i = 0; i < currentUser.wishlist.size(); i++) {
	                if (currentUser.wishlist.get(i).isbn.equals(isbn)) {
	                    currentUser.wishlist.remove(i);
	                    removed = true;
	                    System.out.println("Book removed from wishlist.");
	                    break;
	                }
	            }

	            if (!removed) {
	                System.out.println("Book not found in wishlist.");
	            }

	        } else if (choice == 3) {
	            System.out.println("Going back...");
	            return;

	        } else {
	            System.out.println("Invalid choice. Please try again.");
	        }
	    } else {
	        System.out.println("Invalid input. Please enter a valid option.");
	        scanner.next(); // Clear invalid input
	    }
	}


	private void viewOrderHistory() {
		System.out.println("\nOrder History:");
		if (currentUser.purchaseHistory.isEmpty()) {
			System.out.println("No purchase history found.");
		} else {
			currentUser.purchaseHistory.forEach(System.out::println);
		}
	}
}

public class OnlineBookStore {
	public static void main(String[] args) {
		BookStore store = new BookStore();
		store.showMainMenu();
	}
}