package viewmodel;


import model.mediator.MVVMModelClient;


public class MainViewModel
{
   private MVVMModelClient model;
   private LogInViewModel logInViewModel;
   private AddBookViewModel addBookViewModel;
   private ProfileViewModel profileViewModel;
   private RegisterUserAndBookViewModel registerUserAndBookViewModel;
   private StartViewModel startViewModel;
   private SearchViewModel searchViewModel;
   private MyBooksViewModel mybooksViewModel;
   private BorrowedBooksViewModel borrowedBooksViewModel;
   private LentBooksViewModel lentBooksViewModel;
   private RequestViewModel requestViewModel;
   
   
   public MainViewModel(MVVMModelClient model)
   {
      
      this.model = model;
      this.logInViewModel = new LogInViewModel(model);
      this.addBookViewModel = new AddBookViewModel(model);
      this.profileViewModel = new ProfileViewModel(model);
      this.registerUserAndBookViewModel = new RegisterUserAndBookViewModel(model);
      this.startViewModel = new StartViewModel(model);
      this.searchViewModel = new SearchViewModel(model);
      this.mybooksViewModel = new MyBooksViewModel(model);
      this.borrowedBooksViewModel = new BorrowedBooksViewModel(model);
      this.lentBooksViewModel = new LentBooksViewModel(model);
      this.requestViewModel = new RequestViewModel(model);
   }

  /**
   * Getter for logInViewModel.
   * @return logInViewModel
   */
   public LogInViewModel getLogInViewModel()
   {
      return logInViewModel;
   }
    /**
    * Getter for addBookViewModel.
    * @return addBookViewModel
    */
   public AddBookViewModel getAddBookViewModel()
   {
      return addBookViewModel;
   }
   /**
    * Getter for profileViewModel.
    * @return profileViewModel
    */
   public ProfileViewModel getProfileViewModel()
   {
      return profileViewModel;
   }
    /**
    * Getter for registerUserAndBookViewModel.
    * @return registerUserAndBookViewModel
    */
   public RegisterUserAndBookViewModel getRegisterUserAndBookViewModel()
   {
      return registerUserAndBookViewModel;
   }
   /**
    * Getter for startViewModel.
    * @return startViewModel
    */
   public StartViewModel getStartViewModel()
   {
      return startViewModel;
   }
   /**
    * Getter for searchViewModel.
    * @return searchViewModel
    */
   public SearchViewModel getSearchViewModel()
   {
      return searchViewModel;
   }
   /**
    * Getter for mybooksViewModel.
    * @return mybooksViewModel
    */
   public MyBooksViewModel getMyBooksViewModel()
   {
      return mybooksViewModel;
   }
   /**
    * Getter for borrowedBooksViewModel.
    * @return borrowedBooksViewModel
    */
   public BorrowedBooksViewModel getBorrowedBooksViewModel()
   {
      return borrowedBooksViewModel;
   }
   /**
    * Getter for lentBooksViewModel.
    * @return lentBooksViewModel
    */
   public LentBooksViewModel getLentBooksViewModel()
   {
      return lentBooksViewModel;
   }

   /**
    * Getter for requestViewModel.
    * @return requestViewModel
    */
   public RequestViewModel getRequestViewModel()
   {
      return requestViewModel;
   }
   
   

}
