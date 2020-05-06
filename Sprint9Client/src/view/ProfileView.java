package view;

import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.domain.Book;
import model.domain.Request;
import viewmodel.BorrowedBooksViewModel;
import viewmodel.LentBooksViewModel;
import viewmodel.MyBooksViewModel;
import viewmodel.ProfileViewModel;
import viewmodel.RequestViewModel;

/**
 * @Author Group 4
 */
public class ProfileView
{
   @FXML
   private TextField usernameField;
   @FXML
   private TextField emailField;
   @FXML
   private TextField phoneField;
   @FXML
   private Button searchButton;
   @FXML
   private Button logoutButton;
   @FXML
   private Button addButton;
   @FXML
   private ListView<Book> mybookList;
   @FXML
   private Button deleteButton;
   @FXML
   private ListView<Book> borrowedList;
   @FXML
   private Button returnButton;
   @FXML
   private ListView<Book> lentList;
   @FXML
   private ListView<Book> wishList;
   @FXML
   private ListView<Request> requestList;
   @FXML
   private Button acceptButton;
   @FXML
   private Button declineButton;
   @FXML
   private Button deleteWishButton;




   private String title;
   private Scene scene;
   private MainView view;
   private ProfileViewModel viewModel;
   private MyBooksViewModel myBooksViewModel;
   private BorrowedBooksViewModel borrowedBooksViewModel;
   private LentBooksViewModel lentBooksViewModel;
   private RequestViewModel requestViewModel;


   public ProfileView()
   {

   }

   /**
    * Initialize profile view
    * Bind the properties with FXML elements.
    * @param viewModel
    * @param myBooksViewModel
    * @param borrowedBooksViewModel
    * @param lentBooksViewModel
    * @param requestViewModel
    * @param mainView
    * @param scene
    * @param title
    */
   public void init( ProfileViewModel viewModel,MyBooksViewModel myBooksViewModel,
                     BorrowedBooksViewModel borrowedBooksViewModel,LentBooksViewModel lentBooksViewModel,
                     RequestViewModel requestViewModel,
                     MainView mainView,
                     Scene scene,
                     String title)
   {

      this.viewModel = viewModel;
      this.scene = scene;
      this.title = title;
      this.view = mainView;
      this.myBooksViewModel = myBooksViewModel;
      this.borrowedBooksViewModel = borrowedBooksViewModel;
      this.lentBooksViewModel = lentBooksViewModel;
      this.requestViewModel = requestViewModel;

      mybookList.setCellFactory(param -> new ListCell<Book>()
      {
         @Override
         protected void updateItem(Book item, boolean empty)
         {
            super.updateItem(item, empty);
            if (empty || item == null) {
               setText(null);
            } else {
               String text = String.format("%s, %s, %s", item.getTitle(), item.getAuthor(), item.getYear());
               setText(text);
            }
         }
      });

            requestList.setCellFactory(param -> new ListCell<Request>()
      {
         @Override
         protected void updateItem(Request item, boolean empty)
         {
            super.updateItem(item, empty);
            if (empty || item == null) {
               setText(null);
            } else {
               String text = String.format("%s wants %s, %s, %s",item.getBorrower().getUsername(),
                       item.getBook().getTitle(),
                       item.getBook().getAuthor(),
                       item.getBook().getYear());
               setText(text);
            }
         }
      });

      mybookList.itemsProperty().bind(myBooksViewModel.getMybookList());
      requestList.itemsProperty().bind(requestViewModel.getRequestList());

      usernameField.textProperty().bind(viewModel.getUsernameProperty());
      emailField.textProperty().bind(viewModel.getEmailProperty());
      phoneField.textProperty().bind(viewModel.getPhoneProperty());

   }

   /**
    * Getter for title
    * @return title as the title of the window
    */
   public String getTitle()
   {
      return title;
   }

   /**
    * Getter for Scene
    * @return scene
    */
   public Scene getScene()
   {
      return scene;
   }

   /**
    * the search window will be opened in case of clicking the search button
    */
   @FXML
   public void searchButtonPressed()
   {
      view.setWindow("SEARCH");
   }

   /**
    * The addBook window will be opened if the add button is clicked
    */
   @FXML
   public void addButtonPressed()
   {
      view.setWindow("ADD");
   }

   /**
    * the action after pressing the logout button 
    * is to navigate the student to the start window
    */
   @FXML
   public void logoutButtonPressed()
   {
      view.setWindow("START");
   }

   /**
    * 
    */
   @FXML
   public void returnButtonPressed()
   {

   }

   /**
    * if the delete button is clicked
    * the selected book will be removed from the list
    * @throws RemoteException
    */
   @FXML
   public void deleteButtonPressed() throws RemoteException
   {
      Book item = mybookList.getSelectionModel().getSelectedItem();
      if (item != null)
      {
         myBooksViewModel.removeBook(item);
      }

   }

   /** Declining the request if an item has been selected from the list
    * and decline button is pressed.
    * @throws RemoteException
    */
   @FXML
   public void declineButtonPressed() throws RemoteException
   {

      Request request = requestList.getSelectionModel().getSelectedItem();
      if(request!=null)
      {
         requestViewModel.declineRequest(request);
      }
   }

   /**Accepting the request if an item has been selected from the list
    * and accept button is pressed.
    * @throws RemoteException
    */
   @FXML
   public void acceptButtonPressed() throws RemoteException
   {

      Request request = requestList.getSelectionModel().getSelectedItem();
      if(request!=null)
      {
         requestViewModel.acceptRequest(request);
      }
   }

   /**
    * 
    */
   @FXML
   public void deleteWishButtonPressed()
   {

   }

}
