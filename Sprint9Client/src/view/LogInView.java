package view;



import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import viewmodel.LogInViewModel;

/**
 *  @Author Group 4
 * 
 */
public class LogInView
{
   @FXML
   private TextField usernameField;
   @FXML
   private PasswordField passwordField;
   @FXML
   private Label error;
   @FXML
   private Button cancelButton;
   @FXML
   private Button loginButton;

   private String title;
   private Scene scene;
   private MainView mainView;
   private LogInViewModel viewModel;

   public LogInView()
   {

   }

   /**
    * Initialize the LoginView with the passing parameters.
    * Bind the properties with FXML elements
    * @param viewModel
    * @param mainView
    * @param scene
    * @param title
    */
   public void init(LogInViewModel viewModel,
                    MainView mainView,
                    Scene scene,
                    String title)
   {
      this.viewModel = viewModel;
      this.mainView = mainView;
      this.scene = scene;
      this.title = title;

      usernameField.textProperty().bindBidirectional(viewModel.getUsernameProperty());
      passwordField.textProperty().bindBidirectional(viewModel.getPasswordProperty());
       error.textProperty().bind(viewModel.getErrorProperty());
   }

   /**
    * Get method for the title
    * @return title as the title of the window
    */
   public String getTitle()
   {
      return title;
   }

   /**
    * Get method for Scene
    * @return scene
    */
   public Scene getScene()
   {
      return scene;
   }

   /**
    * Action after cancel button is clicked
    *     redirects to the start window.
    */
   @FXML
   public void cancelButtonPressed()
   {
      mainView.setWindow("START");
   }

   /**
    * 
    * @param event
    */
   @FXML
   public void onEnter(Event event)
   {
      if(event.getSource()==usernameField)
      {
         passwordField.requestFocus();
      }
      else
      {
         loginButtonPressed();
      }
   }

   /**
    * After the login button is clicked
    * the login information will be validated 
    * and teh profile window will be opened.
    */
   @FXML
   public void loginButtonPressed()
   {
      viewModel.validateLogin();
      mainView.setWindow("P");

   }



}
