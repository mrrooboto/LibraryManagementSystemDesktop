package com.LMS.Benjamin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Library {
    //CONSTANTS
    private final int FRAME_WIDTH = 1280;
    private final int FRAME_HEIGHT = 800;
    private final int WELCOME_MSG_WIDTH = 400;
    private final int WELCOME_MSG_HEIGHT = 300;
    private final Scanner sc = new Scanner(System.in);
    private final Book book = new Book();
    protected List<Book> availableBooks = new ArrayList<>();
    protected List<Book> unavailableBooks = new ArrayList<>();
    private final ImageIcon AppImage = new ImageIcon("C:\\Users\\bennj\\IDEA_PROJECTS\\ChatGPTHelpAssignments\\src\\LIbraryManagementSystemDesktopApp\\src\\main\\java\\com\\LMS\\Benjamin\\library.png");
    private JFrame mainFrame;
    private JDialog welcomeMsg; //First window message
    private JPanel leftSide; //Left panel of MainFrame
    private JPanel rightSide; //Right panel of MainFrame
    private JButton continueButton; //Continue button for welcomeMsg
    private JCheckBox dontShowAgain; //Don't show again welcomeMsg checkbox
    //Left panel buttons
    private JButton addBook; // Button for leftSide panel
    private JButton removeBook; //Button for leftSide panel
    private JButton showAvailableBooks; //Button for leftSide panel
    private JButton checkoutBook; //Button for leftSide panel
    private JButton returnBook; //Button for leftSide panel
    private JButton exitSystem; //Button for leftSide panel
    private JPanel bookImgPanel; //Panel inside rightSide Panel for initial image (question-markPic)
    private JLabel imgLabel; //Label inside the bookImgPanel in rightSide Panel that holds the picture
    private JPanel bookInformation; //Panel inside rightSide Panel that holds textFields which can be filled to add book information
    private JPopupMenu imgPopUpMenu; //Popup menu inside rightSide Panel which activates when right mouse button is clicked to add a picture
    private JMenuItem addImageMenuItem;// imgPopUpMenu option to add picture
    private ImageIcon firstImage;// starting questionMark picture in imgLabel

    public Library() {
        setupGUI();
    }

    public void setupGUI() {
        setupMainFrame();
        setupLeftPanel();
        setupRightPanel();
        setAddBookPanel();
        setupWelcomeDialog();
        setupWelcomeMessage();
        setupEvents();
    }
     private void setupMainFrame() {
        mainFrame = new JFrame("LMS");
        mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setIconImage(AppImage.getImage());
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
    }
    private void setupWelcomeDialog() {
        welcomeMsg = new JDialog(mainFrame, "Welcome");
        welcomeMsg.setSize(WELCOME_MSG_WIDTH, WELCOME_MSG_HEIGHT);
        welcomeMsg.setLocationRelativeTo(null);
        welcomeMsg.setIconImage(AppImage.getImage());
        welcomeMsg.setLayout(null);
        welcomeMsg.setVisible(true);
        welcomeMsg.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    private void setupLeftPanel() {
        //Initialized buttons
        addBook = new JButton("Add book");
        removeBook = new JButton("Remove book");
        showAvailableBooks = new JButton("Show available books");
        checkoutBook = new JButton("Check out book");
        returnBook = new JButton("Return book");
        exitSystem = new JButton("Quit program");
        leftSide = new JPanel();
        addBook.setBounds(220,250,200,40);
        removeBook.setBounds(220,300,200,40);
        showAvailableBooks.setBounds(220,350,200,40);
        checkoutBook.setBounds(220,400,200,40);
        returnBook.setBounds(220,450,200,40);
        exitSystem.setBounds(220,500,200,40);
        leftSide.setBounds(0,0,620,800);
        leftSide.setLayout(null);
        leftSide.setBackground(Color.lightGray);
        leftSide.add(addBook);leftSide.add(removeBook);leftSide.add(showAvailableBooks);leftSide.add(checkoutBook);leftSide.add(returnBook);leftSide.add(exitSystem);
        mainFrame.add(leftSide);
    }
    private void setupRightPanel() {
        rightSide = new JPanel();
        rightSide.setLayout(null);
        rightSide.setBounds(640,0,640,800);
        rightSide.setBackground(Color.gray);
        mainFrame.add(rightSide);
    }
    private void setupWelcomeMessage() {
        JLabel welcomeText = new JLabel("Welcome to the Library Management System application !"); // First line of text in popup
        JLabel instructions = new JLabel("<html>This application allows you to manage your own Library.<br/> You can add books, remove them, check out book, show the ones that are available and return the book that has been checked out.</html>"); // The instructions below the text
        continueButton = new JButton("Continue"); // Proceed button
        dontShowAgain = new JCheckBox("Don't show this message again");

        welcomeText.setBounds(35,20,350,30); // Where the first line of text is placed
        instructions.setBounds(20, 10, 350, 220);// Where the instructions are placed
        continueButton.setBounds(150, 200, 90, 30); // Where the button is located
        dontShowAgain.setBounds(90, 170, 210,30); // Where the checkbox is
        instructions.setFont(new Font("serif",5,15)); // Font of the instructions

        welcomeMsg.add(welcomeText);welcomeMsg.add(instructions);welcomeMsg.add(continueButton);welcomeMsg.add(dontShowAgain);
        //Had to force repaint for some reason because the text doesn't show otherwise
        welcomeText.repaint();
        instructions.repaint();
        continueButton.repaint();
        dontShowAgain.repaint();
    }
    private void setAddBookPanel() {
        bookImgPanel = new JPanel(); //Top side rectangle
        firstImage = new ImageIcon("C:\\Users\\bennj\\IDEA_PROJECTS\\ChatGPTHelpAssignments\\src\\LIbraryManagementSystemDesktopApp\\src\\main\\java\\com\\LMS\\Benjamin\\emptyIMG.png");
        imgLabel = new JLabel(firstImage);
        bookInformation = new JPanel(); //TODO Bottom side rectangle where the book info is inputted
        imgPopUpMenu = new JPopupMenu(); //Popup that shows when top panel is clicked
        addImageMenuItem = new JMenuItem("Add image"); //MenuItem of imgPopUpMenu which adds selected image

        bookImgPanel.setLayout(null);bookInformation.setLayout(null);imgLabel.setLayout(null); //Layout of panels

        bookImgPanel.setBounds(0,0,640,380); //bookImg panel position
        imgLabel.setBounds(180,0,270,350); //imgLabel contains starting image which is QuestionMark
        bookInformation.setBounds(0,400,640,400); //bookInformation panel position

        bookInformation.setBackground(Color.lightGray);//Color of bottom side panel
        bookImgPanel.setBackground(Color.lightGray);//Color of top side panel

        imgPopUpMenu.add(addImageMenuItem);
        rightSide.add(bookImgPanel);//Added child panel to parent
        bookImgPanel.add(imgLabel);//Temporary label until a picture of a book is not added
        rightSide.add(bookInformation);//Added child panel to parent
    }
    private void setRemoveBookPanel() {

    }
    private void setShowAvailableBooksPanel() {

    }
    private void setCheckoutBookPanel() {

    }
    private void setReturnBookPanel() {

    }
    private void setupEvents() {
        dontShowAgain.addActionListener(e -> {
            if (dontShowAgain.isSelected()) { //If checkbox to not show the message again is selected, message won't appear again if the app is turned on
                mainFrame.remove(welcomeMsg);
            }
        });
        continueButton.addActionListener(e -> welcomeMsg.dispose()); //When continue button is clicked the JDialog(welcomeMsg) will disappear
        addImageMenuItem.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
                int returnedFile = fileChooser.showSaveDialog(null);
                if (returnedFile == JFileChooser.APPROVE_OPTION) {
                    File chosenFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage img = ImageIO.read(chosenFile.getAbsoluteFile());
                        Image dimg = img.getScaledInstance(imgLabel.getWidth(),imgLabel.getHeight(),BufferedImage.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(dimg);
                        imgLabel.setIcon(icon);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        });
        bookImgPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imgPopUpMenu.show(bookImgPanel, e.getX(), e.getY());
            }
        });
    }

    //TODO  Write a function to add a new book FINISHED
    public void addBook() {

        System.out.println("Enter the title of the book: ");
        do {
            book.setTitle(sc.nextLine());
            if (book.getTitle().isEmpty()) {
                System.out.println("Cannot be blank!");
                System.out.println("Enter the title of the book: ");
            }
        } while (book.getTitle().isEmpty());

        System.out.println("Enter the author of the book: ");
        do {
            book.setAuthor(sc.nextLine());
            if (book.getAuthor().isEmpty()) {
                System.out.println("Cannot be blank!");
                System.out.println("Enter the author of the book: ");
            }
        } while (book.getAuthor().isEmpty());

        System.out.println("Enter the ISBN: ");

        do {
            book.setISBN(sc.nextLine());
            if (book.getISBN().isEmpty() || book.getISBN().matches("[A-Za-z]+")) {
                System.out.println("Cannot be blank or contain characters! \nEnter the ISBN:");
            }
        } while (book.getISBN().isEmpty() || book.getISBN().matches("[A-Za-z]+"));

        System.out.println("Book added successfully! \n");
        Book newBook = new Book(book.getTitle(), book.getAuthor(), book.getISBN(), true);
        availableBooks.add(newBook);
        System.out.println();

        //function to add a book to arraylist and set availability to "true"
        //adding a book will require to enter a title, author and ISBN
    }

    //TODO  Function to remove a book FINISHED
    public void removeBook() {
        System.out.println("Enter the title of the book: ");
        do {
            book.setTitle(sc.nextLine());
            if (book.getTitle().isEmpty()) {
                System.out.println("Cannot be blank!");
                System.out.println("Enter the title of the book: ");
            }
        } while (book.getTitle().isEmpty());

        System.out.println("Enter the author of the book: ");
        do {
            book.setAuthor(sc.nextLine());
            if (book.getAuthor().isEmpty()) {
                System.out.println("Cannot be blank!");
                System.out.println("Enter the author of the book: ");
            }
        } while (book.getAuthor().isEmpty());

        System.out.println("Enter the ISBN: ");
        do {
            book.setISBN(sc.nextLine());
            if (book.getISBN().isEmpty() || book.getISBN().matches("[A-Za-z]")) {
                System.out.println("Cannot be blank or contain characters! \nEnter the ISBN: ");
            }
        } while (book.getISBN().isEmpty() || book.getISBN().matches("[A-Za-z]"));

        for (Book availableBook : availableBooks) {
            if (    availableBook.getTitle().equals(book.getTitle()) &&
                    availableBook.getAuthor().equals(book.getAuthor()) &&
                    availableBook.getISBN().equals(book.getISBN())) {
                availableBooks.remove(availableBook); // Remove the matching book from arrayList
                System.out.println("Book removed successfully! \n");
                break; //Exit loop once match is found
            } else {
                System.out.println("Wrong data entered!");
            }
        }

        //function to remove title, author and ISBN from lists also set availability to false
    }

    //TODO  Function to Display available books (All the books) FINISHED
    public void showAvailableBooks() {
        System.out.println("Available books: ");
        System.out.println();
        for (Book availableBook : availableBooks) {
            System.out.println("Title: " + availableBook.getTitle());
            System.out.println("Author: " + availableBook.getAuthor());
            System.out.println("ISBN: " + availableBook.getISBN());
            System.out.println();
        }

        if (availableBooks.isEmpty()) {
            System.out.println("No books available.");
        }
        System.out.println();
        //function to see all the available books and print them out the same way
        //as when they are entered
    }

    //TODO  Function to check out a book FINISHED
    public void checkOutBook() {
        System.out.println("Enter the title or ISBN of the book you want to check out: ");
        String titleOrIBSN = sc.nextLine();
        boolean bookFound = false;

        for (Book availableBook : availableBooks) {
            if (availableBook.getTitle().equals(titleOrIBSN) || availableBook.getISBN().equals(titleOrIBSN)) {
                if (availableBook.isAvailable()) {
                    availableBook.setAvailable(false);
                    unavailableBooks.add(availableBook);
                    availableBooks.remove(availableBook);
                    System.out.println("Book checked out successfully!");
                }
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            System.out.println("Book not found or already checked out.");
        }

    }

    //TODO  Return a book FINISHED

    public void returnBook() {
        System.out.println("Enter the title or ISBN of the book you want to return: ");
        String titleOrISBN = sc.nextLine();
        boolean bookFound = false;

        for (Book unavailableBook : unavailableBooks) {
            if (unavailableBook.getTitle().equals(titleOrISBN) || unavailableBook.getISBN().equals(titleOrISBN)) {
                if (!unavailableBook.isAvailable()){
                    unavailableBook.setAvailable(true);
                    availableBooks.add(unavailableBook);
                    unavailableBooks.remove(unavailableBook);
                    System.out.println("Book returned successfully!");
                }
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            System.out.println("Book is not in the system or not to be returned.");
        }
    }

    //TODO  Finally to Exit the program
    public void exitSystem() {
        System.out.println("Quitting app.");
    }
}
