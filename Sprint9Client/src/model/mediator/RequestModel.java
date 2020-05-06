package model.mediator;

import java.rmi.RemoteException;

import model.domain.Request;
import model.domain.RequestList;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeProxy;

public class RequestModel implements LocalSubject<RequestList,RequestList>
{

   private RequestList requestList;
   private ModelClient adaptee;
   private PropertyChangeProxy<RequestList,RequestList> property;
   
   public RequestModel(ModelClient adaptee)
   {
      this.property = new PropertyChangeProxy<>(this);
      this.requestList = new RequestList();
      this.adaptee = adaptee;
   }
   
   public RequestList getRequests()
   {
      return requestList;
   }
   /**
    * Adds request to local requestList.
    * Fires property change with property name "requestlist".
    * Sets new value to updated requestList.
    * @param request
    */
   public void addRequest(Request request)
   {
     requestList.add(request);
     property.firePropertyChange("requestlist", null, requestList);
     
   }

   /**
    *
    * Adds the listner passed as argument as listener to events emitted by the class
    *
    * @param listener
    * @param propertyNames
    * @return boolean
    */
   @Override
   public boolean addListener(
         GeneralListener<RequestList, RequestList> listener,
         String... propertyNames)
   {
      return property.addListener(listener, propertyNames);
           
   }
   /**
    *
    * Removes the listner passed as argument as listener to events emitted by the class
    *
    * @param listener
    * @param propertyNames
    * @return boolean
    */
   @Override
   public boolean removeListener(
         GeneralListener<RequestList, RequestList> listener,
         String... propertyNames)
   {
      return property.removeListener(listener, propertyNames);
   }
   /**
    * Assigns parameter list to local request list.
    * Fires property change with property name "requestlist".
    * Sets new value to updated requestList.
    * @param list
    */
   public void setRequests(RequestList list) 
   {
      this.requestList = list;
      property.firePropertyChange("requestlist", null, requestList);
      
   }
   /**
    * Sets request status to "accepted".
    * Calls sendAnswerToRequest()in ModelClient.
    * @param request
    * @throws RemoteException
    */
   public void acceptRequest(Request request) throws RemoteException
   {
      request.setStatus("accepted");
      adaptee.sendAnswerToRequest(request);
     
      
   }
   /**
    * Sets request status to "declined".
    * Calls sendAnswerToRequest()in ModelClient.
    * @param request
    * @throws RemoteException
    */
   public void declineRequest(Request request) throws RemoteException
   {
      request.setStatus("declined");
      adaptee.sendAnswerToRequest(request);
      
   }
   
   
}
