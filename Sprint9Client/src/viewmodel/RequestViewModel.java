package viewmodel;

import java.rmi.RemoteException;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.Request;
import model.domain.RequestList;
import model.mediator.MVVMModelClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class RequestViewModel implements LocalListener<RequestList,RequestList>
{
   private MVVMModelClient model;
   private ListProperty<Request> requestList;
   
   public RequestViewModel(MVVMModelClient model)
   {
      this.model = model;
      this.requestList = new SimpleListProperty<Request>();
      model.getRequestModel().addListener(this, "requestlist");
      this.requestList.set(FXCollections.observableArrayList(model.getRequestModel().getRequests().getRequests()));
   }
   /**
    * Getter for requestList
    * @return requestList
    */
   public ListProperty<Request> getRequestList()
   {
      return requestList;
   }
   /**
    * Calls MVVMModelClient and gets requestModel instance.
    * Calls getRequests() in MVVMModelClient which gets the list of request.
    * Sets Requests list to the fetched list.
    */
   @Override
   public void propertyChange(ObserverEvent<RequestList, RequestList> event)
   {
    

      Platform.runLater(new Runnable() {

         @Override
         public void run()
         {
             requestList.set(FXCollections.observableArrayList(model.getRequestModel().getRequests().getRequests()));
         }  
      });
   }
   /**
    * Calls MVVMModelClient and gets requestModel instance.
    * Calls acceptRequest() in the RequestModel.
    * @param request
    * @throws RemoteException
    */
   public void acceptRequest(Request request) throws RemoteException
   {
      model.getRequestModel().acceptRequest(request);
   }
    /**
    * Calls MVVMModelClient and gets requestModel instance.
    * Calls declineRequest() in the RequestModel.
    * @param request
    * @throws RemoteException
    */
   public void declineRequest(Request request) throws RemoteException
   {
      model.getRequestModel().declineRequest(request);
   }
   
   
   
   
}
