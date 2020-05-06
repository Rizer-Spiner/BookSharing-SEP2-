package view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewmodel.BorrowedBooksViewModel;
import viewmodel.LentBooksViewModel;
import viewmodel.MainViewModel;
import viewmodel.SearchViewModel;
/**
 *@Author Group 4
 */

public class MainView
{

   private Stage primaryStage;
   private RegisterUserAndBookView registerUserAndBookView;
   private StartView startView;
   private LogInView logInView;
   private AddBookView registerBookView;
   private ProfileView profileView;
   private MainViewModel mainViewModel;
   private SearchView searchView;
   private AddBookView addBookView;




   private String currentViewID;


   /**
    * The constructor to initialize the instance variable of MainViewModel
    * @param mainViewModel
    */
   public MainView(MainViewModel mainViewModel)

   {
      this.mainViewModel = mainViewModel;
   }
   /**
    * Sets the primary stage to passed argument.
    * Calls setWindow() method and opens Start window.
    * @param primaryStage
    */
   public void start(Stage primaryStage)
   {
      this.primaryStage = primaryStage;
      setWindow("START");
   }
   /**
    * Calls method responsible for loading the view based on currentViewID. 
    * @param id
    */
   public void setWindow(String id)
   {
      currentViewID = id;
      switch (id)
      {
         case "START":
            loadStartWindow("Start", "Start.fxml", 600, 600);
            break;
         case "ADD BOOK":
            loadAddBookWindow("Register Book", "RegisterBook.fxml", 600, 600);
            break;

         case "REGISTER USER":
            loadRegisterUserAndBookWindow("Register User","RegisterUserAndBook.fxml", 600, 600);
            break;
         case "P":
            loadProfileWindow("User profile","Profile.fxml", 600, 400);
            break;
         case "LOG":
            loadLogInWindow("Log In", "LogIn.fxml", 600, 300);
            break;
         case "SEARCH":
            loadSearchWindow("Search", "Search.fxml",700,480);
         case "ADD":
            loadAddBook("Add book","AddBook.fxml",600,400);


      }
   }
   /**
    * Loads the RegisterUserAndBook window if the value of corresponding view is null.
    * Sets scene and title.
    * @param title
    * @param fxmlFile
    * @param width
    * @param height
    */
   private void loadRegisterUserAndBookWindow(String title, String fxmlFile, double width,
                                              double height)
   {
      if (registerUserAndBookView == null)
      {
         try
         {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            registerUserAndBookView = loader.getController();
            registerUserAndBookView.init(mainViewModel.getRegisterUserAndBookViewModel(),this, scene, title);
         }
         catch (Exception e)
         {

            e.printStackTrace();
         }
      }
      else
      {

      }

      primaryStage.setScene(registerUserAndBookView.getScene());
      primaryStage.setTitle(registerUserAndBookView.getTitle());
      primaryStage.show();
   }
   /**
    * Loads the AddBook window if the value of corresponding view is null.
    * Sets scene and title.
    * @param title
    * @param fxmlFile
    * @param width
    * @param height
    */
   private void loadAddBookWindow(String title, String fxmlFile, double width,
                                  double height)
   {
      if (registerBookView == null)
      {
         try
         {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            registerBookView = loader.getController();
            registerBookView.init(mainViewModel.getAddBookViewModel(),this, scene, title);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      else
      {

      }

      primaryStage.setScene(registerBookView.getScene());
      primaryStage.setTitle(registerBookView.getTitle());
      primaryStage.show();
   }
   /**
    * Loads the Start window if the value of corresponding view is null.
    * Sets scene and title.
    * @param title
    * @param fxmlFile
    * @param width
    * @param height
    */
   private void loadStartWindow(String title, String fxmlFile, double width,
                                double height)
   {
      if (startView == null)
      {
         try
         {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            startView = loader.getController();
            startView.init(mainViewModel.getStartViewModel(),this, scene, title);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      else
      {

      }

      primaryStage.setScene(startView.getScene());
      primaryStage.setTitle(startView.getTitle());
      primaryStage.show();
   }
   /**
    * Loads the Profile window if the value of corresponding view is null.
    * Sets scene and title.
    * @param title
    * @param fxmlFile
    * @param width
    * @param height
    */
   private void loadProfileWindow(String title, String fxmlFile, double width,
                                  double height)
   {
      if (profileView == null)
      {
         try
         {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            profileView = loader.getController();
            profileView.init(mainViewModel.getProfileViewModel(),mainViewModel.getMyBooksViewModel(),
                    mainViewModel.getBorrowedBooksViewModel(), mainViewModel.getLentBooksViewModel(),
                    mainViewModel.getRequestViewModel(),
                    this, scene, title);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      else
      {

      }

      primaryStage.setScene(profileView.getScene());
      primaryStage.setTitle(profileView.getTitle());
      primaryStage.show();
   }
   /**
    * Loads the LogIn window if the value of corresponding view is null.
    * Sets scene and title.
    * @param title
    * @param fxmlFile
    * @param width
    * @param height
    */
   private void loadLogInWindow(String title, String fxmlFile, double width,
                                double height)
   {
      if (logInView == null)
      {
         try
         {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            logInView = loader.getController();
            logInView.init(mainViewModel.getLogInViewModel(),this, scene, title);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      else
      {

      }

      primaryStage.setScene(logInView.getScene());
      primaryStage.setTitle(logInView.getTitle());
      primaryStage.show();
   }
   /**
    * Loads the Search window if the value of corresponding view is null.
    * Sets scene and title.
    * @param title
    * @param fxmlFile
    * @param width
    * @param height
    */
   private void loadSearchWindow(String title,String fxmlFile, double width, double height)
   {
      if(searchView==null)
      {
         try
         {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            searchView = loader.getController();
            searchView.init(mainViewModel.getSearchViewModel(),this, scene, title);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      else
      {

      }

      primaryStage.setScene(searchView.getScene());
      primaryStage.setTitle(searchView.getTitle());
      primaryStage.show();

   }
   /**
    * Loads the AddBook window if the value of corresponding view is null.
    * Sets scene and title.
    * @param title
    * @param fxmlFile
    * @param width
    * @param height
    */
   private void loadAddBook(String title,String fxmlFile, double width, double height)
   {
      if(addBookView==null)
      {
         try
         {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            addBookView = loader.getController();
            addBookView.init(mainViewModel.getAddBookViewModel(),this, scene, title);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      else
      {

      }

      primaryStage.setScene(addBookView.getScene());
      primaryStage.setTitle(addBookView.getTitle());
      primaryStage.show();

   }
}
   
   
   
 
   