
import javafx.application.Application;
import javafx.stage.Stage;
import model.mediator.MVVMModelClient;
import model.mediator.MVVMModelManagerClient;
import model.mediator.ModelClient;
import model.mediator.ModelManagerClient;
import view.MainView;
import viewmodel.MainViewModel;



public class Main extends Application
{
   @Override 
   public void start(Stage primaryStage) throws Exception
   {
      ModelClient modelClient = new ModelManagerClient();
      MVVMModelClient model = new MVVMModelManagerClient(modelClient);
      modelClient.addListener(model, "BOOKLIST","REQUEST");
      
      
      MainViewModel mainViewModel = new MainViewModel(model);
      MainView view = new MainView(mainViewModel);
      view.start(primaryStage);
      
   }

   

}
